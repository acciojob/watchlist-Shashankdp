package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String, Movie> movieMap = new HashMap<>();
    Map<String, Director> directorMap = new HashMap<>();
    Map<String, List<String>> directorMovieMapping = new HashMap<>();

    public String addMovie(Movie movie) {
        String name = movie.getName();
        movieMap.put(name, movie);
        return "New Movie added successfully";
    }

    public String addDirector(Director director) {
        String name = director.getName();
        directorMap.put(name, director);
        return "New Director added successfully";
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
//        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){
//            movieMap.put(movieName,movieMap.get(movieName));
//            directorMap.put(directorName,directorMap.get(directorName));

        List<String> currentMovies = new ArrayList<String>();

        if (directorMovieMapping.containsKey(directorName)) {
            currentMovies = directorMovieMapping.get(directorName);
        }

        currentMovies.add(movieName);
        directorMovieMapping.put(directorName, currentMovies);

}

    public Movie getMovieByName(String name){
        return movieMap.get(name);
    }
    public Director getDirectorByName(String name){
        return directorMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movieList=new ArrayList<>();
        if(directorMovieMapping.containsKey(directorName)){
            movieList=directorMovieMapping.get(directorName);
        }
        return movieList;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }


    public String deleteDirectorByName(String name){
        List<String> movies=new ArrayList<>();
         if(directorMovieMapping.containsKey(name)){
             movies=directorMovieMapping.get(name);
         }
         for(String movie:movies){
             if(movieMap.containsKey(movie)){
                 movieMap.remove(movie);
             }
         }
         directorMovieMapping.remove(name);
         return "Deleted Successfully";
    }

    public void deleteAllDirector(){
        HashSet<String> moviesSet = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String director: directorMovieMapping.keySet()){
            for(String movie: directorMovieMapping.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }

    }
}
