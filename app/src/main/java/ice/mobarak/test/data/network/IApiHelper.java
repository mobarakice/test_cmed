package ice.mobarak.test.data.network;

import android.support.annotation.NonNull;


/**
 * This is a API helper interface
 * Created by Mobarak on 07-April-18.
 */

public interface IApiHelper {


    void invokeGetUserListAPI(@NonNull IResponseCallback callback);

}
