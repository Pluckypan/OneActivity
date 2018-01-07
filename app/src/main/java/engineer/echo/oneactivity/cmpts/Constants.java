package engineer.echo.oneactivity.cmpts;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.animator.EnterOvershootAnimator;
import engineer.echo.oneactivity.animator.VerticalSlideAnimator;
import engineer.echo.oneactivity.animator.WeChatPageAnimator;
import engineer.echo.oneactivity.cmpts.beans.AnimatorBean;

/**
 * Constants
 * Created by Plucky<plucky@echo.engineer> on 2018/1/7 上午11:55.
 * more about me: http://www.1991th.com
 */

public class Constants {
    public static AnimatorBean[] ANIMATORS = {
            new AnimatorBean("动画·一式", R.color.color_00CCFF, WeChatPageAnimator.class, 500),
            new AnimatorBean("动画·二式", R.color.color_0099FF, EnterOvershootAnimator.class, 1000),
            new AnimatorBean("动画·三式", R.color.color_00FFFF, VerticalSlideAnimator.class, 2000),
    };

    public static final int TRANSITION_TIME = 600;
    public static final int TRANSITION_TIME_SHORT = 400;
    public static final int TRANSITION_TIME_LONG = 800;
}
