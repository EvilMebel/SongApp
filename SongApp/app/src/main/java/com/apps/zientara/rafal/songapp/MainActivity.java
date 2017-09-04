package com.apps.zientara.rafal.songapp;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

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

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
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

    @Override
    public void onSongClicked(SongModel songModel) {
        //openSongDetailsFragment(songModel);
    }
}
