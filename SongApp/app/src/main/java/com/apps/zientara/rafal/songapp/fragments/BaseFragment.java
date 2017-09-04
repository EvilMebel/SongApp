package com.apps.zientara.rafal.songapp.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

/**
 * Created by Evil on 30.08.2017.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(getFragmentTag(),"onAttach");
    }

    private String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(getFragmentTag(),"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(getFragmentTag(),"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(getFragmentTag(),"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(getFragmentTag(),"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(getFragmentTag(),"onDestroyView");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Log.i(getFragmentTag(),"onPrepareOptionsMenu");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.i(getFragmentTag(),"onCreateOptionsMenu");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.i(getFragmentTag(),"onCreateContextMenu");
    }
}
