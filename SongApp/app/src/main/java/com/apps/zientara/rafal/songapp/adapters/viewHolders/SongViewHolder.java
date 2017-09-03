package com.apps.zientara.rafal.songapp.adapters.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apps.rafal.zientara.songs.core.helpers.SongHelper;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SongViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.songItem_artistText)
    TextView artistText;

    @BindView(R.id.songItem_releaseYearText)
    TextView releaseYearText;

    @BindView(R.id.songItem_songNameText)
    TextView songNameText;

    public SongViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateFields(SongModel song) {
        artistText.setText(song.getArtist());
        releaseYearText.setText(SongHelper.getYearText(song));
        songNameText.setText(song.getSongName());
    }

}
