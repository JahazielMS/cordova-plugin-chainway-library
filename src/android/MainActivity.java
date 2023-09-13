/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.infra.entregas;

import com.al.chainwaylibrary.Barcode2D;
import com.al.chainwaylibrary.IBarcodeResult;

import android.os.Bundle;
import android.util.Log;
import android.os.AsyncTask;

import org.apache.cordova.*;

public class MainActivity extends CordovaActivity implements IBarcodeResult {
    private static MainActivity instance;
    public static String Barcode = "";
    private String TAG = "MainActivity_2D";
    private Barcode2D barcode2D;

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                open();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
    }

    @Override
    public void getBarcode(String barcode) {
        Barcode = barcode;
        Log.d(TAG, "Barcode: " + barcode);
    }

    public static String obtenerDatoDesdeMainActivity() {
        Log.d("MainActivity:", "Se escaneo: " + Barcode);
        return Barcode;
    }

    private void start() {
        Log.d(TAG, "Start Scan Main Activity");
        barcode2D.startScan(this);
    }

    private void stop() {
        Log.d(TAG, "Stop Scan Main Activity");
        barcode2D.stopScan(this);
    }

    private void open() {
        Log.d(TAG, "Open Scan Main Activity");
        barcode2D.open(this, this);
    }

    private void close() {
        Log.d(TAG, "Close Scan Main Activity");
        barcode2D.stopScan(this);
        barcode2D.close(this);
    }
}