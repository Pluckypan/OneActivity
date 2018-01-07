package engineer.echo.oneactivity;

import android.os.Bundle;

import engineer.echo.oneactivity.app.main.fragment.MainFragment;
import engineer.echo.oneactivity.core.FragmentMaster;
import engineer.echo.oneactivity.core.IMasterFragment;
import engineer.echo.oneactivity.core.MasterCompatActivity;
import engineer.echo.oneactivity.core.Request;

public class MainActivity extends MasterCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int DURATION = 600;

    private FragmentMaster mMaster;
    private FragmentMaster.FragmentLifecycleCallbacks mCallBacks = new FragmentMaster.SimpleFragmentLifecycleCallbacks() {
        @Override
        public void onFragmentResumed(IMasterFragment fragment) {
            super.onFragmentResumed(fragment);
            App.LOG(TAG, "onFragmentResumed: " + fragment);
        }

        @Override
        public void onFragmentPaused(IMasterFragment fragment) {
            super.onFragmentPaused(fragment);
            App.LOG(TAG, "onFragmentPaused: " + fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMaster = getFragmentMaster();
        mMaster.setScrollDuration(DURATION);
        mMaster.registerFragmentLifecycleCallbacks(mCallBacks);
        mMaster.install(R.id.app_main_container, new Request(MainFragment.class), true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMaster != null) {
            mMaster.unregisterFragmentLifecycleCallbacks(mCallBacks);
        }
    }
}
