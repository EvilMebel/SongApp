package com.apps.zientara.rafal.songapp.observables;

import android.support.v7.widget.SearchView;
import android.text.TextWatcher;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Evil on 04.09.2017.
 */

public class SearchViewObservable {
    private SearchView searchView;

    public SearchViewObservable(SearchView searchView) {
        this.searchView = searchView;
    }

    public Observable<String> create(final boolean initIfEmpty) {
        return BehaviorSubject.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                prepareQueryListener(emitter);
                initIfEmptyQuery(emitter, initIfEmpty);
            }
        });
    }

    private void prepareQueryListener(final ObservableEmitter<String> emitter) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SearchViewObservable", "onQueryTextChange:" + newText);
                emitter.onNext(newText);
                return false;
            }
        });
    }

    private void initIfEmptyQuery(ObservableEmitter<String> emitter, boolean initIfEmpty) {
        String startQuery = searchView.getQuery().toString();
        if (initIfEmpty && startQuery.isEmpty())
            emitter.onNext("");
    }
}
