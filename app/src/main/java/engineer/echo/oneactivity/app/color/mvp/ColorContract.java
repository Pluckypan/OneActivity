package engineer.echo.oneactivity.app.color.mvp;

import engineer.echo.oneactivity.cmpts.mvp.AbstractPresenter;
import engineer.echo.oneactivity.cmpts.mvp.IModel;
import engineer.echo.oneactivity.cmpts.mvp.IView;

/**
 * ColorContract
 *
 * @author Plucky at 2018/01/06.
 */

public class ColorContract {

    public interface View extends IView {

    }

    public static abstract class Presenter extends AbstractPresenter<View, Model>{

    }

    public interface Model extends IModel {

    }
}
