package com.apps.zientara.rafal.songapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songapp.preferences.DataSourcePreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 30.08.2017.
 */

public class SettingsFragment extends Fragment {
    private DataSourcePreferences dataSourcePreferences;

    @BindView(R.id.settingsFragment_useFakeDataSwitch)
    Switch useFakeDataSwitch;

    @BindView(R.id.settingsFragment_useLocalDatabaseSwitch)
    Switch useLocalDatabaseSwitch;

    @BindView(R.id.settingsFragment_useTunesSwitch)
    Switch useTunesSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataSourcePreferences = new DataSourcePreferences(getActivity().getApplicationContext());
        refreshView();
        setListeners();
    }

    private void refreshView() {
        useFakeDataSwitch.setChecked(dataSourcePreferences.isFakeDataEnabled());
        useLocalDatabaseSwitch.setChecked(dataSourcePreferences.isLocalDbEnabled());
        useTunesSwitch.setChecked(dataSourcePreferences.isTunesEnabled());
    }

    private void setListeners() {
        setFakeSwitchListener();
        setLocalSwitchListener();
        setTunesSwitchListener();
    }

    private void setFakeSwitchListener() {
        useFakeDataSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean newValue) {
                dataSourcePreferences.setFakeDataEnabled(newValue);
            }
        });
    }

    private void setLocalSwitchListener() {
        useLocalDatabaseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean newValue) {
                dataSourcePreferences.setLocalDbEnabled(newValue);
            }
        });
    }

    private void setTunesSwitchListener() {
        useTunesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean newValue) {
                dataSourcePreferences.setTunesEnabled(newValue);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dataSourcePreferences = null;
    }
}
