package engineer.echo.oneactivity.app.animator.fragment;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.animator.PageAnimator;
import engineer.echo.oneactivity.animator.VerticalSlideAnimator;
import engineer.echo.oneactivity.app.animator.mvp.AnimatorPresenter;
import engineer.echo.oneactivity.cmpts.beans.AnimatorBean;
import engineer.echo.oneactivity.cmpts.context.BaseFragment;
import engineer.echo.oneactivity.cmpts.mvp.AbstractMvpFragment;
import engineer.echo.oneactivity.app.animator.mvp.AnimatorContract;
import engineer.echo.oneactivity.core.Request;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

/**
 * AnimatorFragment
 *
 * @author Plucky at 2018/01/07.
 */

public class AnimatorFragment extends AbstractMvpFragment<AnimatorContract.Presenter> implements AnimatorContract.View {
    // ===========================================================
    // Constants
    // ===========================================================
    public static final String KEY_ANIMATOR = "key_for_animator";

    // ===========================================================
    // Fields
    // ===========================================================
    private AnimatorBean mAnimatorBean;
    private ConstraintLayout mRoot;
    private TextView mNameTV;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected AnimatorContract.Presenter providePresenter() {
        return new AnimatorPresenter();
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_animator;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAnimatorBean = getRequest().getParcelableExtra(KEY_ANIMATOR);
        // 设置是否允许侧滑
        // TODO: Plucky 2018/1/7 下午1:08  实现滑动的位置Edge:LEFT、RIGHT、TOP、BOTTOM
        allowSwipeBack(mAnimatorBean.getType() != VerticalSlideAnimator.class);
    }

    @Override
    protected void initViews(View view) {
        mRoot = view.findViewById(R.id.app_animator_root);
        mRoot.setBackgroundColor(getResources().getColor(mAnimatorBean.getColor()));
        mNameTV = view.findViewById(R.id.app_animator_name_tv);
        mNameTV.setText(mAnimatorBean.getTitle());
    }

    @Override
    public PageAnimator onCreatePageAnimator() {
        return PageAnimator.getAnimatorByClass(mAnimatorBean.getType());
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    public static void openPage(BaseFragment fragment, AnimatorBean bean) {
        Request request = new Request(AnimatorFragment.class);
        request.putExtra(KEY_ANIMATOR, bean);
        fragment.startFragment(request);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================
}
