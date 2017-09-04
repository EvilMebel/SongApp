package com.apps.zientara.rafal.songapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.rafal.zientara.songs.core.helpers.SongHelper;
import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songapp.helpers.SharedElementHelper;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 03.09.2017.
 */

public class SongDetailsFragment extends Fragment {
    private static final String SONG_KEY = "song";
    private SongModel songModel;

    @BindView(R.id.songDetailsFragment_imageView)
    ImageView imageView;

    @BindView(R.id.songDetailsFragment_songNameText)
    TextView songNameText;

    @BindView(R.id.songDetailsFragment_artistText)
    TextView artistText;

    @BindView(R.id.songDetailsFragment_yearText)
    TextView yearText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_details, container, false);
        ButterKnife.bind(this, view);
        ViewCompat.setTransitionName(imageView, SharedElementHelper.getUniqueName(songModel));
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
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null)
            songModel = Parcels.unwrap(arguments.getParcelable(SONG_KEY));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateView();
    }

    private void updateView() {
        if (songModel != null) {
            songNameText.setText(songModel.getSongName());
            artistText.setText(songModel.getArtist());
            yearText.setText(SongHelper.getYearText(songModel));
        }
    }
}
