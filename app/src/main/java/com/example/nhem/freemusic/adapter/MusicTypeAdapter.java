package com.example.nhem.freemusic.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhem.freemusic.R;
import com.example.nhem.freemusic.database.MusicTypeModel;
import com.example.nhem.freemusic.events.OnClickMusicTypeEvent;
import com.example.nhem.freemusic.fragment.TopSongFragment;
import com.example.nhem.freemusic.utils.Utils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by NHEM on 15/10/2017.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTypeViewHolder> {
    private List<MusicTypeModel> musicTypeModels;
    private Context context;

    public MusicTypeAdapter(List<MusicTypeModel> musicTypeModels, Context context) {
        this.musicTypeModels = musicTypeModels;
        this.context = context;
    }

    // Creat item view if needed
    @Override
    public MusicTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_music_type, parent, false);

        return new MusicTypeViewHolder(itemView);
    }

    //Load data
    @Override
    public void onBindViewHolder(MusicTypeViewHolder holder, int position) {
        holder.setData(musicTypeModels.get(position));
    }

    @Override
    public int getItemCount() {
        return musicTypeModels.size();
    }

    //TODO R1: Creat ViewHolder
    class MusicTypeViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMusicType;
        TextView tvMusicType;
        View view;

        public MusicTypeViewHolder(View itemView) {
            super(itemView);

            ivMusicType = itemView.findViewById(R.id.iv_music_type);
            tvMusicType = itemView.findViewById(R.id.tv_music_type);
            view = itemView;
        }

        public void setData(final MusicTypeModel musicTypeModel) {
            Picasso.with(context).load(musicTypeModel.getImageID()).into(ivMusicType);
            tvMusicType.setText(musicTypeModel.getKey());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().postSticky(new OnClickMusicTypeEvent(musicTypeModel));
                    Utils.openFragment(
                            ((FragmentActivity)context).getSupportFragmentManager(),
                            R.id.layout_container,
                            new TopSongFragment());

                }
            });
        }
    }
}