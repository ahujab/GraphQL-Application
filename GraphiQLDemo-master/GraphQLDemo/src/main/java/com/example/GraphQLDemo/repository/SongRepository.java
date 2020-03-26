package com.example.GraphQLDemo.repository;

import com.example.GraphQLDemo.model.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> { }

