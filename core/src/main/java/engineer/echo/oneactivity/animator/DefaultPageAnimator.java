package engineer.echo.oneactivity.animator;

import android.view.View;

public class DefaultPageAnimator extends PageAnimator {

    /**
     * 默认页面切换动画 不添加任何效果 即ViewPagerCompat默认效果
     */
    private static final String TAG = "DefaultPageAnimator";

    public static final DefaultPageAnimator INSTANCE = new DefaultPageAnimator();

    @Override
    protected void transformBackgroundPage(View page, float position,
                                           boolean enter) {
    }

    @Override
    protected void transformForegroundPage(View page, float position,
                                           boolean enter) {
    }

}
