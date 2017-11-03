package com.example.nhem.freemusic.events;

import com.example.nhem.freemusic.database.MusicTypeModel;

/**
 * Created by NHEM on 17/10/2017.
 */

public class OnClickMusicTypeEvent {
    private MusicTypeModel musicTypeModel;

    public OnClickMusicTypeEvent(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }

    public MusicTypeModel getMusicTypeModel() {
        return musicTypeModel;
    }

    public void setMusicTypeModel(MusicTypeModel musicTypeModel) {
        this.musicTypeModel = musicTypeModel;
    }
}
