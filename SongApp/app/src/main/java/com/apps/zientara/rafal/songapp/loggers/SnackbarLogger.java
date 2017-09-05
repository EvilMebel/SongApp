package com.apps.zientara.rafal.songapp.loggers;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.apps.rafal.zientara.songs.core.loggers.Logger;

public class SnackbarLogger implements Logger {
    private View view;

    public void enable(View view) {
        this.view = view;
    }

    public void disable() {
        this.view = null;
    }

    @Override
    public void error(String message) {
        if (isEnabled())
            Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    private boolean isEnabled() {
        return view != null;
    }

    @Override
    public void info(String message) {
        if (isEnabled())
            Snackbar.make(view, message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}
