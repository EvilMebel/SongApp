package com.apps.zientara.rafal.songapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songapp.preferences.DataOrderPreferences;
import com.apps.zientara.rafal.songapp.preferences.DataSourcePreferences;
import com.apps.zientara.rafal.songapp.preferences.enums.CriteriaTypeEnum;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 30.08.2017.
 */

public class SettingsFragment extends BaseFragment {
    private DataSourcePreferences dataSourcePreferences;
    private DataOrderPreferences dataOrderPreferences;

    @BindView(R.id.settingsFragment_useFakeDataSwitch)
    Switch useFakeDataSwitch;

    @BindView(R.id.settingsFragment_useLocalDatabaseSwitch)
    Switch useLocalDatabaseSwitch;

    @BindView(R.id.settingsFragment_useTunesSwitch)
    Switch useTunesSwitch;

    @BindView(R.id.settingsFragment_searchingOrderDescendingSwitch)
    Switch searchingOrderDescendingSwitch;

    @BindView(R.id.settingsFragment_searchingCriteriaRadioGroup)
    RadioGroup searchingCriteriaRadioGroup;

    @BindView(R.id.settingsFragment_searchingCriteriaArtistRadioButton)
    RadioButton searchingCriteriaArtistRadioButton;

    @BindView(R.id.settingsFragment_searchingCriteriaDateRadioButton)
    RadioButton searchingCriteriaDateRadioButton;

    @BindView(R.id.settingsFragment_searchingCriteriaSongNameRadioButton)
    RadioButton searchingCriteriaSongNameRadioButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadPreferences();
        refreshView();
        setListeners();
    }

    private void loadPreferences() {
        dataSourcePreferences = DataSourcePreferences.getInstance(getActivity().getApplicationContext());
        dataOrderPreferences = DataOrderPreferences.getInstance(getActivity().getApplicationContext());
    }

    private void refreshView() {
        refreshSoungSources();
        searchingOrderDescendingSwitch.setChecked(dataOrderPreferences.isDescending());
        refreshSearchingCriteria();
    }

    private void refreshSearchingCriteria() {
        switch (dataOrderPreferences.getSortingCriteria()) {
            case ARTIST:
                searchingCriteriaArtistRadioButton.setChecked(true);
                break;
            case DATE:
                searchingCriteriaDateRadioButton.setChecked(true);
                break;
            case SONG_NAME:
                searchingCriteriaSongNameRadioButton.setChecked(true);
                break;
        }
    }

    private void refreshSoungSources() {
        useFakeDataSwitch.setChecked(dataSourcePreferences.isFakeDataEnabled());
        useLocalDatabaseSwitch.setChecked(dataSourcePreferences.isLocalDbEnabled());
        useTunesSwitch.setChecked(dataSourcePreferences.isTunesEnabled());
    }

    private void setListeners() {
        setFakeSwitchListener();
        setLocalSwitchListener();
        setTunesSwitchListener();
        setSearchOrderListener();
        setSearchingCriteriaListener();
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

    private void setSearchOrderListener() {
        searchingOrderDescendingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean newValue) {
                dataOrderPreferences.setDescending(newValue);
            }
        });
    }

    private void setSearchingCriteriaListener() {
        searchingCriteriaRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.settingsFragment_searchingCriteriaArtistRadioButton:
                        dataOrderPreferences.setSortingCriteria(CriteriaTypeEnum.ARTIST);
                        break;
                    case R.id.settingsFragment_searchingCriteriaDateRadioButton:
                        dataOrderPreferences.setSortingCriteria(CriteriaTypeEnum.DATE);
                        break;
                    case R.id.settingsFragment_searchingCriteriaSongNameRadioButton:
                        dataOrderPreferences.setSortingCriteria(CriteriaTypeEnum.SONG_NAME);
                        break;
                    default:
                        Log.e("SettingsSwitch", "Radio button for this criteria is not supported!");
                }
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dataSourcePreferences = null;
    }
}
