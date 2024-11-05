package com.apimovies.apimovies.controller;

import com.apimovies.apimovies.exceptions.MovieNotFoundException;
import com.apimovies.apimovies.models.MovieDataModel;
import com.apimovies.apimovies.dataaccess.MovieRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Movie Rest API Controller
 * Provides basic CRUD functionality for a Movie database type application.  The actions include
 *  - Get All (HTTP Get)
 *  - Get One (HTTP Get)
 *  - Add (HTTP Post)
 *  - Update (HTTP Put)
 *  - Delete (HTTP Delete)
 */
@RestController
public class MovieController {

    private final MovieRepository repository;

    MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/movies", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<MovieDataModel> getAllMovies() {
        return repository.findAll();
    }

    @GetMapping(value = "/movies/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    MovieDataModel getSingleMovie(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @PostMapping(value = "/movies",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    MovieDataModel newMovie(@RequestBody MovieDataModel newMovie) {
        return repository.save(newMovie);
    }

    @PutMapping(value = "/movies/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    MovieDataModel updateMovie(@RequestBody MovieDataModel movie, @PathVariable Integer id) {

        return repository.findById(id)
                .map(item -> {
                    item.setTitle(movie.getTitle());
                    item.setDescription(movie.getDescription());
                    item.setReleaseDate(movie.getReleaseDate());
                    item.setAvailableOnDvd(movie.isAvailableOnDvd());
                    return repository.save(item);
                })
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @DeleteMapping(value = "/movies/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    void deleteMovie(@PathVariable Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new MovieNotFoundException(id);
        }

        repository.deleteById(id);
    }

}