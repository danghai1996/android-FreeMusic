package com.example.nhem.freemusic.network.json_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by NHEM on 24/10/2017.
 */

public class TopSongResponseJSON {
    private FeedJSON feed;

    public FeedJSON getFeed() {
        return feed;
    }


    public class FeedJSON {
        List<EntryJSON> entry;

        public List<EntryJSON> getEntry() {
            return entry;
        }



        public class EntryJSON {
            @SerializedName("im:name")
            private NameJSON nameJSON;
            @SerializedName("im:image")
            private List<ImageJSON> imageJSON;
            @SerializedName("im:artist")
            private ArtistJSON artistJSON;

            public NameJSON getNameJSON() {
                return nameJSON;
            }

            public List<ImageJSON> getImageJSON() {
                return imageJSON;
            }

            public ArtistJSON getArtistJSON() {
                return artistJSON;
            }

            public class NameJSON {
                private String label;

                public String getLabel() {
                    return label;
                }
            }

            public class ImageJSON {
                private String label;

                public String getLabel() {
                    return label;
                }
            }

            public class ArtistJSON {
                private String label;

                public String getLabel() {
                    return label;
                }
            }
        }
    }
}
