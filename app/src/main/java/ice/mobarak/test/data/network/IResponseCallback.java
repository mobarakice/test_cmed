package ice.mobarak.test.data.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * This is Network response callback
 * Created by Mobarak on 09/02/2018.
 */

public interface IResponseCallback {

    void onResponse(JSONObject response);

    void onErrorResponse(VolleyError error);

}
