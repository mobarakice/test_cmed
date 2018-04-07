package ice.mobarak.test2.data.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mobarak 09/02/2018.
 */

public class JsonParser {

    private IResponseCallback mCallback;

    public JsonParser(IResponseCallback mCallback) {
        this.mCallback = mCallback;
    }


    /**
     * Invoke to call http GET method
     *
     * @param url
     */
    public synchronized void getMethod(String url) {
        if (url == null) {
            return;
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> mCallback.onResponse(response), error -> mCallback.onErrorResponse(error));

        objectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance().addToRequestQueue(objectRequest);
    }


}
