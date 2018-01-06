package engineer.echo.oneactivity.cmpts.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import engineer.echo.oneactivity.cmpts.context.BaseFragment;

/**
 * AbstractMvpFragment
 *
 * @author Plucky at 2018/01/06.
 */

public abstract class AbstractMvpFragment<P extends IPresenter> extends BaseFragment implements IView {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================
    protected P mPresenter;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = providePresenter();
        mPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(provideLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
        initData(savedInstanceState);
        initViews(view);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        super.onDestroyView();
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    /**
     * 设置Presenter
     *
     * @return IPresenter
     */
    protected abstract P providePresenter();

    /**
     * 设置布局文件
     *
     * @return ResId
     */
    protected abstract int provideLayout();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initViews(View view);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
