package engineer.echo.oneactivity.app.color.fragment;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.app.color.mvp.ColorPresenter;
import engineer.echo.oneactivity.cmpts.context.BaseFragment;
import engineer.echo.oneactivity.cmpts.mvp.AbstractMvpFragment;
import engineer.echo.oneactivity.app.color.mvp.ColorContract;
import engineer.echo.oneactivity.core.Request;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * ColorFragment
 *
 * @author Plucky at 2018/01/06.
 */

public class ColorFragment extends AbstractMvpFragment<ColorContract.Presenter> implements ColorContract.View, View.OnClickListener {
    // ===========================================================
    // Constants
    // ===========================================================
    public static final String KEY_COLOR = "key_for_selected_color";

    // ===========================================================
    // Fields
    // ===========================================================
    private int mColor;
    private TextView mColorTV;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected ColorContract.Presenter providePresenter() {
        return new ColorPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_color;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mColor = getRequest().getIntExtra(KEY_COLOR, -1);
    }

    @Override
    protected void initViews(View view) {
        mColorTV = view.findViewById(R.id.app_color_selected_tv);
        mColorTV.setBackgroundColor(mColor);

        view.findViewById(R.id.app_color_1_tv).setOnClickListener(this);
        view.findViewById(R.id.app_color_2_tv).setOnClickListener(this);
        view.findViewById(R.id.app_color_3_tv).setOnClickListener(this);
        view.findViewById(R.id.app_color_4_tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_color_1_tv:
            case R.id.app_color_2_tv:
            case R.id.app_color_3_tv:
            case R.id.app_color_4_tv:
                onColorClick(v);
                break;
        }
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    public static void openPage(BaseFragment fragment, int color, int code) {
        Request request = new Request(ColorFragment.class);
        request.putExtra(KEY_COLOR, color);
        fragment.startFragmentForResult(request, code);
    }

    private void onColorClick(View view) {
        Request request = new Request();
        Drawable drawable = view.getBackground();
        if (drawable != null && drawable instanceof ColorDrawable) {
            request.putExtra(KEY_COLOR, ((ColorDrawable) drawable).getColor());
        }
        setResult(RESULT_OK, request);
        finish();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
}
