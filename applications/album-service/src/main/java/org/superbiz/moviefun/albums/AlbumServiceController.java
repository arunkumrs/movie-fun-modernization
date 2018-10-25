package org.superbiz.moviefun.albums;

import org.apache.tika.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.superbiz.moviefun.blobstore.Blob;
import org.superbiz.moviefun.blobstore.BlobStore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;


@RestController
@RequestMapping("/albums")
public class AlbumServiceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AlbumsBean albumsBean;

    public AlbumServiceController(AlbumsBean albumsBean) {

        this.albumsBean = albumsBean;
    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        logger.info("album {}"+ album.toString());
        albumsBean.addAlbum(album);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<Album> find(long id) {

        return new ResponseEntity<>(albumsBean.find(id), HttpStatus.OK);

    }

    @GetMapping
    public  ResponseEntity<List<Album>> getAlbums() {

        return new ResponseEntity<>(albumsBean.getAlbums(), HttpStatus.OK);
    }


    @DeleteMapping
    public void deleteAlbum(Album album) {
        albumsBean.deleteAlbum(album);
    }


    @PutMapping
    public void updateAlbum(Album album) {
        albumsBean.updateAlbum(album);
    }


}
