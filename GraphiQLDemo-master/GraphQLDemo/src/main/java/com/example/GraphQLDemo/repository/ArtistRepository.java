package com.example.GraphQLDemo.repository;

import com.example.GraphQLDemo.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> { }
