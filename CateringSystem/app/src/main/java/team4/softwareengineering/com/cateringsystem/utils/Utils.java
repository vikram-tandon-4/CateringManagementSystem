package team4.softwareengineering.com.cateringsystem.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;
import android.view.WindowManager;

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

    public static Dialog showConfirmationDialog(Context mContext)
    {
        Dialog confirmationDialog = new Dialog(mContext, R.style.DialogSlideAnim);
        fillDialogToWindow(mContext, confirmationDialog);
        confirmationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        confirmationDialog.setContentView(R.layout.logout_confirmation_dialog);
        confirmationDialog.setCancelable(false);
        return confirmationDialog;
    }

    public static void fillDialogToWindow(Context context, Dialog dialog)
    {
        if (dialog != null)
        {
            // Grab the window of the dialog, and change the width
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dialog.getWindow();
            lp.copyFrom(window.getAttributes());
            // This makes the dialog take up the full width
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }
}
