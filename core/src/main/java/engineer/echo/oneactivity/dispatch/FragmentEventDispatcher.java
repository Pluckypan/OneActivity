package engineer.echo.oneactivity.dispatch;

import android.support.v4.view.KeyEventCompat2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import engineer.echo.oneactivity.core.IMasterFragment;

/**
 * FragmentEventDispatcher
 * Created by Plucky<plucky@echo.engineer> on 2018/1/6 下午6:07.
 * more about me: http://www.1991th.com
 */
public class FragmentEventDispatcher implements EventDispatcher {

    private IMasterFragment mMasterFragment;

    public FragmentEventDispatcher(IMasterFragment masterFragment) {
        mMasterFragment = masterFragment;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        View view = mMasterFragment.getView();
        return KeyEventCompat2.dispatch(event, mMasterFragment,
                view != null
                        ? KeyEventCompat2.getKeyDispatcherState(view)
                        : null, this);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return mMasterFragment.onKeyShortcut(event.getKeyCode(), event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return mMasterFragment.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return mMasterFragment.onTrackballEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return mMasterFragment.onGenericMotionEvent(event);
    }
}
