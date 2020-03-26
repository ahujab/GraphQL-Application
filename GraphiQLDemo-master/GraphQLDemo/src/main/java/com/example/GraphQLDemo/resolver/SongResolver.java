package com.example.GraphQLDemo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.GraphQLDemo.model.Artist;
import com.example.GraphQLDemo.model.Song;
import com.example.GraphQLDemo.repository.ArtistRepository;

public class SongResolver implements GraphQLResolver<Song> {
    private ArtistRepository artistRepository;

    public SongResolver(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist getArtist(Song song) {
        return artistRepository.findById(song.getArtist().getId()).get();
    }
}
