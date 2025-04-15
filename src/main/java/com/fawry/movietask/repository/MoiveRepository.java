package com.fawry.movietask.repository;

import com.fawry.movietask.entity.MovieEntity;
import com.fawry.movietask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoiveRepository extends JpaRepository<MovieEntity, Long> {

}
