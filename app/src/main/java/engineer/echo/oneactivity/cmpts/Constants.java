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
            new AnimatorBean("动画·一式", R.color.color_0099FF, EnterOvershootAnimator.class),
            new AnimatorBean("动画·二式", R.color.color_00CCFF, WeChatPageAnimator.class),
            new AnimatorBean("动画·三式", R.color.color_00FFFF, VerticalSlideAnimator.class),
    };
}
