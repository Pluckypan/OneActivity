package engineer.echo.oneactivity.dispatch;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * WindowEventDispatcher
 * Created by Plucky<plucky@echo.engineer> on 2018/1/6 下午6:07.
 * more about me: http://www.1991th.com
 */
class WindowEventDispatcher implements EventDispatcher {

    private Activity mActivity;

    WindowEventDispatcher(Activity activity) {
        mActivity = activity;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return mActivity.getWindow().superDispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return mActivity.getWindow().superDispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return mActivity.getWindow().superDispatchTouchEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return mActivity.getWindow().superDispatchTrackballEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return mActivity.getWindow().superDispatchGenericMotionEvent(event);
    }
}
