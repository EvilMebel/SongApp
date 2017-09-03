package com.apps.zientara.rafal.songapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.zientara.rafal.songapp.R;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.adapters.viewHolders.SongViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongViewHolder>{
    private final LayoutInflater inlfater;
    private ClickListener clickListener;
    private List<SongModel> songsList;

    public SongsAdapter(Context context) {
        inlfater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.songsList = new ArrayList<>();
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inlfater.inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        final SongModel song = songsList.get(position);
        holder.updateFields(song);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.songClicked(song);//// TODO: 03.09.2017 test
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public void setSongsList(List<SongModel> songsList) {
        this.songsList = songsList;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void songClicked(SongModel songModel);
    }
}
