package engineer.echo.oneactivity.animator;

import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

public class EnterOvershootAnimator extends PageAnimator {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    private static final Interpolator sInterpolator = new OvershootInterpolator(
            1.0f);

    @Override
    protected void transformBackgroundPage(View page, float position,
                                           boolean enter) {
        page.setVisibility(position == -1 ? View.INVISIBLE : View.VISIBLE);
        int pageWidth = page.getWidth();

        // Counteract the default slide transition
        setTranslationX(page, pageWidth * -position);

        // Fade the page out (between MIN_ALPHA and 1)
        setAlpha(page, MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position));

        // Scale the page down (between MIN_SCALE and 1)
        float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
        setScale(page, scaleFactor);
    }

    @Override
    protected void transformForegroundPage(View page, float position,
                                           boolean enter) {
        float offset = 0;
        if (enter) {
            // Perform overshot animation if enter.
            offset = page.getWidth()
                    * ((1 - position) - sInterpolator
                    .getInterpolation(1 - position));
        }
        setTranslationX(page, offset);
        setAlpha(page, 1);
        setScale(page, 1);
    }
}
