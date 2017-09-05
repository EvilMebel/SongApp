package com.apps.zientara.rafal.songapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songapp.SearchEngine;
import com.apps.zientara.rafal.songapp.adapters.SongsAdapter;
import com.apps.zientara.rafal.songapp.adapters.viewHolders.SongViewHolder;
import com.apps.zientara.rafal.songapp.loggers.ConsoleLogger;
import com.apps.zientara.rafal.songapp.fragments.materialEffects.SongsFragmentEffects;
import com.apps.zientara.rafal.songapp.observables.SearchViewObservable;
import com.apps.zientara.rafal.songapp.preferences.DataOrderPreferences;

import org.parceler.Parcels;

import java.util.ArrayList;
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
    private static final String TAG = SongsFragment.class.getSimpleName();
    private static final int LOADING_TIME_OFFSET = 300;
    private static final String SONGS_KEY = "songs";
    private static final String SEARCH_PHRASE_KEY = "search_phrase";
    private Observable<String> searchViewObservable;
    private ConsoleLogger consoleLogger;
    private DataOrderPreferences dataOrderPreferences;
    private Disposable searchDisposable;
    private SearchEngine searchEngine;
    private SongsAdapter songsAdapter;
    private SearchView searchView;

    @BindView(R.id.songsFragment_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.songsFragment_progressSpinner)
    ProgressBar progressSpinner;

    @BindView(R.id.songsFragment_messageImage)
    ImageView messageImage;

    @BindView(R.id.songsFragment_messageLinearLayout)
    LinearLayout messageLinearLayout;

    @BindView(R.id.songsFragment_messageText)
    TextView messageText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposeSearch();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        storeSongsList();
        List<SongModel> songsList = songsAdapter.getSongsList();
        outState.putParcelable(SONGS_KEY, Parcels.wrap(songsList));
    }

    private void showEmptyResultMessage() {
        messageLinearLayout.setVisibility(View.VISIBLE);
    }

    private void hideEmptyResultMessage() {
        messageLinearLayout.setVisibility(View.GONE);
    }

    private void prepareRecyclerViewAdapter() {
        List<SongModel> songModels = restoreSongs();
        songsAdapter = new SongsAdapter(getActivity(), songModels);
        songsAdapter.setClickListener(this);
        recyclerView.setAdapter(songsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshEmptyTextVisibility();
    }

    private List<SongModel> restoreSongs() {
        List<SongModel> songModels = Parcels.unwrap(getBundleNonNull().getParcelable(SONGS_KEY));
        if (songModels == null)
            songModels = new ArrayList<>();
        else
            consoleLogger.info("restored records: " + songModels.size());
        return songModels;
    }

    private void subscribeSearchingWithDataLoading() {
        if (searchDisposable == null)
            searchDisposable = searchViewObservable
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
                            consoleLogger.info("accept:" + songsList.size() + songsList);
                            hideProgressBar();
                            showResult(songsList);
                        }
                    });
    }

    @Override
    public void onPause() {
        super.onPause();
        disposeSearch();
    }

    private void disposeSearch() {
        if (searchDisposable != null && !searchDisposable.isDisposed()) {
            searchDisposable.dispose();
            searchDisposable = null;
        }
    }

    private void showResult(List<SongModel> songsList) {
        refreshList(songsList);
        refreshEmptyTextVisibility();
    }

    private void refreshEmptyTextVisibility() {
        if (songsAdapter.getSongsList().isEmpty())
            showEmptyResultMessage();
        else
            hideEmptyResultMessage();
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
    public void songClicked(SongModel songModel, SongViewHolder holder) {
        storeSongsList();
        storeSearchPhrase();
        openSongDetailsFragment(songModel, holder);
    }

    private void storeSearchPhrase() {
        if (searchView != null) {
            String searchPhrase = searchView.getQuery().toString();
            getBundleNonNull().putString(SEARCH_PHRASE_KEY, searchPhrase);
        }
    }

    private void openSongDetailsFragment(SongModel songModel, SongViewHolder holder) {
        SongDetailsFragment songDetailsFragment = SongDetailsFragment.newInstance(songModel);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        applyMaterialEffects(holder, songDetailsFragment, transaction);
        transaction.replace(R.id.mainActivity_fragmentContainer, songDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    private void applyMaterialEffects(SongViewHolder holder, SongDetailsFragment songDetailsFragment, FragmentTransaction transaction) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SongsFragmentEffects songsFragmentEffects = new SongsFragmentEffects(getActivity());
            songsFragmentEffects.applyGoToDetailsEffects(holder, songDetailsFragment, transaction);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_songs, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        restoreSearchPhrase(searchView);
        searchViewObservable = new SearchViewObservable(searchView).create();
        subscribeSearchingWithDataLoading();
    }

    private void restoreSearchPhrase(SearchView searchView) {
        String searchPhrase = getBundleNonNull().getString(SEARCH_PHRASE_KEY, "");
        searchView.setQuery(searchPhrase, false);
    }

    private void storeSongsList() {
        List<SongModel> songsList = songsAdapter.getSongsList();
        Bundle bundle = getBundleNonNull();
        bundle.putParcelable(SONGS_KEY, Parcels.wrap(songsList));
        setArguments(bundle);
    }
}
