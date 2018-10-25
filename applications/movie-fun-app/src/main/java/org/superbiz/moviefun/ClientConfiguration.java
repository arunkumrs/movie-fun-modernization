package org.superbiz.moviefun;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.superbiz.moviefun.albumsapi.AlbumsClient;
import org.superbiz.moviefun.blobstore.BlobStore;
import org.superbiz.moviefun.blobstore.S3Store;
import org.superbiz.moviefun.cfsupport.ServiceCredentials;
import org.superbiz.moviefun.moviesapi.MoviesClient;

@Configuration
public class ClientConfiguration {
    @Value("${movies.url}") String moviesUrl;

    @Value("${albums.url}") String albumsUrl;
    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    public MoviesClient moviesClient(RestOperations restOperations) {
        return new MoviesClient(moviesUrl, restOperations);
    }

    @Bean
    public AlbumsClient albumsClient(RestOperations restOperations) {
        return new AlbumsClient(albumsUrl, restOperations);
    }

    @Bean
    ServiceCredentials serviceCredentials(@Value("${vcap.services}") String vcapServices) {
        return new ServiceCredentials(vcapServices);
    }

    @Bean
    public BlobStore blobStore(
            ServiceCredentials serviceCredentials,
            @Value("${s3.endpointUrl:#{null}}") String s3EndpointUrl
    ) {
        String s3AccessKey = serviceCredentials.getCredential("moviefun-s3", "access_key_id");
        String s3SecretKey = serviceCredentials.getCredential("moviefun-s3", "secret_access_key");
        String s3BucketName = serviceCredentials.getCredential("moviefun-s3", "bucket");

        AWSCredentials credentials = new BasicAWSCredentials(s3AccessKey, s3SecretKey);
        AmazonS3Client s3Client = new AmazonS3Client(credentials);

        if (s3EndpointUrl != null) {
            s3Client.setEndpoint(s3EndpointUrl);
        }

        return new S3Store(s3Client, s3BucketName);
    }

}
