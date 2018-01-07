package engineer.echo.oneactivity.app.transition.fragment.settings.fragment;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.app.transition.fragment.camera.fragment.CameraFragment;
import engineer.echo.oneactivity.app.transition.fragment.settings.mvp.SettingsPresenter;
import engineer.echo.oneactivity.cmpts.mvp.AbstractMvpChildFragment;
import engineer.echo.oneactivity.app.transition.fragment.settings.mvp.SettingsContract;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import static engineer.echo.oneactivity.cmpts.Constants.TRANSITION_TIME;

/**
 * SettingsFragment
 *
 * @author Plucky at 2018/01/07.
 */

public class SettingsFragment extends AbstractMvpChildFragment<SettingsContract.Presenter> implements SettingsContract.View, View.OnClickListener {
    // ===========================================================
    // Constants
    // ===========================================================

    public SettingsFragment() {

    }

    public static SettingsFragment getInstance() {
        return new SettingsFragment();
    }


    // ===========================================================
    // Fields
    // ===========================================================
    private ImageView mTakePhotoIV;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected SettingsContract.Presenter providePresenter() {
        return new SettingsPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_child_settings;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews(View view) {
        mTakePhotoIV = view.findViewById(R.id.app_settings_take_photo_btn);
        mTakePhotoIV.setOnClickListener(this);
        ViewCompat.setTransitionName(mTakePhotoIV, CameraFragment.SHARE_NAME_TAKEPHOTO);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_settings_take_photo_btn:
                getFragmentManager().popBackStack();
                break;
        }
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    public static void gotoPage(FragmentManager manager, Pair<View, String> shareView) {

        SettingsFragment fragment = SettingsFragment.getInstance();

        fragment.setAllowEnterTransitionOverlap(false);
        fragment.setAllowReturnTransitionOverlap(false);

        // TODO: Plucky 2018/1/8 上午12:19 只有 API>=19 才支持Visibility动画  只有API>=21 才支持路径动画 需要检查下项目
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Fade fadeIn = new Fade();
            fadeIn.setDuration(TRANSITION_TIME);
            fragment.setEnterTransition(fadeIn);

            Fade fadeOut = new Fade();
            fadeOut.setDuration(TRANSITION_TIME);
            fragment.setReturnTransition(fadeOut);

            ChangeBounds boundsIn = new ChangeBounds();
            boundsIn.setDuration(TRANSITION_TIME);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                boundsIn.setPathMotion(new ArcMotion());
            }
            fragment.setSharedElementEnterTransition(boundsIn);


            ChangeBounds boundsOut = new ChangeBounds();
            boundsOut.setDuration(TRANSITION_TIME);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                boundsIn.setPathMotion(new ArcMotion());
            }
            fragment.setSharedElementReturnTransition(boundsOut);
        }

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.app_transition_container, fragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.addSharedElement(shareView.first, shareView.second);

        fragmentTransaction.commit();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
}
