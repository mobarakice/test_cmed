package ice.mobarak.test.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import ice.mobarak.test.R;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Mobarak on 9/18/2017.
 */

public class UtilityFunctions {

    private static ProgressDialog progressDialog;
    /**
     * invoke to change fragment
     *
     * @param activity
     * @param fragment
     * @param isAddToBackStack
     */
    public static void changeFragment(FragmentActivity activity, Fragment fragment, boolean isAddToBackStack) {

        if (activity == null || fragment == null) {
            return;
        }

        FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();
        if (isAddToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container, fragment, Constants.CURRENT_FRAGMENT);

        transaction.commitAllowingStateLoss();
        activity.getSupportFragmentManager().executePendingTransactions();
    }
    @SuppressLint("MissingPermission")
    public static String getDeviceId(Context context) {
        if (context == null) {
            return "";
        }

        boolean isTelephonyService = false;
        PackageManager packageManager = context.getPackageManager();
        // Check the Package Manager isn't exists
        if (packageManager != null) {
            isTelephonyService = packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
        }
        // Check it has Telephony Service
        if (isTelephonyService) {
            // Get the IMEI number
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return "IMEI is :" + telephonyManager.getImei();
            } else {
                return "IMEI is :" + telephonyManager.getDeviceId();
            }
        } else {
            // Get the MAC Address
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            return "MAC Address: " + wifiManager.getConnectionInfo().getMacAddress();
        }

    }




    /**
     * This method convert dp to pixel
     *
     * @param dp
     * @param context
     * @return
     */
    public static float dpToPixel(float dp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static boolean isAlphaNumeric(String aString) {
        String reges = "[A-Za-z0-9]+";
        return aString.matches(reges);
    }


    public static boolean isNumeric(String aString) {
        String reges = "[0-9]+";
        return aString.matches(reges);
    }




    public static void showProgressBar(final FragmentActivity aActivity) {

        try {
            if (progressDialog != null || aActivity == null) {
                return;
            }

            progressDialog = new ProgressDialog(aActivity);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Connecting");
            progressDialog.setCancelable(false);
            progressDialog.show();


        } catch (Exception ex) {

        }

    }

    public static void stopProgressBar() {
        try {

            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception ex) {

        }
    }

    /**
     * This method is checked internet availability's
     *
     * @param context
     * @return true|false
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    /**
     * Invoke get device width
     *
     * @param context
     * @return
     */
    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * Invoke to get device height
     *
     * @param context
     * @return
     */
    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * Invoke to convert dp to pixels
     *
     * @param dp
     * @param context
     * @return
     */
    public static int dpToPx(float dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }



}
