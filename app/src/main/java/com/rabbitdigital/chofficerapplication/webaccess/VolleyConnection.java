package com.rabbitdigital.chofficerapplication.webaccess;

/**
 * Created by Administrator on 06-10-2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rabbitdigital.chofficerapplication.R;
import com.rabbitdigital.chofficerapplication.presentation.globalutilities.constants.ApplicationConstant;
import com.rabbitdigital.chofficerapplication.presentation.globalutilities.constants.VersionControls;
import com.rabbitdigital.chofficerapplication.presentation.globalutilities.singleton.SingletonClass;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by shubham.agarwal on 19/7/16. @Webkul Software Pvt. Ltd
 */
public class VolleyConnection {

    private final Context mContext;
    private OnVolleyResponseListener mOnVolleyResponseListener;

    @SuppressWarnings("FieldCanBeLocal")

    private String mMethodName;
    private String[] mCurrentRequestParams;

    public VolleyConnection(Context context, OnVolleyResponseListener onVolleyResponseListener) {
        mContext = context;
        mOnVolleyResponseListener = onVolleyResponseListener;
    }

    public VolleyConnection(Context context) {
        mContext = context;
        if (context instanceof OnVolleyResponseListener) {
            Log.d(ApplicationConstant.TAG, "VolleyConnection VolleyConnection: context instanceof OnVolleyResponseListener");
            mOnVolleyResponseListener = (OnVolleyResponseListener) context;
        } else {
            Log.d(ApplicationConstant.TAG, "VolleyConnection VolleyConnection: context NOT instanceof OnVolleyResponseListener ': ");
        }
    }

    public void execute(final String... params) {
//        for (int paramsIndex = 0; paramsIndex < params.length; paramsIndex++) {
//            Log.d(ApplicationConstant.TAG, "Params " + paramsIndex + " " + params[paramsIndex]);
//        }
        mCurrentRequestParams = params;

        mMethodName = getNewMethodName(params[0]);
        Log.d(ApplicationConstant.TAG, "execute: method url rest: " + mMethodName);

        final StringRequest stringRequest = new StringRequest(getRequestMethod(), getRequestUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(ApplicationConstant.TAG, "execute onResponse: " + response);
                        if (isSessionExpired(response)) {
                            getSessionId();
                        } else {
                            if (mOnVolleyResponseListener != null) {
                                mOnVolleyResponseListener.onResponse(response, params[0]);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.d(ApplicationConstant.TAG, "onErrorResponse:  in execute " + error.toString());
                try {
                    if (error instanceof NoConnectionError) {
                        SingletonClass.getAlertdialog(mContext, mContext.getResources().getString(R.string.error_intenet_unavailable), mContext.getResources().getString(R.string.retry), new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        new VolleyConnection(mContext, mOnVolleyResponseListener).execute(params);
                                    }
                                }, mContext.getResources().getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (mOnVolleyResponseListener != null) {
                                    mOnVolleyResponseListener.onResponse(error.toString(), params[0]);
                                }

                            }
                        }, false).create().show();
                    } else if (error instanceof TimeoutError) {
                        Log.d(ApplicationConstant.TAG, "onErrorResponse: params[0]" + params[0]);
                        if (params[0].equals("mobikulmpMarketplaceContactSeller") || params[0].equals("mobikulmpMarketplaceAskQuestion") ||
                                params[0].equals("mobikulmpMarketplaceInvoice") || params[0].equals("mobikulmpMarketplaceSendinvoiceMail")) {
                            // Toast.makeText(mContext, mContext.getString(R.string.request_send), Toast.LENGTH_SHORT).show();
                            if (mOnVolleyResponseListener != null) {
                                mOnVolleyResponseListener.onResponse(error.toString(), params[0]);
                            }
                            return;
                        }

                        SingletonClass.getAlertdialog(mContext, mContext.getString(R.string.request_failed), mContext.getResources().getString(R.string.retry), new DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new VolleyConnection(mContext, mOnVolleyResponseListener).execute(params);
                            }
                        }, mContext.getResources().getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (mOnVolleyResponseListener != null) {
                                    mOnVolleyResponseListener.onResponse(error.toString(), params[0]);
                                }
                            }
                        }, true).create().show();
                    } else if (error instanceof AuthFailureError || error instanceof ServerError || error instanceof NetworkError || error instanceof ParseError) {
                        if (mOnVolleyResponseListener != null) {
                            mOnVolleyResponseListener.onResponse(error.toString(), params[0]);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
//                if (!ApplicationSingleton.getInstance().getSessionId().isEmpty())
//                    headers.put("Cookie", "PHPSESSID=" + ApplicationSingleton.getInstance().getSessionId());
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Log.d(ApplicationConstant.TAG, "getParams: ");
                Map<String, String> postParams = new HashMap<>();
                //postParams.put("sessionId", ApplicationSingleton.getInstance().getSessionId());


                Log.d(ApplicationConstant.TAG, "Post Request Params: " + postParams);
                return postParams;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 30, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }

    private String getRequestUrl() {
        return VersionControls.getVersionControls(mContext).getUrl() + mMethodName;
    }


    private int getRequestMethod() {
        return Request.Method.POST;
    }

    private String getNewMethodName(String methodName) {
        switch (methodName) {

        }
        return methodName;
    }

    private void getSessionId() {
        Log.d(ApplicationConstant.TAG, "getSessionId");
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, VersionControls.getVersionControls(mContext).getUrl() + WebServiceList.CHOFFICER_SOAP_LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(ApplicationConstant.TAG, "getSessionId onResponse: " + response);
                        JSONObject responseJSON;
                        try {
                            responseJSON = new JSONObject(response);
                            if (responseJSON.has("error")) {
                                if (responseJSON.getInt("error") == 0) {
                                    //  ApplicationSingleton.getInstance().setSessionId(responseJSON.getString("sessionId"));
                                } else if (responseJSON.getInt("error") == 1) {
                                    Toast.makeText(mContext, responseJSON.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            }
                            new VolleyConnection(mContext, mOnVolleyResponseListener).execute(mCurrentRequestParams);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(ApplicationConstant.TAG, "onErrorResponse: in getSessionId" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> postParams = new HashMap<>();
//                postParams.put("username", WebServiceList.SOAP_USER_NAME);
//                postParams.put("password", WebServiceList.SOAP_PASSWORD);
                Log.d(ApplicationConstant.TAG, "getSessionId getParams() returned: " + postParams);
                return postParams;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 30, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }

    private boolean isSessionExpired(String response) {
        try {
            Object json = new JSONTokener(response).nextValue();
            if (json instanceof JSONObject) {
                JSONObject responseJSON = new JSONObject(response);
                if (responseJSON.has("error") && responseJSON.getInt("error") == 5) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}