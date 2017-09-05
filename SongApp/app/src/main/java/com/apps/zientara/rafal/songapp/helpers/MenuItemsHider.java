package com.apps.zientara.rafal.songapp.helpers;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsHider {
    private static final int SMALLEST_MAIN_OPTION_ORDER = 1000;
    private List<MenuItem> foreignMenuItems = new ArrayList<>();
    private boolean shouldHideActivityMenuItems = false;

    public void hideForeignMenuItems() {
        for (MenuItem menuItem : foreignMenuItems)
            disableMenuItem(menuItem);
    }

    public void showForeignMenuItems() {
        for (MenuItem menuItem : foreignMenuItems)
            enableMenuItem(menuItem);
    }

    public void getForeignMenuItems(Menu menu) {
        foreignMenuItems.clear();
        int menuSize = menu.size();
        for (int i = 0; i < menuSize; i++) {
            MenuItem menuItem = menu.getItem(i);
            if (shouldHideActivityMenuItems || !isActivityMenuItem(menuItem))
                foreignMenuItems.add(menuItem);
        }
    }

    private boolean isActivityMenuItem(MenuItem menuItem) {
        return menuItem.getOrder() > SMALLEST_MAIN_OPTION_ORDER;
    }

    private void disableMenuItem(MenuItem menuItem) {
        menuItem.setVisible(false);
        menuItem.setEnabled(false);
    }

    private void enableMenuItem(MenuItem menuItem) {
        menuItem.setVisible(false);
        menuItem.setEnabled(false);
    }

    public void clearList() {
        foreignMenuItems.clear();
    }
}
