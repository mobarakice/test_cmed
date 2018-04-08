package ice.mobarak.test.data.network;

import android.support.annotation.NonNull;

/**
 * This is singleton ApiManager class and used to manage all API calling
 * Created by Mobarak on 26-Sep-17.
 */

public class ApiManager implements IApiHelper {

    /**
     * Global instance of ApiManager
     */
    private static ApiManager mInstance;

    /**
     * Default private constructor
     */
    private ApiManager() {

    }

    /**
     * Invoke to get ApiManager instance. If instance is null,
     * then create new object otherwise return previous one
     *
     * @return mInstance
     */
    public static synchronized ApiManager getInstance() {
        if (mInstance == null) {
            mInstance = new ApiManager();
        }
        return mInstance;
    }

    /**
     * Invok to call get user list API
     *
     * @param callback IResponseCallback
     */
    @Override
    public void invokeGetUserListAPI(@NonNull IResponseCallback callback) {
        new JsonParser(callback).getMethod(ApiUrls.USER_LIST_URL);
    }
}
