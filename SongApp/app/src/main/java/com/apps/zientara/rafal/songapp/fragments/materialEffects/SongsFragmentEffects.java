package com.apps.zientara.rafal.songapp.fragments.materialEffects;

import android.content.Context;
import android.os.Build;
import android.support.transition.ChangeBounds;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.transition.Slide;
import android.view.Gravity;

import com.apps.zientara.rafal.songapp.R;
import com.apps.zientara.rafal.songapp.adapters.viewHolders.SongViewHolder;
import com.apps.zientara.rafal.songapp.fragments.SongDetailsFragment;

public class SongsFragmentEffects {
    public static final boolean USE_OVERLAP = false;
    private final int slideDurationMillis;
    private final int changeBoundsDurationMillis;

    public SongsFragmentEffects(Context context) {
        slideDurationMillis = context.getResources().getInteger(R.integer.anim_duration_medium);
        changeBoundsDurationMillis = context.getResources().getInteger(R.integer.anim_duration_long);
    }

    public void applyGoToDetailsEffects(SongViewHolder holder, SongDetailsFragment songDetailsFragment, FragmentTransaction transaction) {
        applyChangeBounds(songDetailsFragment);
        applySlideForLolipop(songDetailsFragment);
        applyOverlap(songDetailsFragment);
        applySharedElements(holder, transaction);
    }

    private void applyChangeBounds(SongDetailsFragment songDetailsFragment) {
        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(changeBoundsDurationMillis);
        songDetailsFragment.setSharedElementEnterTransition(changeBoundsTransition);
    }

    private void applyOverlap(SongDetailsFragment songDetailsFragment) {
        songDetailsFragment.setAllowEnterTransitionOverlap(USE_OVERLAP);
        songDetailsFragment.setAllowReturnTransitionOverlap(USE_OVERLAP);
    }

    private void applySlideForLolipop(SongDetailsFragment songDetailsFragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide(Gravity.RIGHT);
            slideTransition.setDuration(slideDurationMillis);
            songDetailsFragment.setEnterTransition(slideTransition);
        }
    }

    private void applySharedElements(SongViewHolder holder, FragmentTransaction transaction) {
        transaction.addSharedElement(holder.artistText, ViewCompat.getTransitionName(holder.artistText))
                .addSharedElement(holder.imageView, ViewCompat.getTransitionName(holder.imageView))
                .addSharedElement(holder.songNameText, ViewCompat.getTransitionName(holder.songNameText));
    }
}
