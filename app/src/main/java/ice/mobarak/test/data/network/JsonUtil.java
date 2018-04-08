package ice.mobarak.test.data.network;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;

/**
 * Created by Mobarak on 08-Jun-17.
 */

public class JsonUtil {

    /**
     * String tag
     */
    private static final String TAG = "RequestBody";

    /**
     * Invoke to parse json string
     *
     * @param object json object(JSONObject)
     * @param key    String->end point
     * @return
     * @throws JSONException
     */
    public static String getParseString(JSONObject object, String key) throws JSONException {
        return (object != null && object.has(key)) ? object.getString(key) : "";
    }

    /**
     * Invoke to parse json double
     *
     * @param object json object(JSONObject)
     * @param key    String->end point
     * @return
     * @throws JSONException
     */
    public static double getParseDouble(JSONObject object, String key) throws JSONException {
        return (object != null && object.has(key)) ? object.getDouble(key) : 0.0;
    }

    /**
     * Invoke to parse json integer
     *
     * @param object json object(JSONObject)
     * @param key    String->end point
     * @return
     * @throws JSONException
     */
    public static int getParseInt(JSONObject object, String key) throws JSONException {
        return (object != null && object.has(key)) ? object.getInt(key) : 0;
    }

    /**
     * Invoke to parse json integer
     *
     * @param object json object(JSONObject)
     * @param key    String->end point
     * @return
     * @throws JSONException
     */
    public static boolean getParseBoolean(JSONObject object, String key) throws JSONException {
        return (object != null && object.has(key)) ? object.getBoolean(key) : false;
    }

    /**
     * Invoke to parse json integer
     *
     * @param object json object(JSONObject)
     * @param key    String->end point
     * @return
     * @throws JSONException
     */
    public static BigInteger getParseBigInt(JSONObject object, String key) throws JSONException {
        return (object != null && object.has(key)) ? new BigInteger(object.getString(key)) : BigInteger.ZERO;
    }

    /**
     * Invoke to parse json long
     *
     * @param object json object(JSONObject)
     * @param key    String->end point
     * @return
     * @throws JSONException
     */
    public static long getParseLong(JSONObject object, String key) throws JSONException {
        return (object != null && object.has(key)) ? object.getLong(key) : 0;
    }

    /**
     * Invoke to parse json array(JSONArray)
     *
     * @param object json object(JSONObject)
     * @param key    String->end point
     * @return
     * @throws JSONException
     */
    public static JSONArray getParseJsonArray(JSONObject object, String key) throws JSONException {
        return (object != null && object.has(key)) ? object.getJSONArray(key) : null;
    }

    /**
     * Invoke to parse json object(JSONObject)
     *
     * @param object json object(JSONObject)
     * @param key    String->end point
     * @return
     * @throws JSONException
     */
    public static JSONObject getParseJsonObject(JSONObject object, String key) throws JSONException {
        return (object != null && object.has(key)) ? object.getJSONObject(key) : null;
    }


//    /**
//     * Invoke to generate json object for vendor logout
//     *
//     * @param vendorId
//     * @return
//     */
//    public static JSONObject getVendorCommonJsonObject(String vendorId) {
//        JSONObject object = new JSONObject();
//
//        try {
//            JSONObject venObject = new JSONObject();
//            venObject.put(Constants.ID, vendorId);
//            object.put(Constants.USER, venObject);
//            object.put(Constants.TOKEN, DataManager.getInstance().getAuthToken() != null ? DataManager.getInstance().getAuthToken() : "");
//            object.put(Constants.TERMINAL_ID, DataManager.getInstance().getTerminalId() == null ? "" : DataManager.getInstance().getTerminalId());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Log.i(TAG, "" + object.toString());
//        return object;
//
//    }


}
