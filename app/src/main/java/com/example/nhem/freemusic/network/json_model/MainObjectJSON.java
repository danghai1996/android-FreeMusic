package com.example.nhem.freemusic.network.json_model;

import java.util.List;

/**
 * Created by NHEM on 12/10/2017.
 */

public class MainObjectJSON {
    List<SubgenresJSON> subgenres;

    public List<SubgenresJSON> getSubgenres() {
        return subgenres;
    }


    public void setSubgenres(List<SubgenresJSON> subgenres) {
        this.subgenres = subgenres;
    }
}
