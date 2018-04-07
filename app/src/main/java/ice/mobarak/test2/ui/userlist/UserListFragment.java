package ice.mobarak.test2.ui.userlist;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ice.mobarak.test2.R;
import ice.mobarak.test2.data.model.User;
import ice.mobarak.test2.ui.base.BaseFragment;
import ice.mobarak.test2.utils.UtilityFunctions;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends BaseFragment implements IUserListContract.IView {

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private UserListAdapter adapter;
    private IUserListContract.IPresenter presenter;


    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialized presenter & call user list api
        presenter = new UserListPresenter(this);
        presenter.invokeGetUserListAPI();
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitleText(getString(R.string.label_text_users));
        setBackArrowVisibility(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        initializedView(view);
        setClickListener();
        return view;
    }

    @Override
    protected void initializedView(View view) {
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new UserListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addUser(User user) {
//        new Handler().post(() -> adapter.addUser(user));
        adapter.addUser(user);
    }

    @Override
    public void addUsers(List<User> users) {
        adapter.addUsers(users);
    }

    @Override
    public void showProgressDialog() {
        UtilityFunctions.showProgressBar(getActivity());
    }

    @Override
    public void hideProgressDialog() {
        UtilityFunctions.stopProgressBar();
    }
}
