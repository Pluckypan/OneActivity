package engineer.echo.oneactivity.core;

import android.app.Activity;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.annotation.Configuration;

class FragmentContext extends ContextThemeWrapper {

    FragmentContext(IMasterFragment fragment) {
        super(fragment.getActivity(), getMasterFragmentThemeRes(fragment.getActivity(), fragment));
    }

    private static int getMasterFragmentThemeRes(Activity context, IMasterFragment fragment) {
        int themeRes = -1;
        Class clazz = fragment.getClass();
        // Get theme from Configuration annotation.
        if (clazz.isAnnotationPresent(Configuration.class)) {
            Configuration configuration = (Configuration) clazz.getAnnotation(Configuration.class);
            themeRes = configuration.theme();
        }
        // Get theme from Theme attrs.
        if (themeRes == -1) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.MasterFragmentTheme, outValue, true);
            themeRes = outValue.resourceId;
        }
        return themeRes;
    }
}
