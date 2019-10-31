package com.example.musicapp;

import java.util.ArrayList;

public class Album {
    private ArrayList<Song> songList;
    private String albumName;
    private String Artist;
    private int AlbumImage;

    /*
        can't create album without initializing all its data members
         for now, because not all getters and setters are available ->just to optimize my code
     */
    public Album(ArrayList<Song> songList, String albumName, String artist, int albumImage) {
        this.songList = songList;
        this.albumName = albumName;
        Artist = artist;
        AlbumImage = albumImage;
    }


    /*
    All getters and setters will be added when connecting to network.
    For now,
    some getters and setters are not used so no need
    to write them at the moment
     */
    public String getAlbumName() {
        return albumName;
    }

    public String getArtist() {
        return Artist;
    }

    public int getAlbumImage() {
        return AlbumImage;
    }
}
