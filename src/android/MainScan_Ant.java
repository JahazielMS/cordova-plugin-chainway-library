package com.al.chainwaylibrary;

import android.os.AsyncTask;
import android.util.Log;
import android.os.Bundle;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import java.util.ArrayList;
import android.webkit.WebView;

public class MainScan extends CordovaActivity implements IBarcodeResult {
    private Barcode2D barcode2D;
    public static String BarcodeRes = "";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        barcode2D = new Barcode2D(this);
        new InitTask().execute();
    }

    public void initScanner() {
        Log.d(TAG,"initScanner");
    }

    // @Override
    public void getBarcode(String barcode) {
        Log.d(TAG,"BarcodeResult:" + barcode);
        BarcodeRes = barcode;
    }

    // @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        } else if (action.equals("start")) {
            String message = args.getString(0);
            return true;
        } else if (action.equals("IResult")) {
            String message = "Test";
            // this.currentCallbackContext = callbackContext;
            this.IResult(message, callbackContext);
            // callbackContext.sendPluginResult(result);
            // cordova.getThreadPool().execute(() -> {
            //   PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
            //   result.setKeepCallback(true);
            //   callbackContext.sendPluginResult(result);
            // });
            return true;
        }
        return false;
    }

    private void IResult(String message, CallbackContext callbackContext) {

        callbackContext.success(message + " - " + BarcodeRes);

        // if (message != null && message.length() > 0) {
        //     callbackContext.success(message + " - " + BarcodeRes);
        // } else {
        //     callbackContext.error("Expected one non-empty string argument.");
        // }
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                open();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Log.d(TAG,"onPostExecute:" + result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"onPreExecute: init..." );
    }
    }

    private void start() {
        barcode2D.startScan(this);
    }

    private void stop() {
        barcode2D.stopScan(this);
    }

    private void open() {
        barcode2D.open(this, this);
    }

    private void close() {
        barcode2D.stopScan(this);
        barcode2D.close(this);
    }
}
