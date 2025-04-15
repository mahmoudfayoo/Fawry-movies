package com.fawry.movietask.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fawry.movietask.dto.MovieDto;
import com.fawry.movietask.entity.MovieEntity;
import com.fawry.movietask.integration.OmdbClient;
import com.fawry.movietask.repository.MoiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoveAdminService {
    @Autowired
    private OmdbClient omdbClient;
    @Autowired
    private ObjectMapper objectMapper ;
    @Autowired
    private MoiveRepository moiveRepository;
    public ResponseEntity<?> addMovieToDatabase(String MovieId,String TokenId){
       MovieDto movieDto = omdbClient. getPostById(MovieId,TokenId);
       MovieEntity movieEntity = objectMapper.convertValue(movieDto, MovieEntity.class);
       return ResponseEntity.ok().body(moiveRepository.saveAndFlush(movieEntity));
  }

  public ResponseEntity<?> deleteMovieFromDatabase(String MovieId){
        Optional<MovieEntity> optionalMovieEntity= moiveRepository.findById(Long.valueOf(MovieId));
        if(optionalMovieEntity.isPresent()){
            moiveRepository.deleteById(Long.valueOf(MovieId));
            return ResponseEntity.ok().body("Movie with ID " + MovieId + " has been deleted successfully.");
        }
        else {
            return ResponseEntity.ok().body("Movie with ID " + MovieId + " does not exist.");
        }
  }

  public ResponseEntity<?> GetAllMoviesFromDatabase(){
        List<MovieEntity> movieEntityList=moiveRepository.findAll();
        List<MovieDto> movieDtoLis=objectMapper.convertValue(movieEntityList, new TypeReference<List<MovieDto>>() {
        });
        return ResponseEntity.ok().body(movieDtoLis);
  }

    public ResponseEntity<?> getMovieFromDataBase(String MovieId){
        Optional<MovieEntity> optionalMovieEntity= moiveRepository.findById(Long.valueOf(MovieId));
        if(optionalMovieEntity.isPresent()){
            MovieDto movieDto = objectMapper.convertValue(optionalMovieEntity, MovieDto.class);
            return ResponseEntity.ok().body(movieDto);
        }
        else {
            return ResponseEntity.ok().body("Movie with ID " + MovieId + " does not exist.");
        }
    }

}
