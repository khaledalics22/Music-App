package com.example.musicapp;

public class Song {
    private String songPath;
    // temporary
    private int songResource;
    private int icon;
    private String artistName;
    private String description;
    private String songTitle;
    private String albumName=null;
    /*
         I use this to indicate to the position in the ArrayList so that i can get it when i search
     */
    private int Id;

    public String getAlbumName() {
            return albumName;
    }

    public Song( int songResource, int icon, String artistName, String description, String songTitle, int id, String albumName) {
        this.songResource = songResource;
        this.icon = icon;
        this.artistName = artistName;
        this.description = description;
        this.songTitle = songTitle;
        this.albumName = albumName;
        Id = id;
    }

    public void setSongResource(int songResource) {
        this.songResource = songResource;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setId(int id) {
        Id = id;
    }

    public Song(int songResource, int icon, String artistName, String description, String songTitle, int Id) {
        this.songResource = songResource;
        this.icon = icon;
        this.artistName = artistName;
        this.description = description;
        this.songTitle = songTitle;
        this.Id = Id;
    }

    public int getId() {
        return Id;
    }

    public int getSongResource() {
        return songResource;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongPath() {
        return songPath;
    }

    public int getIcon() {
        return icon;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getDescription() {
        return description;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
