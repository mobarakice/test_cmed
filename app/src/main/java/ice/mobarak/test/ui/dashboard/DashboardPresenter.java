package ice.mobarak.test.ui.dashboard;

/**
 * This is presenter class. In this class, all business logic will be implemented.
 * Created by Mobarak on 07 April, 2018
 */
public class DashboardPresenter implements IDashboardContract.IPresenter {

    /**
     * View object
     */
    private IDashboardContract.IView view;

    /**
     * Constructor
     *
     * @param view
     */
    public DashboardPresenter(IDashboardContract.IView view) {
        this.view = view;
    }

    /**
     * Invoke when click on user list button
     */
    @Override
    public void onUserListClicked() {
        if (view != null) {
            view.gotoUserListScreen();
        }
    }
    /**
     * Invoke when click on download button
     */
    @Override
    public void onDownloadClicked() {
        if (view != null) {
            view.gotoDownloadScreen();
        }
    }
}
