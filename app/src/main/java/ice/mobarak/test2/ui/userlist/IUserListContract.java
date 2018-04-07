package ice.mobarak.test2.ui.userlist;

import java.util.List;

import ice.mobarak.test2.data.model.User;

/**
 * This is mvp contract interface
 */
public interface IUserListContract {

    interface IView {

        void addUser(User user);

        void addUsers(List<User> users);

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface IPresenter {
        void invokeGetUserListAPI();
    }
}
