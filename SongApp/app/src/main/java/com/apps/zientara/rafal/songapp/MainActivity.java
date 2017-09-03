package com.apps.zientara.rafal.songapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.apps.rafal.zientara.songs.core.model.SongModel;
import com.apps.zientara.rafal.songapp.fragments.SettingsFragment;
import com.apps.zientara.rafal.songapp.fragments.SongDetailsFragment;
import com.apps.zientara.rafal.songapp.fragments.SongsFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements SongsFragment.InteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        openHomeScreen();
    }

    private void openHomeScreen() {
        Fragment activeFragment = getActiveFragment();
        if (activeFragment == null || !(activeFragment instanceof SongsFragment))
            openFragment(new SongsFragment(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                hideKeyboard();
                openSettingsFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettingsFragment() {
        Fragment activeFragment = getActiveFragment();
        if (activeFragment == null || !(activeFragment instanceof SettingsFragment))
            openFragment(new SettingsFragment(), true);
    }

    private void openSongDetailsFragment(SongModel songModel) {
            openFragment(SongDetailsFragment.newInstance(songModel), true);
    }

    private Fragment getActiveFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.mainActivity_fragmentContainer);
    }

    protected void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onSongClicked(SongModel songModel) {
        openSongDetailsFragment(songModel);
    }
}
