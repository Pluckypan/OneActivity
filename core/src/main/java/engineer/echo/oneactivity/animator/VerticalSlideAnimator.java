package engineer.echo.oneactivity.animator;

import android.view.View;

public class VerticalSlideAnimator extends PageAnimator {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    protected void transformBackgroundPage(View page, float position,
                                           boolean enter) {
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
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        setTranslationX(page, pageWidth * -position);
        setTranslationY(page, pageHeight * position);
        setAlpha(page, 1);
        setScale(page, 1);
    }
}
