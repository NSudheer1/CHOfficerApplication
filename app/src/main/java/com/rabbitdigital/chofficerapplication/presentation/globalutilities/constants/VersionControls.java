package com.rabbitdigital.chofficerapplication.presentation.globalutilities.constants;

import android.content.Context;

public class VersionControls {

    public static boolean isUpdated = false;

    private static VersionControls instance = null;

    private String Url = URLS.Staging.getURL();


    private String PAYMENT_BASE_URL = PaymentGateWayURLS.Production.getPayment_base_url();

    private int SyncFreq = 5;

    public static VersionControls getVersionControls(Context context) {
        if (isUpdated || instance == null)
            instance = new VersionControls(context);
        return instance;
    }

    public VersionControls(Context context) {

    }

    static enum URLS {
        Development(
                ""), Staging(
                ""), Production(
                "");
        //

        private String URL;

        URLS(String URL) {
            this.URL = URL;
        }


        public void setURL(String URL) {
            this.URL = URL;
        }

        public String getURL() {
            return this.URL;
        }
    }

    //http://172.16.0.135:1224/#/dashboard/ --old url
    static enum PaymentGateWayURLS {
        Development(
                ""), Staging(
                ""), Production(
                "");


        //  Log.e("load 11-", s_URL);

        //

        public String getPayment_base_url() {
            return payment_base_url;
        }

        public void setPayment_base_url(String payment_base_url) {
            this.payment_base_url = payment_base_url;
        }

        private String payment_base_url;

        PaymentGateWayURLS(String payment_base_url) {
            this.payment_base_url = payment_base_url;
        }


    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    /**
     * @return the syncFreq
     */
    public int getSyncFreq() {
        return SyncFreq;
    }

    /**
     * @param syncFreq the syncFreq to set
     */
    public void setSyncFreq(int syncFreq) {
        SyncFreq = syncFreq;
    }


    public String getPAYMENT_BASE_URL() {
        return PAYMENT_BASE_URL;
    }

    public void setPAYMENT_BASE_URL(String PAYMENT_BASE_URL) {
        this.PAYMENT_BASE_URL = PAYMENT_BASE_URL;
    }


}
