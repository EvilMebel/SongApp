package com.apps.zientara.rafal.songapp.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songapp.SearchEngine;
import com.apps.zientara.rafal.songapp.adapters.SongsAdapter;
import com.apps.zientara.rafal.songapp.loggers.ConsoleLogger;
import com.apps.zientara.rafal.songapp.observables.SearchObservable;
import com.apps.zientara.rafal.songapp.preferences.DataOrderPreferences;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SongsFragment extends BaseFragment implements SongsAdapter.ClickListener {
    private static final int LOADING_TIME_OFFSET = 300;
    private Observable<String> searchObservable;
    private ConsoleLogger consoleLogger;
    private DataOrderPreferences dataOrderPreferences;
    private Disposable searchDisposable;
    private SearchEngine searchEngine;
    private SongsAdapter songsAdapter;
    private InteractionListener interactionListener;

    @BindView(R.id.songsFragment_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.songsFragment_searchEditText)
    EditText searchEditText;

    @BindView(R.id.songsFragment_progressSpinner)
    ProgressBar progressSpinner;

    @BindView(R.id.songsFragment_smallImage)
    ImageView smallImage;

    @BindView(R.id.songsFragment_messageImage)
    ImageView messageImage;

    @BindView(R.id.songsFragment_messageLinearLayout)
    LinearLayout messageLinearLayout;

    @BindView(R.id.songsFragment_messageText)
    TextView messageText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        consoleLogger = new ConsoleLogger();
        searchEngine = new SearchEngine(consoleLogger, getActivity().getApplicationContext());
        dataOrderPreferences = DataOrderPreferences.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_songs, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareRecyclerViewAdapter();
        hideProgressBar();
        prepareObservable();
        subscribeSearchingWithDataLoading();
    }

    private void prepareObservable() {
        if (searchObservable == null)
            searchObservable = new SearchObservable(searchEditText).create();
    }

    private void showEmptyResultMessage() {
        messageLinearLayout.setVisibility(View.VISIBLE);
    }

    private void hideEmptyResultMessage() {
        messageLinearLayout.setVisibility(View.GONE);
    }

    private void prepareRecyclerViewAdapter() {
        songsAdapter = new SongsAdapter(getActivity());
        songsAdapter.setClickListener(this);
        recyclerView.setAdapter(songsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void subscribeSearchingWithDataLoading() {
        if (searchDisposable == null)
            searchDisposable = searchObservable
                    .debounce(LOADING_TIME_OFFSET, TimeUnit.MILLISECONDS)
                    .filter(new Predicate<String>() {
                        @Override
                        public boolean test(String query) throws Exception {
                            int length = query.length();
                            return length != 1;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<String>() {
                        @Override
                        public void accept(String s) {
                            showProgressBar();
                        }
                    })
                    .observeOn(Schedulers.io())
                    .map(new Function<String, List<SongModel>>() {
                        @Override
                        public List<SongModel> apply(String query) {
                            searchEngine.refreshSourcesEnableState(getContext().getApplicationContext());
                            searchEngine.refreshSongsComparator(dataOrderPreferences);
                            if (query == null || query.isEmpty())
                                return searchEngine.getAllSongs();
                            return searchEngine.searchSongs(query);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<SongModel>>() {
                        @Override
                        public void accept(List<SongModel> songsList) {
                            hideProgressBar();
                            showResult(songsList);
                        }
                    });
    }

    @Override
    public void onResume() {
        super.onResume();
        consoleLogger.info("onResume");
        subscribeSearchingWithDataLoading();
    }

    @Override
    public void onPause() {
        super.onPause();
        dispose();
    }

    private void dispose() {
        if (searchDisposable != null && !searchDisposable.isDisposed())
            searchDisposable.dispose();
    }

    private void showResult(List<SongModel> songsList) {
        if (songsList.isEmpty())
            showEmptyResultMessage();
        else
            hideEmptyResultMessage();
        refreshList(songsList);
    }

    private void refreshList(List<SongModel> songsList) {
        songsAdapter.setSongsList(songsList);
        songsAdapter.notifyDataSetChanged();
    }

    private void hideProgressBar() {
        progressSpinner.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        hideEmptyResultMessage();
        progressSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void songClicked(SongModel songModel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            materialAnimation(songModel);
        else
            interactionListener.onSongClicked(songModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void materialAnimation(SongModel songModel) {
        boolean overlap = false;
        SongDetailsFragment songDetailsFragment = SongDetailsFragment.newInstance(songModel);

        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        android.transition.ChangeBounds changeBoundsTransition = new android.transition.ChangeBounds();
        changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        songDetailsFragment.setEnterTransition(slideTransition);
        songDetailsFragment.setAllowEnterTransitionOverlap(overlap);
        songDetailsFragment.setAllowReturnTransitionOverlap(overlap);
        songDetailsFragment.setSharedElementEnterTransition(changeBoundsTransition);

        getFragmentManager().beginTransaction()
                .replace(R.id.mainActivity_fragmentContainer, songDetailsFragment)
                .addToBackStack(null)
                .addSharedElement(smallImage, getString(R.string.transition_songIcon))
                .commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener)
            interactionListener = (InteractionListener) context;
        else
            throw new RuntimeException(String.format("%s must implement InteractionListener", context.toString()));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }

    public interface InteractionListener {
        void onSongClicked(SongModel songModel);
    }
}
