package com.rabbitdigital.chofficerapplication.businesslogic.model;

/**
 * Created by durgaprasad.polaki on 17/05/17.
 */

public class ExceptionCore {

    private String logException;



    private String displayName;
    private String imeiNum;
    private String exception_acitvity_name;
    private int isExceptionformTry, editFlag, _id;
    private String exceptionDate;

    public String getLogException() {
        return logException;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImeiNum() {
        return imeiNum;
    }

    public void setImeiNum(String imeiNum) {
        this.imeiNum = imeiNum;
    }

    public void setLogException(String logException) {
        this.logException = logException;
    }






    public int getIsExceptionformTry() {
        return isExceptionformTry;
    }

    /**
     * @param isExceptionformTry values is 1 means exception occur in try catch block or
     *                           2 means exceptions occur in activity with force close.
     */
    public void setIsExceptionformTry(int isExceptionformTry) {
        this.isExceptionformTry = isExceptionformTry;
    }

    public int getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(int editFlag) {
        this.editFlag = editFlag;
    }

    public String getException_acitvity_name() {
        return exception_acitvity_name;
    }

    public void setException_acitvity_name(String exception_acitvity_name) {
        this.exception_acitvity_name = exception_acitvity_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getExceptionDate() {
        return exceptionDate;
    }

    public void setExceptionDate(String exceptionDate) {
        this.exceptionDate = exceptionDate;
    }

}
