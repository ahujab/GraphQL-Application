package com.example.GraphQLDemo.model;

import javax.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String title;

    private String genre;

    private int duration;

    @ManyToOne
    @JoinColumn(name = "artist_id",
            nullable = false, updatable = false)
    private Artist artist;

    public Song() {
    }

    public Song(String title, String genre, int duration, Artist artist) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getgenre() {
        return genre;
    }

    public void setgenre(String genre) {
        this.genre = genre;
    }

    public int getduration() {
        return duration;
    }

    public void setduration(int duration) {
        this.duration = duration;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return id.equals(song.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", artist=" + artist +
                '}';
    }
}