package com.infra.entregas;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import com.al.chainwaylibrary.Barcode2D;
import com.al.chainwaylibrary.IBarcodeResult;
import com.al.chainwaylibrary.MainScan;

import org.apache.cordova.*;

public class MainActivity extends CordovaActivity implements IBarcodeResult
{
    private String TAG = "MainActivity_2D";
    private Barcode2D barcode2D;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        barcode2D = new Barcode2D(this);
        new InitTask().execute();
        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

    // @Override
    public void getBarcode(String barcode) {
        Log.d(TAG, "Barcode Result: " + barcode);
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
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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