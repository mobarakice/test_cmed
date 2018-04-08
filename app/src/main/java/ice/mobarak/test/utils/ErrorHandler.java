package ice.mobarak.test.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;

import ice.mobarak.test.R;


/**
 * Created by Mobarak on 27-Sep-17.
 */

public class ErrorHandler {

    public static String getErrorMessage(Context context, VolleyError aError) {
        String errorMessage = "";
        if (isNetworkProblem(aError)) {
            errorMessage = "Need to error message";
        } else {
            errorMessage = "Need to error message";
        }
        Log.e("ErrorMessage", errorMessage);
        return errorMessage;

    }

    private static boolean isNetworkProblem(VolleyError error) {
        return (error instanceof NetworkError || error instanceof NoConnectionError);
    }

    /**
     * Invoke to showing dialog for showing error message
     *
     * @param context
     * @param error
     */
    public static void showDialog(final Context context, VolleyError error) {

        if (context == null) {
            return;
        }
        final String errorMsg = getErrorMessage(context, error);
        Log.i("Error-MSG:", " " + errorMsg);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.error_pop_up, null);
        TextView tvShowError = dialogView.findViewById(R.id.tv_show_error);
        TextView btnOk = dialogView.findViewById(R.id.btn_error_ok);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final Dialog errorDialog = builder.setView(dialogView).create();

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(errorDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        tvShowError.setText(errorMsg);
        errorDialog.setCancelable(false);
        errorDialog.getWindow().setAttributes(params);
        btnOk.setOnClickListener(v -> {
            errorDialog.dismiss();
        });

        errorDialog.show();

    }

}
