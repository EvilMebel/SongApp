package com.apps.zientara.rafal.songapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.zientara.rafal.songapp.R;
import com.example.model.SongModel;

import java.util.List;

/**
 * Created by Evil on 26.08.2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongViewHolder>{
    private final LayoutInflater inlfater;
    List<SongModel> songsList;

    public SongsAdapter(Context context, List<SongModel> songsList) {
        inlfater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.songsList = songsList;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inlfater.inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        SongModel song = songsList.get(position);
        holder.updateFields(song);
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }
}
