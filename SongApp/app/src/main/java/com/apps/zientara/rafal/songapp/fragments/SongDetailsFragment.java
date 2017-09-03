package com.apps.zientara.rafal.songapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songs.impl.models.TooplooxSong;
import com.apps.zientara.rafal.songs.impl.models.tunes.TunesSong;
import com.apps.zientara.rafal.songs.impl.sources.JsonSongsSource;

import org.parceler.Parcels;

import butterknife.ButterKnife;

/**
 * Created by Evil on 03.09.2017.
 */

public class SongDetailsFragment extends Fragment {
    private static final String SONG_KEY = "song";
    private SongModel songModel;

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
        bundle.putParcelable(SONG_KEY, Parcels.wrap(songModel));
        return bundle;

//        if(songModel instanceof TunesSong) {
//            TunesSong tunesSong = (TunesSong) songModel;
//            bundle.putParcelable("song", Parcels.wrap(tunesSong));
//        } else if(songModel instanceof TooplooxSong) {
//            TooplooxSong songModel1 = (TooplooxSong) songModel;
//        }
//        return bundle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            songModel = Parcels.unwrap(arguments.getParcelable(SONG_KEY));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), songModel.toString(), Toast.LENGTH_SHORT).show();
    }
}
