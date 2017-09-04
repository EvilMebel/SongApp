package com.apps.zientara.rafal.songapp.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
import com.apps.zientara.rafal.songapp.helpers.SharedElementHelper;
import com.apps.zientara.rafal.songapp.loggers.ConsoleLogger;
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

public class SongsFragment extends BaseFragment implements SongsAdapter.ClickListener, SearchView.OnQueryTextListener {
    private static final String TAG = SongsFragment.class.getSimpleName();
    private static final int LOADING_TIME_OFFSET = 300;
    public static final String SONGS_KEY = "songs";
    private Observable<String> searchObservable;
    private Observable<String> searchViewObservable;
    private ConsoleLogger consoleLogger;
    private DataOrderPreferences dataOrderPreferences;
    private Disposable searchDisposable;
    private SearchEngine searchEngine;
    private SongsAdapter songsAdapter;
    private InteractionListener interactionListener;

    @BindView(R.id.songsFragment_recyclerView)
    RecyclerView recyclerView;

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

//        searchObservable = null;
//        searchViewObservable = null;
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
        List<SongModel> songModels = Parcels.unwrap(createBundle().getParcelable(SONGS_KEY));
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
    public void onResume() {
        super.onResume();
        consoleLogger.info("onResume");
//        subscribeSearchingWithDataLoading();
    }

    @Override
    public void onPause() {
        super.onPause();
        disposeSearchEditTextObservable();
    }

    private void disposeSearchEditTextObservable() {
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            materialAnimation(songModel, holder);
        else
            interactionListener.onSongClicked(songModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void materialAnimation(SongModel songModel, SongViewHolder holder) {
        boolean overlap = false;
        SongDetailsFragment songDetailsFragment = SongDetailsFragment.newInstance(songModel);

        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        android.transition.ChangeBounds changeBoundsTransition = new android.transition.ChangeBounds();
        changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));

        songDetailsFragment.setEnterTransition(slideTransition);
        songDetailsFragment.setAllowEnterTransitionOverlap(overlap);
        songDetailsFragment.setAllowReturnTransitionOverlap(overlap);
        songDetailsFragment.setSharedElementEnterTransition(changeBoundsTransition);

//        ViewCompat.setTransitionName(holder.imageView, getString(R.string.transition_songIcon));
//        ViewCompat.setTransitionName(holder.songNameText, getString(R.string.transition_songName));
//        ViewCompat.setTransitionName(holder.artistText, getString(R.string.transition_songArtist));

//        ViewCompat.setTransitionName(artistText, SharedElementHelper.getArtistTrans(songModel));
//        ViewCompat.setTransitionName(imageView, SharedElementHelper.getIconTrans(songModel));
//        ViewCompat.setTransitionName(songNameText, SharedElementHelper.getNameTrans(songModel));

        getFragmentManager().beginTransaction()
                .replace(R.id.mainActivity_fragmentContainer, songDetailsFragment)
                .addToBackStack(null)
                .addSharedElement(holder.artistText, SharedElementHelper.getArtistTrans(songModel))
                .addSharedElement(holder.imageView, SharedElementHelper.getIconTrans(songModel))
                .addSharedElement(holder.songNameText, SharedElementHelper.getNameTrans(songModel))
                .commit();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_songs, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchViewObservable = new SearchViewObservable(searchView).create();
        subscribeSearchingWithDataLoading();
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(TAG, "onQueryTextSubmit " + query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, "onQueryTextChange " + newText);
        return false;
    }

    public interface InteractionListener {
        void onSongClicked(SongModel songModel);
    }

    private void storeSongsList() {
        List<SongModel> songsList = songsAdapter.getSongsList();
        Bundle bundle = createBundle();
        bundle.putParcelable(SONGS_KEY, Parcels.wrap(songsList));
        setArguments(bundle);
    }

    private Bundle createBundle() {
        Bundle arguments = getArguments();
        if (arguments == null)
            return new Bundle();
        return arguments;
    }
}
