package engineer.echo.oneactivity.animator;

import android.view.View;

public class WeChatPageAnimator extends PageAnimator {

    /**
     * 添加WeChat侧滑效果
     * WeChat在侧滑时,背景的滑动幅度很小，这样就存在一个视差效果
     * 需要注意的是，因为背景Fragment其实是一直存在的,所以前景Fragment需要设置背景
     */
    private static final String TAG = "WeChatPageAnimator";

    private static final float SPEED = 0.8f;

    @Override
    protected void transformBackgroundPage(View page, float position,
                                           boolean enter) {
        int _width = page.getWidth();
        float _x = -SPEED * position * _width;
        setTranslationX(page, _x);
    }

    @Override
    protected void transformForegroundPage(View page, float position,
                                           boolean enter) {
    }

}
