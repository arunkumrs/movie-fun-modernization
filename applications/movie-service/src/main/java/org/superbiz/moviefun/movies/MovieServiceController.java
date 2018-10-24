package org.superbiz.moviefun.movies;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieServiceController {

    private final MoviesBean moviesBean;


    public MovieServiceController(MoviesBean moviesBean){

        this.moviesBean = moviesBean;
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie){
          moviesBean.addMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieId(@PathVariable long id){
        moviesBean.deleteMovieId(id);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<Movie> find(long id){
        return new ResponseEntity<>(moviesBean.find(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        return new ResponseEntity<>(moviesBean.getMovies(), HttpStatus.OK);
    }

    @GetMapping(params = {"start", "pageSize"})
    public ResponseEntity<List<Movie>> findAll(int start, int pageSize){
        return new ResponseEntity<>(moviesBean.findAll(start, pageSize), HttpStatus.OK);
    }

    @GetMapping("/count")
    public int countAll(){
        return moviesBean.countAll();
    }

    @GetMapping(path = "/count", params = {"field","key"})
    public int count(String field, String key){
        return moviesBean.count(field, key);
    }

    @GetMapping(params = {"field", "key", "start", "pageSize"})
    public ResponseEntity<List<Movie>> findRange(String field, String key, int start, int pageSize){
        return new ResponseEntity<>(moviesBean.findRange(field, key, start, pageSize), HttpStatus.OK);
    }

    @PutMapping
    public void updateMovie(Movie movie){
        moviesBean.updateMovie(movie);
    }


    @DeleteMapping
    public  void clean(){
        moviesBean.clean();
    }

}
