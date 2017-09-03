package com.apps.zientara.rafal.songapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

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
    private static final int LOADING_TIME_OFFSET = 100;//300;
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
        loadData();
    }

    private void prepareRecyclerViewAdapter() {
        songsAdapter = new SongsAdapter(getActivity());
        songsAdapter.setClickListener(this);
        recyclerView.setAdapter(songsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void loadData() {
        Observable<String> searchObservable = new SearchObservable(searchEditText).create();

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
        searchEngine.refreshSourcesEnableState(getActivity().getApplicationContext());
    }

    private void dispose() {
        if (searchDisposable != null && !searchDisposable.isDisposed())
            searchDisposable.dispose();
    }

    private void showResult(List<SongModel> songsList) {
        songsAdapter.setSongsList(songsList);
        songsAdapter.notifyDataSetChanged();
    }

    private void hideProgressBar() {
        progressSpinner.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        progressSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void songClicked(SongModel songModel) {
        interactionListener.onSongClicked(songModel);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            interactionListener = (InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
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
