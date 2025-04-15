package com.fawry.movietask.controller;

import com.fawry.movietask.service.MoveAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/movie")
public class MovieUserController {
    @Autowired
    MoveAdminService moveAdminService;

    @GetMapping("/getAll")
    public Object getAllMovies() {
        return moveAdminService.GetAllMoviesFromDatabase();
    }

    @GetMapping("/getById")
    public Object getMovieFromDataBase(@RequestParam("i") String movieId ) {
        return moveAdminService.getMovieFromDataBase(movieId);
    }

    //delete
    //getAll
    //Auth filter
}
