package com.apimovies.apimovies.dataaccess;

import com.apimovies.apimovies.models.MovieDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieDataModel, Integer> {
}
