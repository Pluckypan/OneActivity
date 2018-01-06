package engineer.echo.oneactivity.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Host activity of MasterCompatActivity.
 */
public abstract class MasterCompatActivity extends AppCompatActivity implements IMasterActivity {

    private final MasterActivityDelegate mImpl = new MasterActivityDelegate(this);

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mImpl.onCreate(bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mImpl.onSaveInstanceState(outState);
    }

    public FragmentMaster getFragmentMaster() {
        return mImpl.getFragmentMaster();
    }

    @Override
    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        return mImpl.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(@NonNull KeyEvent event) {
        return mImpl.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        return mImpl.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        return mImpl.dispatchGenericMotionEvent(ev);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        return mImpl.dispatchTrackballEvent(ev);
    }
}
