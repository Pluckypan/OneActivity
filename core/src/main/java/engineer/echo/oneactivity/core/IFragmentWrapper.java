package engineer.echo.oneactivity.core;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Define native fragment's methods that used by FragmentMaster.
 */
interface IFragmentWrapper {

    Fragment getFragment();

    Activity getActivity();

    Fragment getParentFragment();

    FragmentManager getChildFragmentManager();

    void setTargetFragment(Fragment target, int requestCode);

    Fragment getTargetFragment();

    int getTargetRequestCode();

    void setMenuVisibility(boolean isPrimary);

    void setUserVisibleHint(boolean isPrimary);

    boolean isResumed();

    View getView();
}
