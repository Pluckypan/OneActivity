package engineer.echo.oneactivity;

import android.app.Application;
import android.util.Log;

/**
 * App
 * Created by Plucky<plucky@echo.engineer> on 2018/1/6 下午10:40.
 * more about me: http://www.1991th.com
 */

public class App extends Application {

    private static Application mApp;
    public static final boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Application getApp() {
        return mApp;
    }

    public static void LOG(String TAG, String msg) {
        if (DEBUG) {
            Log.d(TAG, "LOG: " + msg);
        }
    }
}
