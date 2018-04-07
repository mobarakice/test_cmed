package ice.mobarak.test2.ui.dashboard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ice.mobarak.test2.R;
import ice.mobarak.test2.ui.base.BaseFragment;
import ice.mobarak.test2.ui.downloadfile.DownloadFileFragment;
import ice.mobarak.test2.ui.userlist.UserListFragment;
import ice.mobarak.test2.utils.UtilityFunctions;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements IDashboardContract.IView {

    /**
     * Presenter instance
     */
    private IDashboardContract.IPresenter presenter;

    private Button btnGetUserList;
    private Button btnGoToDownload;


    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DashboardPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitleText(getString(R.string.label_text_dashboard));
        setBackArrowVisibility(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initializedView(view);
        setClickListener();
        return view;
    }

    /**
     * Initialized view
     *
     * @param view
     */
    @Override
    protected void initializedView(View view) {
        btnGetUserList = view.findViewById(R.id.btn_user_list);
        btnGoToDownload = view.findViewById(R.id.btn_download);
    }

    /**
     * Set click listener
     */
    @Override
    protected void setClickListener() {
        btnGetUserList.setOnClickListener(v -> presenter.onUserListClicked());
        btnGoToDownload.setOnClickListener(v -> presenter.onDownloadClicked());
    }

    /**
     * Invoke to go to user list screen
     */
    @Override
    public void gotoUserListScreen() {
        UtilityFunctions.changeFragment(getActivity(), new UserListFragment(), true);
    }

    /**
     * Invoke to go to download screen
     */
    @Override
    public void gotoDownloadScreen() {
        UtilityFunctions.changeFragment(getActivity(), new DownloadFileFragment(), true);
    }
}
