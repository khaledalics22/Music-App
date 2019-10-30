package com.example.musicapp;

import java.util.List;

public class Album {
    private List<Song> songList;
    private String albumName;
    private String Artist;
    private int AlbumImage;

    public Album(List<Song> songList, String albumName, String artist, int albumImage) {
        this.songList = songList;
        this.albumName = albumName;
        Artist = artist;
        AlbumImage = albumImage;
    }

    public int getAlbumImage() {
        return AlbumImage;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtist() {
        return Artist;
    }
}
