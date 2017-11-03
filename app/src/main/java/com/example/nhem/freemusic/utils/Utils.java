package com.example.nhem.freemusic.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.nhem.freemusic.database.TopSongModel;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.List;
/**
 * Created by NHEM on 17/10/2017.
 */

public class Utils {
    public static void openFragment(FragmentManager fragmentManager, int layoutID, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void rotateImage(ImageView imageView, boolean isRotate) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(10000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        if (isRotate) {
            if (imageView.getAnimation() == null) imageView.startAnimation(rotateAnimation);
        } else {
            imageView.setAnimation(null);
        }
    }

    public static String convertTime(long milis) {
        long min = TimeUnit.MILLISECONDS.toMinutes(milis);
        return String.format("%02d:%02d",
                min,
                TimeUnit.MILLISECONDS.toSeconds(milis - min*60*1000));
    }

    public static File[] getMusicDownloaded(Context context) {
        File[] files = context.getExternalFilesDir("").listFiles();
        return files;
    }

    public static List<TopSongModel> getListSongsDownloaded(Context context) {
        File[] files = getMusicDownloaded(context);
        List<TopSongModel> topSongModels = new ArrayList<>();

        for (File file : files) {
            String songName = "";
            String artist = "";

            int i = 0;
            while(file.getName().charAt(i) != '-') {
                songName += file.getName().charAt(i);
                i++;
            }

            i++;

            while (file.getName().charAt(i) != '.') {
                artist += file.getName().charAt(i);
                i++;
            }

            TopSongModel topSongModel = new TopSongModel();
            topSongModel.setDataFromFile(songName, artist, file.getAbsolutePath());
            topSongModels.add(topSongModel);
        }
        return topSongModels;
    }
}
