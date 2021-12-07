package com.al.chainwaylibrary;

import android.content.Context;
import android.content.Intent;

import org.apache.cordova.*;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainScan extends CordovaPlugin {

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

    // private void ScanActivity(Context context) {
    private void ScanActivity(String message, CallbackContext callbackContext) {
        Context context = cordova.getActivity().getApplicationContext();
        if (message != null && message.length() > 0) {
            ScanActivity appState = ((ScanActivity)getApplicationContext());
            String x = appState.getState();
            appState.setState(x);
            callbackContext.success(x);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
        

        // Intent intent = new Intent(context, ScanActivity.class);
        // this.cordova.getActivity().startActivity(intent);
    }
}
