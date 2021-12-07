package com.al.chainwaylibrary;

import android.content.Context;
import android.content.Intent;

import com.infra.entregas.MainActivity;

import org.apache.cordova.*;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainScan extends CordovaPlugin {
    public String Barcode = "";
    MainActivity mainActivity = new MainActivity();
    
    // @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        } else if (action.equals("IResult")) {
            String message = args.getString(0);
            this.ScanActivity(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void ScanActivity(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            String barcodeTst = mainActivity.BarcodeString;
            callbackContext.success(barcodeTst);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
