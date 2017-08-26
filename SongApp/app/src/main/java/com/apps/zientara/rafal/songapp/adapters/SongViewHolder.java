package com.apps.zientara.rafal.songapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apps.zientara.rafal.songapp.R;
import com.example.model.SongModel;

import butterknife.BindView;

/**
 * Created by Evil on 26.08.2017.
 */

class SongViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.songItem_artistText)
    TextView artistText;

    @BindView(R.id.songItem_releaseYearText)
    TextView releaseYearText;

    @BindView(R.id.songItem_songNameText)
    TextView songNameText;

    public SongViewHolder(View itemView) {
        super(itemView);
    }

    public void updateFields(SongModel song) {
        artistText.setText(song.getArtist());
        releaseYearText.setText(song.getReleaseYear());
        songNameText.setText(song.getSongName());
    }
}
