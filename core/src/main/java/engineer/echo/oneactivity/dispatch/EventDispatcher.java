package engineer.echo.oneactivity.dispatch;

import android.view.KeyEvent;
import android.view.MotionEvent;

public interface EventDispatcher {

    boolean dispatchKeyEvent(KeyEvent event);

    boolean dispatchKeyShortcutEvent(KeyEvent event);

    boolean dispatchTouchEvent(MotionEvent event);

    boolean dispatchTrackballEvent(MotionEvent event);

    boolean dispatchGenericMotionEvent(MotionEvent event);
}


