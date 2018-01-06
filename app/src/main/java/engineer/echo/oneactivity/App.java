package engineer.echo.oneactivity;

import android.app.Application;

/**
 * App
 * Created by Plucky<plucky@echo.engineer> on 2018/1/6 下午10:40.
 * more about me: http://www.1991th.com
 */

public class App extends Application {

    private static Application mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Application getApp() {
        return mApp;
    }
}
