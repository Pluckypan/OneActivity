package engineer.echo.oneactivity.app.softinput.fragment;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.annotation.Configuration;
import engineer.echo.oneactivity.app.softinput.mvp.SoftInputPresenter;
import engineer.echo.oneactivity.cmpts.context.BaseFragment;
import engineer.echo.oneactivity.cmpts.mvp.AbstractMvpFragment;
import engineer.echo.oneactivity.app.softinput.mvp.SoftInputContract;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * SoftInputFragment
 *
 * @author Plucky at 2018/01/07.
 */
@Configuration(theme = R.style.Theme_AppCompat_DayNight)
public class SoftInputFragment extends AbstractMvpFragment<SoftInputContract.Presenter> implements SoftInputContract.View {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================
    private RadioGroup mGroup;
    private EditText mMessage;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected SoftInputContract.Presenter providePresenter() {
        return new SoftInputPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_softinput;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews(View view) {
        mGroup = view.findViewById(R.id.app_softinput_rg);
        mMessage = view.findViewById(R.id.app_softinput_message);
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mMessage.setText(String.valueOf(checkedId));
                if (checkedId == R.id.app_softinput_resize) {
                    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                } else {
                    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                }
            }
        });
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    public static void openPage(BaseFragment fragment) {
        fragment.startFragment(SoftInputFragment.class);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
}
