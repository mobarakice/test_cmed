package ice.mobarak.test2.data;

import android.support.annotation.NonNull;

import ice.mobarak.test2.data.network.ApiManager;
import ice.mobarak.test2.data.network.IApiHelper;
import ice.mobarak.test2.data.network.IResponseCallback;

/**
 * This is singleton DataManager class and used to manipulate all kinds of data
 * Created by Mobarak on 09-Feb-18.
 */

public class DataManager implements IApiHelper {


    /**
     * IApiHelper instance;
     */
    private IApiHelper mApiHelper;

    /**
     * DataManager instance;
     */
    private static DataManager instance;

    /**
     * Default contsructor
     */
    private DataManager() {

        mApiHelper = ApiManager.getInstance();

    }

    /**
     * Invoke to get DataManager instance. If instance is null,
     * then create new object otherwise return previous one
     *
     * @return mInstance
     */
    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null)
                    instance = new DataManager();
            }
        }
        return instance;
    }


    /**
     * Invoke get user list api
     * @param callback
     */
    @Override
    public void invokeGetUserListAPI(@NonNull IResponseCallback callback) {
        mApiHelper.invokeGetUserListAPI(callback);
    }
}
