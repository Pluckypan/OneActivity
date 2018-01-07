package engineer.echo.oneactivity.cmpts.context;

import engineer.echo.oneactivity.core.MasterFragment;

/**
 * BaseFragment
 * Created by Plucky<plucky@echo.engineer> on 2018/1/6 下午11:03.
 * more about me: http://www.1991th.com
 */

public class BaseFragment extends MasterFragment {
    public final String TAG = getClass().getSimpleName();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
