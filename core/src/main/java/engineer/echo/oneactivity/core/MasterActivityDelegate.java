package engineer.echo.oneactivity.core;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

class MasterActivityDelegate {
    /**
     * Persistence key for FragmentMaster
     */
    private static final String FRAGMENTS_TAG = "FragmentMaster:fragments";
    private FragmentMaster mFragmentMaster;

    public MasterActivityDelegate(FragmentActivity activity) {
        mFragmentMaster = new FragmentMasterImpl(activity);
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            Parcelable p = bundle.getParcelable(FRAGMENTS_TAG);
            mFragmentMaster.restoreAllState(p);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        Parcelable p = mFragmentMaster.saveAllState();
        if (p != null) {
            outState.putParcelable(FRAGMENTS_TAG, p);
        }
    }

    public FragmentMaster getFragmentMaster() {
        return mFragmentMaster;
    }

    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        return mFragmentMaster.dispatchKeyEvent(event);
    }

    public boolean dispatchKeyShortcutEvent(@NonNull KeyEvent event) {
        return mFragmentMaster.dispatchKeyShortcutEvent(event);
    }

    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        return mFragmentMaster.dispatchTouchEvent(ev);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        return mFragmentMaster.dispatchGenericMotionEvent(ev);
    }

    public boolean dispatchTrackballEvent(MotionEvent ev) {
        return mFragmentMaster.dispatchTrackballEvent(ev);
    }
}