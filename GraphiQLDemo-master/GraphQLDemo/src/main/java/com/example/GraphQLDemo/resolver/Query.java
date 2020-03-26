package com.example.GraphQLDemo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.GraphQLDemo.model.Artist;
import com.example.GraphQLDemo.model.Song;
import com.example.GraphQLDemo.repository.ArtistRepository;
import com.example.GraphQLDemo.repository.SongRepository;

public class Query implements GraphQLQueryResolver {
    private SongRepository songRepository;
    private ArtistRepository artistRepository;

    public Query(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    public Iterable<Song> findAllSongs() {
        return songRepository.findAll();
    }

    public Iterable<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    public long countSongs() {
        return songRepository.count();
    }
    public long countArtists() {
        return artistRepository.count();
    }
}
