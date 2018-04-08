package ice.mobarak.test.data.network;

/**
 * Created by Mobarak on 26-Sep-17.
 */

public interface ApiUrls {

    /**
     * User list API URL
     */
    String USER_LIST_URL = "http://dropbox.sandbox2000.com/intrvw/users.json";
    /**
     * Base photo url
     */
    String PHOTO_BASE_URL = "https://randomuser.me/api/portraits/";
    /**
     * Men's Photo url
     */
    String MEN_PHOTO_URL = PHOTO_BASE_URL + "men/";
    /**
     * Women's Photo url
     */
    String WOMEN_PHOTO_URL = PHOTO_BASE_URL + "women/";
}
