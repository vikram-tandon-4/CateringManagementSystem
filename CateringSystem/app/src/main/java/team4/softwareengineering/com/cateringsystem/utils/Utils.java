package team4.softwareengineering.com.cateringsystem.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;

import team4.softwareengineering.com.cateringsystem.R;


public class Utils {

    /**
     * display progress dialog
     *
     * @param pContext
     * @return
     */
    public static Dialog showProgressDialog(Context pContext) {
        Dialog progressDialog = new Dialog(pContext);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.progress_layout);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    /**
     * cancel progress dialog
     *
     * @param progressDialog
     */
    public static void cancelProgressDialog(Dialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
