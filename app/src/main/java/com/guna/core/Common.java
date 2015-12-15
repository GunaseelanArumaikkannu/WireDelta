package com.guna.core;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Gunaseelan on 10-12-2015.
 * This class is used to do common operations in all activities.
 */
public class Common {
    private Context context;
    private ProgressDialog dialog;

    public Common(Context context) {
        this.context = context;
    }

    public void showProgressDialog(String text) {
        this.dialog = new ProgressDialog(this.context);//Assuming that you are using fragments.
        this.dialog.setMessage(text);
        this.dialog.setIndeterminate(true);
        this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.dialog.show();
    }

    public void hideProgressDialog() {
        if (dialog != null)
            this.dialog.hide();
    }

    public void toast(String message) {
        Toast toast = Toast.makeText(this.context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                0, 0);
        toast.show();
    }

}