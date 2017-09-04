package com.apps.zientara.rafal.songapp.adapters.viewHolders;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.rafal.zientara.songs.core.helpers.SongHelper;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songapp.helpers.SharedElementHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SongViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.songItem_artistText)
    public TextView artistText;

    @BindView(R.id.songItem_releaseYearText)
    public TextView releaseYearText;

    @BindView(R.id.songItem_songNameText)
    public TextView songNameText;

    @BindView(R.id.songItem_imageView)
    public ImageView imageView;

    public SongViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateFields(SongModel songModel) {
        artistText.setText(songModel.getArtist());
        releaseYearText.setText(SongHelper.getYearText(songModel));
        songNameText.setText(songModel.getSongName());

        ViewCompat.setTransitionName(artistText, SharedElementHelper.getArtistTrans(songModel));
        ViewCompat.setTransitionName(imageView, SharedElementHelper.getIconTrans(songModel));
        ViewCompat.setTransitionName(songNameText, SharedElementHelper.getNameTrans(songModel));
    }

}
