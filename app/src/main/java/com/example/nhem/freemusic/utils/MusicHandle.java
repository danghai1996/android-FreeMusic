package com.example.nhem.freemusic.utils;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.nhem.freemusic.R;
import com.example.nhem.freemusic.database.TopSongModel;
import com.example.nhem.freemusic.network.GetSearchSongService;
import com.example.nhem.freemusic.network.RetrofitFactory;
import com.example.nhem.freemusic.network.json_model.SearchSongJSON;


import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NHEM on 29/10/2017.
 */

public class MusicHandle {
    public static HybridMediaPlayer hybridMediaPlayer;
    public static boolean keepUpdating = true;

    public static void searchSong(final TopSongModel topSongModel, final Context context) {
        GetSearchSongService searchSongService =
                RetrofitFactory.getInstance().create(GetSearchSongService.class);
        searchSongService.getSearchSong(topSongModel.getSongName() + " " + topSongModel.getArtistName())
                .enqueue(new Callback<SearchSongJSON>() {
            @Override
            public void onResponse(Call<SearchSongJSON> call, Response<SearchSongJSON> response) {
                topSongModel.setUrl(response.body().getData().getUrl());
                topSongModel.setLargeImage(response.body().getData().getThumbnail());

                playMusic(topSongModel, context);
            }

            @Override
            public void onFailure(Call<SearchSongJSON> call, Throwable t) {

            }
        });
    }

    public static void playMusic(TopSongModel topSongModel, Context context) {
        if (hybridMediaPlayer != null) {
            hybridMediaPlayer.pause();
            hybridMediaPlayer.release();
        }
        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.getUrl());
        hybridMediaPlayer.prepare();
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                hybridMediaPlayer.play();
            }
        });
    }

    public static void playPause() {
        if (hybridMediaPlayer.isPlaying()) {
            hybridMediaPlayer.pause();
        } else {
            hybridMediaPlayer.play();
        }
    }

    public static void updateRealtime(final SeekBar seekBar,
                                      final FloatingActionButton floatingActionButton,
                                      final ImageView imageView,
                                      final TextView tvCurrent,
                                      final TextView tvDuration) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //updateUI
                if (keepUpdating && hybridMediaPlayer != null) {
                    seekBar.setMax(hybridMediaPlayer.getDuration());
                    seekBar.setProgress(hybridMediaPlayer.getCurrentPosition());

                    if (hybridMediaPlayer.isPlaying()) {
                        floatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);
                    } else {
                        floatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    }
                    Utils.rotateImage(imageView, hybridMediaPlayer.isPlaying());

                    if (tvCurrent != null) {
                        tvDuration.setText(Utils.convertTime(hybridMediaPlayer.getDuration()));
                        tvCurrent.setText(Utils.convertTime(hybridMediaPlayer.getCurrentPosition()));
                    }
                }
                //reqeat
                handler.postDelayed(this, 100);
            }
        };
        runnable.run();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                keepUpdating = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hybridMediaPlayer.seekTo(seekBar.getProgress());
                keepUpdating = true;
            }
        });
    }
}
