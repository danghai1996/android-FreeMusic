package com.example.nhem.freemusic.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhem.freemusic.R;
import com.example.nhem.freemusic.adapter.TopSongAdapter;
import com.example.nhem.freemusic.database.MusicTypeModel;
import com.example.nhem.freemusic.database.TopSongModel;
import com.example.nhem.freemusic.events.OnClickMusicTypeEvent;
import com.example.nhem.freemusic.network.GetTopSongService;
import com.example.nhem.freemusic.network.RetrofitFactory;
import com.example.nhem.freemusic.network.json_model.TopSongResponseJSON;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar tbTopSongs;
    @BindView(R.id.iv_favourite)
    ImageView ivFavourite;
    @BindView(R.id.tv_music_type)
    TextView tvMusicType;
    @BindView(R.id.iv_top_song)
    ImageView ivMusicType;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.rv_top_songs)
    RecyclerView rvTopSong;
    @BindView(R.id.av_loading)
    AVLoadingIndicatorView avLoading;

    private MusicTypeModel musicTypeModel;
    private List<TopSongModel> topSongModels = new ArrayList<>();
    TopSongAdapter topSongAdapter;


    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);
        ButterKnife.bind(this, view);

        EventBus.getDefault().register(this);

        setupUI();
        loadData();

        return view;
    }

    private void loadData() {
        GetTopSongService getTopSongService = RetrofitFactory.
                getInstance().create(GetTopSongService.class);
        getTopSongService.getTopSong(musicTypeModel.getId()).enqueue(new Callback<TopSongResponseJSON>() {
            @Override
            public void onResponse(Call<TopSongResponseJSON> call, Response<TopSongResponseJSON> response) {
                avLoading.hide();
                List<TopSongResponseJSON.FeedJSON.EntryJSON> entryJSONList = response.body().getFeed().getEntry();
                for (int i = 0; i < entryJSONList.size(); i++) {
                    TopSongResponseJSON.FeedJSON.EntryJSON entryJSON = entryJSONList.get(i);
                    String song = entryJSON.getNameJSON().getLabel();
                    String artist = entryJSON.getArtistJSON().getLabel();
                    String smallImage = entryJSON.getImageJSON().get(2).getLabel();

                    TopSongModel topSongModel = new TopSongModel(song, artist, smallImage);
                    topSongModels.add(topSongModel);

                    topSongAdapter.notifyItemChanged(i);
                }

//                topSongAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<TopSongResponseJSON> call, Throwable t) {

            }
        });
    }

    @Subscribe(sticky = true)
    public void onEventMusicTypeClicked(OnClickMusicTypeEvent onClickMusicTypeEvent) {
        musicTypeModel = onClickMusicTypeEvent.getMusicTypeModel();
    }

    private void setupUI() {
        tbTopSongs.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tbTopSongs.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        tvMusicType.setText(musicTypeModel.getKey());
        Picasso.with(getContext()).load(musicTypeModel.getImageID()).into(ivMusicType);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i(TAG, "onOffsetChanged: " + verticalOffset);
                if (verticalOffset == 0) {
                    tbTopSongs.setBackground(getResources().getDrawable(R.drawable.custom_gradient_toolbar));
                } else {
                    tbTopSongs.setBackground(null);
                }
            }
        });

        topSongAdapter = new TopSongAdapter(getContext(), topSongModels);
        rvTopSong.setAdapter(topSongAdapter);
        rvTopSong.setLayoutManager(new LinearLayoutManager(getContext()));

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        rvTopSong.addItemDecoration(dividerItemDecoration);
        avLoading.show();

        rvTopSong.setItemAnimator(new SlideInUpAnimator());
        rvTopSong.getItemAnimator().setAddDuration(300);

    }

}
