package com.rabbitdigital.chofficerapplication.presentation.globalutilities.utills;

import android.content.Context;

import com.dms.datalayerapi.db.DBSupportUtil;
import com.dms.datalayerapi.db.core.TableDetails;

import java.util.ArrayList;


/**
 * Created by Raja.p on 09-08-2016.
 */
public class DBUtil extends DBSupportUtil {

    private static final int DB_VERSION_NUMBER = 1;
    public static final String DB_NAME = "CHOfficerDb";
    private static DBUtil instance = null;
    private static boolean nonSingleInstance = false;


    private DBUtil(Context context) {
        super(context);
    }

    @Override
    protected ArrayList<TableDetails> getAllTableDetails(ArrayList<TableDetails> allTableDefinitions) {

       // allTableDefinitions.add(TableDetails.getTableDetails(UserLoginDetails.class));
        return allTableDefinitions;
    }

    public static DBUtil getWDInstance(Context context) {
        return getInstance(context);
    }

    public static DBUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtil(context);
        }
        return instance;
    }

    @Override
    protected String getDatabaseFileName() {
        return DB_NAME;
    }

    @Override
    public int getDatabaseVersion() {
        return DB_VERSION_NUMBER;
    }



}
