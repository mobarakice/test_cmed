package ice.mobarak.test.ui.test02;

import java.util.List;

import ice.mobarak.test.data.model.User;

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
