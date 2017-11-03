package com.example.nhem.freemusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhem.freemusic.R;
import com.example.nhem.freemusic.adapter.TopSongAdapter;
import com.example.nhem.freemusic.database.TopSongModel;
import com.example.nhem.freemusic.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {

    @BindView(R.id.rv_music_download)
    RecyclerView rvDownload;

    private List<TopSongModel> topSongModels;
    private TopSongAdapter topSongAdapter;

    public DownloadFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        loadData(view);
        setupUI(view);
        return view;
    }

    private void loadData(View view) {
        topSongModels = Utils.getListSongsDownloaded(getContext());
    }

    public void setupUI(View view) {
        ButterKnife.bind(this, view);

        topSongAdapter = new TopSongAdapter(getContext(), topSongModels);
        rvDownload.setAdapter(topSongAdapter);
        rvDownload.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
