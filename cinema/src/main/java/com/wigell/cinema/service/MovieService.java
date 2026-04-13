package com.wigell.cinema.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.wigell.cinema.entity.Movie;
import com.wigell.cinema.repository.MovieRepository;

@Service
public class MovieService {
    private static final Logger logger = Logger.getLogger(MovieService.class.getName());

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository repository){
        this.movieRepository = repository;
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found: " + id));
    }

    public Movie addMovie(Movie movie){
        Movie saved = movieRepository.save(movie);
        logger.info("Admin added movie: " + saved.getTitle());
        return saved;    
    }

    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.deleteById(id);
        logger.info("Admin deleted movie" + movie.getTitle());
    }
}
