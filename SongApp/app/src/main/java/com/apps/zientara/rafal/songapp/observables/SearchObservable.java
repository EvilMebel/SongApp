package com.apps.zientara.rafal.songapp.observables;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Evil on 29.08.2017.
 */

public class SearchObservable {
    private EditText searchEditText;

    public SearchObservable(EditText searchEditText) {
        this.searchEditText = searchEditText;
    }

    public Observable<String> create() {
        return BehaviorSubject.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                final TextWatcher textWatcher = createTextWatcher(emitter);
                searchEditText.addTextChangedListener(textWatcher);
                createDispose(emitter, textWatcher);
                //emitter.onNext("");
            }
        });
    }

    private void createDispose(ObservableEmitter<String> emitter, final TextWatcher textWatcher) {
        emitter.setDisposable(new Disposable() {
            @Override
            public void dispose() {
                searchEditText.removeTextChangedListener(textWatcher);
            }

            @Override
            public boolean isDisposed() {
                return false;
            }

        });
    }

    private TextWatcher createTextWatcher(final ObservableEmitter<String> emitter) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emitter.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }
}
