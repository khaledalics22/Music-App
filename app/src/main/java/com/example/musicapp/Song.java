package com.example.musicapp;

public class Song {
    private String songPath;
    // temporary
    private int songResource;
    private int icon;
    private String ArtistName;
    private String description;
    private String songTitle;
    /*
         I use this to indicate to the position in the ArrayList so that i can get it when i search
     */
    private int Id;

    public Song(int songResource, int icon, String artistName, String description, String songTitle, int Id) {
        this.songResource = songResource;
        this.icon = icon;
        ArtistName = artistName;
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
        return ArtistName;
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
        ArtistName = artistName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
