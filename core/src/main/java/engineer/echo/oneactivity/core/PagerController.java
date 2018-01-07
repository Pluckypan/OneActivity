package engineer.echo.oneactivity.core;

import engineer.echo.oneactivity.animator.PageAnimator;

public interface PagerController {

    void allowSwipeBack(boolean allowSwipeBack);

    boolean allowSwipeBack();

    void setPageAnimator(PageAnimator pageAnimator);

    PageAnimator getPageAnimator();

    boolean hasPageAnimator();
}
