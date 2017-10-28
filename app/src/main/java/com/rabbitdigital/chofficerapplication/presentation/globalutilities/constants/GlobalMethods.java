package com.rabbitdigital.chofficerapplication.presentation.globalutilities.constants;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rabbitdigital.chofficerapplication.R;
import com.rabbitdigital.chofficerapplication.presentation.globalutilities.singleton.SingletonClass;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Administrator on 21-09-2017.
 */

public class GlobalMethods {

    public static boolean isOnline(Context context) throws Exception {
        try {
            return SingletonClass.getInstance().isDeviceOnline(context);
        } catch (Exception e) {
            throw e;
        }

    }

    public static boolean isNull(String data) {

        boolean isnull = false;
        if (data != null) {
            if (!data.equals("") && !data.equals("null") && data != null
                    && !data.equals("-1")) {
                isnull = true;
            }
        } else {
            isnull = false;
        }

        return isnull;
    }

    public static boolean isNullCheckWithZero(String data) {

        boolean isnull = false;
        if (data != null) {
            if (!data.equals("") && !data.equals("null") && data != null
                    && !data.equals("-1") && !data.equals("0")) {
                isnull = true;
            }
        } else {
            isnull = false;
        }

        return isnull;
    }


    public static boolean isValidJson(String test) {

        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            return false;
        }
        return true;
    }

    public static boolean isValidJsonArray(String test) {

        try {
            new JSONArray(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...

            return false;
        }
        return true;
    }

    public static ProgressDialog showProgress(Context context) {

        ProgressDialog progress = ProgressDialog.show(context, null, null);
//        progress.setMessage(message);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setContentView(R.layout.progress_layout);
        progress.setCancelable(false);
        progress.show();
        return progress;


    }

    public static ProgressDialog showProgressWithCancelable(Context context) {

        ProgressDialog progress = ProgressDialog.show(context, null, null);
//        progress.setMessage(message);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setContentView(R.layout.progress_layout);
        progress.setCancelable(true);
        progress.show();
        return progress;


    }

    public static void hideProgress(ProgressDialog dialog) {

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * documentation
     *
     * @param activity   represents activity reference
     * @param messageRef is string reference id
     * @param style      is indicates style of toast this values should be follows  Style.INFO,Style.ALERT and Style.CONFIRM,
     * @param view       parameter indicates show the position of the toast if it is null its show defauls position
     * @param duration   paramenter indicates show the duration of the toast this values as follows Configuration.DURATION_LONG,Configuration.DURATION_SHORT,Configuration.DURATION_LINFINITE
     */

    public static void showToast(Activity activity, int messageRef, Style style, ViewGroup view, int duration, boolean isFromTabActivity) throws Exception {
        try {
            SingletonClass.getInstance().shotToast(activity, messageRef, style, view, duration, isFromTabActivity);
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * documentation
     *
     * @param activity represents activity reference
     * @param message  is string message in String formate
     * @param style    is indicates style of toast this values should be follows  Style.INFO,Style.ALERT and Style.CONFIRM,
     * @param view     parameter indicates show the position of the toast if it is null its show defauls position
     * @param duration paramenter indicates show the duration of the toast this values as follows Configuration.DURATION_LONG,Configuration.DURATION_SHORT,Configuration.DURATION_LINFINITE
     */

    public static void showToast(Activity activity, String message, Style style, ViewGroup view, int duration, boolean isFromTabActivity) throws Exception {
        try {
            SingletonClass.getInstance().shotToast(activity, message, style, view, duration, isFromTabActivity);
        } catch (Exception e) {
            throw e;
        }

    }


    public static String getDeviceUUID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getStateName(Context context, Location location) {
        try {
            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String countryName = "";
            if (addresses.size() > 0) {
                countryName = addresses.get(0).getAdminArea();
            }
            return countryName;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getCountryName(Context context, Location location) {
        try {
            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String countryName = "";
            if (addresses.size() > 0) {
                countryName = addresses.get(0).getCountryName();
            }
            return countryName;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDeviceID(Context applicationContext) {
        if (applicationContext != null) {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
                return telephonyManager.getDeviceId();
            } else {
                return "Permission Not Granted";
            }
        } else {
            return "";
        }

    }

    public static String getDeviceSimNo(Context applicationContext) {
        if (applicationContext != null) {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
                return telephonyManager.getSimSerialNumber();
            } else {
                return "Permission Not Granted";
            }
        } else {
            return "";
        }
    }

    public static String getDeviceMobileNumber(Context applicationContext) {
        if (applicationContext != null) {
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
                return telephonyManager.getLine1Number();
            } else {
                return "Permission Not Granted";
            }
        } else {
            return "";
        }
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }


    public static String getCurrentDate() {
        // TODO Auto-generated method stub
        SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date(System.currentTimeMillis());
        return smdf.format(d);
    }

    public static void setStatusBarColor(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

//    public static boolean checkPermistion(Context applicationContext, String accessCoarseLocation) {
//        if (applicationContext != null) {
//            if (Build.VERSION.SDK_INT >= 23) {
//                return ContextCompat.checkSelfPermission(applicationContext, accessCoarseLocation) != PackageManager.PERMISSION_GRANTED;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//
//    }

    public static void callForWordActivity(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }

    public static void callBackWordActivity(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }

    public static void callFinishForBackWordActivity(Context context, boolean isForwardAnimation) {
        try {

            ((Activity) context).finish();
            if (isForwardAnimation) {
                ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                        R.anim.slide_out_left);
            } else {
                ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                        R.anim.slide_right_in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callForWordActivityForResult(Context context, Class forwardClass, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(myIntent, ResultCode);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }

    }

    public static void callBackWordActivityForResult(Context context, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent();
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).setResult(Activity.RESULT_OK, myIntent);
        ((Activity) context).finish();
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in,
                    R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right,
                    R.anim.slide_right_in);
        }


    }

    public static void loadImage(final Context context, final ProgressBar progressBar, final String url, final ImageView viewById, final int isFromBanner) {

        Glide.with(context).load(url)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//
                        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(viewById);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (progressBar != null)
                            progressBar.setVisibility(View.GONE);
                        switch (isFromBanner) {

                        }
                        return false;
                    }
                })
                .crossFade()
                .into(viewById);
    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static boolean isGPSON(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    public static void convertEditTextAsTextView(EditText editText) {
        editText.setLongClickable(false);
        editText.setFocusable(false);
        editText.setCursorVisible(false);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    public static CharSequence setEditextError(Context context, String str, EditText editext) {

        editext.requestFocus();
        editext.setError(str);

        return "";
    }

    public static Drawable changeDrawableColor(Context context, int icon, int newColor) {
        Drawable mDrawable = ContextCompat.getDrawable(context, icon).mutate();
        // mDrawable.setColorFilter(new PorterDuffColorFilter(newColor, PorterDuff.Mode.SRC_IN));
        mDrawable.setColorFilter(newColor, PorterDuff.Mode.MULTIPLY);
        return mDrawable;
    }

    public static int getPixToDP(Context context, int paddingPixel) {
        float density = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int) (paddingPixel * density);
        return paddingDp;
    }


    public static void showErrorMessage(Activity activity, int stringResource, Style info, FrameLayout viewById, int duration, boolean isFormTabedActivity) {
        try {
            GlobalMethods.showToast(activity, stringResource, info, viewById, duration, isFormTabedActivity);
        } catch (Exception e) {

        }
    }

    public static String GetDeviceWidth(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return width + "";


    }


    public static String getMobileNumber(String mobileNo) {
        if (mobileNo.length() == 10) {
            return mobileNo;
        } else if (mobileNo.length() > 10) {
            return mobileNo.substring(mobileNo.length() - 10);
        } else {
            throw new IllegalArgumentException("word has less than 10 characters!");
        }
    }




    public static void callTopActivityForResult(Context context, Class forwardClass, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(myIntent, ResultCode);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_up,
                    R.anim.stay);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.stay,
                    R.anim.slide_down);
        }

    }


    public static void callDownActivityForResult(Context context, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent();
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).setResult(Activity.RESULT_OK, myIntent);
        ((Activity) context).finish();
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_in_up,
                    R.anim.stay);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.stay,
                    R.anim.slide_down);
        }


    }


}

