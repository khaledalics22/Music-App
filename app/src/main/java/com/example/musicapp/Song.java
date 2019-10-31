package com.example.musicapp;

public class Song {
    private String songPath;
    // temporary
    private int songResource;
    private int icon;
    private String artistName;
    private String description;
    private String songTitle;
    private String albumName = null;
    /*
         I use this to indicate to the position in the ArrayList so that i can get it when i search
     */
    private int Id;

    public String getAlbumName() {
        return albumName;
    }

    public Song(int songResource, int icon, String artistName, String description, String songTitle, int id, String albumName) {
        this.songResource = songResource;
        this.icon = icon;
        this.artistName = artistName;
        this.description = description;
        this.songTitle = songTitle;
        this.albumName = albumName;
        Id = id;
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

    /*
    All getters and setters will be added when connecting to network.
    For now,
    some getters and setters are not used so no need
    to write them at the moment
     */
    public int getId() {
        return Id;
    }

    public int getSongResource() {
        return songResource;
    }

    public String getSongTitle() {
        if (songTitle != null)
            return songTitle;
        return "";
    }

    public int getIcon() {
        return icon;
    }

    public String getArtistName() {
        return artistName;
    }
}
