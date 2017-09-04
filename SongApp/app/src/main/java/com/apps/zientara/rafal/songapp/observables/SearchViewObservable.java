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

    public Observable<String> create() {
        return BehaviorSubject.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {

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
//                emitter.onNext("");
            }
        });
    }
}
