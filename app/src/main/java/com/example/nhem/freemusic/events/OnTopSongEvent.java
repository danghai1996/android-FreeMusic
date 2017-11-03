package com.example.nhem.freemusic.events;

import com.example.nhem.freemusic.database.TopSongModel;

/**
 * Created by NHEM on 29/10/2017.
 */

public class OnTopSongEvent {
    private TopSongModel topSongModel;

    public OnTopSongEvent(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }

    public void setTopSongModel(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
