package com.rabbitdigital.chofficerapplication.presentation.globalutilities.singleton;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.ViewGroup;

import com.rabbitdigital.chofficerapplication.R;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


/**
 * Created by DP on 22/09/2017.
 * this class maintain all single instance related methods like dialog.toast..etc
 */

public class SingletonClass {

    private static SingletonClass INSTANCE = null;

    private SingletonClass() {
    }

    public static SingletonClass getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonClass();
        }
        return INSTANCE;
    }


    public boolean isDeviceOnline(Context context) throws Exception {
        try {
            final ConnectivityManager conMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                // notify user you are online
                return true;
            } else {
                // notify user you are not online
                return false;
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void showDefaultDialogWithFinish(final Activity context, String title, String messag) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage(messag)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        context.finish();
                    }
                });


        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(title);
        alert.show();
    }

    public void shotToast(Activity activity, int messageRef, Style style, ViewGroup view, int duration, boolean isFromTabedActivity) {
        if (duration == 0) {
            duration = Configuration.DURATION_LONG;
        }

        if (view != null) {
            Crouton.makeText(activity, messageRef, style, view).setConfiguration(new Configuration.Builder()
                    .setDuration(duration).build()).show();
            //  Crouton.make(activity, customStyle, view).show();
        } else {
            Crouton.makeText(activity, messageRef, style)
                    .setConfiguration(new Configuration.Builder().setDuration(duration).build()).show();
        }
    }

    public void shotToast(Activity activity, String messageRef, Style style, ViewGroup view, int duration, boolean isFromTabedActivity) {
        if (duration == 0) {
            duration = Configuration.DURATION_LONG;
        }

        if (view != null) {
            Crouton.makeText(activity, messageRef, style, view).setConfiguration(new Configuration.Builder()
                    .setDuration(duration).build()).show();

        } else {
            Crouton.makeText(activity, messageRef, style)
                    .setConfiguration(new Configuration.Builder().setDuration(duration).build()).show();
        }
    }

    public void showDefaultDialog(Activity context, String title, String messag) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage(messag)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(title);
        alert.show();
    }

    public static AlertDialog.Builder getAlertdialog(Context context, String message, String positiveButtonMessage, DialogInterface.OnClickListener positiveButtonOnClickListener,
                                                     String negativeButtonMessage, DialogInterface.OnClickListener negativeButtonOnClickListener, boolean isCancelable) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
        dlgAlert.setMessage(message);
        if (positiveButtonMessage != null && !positiveButtonMessage.isEmpty()) {
            dlgAlert.setPositiveButton(positiveButtonMessage, positiveButtonOnClickListener);
        }
        if (negativeButtonMessage != null && !negativeButtonMessage.isEmpty()) {
            dlgAlert.setNegativeButton(negativeButtonMessage, negativeButtonOnClickListener);
        }
        dlgAlert.setCancelable(isCancelable);
        return dlgAlert;
    }
    public static AlertDialog.Builder getAlertdialog(Context context, String message) {
        return getAlertdialog(context, message, context.getResources().getString(android.R.string.ok), null, "", null, true);
    }

    public static AlertDialog.Builder getAlertdialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = getAlertdialog(context, message, context.getResources().getString(android.R.string.ok), null, "", null, true);
        alertDialog.setTitle(title);
        return alertDialog;
    }

}
