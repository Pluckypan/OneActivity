package engineer.echo.oneactivity.app.main.fragment;

import engineer.echo.oneactivity.App;
import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.app.animator.fragment.AnimatorFragment;
import engineer.echo.oneactivity.app.color.fragment.ColorFragment;
import engineer.echo.oneactivity.app.main.mvp.MainPresenter;
import engineer.echo.oneactivity.app.softinput.fragment.SoftInputFragment;
import engineer.echo.oneactivity.cmpts.Constants;
import engineer.echo.oneactivity.cmpts.beans.AnimatorBean;
import engineer.echo.oneactivity.cmpts.mvp.AbstractMvpFragment;
import engineer.echo.oneactivity.app.main.mvp.MainContract;
import engineer.echo.oneactivity.core.Request;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;

/**
 * MainFragment
 *
 * @author Plucky at 2018/01/06.
 */

public class MainFragment extends AbstractMvpFragment<MainContract.Presenter> implements MainContract.View, View.OnClickListener {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final int COLOR_CODE = 0x001;

    // ===========================================================
    // Fields
    // ===========================================================
    private TextView mColorTV;
    private AutoFlowLayout<AnimatorBean> mAnimatorFlow;
    private LayoutInflater mInfalter;
    private int mColor;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected MainContract.Presenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mColor = getResources().getColor(R.color.color_666699);
    }

    @Override
    protected void initViews(View view) {
        mColorTV = view.findViewById(R.id.app_main_color_tv);
        mColorTV.setOnClickListener(this);
        onColorSelected(mColor);
        mInfalter = LayoutInflater.from(App.getApp());
        mAnimatorFlow = view.findViewById(R.id.app_main_animator);
        mAnimatorFlow.setAdapter(new FlowAdapter<AnimatorBean>(Constants.ANIMATORS) {
            @Override
            public View getView(int i) {
                final AnimatorBean item = Constants.ANIMATORS[i];
                View layout = mInfalter.inflate(R.layout.item_animator, null);
                TextView textView = layout.findViewById(R.id.app_main_animator_item_tv);
                textView.setText(item.getTitle());
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AnimatorFragment.openPage(MainFragment.this, item);
                    }
                });
                textView.setBackgroundColor(getResources().getColor(item.getColor()));
                return layout;
            }
        });
        view.findViewById(R.id.app_main_softinput_tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_main_color_tv:
                ColorFragment.openPage(this, mColor, COLOR_CODE);
                break;
            case R.id.app_main_softinput_tv:
                SoftInputFragment.openPage(this);
                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Request data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case COLOR_CODE:
                    onColorSelected(data.getIntExtra(ColorFragment.KEY_COLOR, mColor));
                    break;
            }
        }
    }

    @Override
    public void onColorSelected(int color) {
        mColor = color;
        mColorTV.setBackgroundColor(mColor);
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
}
