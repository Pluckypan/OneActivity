package engineer.echo.oneactivity.animator;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * FixedSpeedScroller
 * Created by Plucky<plucky@echo.engineer> on 2018/1/7 下午6:26.
 * more about me: http://www.1991th.com
 */

public class FixedSpeedScroller extends Scroller {

    public int mPageDuration = -1;

    public FixedSpeedScroller(Context context) {
        super(context);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        if (mPageDuration > 0) {
            startScroll(startX, startY, dx, dy, mPageDuration);
        } else {
            super.startScroll(startX, startY, dx, dy);
        }
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        if (mPageDuration > 0) {
            super.startScroll(startX, startY, dx, dy, mPageDuration);
        } else {
            super.startScroll(startX, startY, dx, dy, duration);
        }
    }

    public int getPageDuration() {
        return mPageDuration;
    }

    public void setPageDuration(int duration) {
        mPageDuration = duration;
    }
}