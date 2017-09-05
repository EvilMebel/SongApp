package com.apps.zientara.rafal.songapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songapp.helpers.MenuItemsHider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evil on 30.08.2017.
 */

public abstract class BaseFragment extends Fragment {

    private MenuItemsHider menuItemsHider = new MenuItemsHider();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(getFragmentTag(), "onAttach");
    }

    private String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(getFragmentTag(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(getFragmentTag(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(getFragmentTag(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(getFragmentTag(), "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(getFragmentTag(), "onDestroyView");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Log.i(getFragmentTag(), "onPrepareOptionsMenu");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.i(getFragmentTag(), "onCreateContextMenu");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.i(getFragmentTag(), "onCreateOptionsMenu");
        menuItemsHider.getForeignMenuItems(menu);
        menuItemsHider.hideForeignMenuItems();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        menuItemsHider.showForeignMenuItems();
        menuItemsHider.clearList();
    }

    Bundle getBundleNonNull() {
        Bundle arguments = getArguments();
        if (arguments == null)
            return new Bundle();
        return arguments;
    }

}
