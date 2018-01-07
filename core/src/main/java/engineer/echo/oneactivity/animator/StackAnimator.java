package engineer.echo.oneactivity.animator;

import android.view.View;

public class StackAnimator extends PageAnimator {
    private static final float MIN_ALPHA = 0.5f;
    private static final float TRANSLATION_FACTOR = 0.65f;

    @Override
    protected void transformBackgroundPage(View page, float position,
                                           boolean enter) {
        page.setVisibility(position == -1 ? View.INVISIBLE : View.VISIBLE);
        int pageWidth = page.getWidth();
        setTranslationX(page, pageWidth
                * (TRANSLATION_FACTOR * -position));
        // Fade the page out (between MIN_ALPHA and 1)
        setAlpha(page, MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position));
    }

    @Override
    protected void transformForegroundPage(View page, float position,
                                           boolean enter) {
        setTranslationX(page, 0);
        setAlpha(page, 1);
    }

}
