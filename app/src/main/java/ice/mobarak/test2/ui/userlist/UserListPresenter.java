package ice.mobarak.test2.ui.userlist;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ice.mobarak.test2.data.DataManager;
import ice.mobarak.test2.data.model.User;
import ice.mobarak.test2.data.network.IResponseCallback;
import ice.mobarak.test2.data.network.JsonUtil;
import ice.mobarak.test2.utils.Constants;

public class UserListPresenter implements IUserListContract.IPresenter, IResponseCallback {

    private String TAG = UserListPresenter.class.getSimpleName();
    private IUserListContract.IView view;

    public UserListPresenter(IUserListContract.IView view) {
        this.view = view;
    }

    @Override
    public void invokeGetUserListAPI() {
        Log.d(TAG, "invokeGetUserListAPI called");
        view.showProgressDialog();
        DataManager.getInstance().invokeGetUserListAPI(this);
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, "onResponse called");
        try {
            if (response != null) {
                Log.d(TAG, "respnse: " + response.toString());
                JSONArray jsonArray = response.getJSONArray(Constants.USERS);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    User user = new User();
                    user.setFirstName(JsonUtil.getParseString(object, Constants.FIRST_NAME));
                    user.setLastName(JsonUtil.getParseString(object, Constants.LAST_NAME));

                    // Mobile
                    JSONObject phones = JsonUtil.getParseJsonObject(object, Constants.PHONES);
                    user.setMobile(JsonUtil.getParseString(phones, Constants.MOBILE));

                    user.setGender(JsonUtil.getParseString(object, Constants.GENDER));
                    user.setPhotoId(JsonUtil.getParseInt(object, Constants.PHOTO));

                    view.addUser(user);
                }
            }
        } catch (JSONException e) {

        } finally {
            view.hideProgressDialog();
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(TAG, "onErrorResponse called");
        view.hideProgressDialog();
    }
}
