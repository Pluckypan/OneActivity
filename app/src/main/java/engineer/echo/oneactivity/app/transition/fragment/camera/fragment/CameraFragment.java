package engineer.echo.oneactivity.app.transition.fragment.camera.fragment;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.app.transition.fragment.camera.mvp.CameraPresenter;
import engineer.echo.oneactivity.app.transition.fragment.settings.fragment.SettingsFragment;
import engineer.echo.oneactivity.app.transition.fragment.camera.mvp.CameraContract;
import engineer.echo.oneactivity.cmpts.mvp.AbstractMvpChildFragment;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Pair;
import android.view.View;
import com.flurgle.camerakit.CameraView;

/**
 * CameraFragment
 *
 * @author Plucky at 2018/01/07.
 */

public class CameraFragment extends AbstractMvpChildFragment<CameraContract.Presenter> implements CameraContract.View, View.OnClickListener {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final String TAG = "CameraFragment";
    public static final String SHARE_NAME_TAKEPHOTO = "share_element_of_take_photo";


    public CameraFragment() {

    }

    public static CameraFragment getInstance() {
        return new CameraFragment();
    }

    // ===========================================================
    // Fields
    // ===========================================================
    private CameraView mCameraView;
    private View mTakePhotoIV;
    private Pair<View, String> mShareView;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected CameraContract.Presenter providePresenter() {
        return new CameraPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_child_camera;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews(View view) {
        mCameraView = view.findViewById(R.id.app_camera_view);
        mTakePhotoIV = view.findViewById(R.id.app_camera_take_photo_btn);
        mTakePhotoIV.setOnClickListener(this);
        ViewCompat.setTransitionName(mTakePhotoIV, SHARE_NAME_TAKEPHOTO);
        mShareView = new Pair<>(mTakePhotoIV, SHARE_NAME_TAKEPHOTO);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCameraView.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_camera_take_photo_btn:
                SettingsFragment.gotoPage(getFragmentManager(), mShareView);
                break;
        }
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
}
