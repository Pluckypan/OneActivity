package engineer.echo.oneactivity.animator;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * A PageAnimator is invoked whenever a visible/attached page is scrolled. This
 * offers an opportunity for the application to apply a custom transformation to
 * the page views.
 */
public abstract class PageAnimator {

    public PageAnimator() {

    }

    public PageAnimator(int mDuraiton) {
        this.mDuraiton = mDuraiton;
    }

    /**
     * Apply a transformation to the given page.
     *
     * @param page     Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     *                 page position to the right, and -1 is one page position to the
     *                 left.
     * @param enter    true if the pager is scrolling from item to item+1.
     */
    public void transformPage(View page, float position, boolean enter) {
        if (position <= -1 || position > 1) {
            transformUnderlyingPage(page, position, enter);
        } else if (position <= 0) { // （-1,0]
            page.setVisibility(View.VISIBLE);
            transformBackgroundPage(page, position, enter);
        } else if (position <= 1) { // (0,1]
            page.setVisibility(View.VISIBLE);
            transformForegroundPage(page, position, enter);
        }
    }

    /**
     * In this stage, transform the background page.
     *
     * @param position (-1,0]
     */
    protected abstract void transformBackgroundPage(View page, float position,
                                                    boolean enter);

    /**
     * In this stage, transform the foreground page.
     *
     * @param position (0,1]
     */
    protected abstract void transformForegroundPage(View page, float position,
                                                    boolean enter);

    /**
     * 设置页面动画执行时间 如 mDuraiton>0 则生效
     */
    private int mDuraiton;

    public int getDuraiton() {
        return mDuraiton;
    }

    public void setDuraiton(int duraiton) {
        this.mDuraiton = duraiton;
    }

    protected void transformUnderlyingPage(View page, float position, boolean enter) {
        page.setAlpha(1);
        page.setScaleX(1);
        page.setScaleY(1);
        page.setTranslationX(0);
        page.setTranslationY(0);
        // Avoid overdraw
        page.setVisibility(View.INVISIBLE);
    }


    public static void setScaleX(View view, float scale) {
        ViewCompat.setScaleX(view, scale);
    }

    public static void setScaleY(View view, float scale) {
        ViewCompat.setScaleY(view, scale);
    }

    public static void setScale(View view, float scale) {
        setScaleX(view, scale);
        setScaleY(view, scale);
    }

    public static void setTranslationX(View view, float x) {
        ViewCompat.setTranslationX(view, x);
    }

    public static void setTranslationY(View view, float y) {
        ViewCompat.setTranslationY(view, y);
    }

    public static void setAlpha(View view, float alpha) {
        ViewCompat.setAlpha(view, alpha);
    }

    public static void setRotation(View view, float rotation) {
        ViewCompat.setRotation(view, rotation);
    }

    public static void setRotationX(View view, float x) {
        ViewCompat.setRotationX(view, x);
    }

    public static void setRotationY(View view, float y) {
        ViewCompat.setRotationY(view, y);
    }

    public static void setPivotX(View view, float x) {
        ViewCompat.setPivotX(view, x);
    }

    public static void setPivotY(View view, float y) {
        ViewCompat.setPivotY(view, y);
    }

    public static PageAnimator getAnimatorByClass(Class cls) {
        return getAnimatorByClass(cls, 0);
    }

    public static PageAnimator getAnimatorByClass(Class cls, int duration) {
        if (cls != null) {
            if (cls == EnterOvershootAnimator.class) {
                return new EnterOvershootAnimator(duration);
            } else if (cls == VerticalSlideAnimator.class) {
                return new VerticalSlideAnimator(duration);
            } else if (cls == WeChatPageAnimator.class) {
                return new WeChatPageAnimator(duration);
            } else {
                return new DefaultPageAnimator(duration);
            }
        } else {
            return new DefaultPageAnimator(duration);
        }
    }

}
