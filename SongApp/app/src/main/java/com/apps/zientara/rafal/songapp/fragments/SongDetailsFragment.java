package com.apps.zientara.rafal.songapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesSong;

import butterknife.ButterKnife;

/**
 * Created by Evil on 03.09.2017.
 */

public class SongDetailsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static SongDetailsFragment newInstance(SongModel songModel) {
        SongDetailsFragment fragment = new SongDetailsFragment();
        Bundle bundle = createBundle(songModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    private static Bundle createBundle(SongModel songModel) {
        Bundle bundle = new Bundle();
        if(songModel instanceof TunesSong) {
            TunesSong tunesSong = (TunesSong) songModel;
//            bundle.putParcelable("tunes_song", tunesSong);//// TODO: 03.09.2017 impl must be a android module
        }
        return bundle;
    }
}
