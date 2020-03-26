package com.example.GraphQLDemo.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.GraphQLDemo.exception.SongNotFoundException;
import com.example.GraphQLDemo.model.Artist;
import com.example.GraphQLDemo.model.Song;
import com.example.GraphQLDemo.repository.ArtistRepository;
import com.example.GraphQLDemo.repository.SongRepository;

public class Mutation implements GraphQLMutationResolver {
    private SongRepository songRepository;
    private ArtistRepository artistRepository;

    public Mutation(ArtistRepository artistRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    public Artist newArtist(String name, String country) {
        Artist artist = new Artist();
        artist.setname(name);
        artist.setcountry(country);

        artistRepository.save(artist);

        return artist;
    }

    public Song newSong(String title, String genre, Integer duration, Long artistId) {
        Song song = new Song();
        song.setArtist(new Artist(artistId));
        song.setTitle(title);
        song.setgenre(genre);
        song.setduration(duration != null ? duration : 0);

        songRepository.save(song);

        return song;
    }

    public boolean deleteSong(Long id) {
        songRepository.deleteById(id);
        return true;
    }

    public Song updateSongduration(Integer duration, Long id) {
        Song song = songRepository.findById(id).get();
        if(song == null) {
            throw new SongNotFoundException("The song to be updated was not found", id);
        }
        song.setduration(duration);

        songRepository.save(song);

        return song;
    }
}
