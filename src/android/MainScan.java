package com.al.chainwaylibrary;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import android.util.Log;

import com.infra.entregas.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainScan extends CordovaPlugin {
    String barcode = MainActivity.getInstance().obtenerDatoDesdeMainActivity();
    
    // @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("IResult")) {
            String message = args.getString(0);
            this.ScanActivity(message, callbackContext);
            barcode = null;
            return true;
        }
        return false;
    }

    private void ScanActivity(String message, CallbackContext callbackContext) {
        if (barcode != null) {
            callbackContext.success(barcode);
        } else {
            callbackContext.error("No se pudo escanear.");
        }
    }
}
