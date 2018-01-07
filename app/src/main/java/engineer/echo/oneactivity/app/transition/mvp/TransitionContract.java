package engineer.echo.oneactivity.app.transition.mvp;

import engineer.echo.oneactivity.cmpts.mvp.AbstractPresenter;
import engineer.echo.oneactivity.cmpts.mvp.IModel;
import engineer.echo.oneactivity.cmpts.mvp.IView;

/**
 * TransitionContract
 *
 * @author Plucky at 2018/01/07.
 */

public class TransitionContract {

    public interface View extends IView {

    }

    public static abstract class Presenter extends AbstractPresenter<View, Model>{

    }

    public interface Model extends IModel {

    }
}
