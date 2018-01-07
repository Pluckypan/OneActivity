package engineer.echo.oneactivity.app.transition.fragment;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.app.transition.fragment.camera.fragment.CameraFragment;
import engineer.echo.oneactivity.app.transition.mvp.TransitionPresenter;
import engineer.echo.oneactivity.cmpts.Constants;
import engineer.echo.oneactivity.cmpts.context.BaseFragment;
import engineer.echo.oneactivity.cmpts.mvp.AbstractMvpFragment;
import engineer.echo.oneactivity.app.transition.mvp.TransitionContract;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

/**
 * TransitionFragment
 *
 * @author Plucky at 2018/01/07.
 */

public class TransitionFragment extends AbstractMvpFragment<TransitionContract.Presenter> implements TransitionContract.View {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected TransitionContract.Presenter providePresenter() {
        return new TransitionPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_transition;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews(View view) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CameraFragment cameraFragment = CameraFragment.getInstance();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            //设置Fragment离开时执行的动画   --变成不可见
            Fade fadeExit = new Fade();
            fadeExit.setDuration(Constants.TRANSITION_TIME);
            cameraFragment.setExitTransition(fadeExit);

            //设置Fragment重新回到栈顶时执行的动画 ---重新可见
            Fade fadeReEnter = new Fade();
            fadeReEnter.setDuration(Constants.TRANSITION_TIME);
            cameraFragment.setReenterTransition(fadeReEnter);
        }

        transaction.add(R.id.app_transition_container, cameraFragment);
        transaction.commit();
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    public static void openPage(BaseFragment fragment) {
        fragment.startFragment(TransitionFragment.class);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
}
