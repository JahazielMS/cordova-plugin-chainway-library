package com.al.chainwaylibrary;

import android.os.AsyncTask;
import android.util.Log;
import android.os.Bundle;
import android.content.Context;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import java.util.ArrayList;
import android.webkit.WebView;

public class ScanActivity extends CordovaActivity implements IBarcodeResult {
    private Barcode2D barcode2D;
    public String BarcodeRes = "";
    private Context context;

    // @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barcode2D = new Barcode2D(this);
        this.context = getApplicationContext();
        new InitTask().execute();
    }

    // @Override
    public void getBarcode(String barcode) {
        Log.d(TAG,"BarcodeResult:" + barcode);
        this.BarcodeRes = barcode;
    }

    public String getState(){
        return BarcodeRes;
    }

    // @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(BarcodeRes);
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
