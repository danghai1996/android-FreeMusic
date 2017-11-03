package com.example.nhem.freemusic.database;

/**
 * Created by NHEM on 24/10/2017.
 */

public class TopSongModel {
    private String songName;
    private String artistName;
    private String smallImage;
    private String url;
    private String largeImage;


    public TopSongModel(String songName, String artistName, String smallImage) {
        this.songName = songName;
        this.artistName = artistName;
        this.smallImage = smallImage;
    }

    public TopSongModel() {
    }

    public void setDataFromFile(String song, String artist, String filePath) {
        this.songName = song;
        this.artistName = artist;
        this.url = filePath;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }
}
