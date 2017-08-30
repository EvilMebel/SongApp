package com.apps.zientara.rafal.songapp.observables;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextWatcher;

import com.apps.zientara.rafal.songapp.preferences.DataSourcePreferences;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Evil on 30.08.2017.
 */

public class ObservablePreferences{
    /*public ObservablePreferences(Context context) {
        super(context);
    }

    public Observable<DataSourcePreferences> create() {
        return BehaviorSubject.create(new ObservableOnSubscribe<DataSourcePreferences>() {
            @Override
            public void subscribe(final ObservableEmitter<DataSourcePreferences> emitter) throws Exception {

                final TextWatcher textWatcher = createTextWatcher(emitter);
                searchEditText.addTextChangedListener(textWatcher);
                createDispose(emitter, textWatcher);
                emitter.onNext("");
            }
        });
    }

    @Override
    protected void saveBoolean(String valueKey, boolean value) {
        super.saveBoolean(valueKey, value);
        emitter.
    }*/
}
