package com.rabbitdigital.chofficerapplication.presentation.globalutilities.exceptions;

import android.content.Context;

import com.rabbitdigital.chofficerapplication.businesslogic.model.ExceptionCore;
import com.rabbitdigital.chofficerapplication.presentation.globalutilities.constants.GlobalMethods;


/**
 * Created by durgaprasad.polaki on 29/09/2017.
 */

public class ExceptionFinder {



    public static void saveExceptionDatainDB(Context myContext,
                                             String mehtodName, String exceptionMess) {
        ExceptionCore logCore = new ExceptionCore();
        logCore.setIsExceptionformTry(1);
        logCore.setLogException(exceptionMess);
        logCore.setImeiNum(GlobalMethods.getDeviceID(myContext));
        logCore.setDisplayName(GlobalMethods.getDeviceName());
        logCore.setEditFlag(1);
        logCore.setException_acitvity_name(mehtodName);
        logCore.setExceptionDate(GlobalMethods.getCurrentDate());

        if (GlobalMethods.isNull(exceptionMess)) {
//           int i= DBUtil.getInstance(myContext).insertOrUpdateTable(logCore, DBAction.INSERT,null);
//            Log.e("","");
        }

        //Crashlytics.log(exceptionMess);

    }

    /**
     * lagflag true means all log will excecute or if lag flag false means all
     * log related to our app will not excecuted
     *
     * so before build release please change the flag to false.
     *
     */
    public static boolean logFlag = true;
}
