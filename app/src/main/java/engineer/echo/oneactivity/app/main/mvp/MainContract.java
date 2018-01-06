package engineer.echo.oneactivity.app.main.mvp;

import engineer.echo.oneactivity.cmpts.mvp.AbstractPresenter;
import engineer.echo.oneactivity.cmpts.mvp.IModel;
import engineer.echo.oneactivity.cmpts.mvp.IView;

/**
 * MainContract
 *
 * @author Plucky at 2018/01/06.
 */

public class MainContract {

    public interface View extends IView {
        void onColorSelected(int color);
    }

    public static abstract class Presenter extends AbstractPresenter<View, Model> {

    }

    public interface Model extends IModel {

    }
}
