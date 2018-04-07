package ice.mobarak.test2.ui.dashboard;

/**
 * This is mvp contract interface.
 * Created by Mobarak on 07 April, 2018
 */

public interface IDashboardContract {

    // MVP  view
    interface IView {

        void gotoUserListScreen();

        void gotoDownloadScreen();
    }

    // MVP  presenter
    interface IPresenter {

        void onUserListClicked();

        void onDownloadClicked();
    }

}
