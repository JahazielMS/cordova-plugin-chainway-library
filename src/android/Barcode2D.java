package com.al.chainwaylibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.os.Bundle;

import com.rscja.barcode.BarcodeUtility;

public class Barcode2D {
    private String TAG = "Scanner_barcodeTest";
    private BarcodeUtility barcodeUtility = null;
    private BarcodeDataReceiver barcodeDataReceiver = null;
    private IBarcodeResult iBarcodeResult = null;
    private Context context;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // barcode2D = new Barcode2D(this);
        // new InitTask().execute();
    }

    // public class InitTask extends AsyncTask<String, Integer, Boolean> {
    //     @Override
    //     protected Boolean doInBackground(String... params) {
    //         try {
    //             open();
    //             Thread.sleep(1000);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //         return true;
    //     }

    //     @Override
    //     protected void onPostExecute(Boolean result) {
    //         super.onPostExecute(result);
    //         Log.d(TAG,"onPostExecute:" + result);
    //     }

    //     @Override
    //     protected void onPreExecute() {
    //         super.onPreExecute();
    //         Log.d(TAG,"onPreExecute: init..." );
    //     }
    // }

    public Barcode2D(Context context) {
        barcodeUtility = BarcodeUtility.getInstance();
        this.context = context;
    }

    public void startScan(Context context) {
        if (barcodeUtility != null) {
            Log.i(TAG, "ScanBarcode");
            barcodeUtility.startScan(context, BarcodeUtility.ModuleType.BARCODE_2D);
        }
    }

    public void stopScan(Context context) {
        if (barcodeUtility != null) {
            Log.i(TAG, "stopScan");
            barcodeUtility.stopScan(context, BarcodeUtility.ModuleType.BARCODE_2D);
        }
    }

    public void open(Context context, IBarcodeResult iBarcodeResult) {
        if (barcodeUtility != null) {
            this.iBarcodeResult = iBarcodeResult;
            barcodeUtility.setOutputMode(context, 2);
            barcodeUtility.setScanResultBroadcast(context, "com.scanner.broadcast", "data");
            barcodeUtility.open(context, BarcodeUtility.ModuleType.BARCODE_2D);
            barcodeUtility.setReleaseScan(context, false);
            barcodeUtility.setScanFailureBroadcast(context, true);
            barcodeUtility.enableContinuousScan(context, false);
            barcodeUtility.enablePlayFailureSound(context, false);
            barcodeUtility.enableEnter(context, false);
            barcodeUtility.setBarcodeEncodingFormat(context, 1);

            if (barcodeDataReceiver == null) {
                barcodeDataReceiver = new BarcodeDataReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.scanner.broadcast");
                context.registerReceiver(barcodeDataReceiver, intentFilter);
            }
        }
    }

    public void close(Context context) {
        if (barcodeUtility != null) {
            barcodeUtility.close(context, BarcodeUtility.ModuleType.BARCODE_2D);
            if (barcodeDataReceiver != null) {
                context.unregisterReceiver(barcodeDataReceiver);
                barcodeDataReceiver = null;
            }
        }
    }

    protected class BarcodeDataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String barCode = intent.getStringExtra("data");
            String status = intent.getStringExtra("SCAN_STATE");

            if (status != null && (status.equals("cancel"))) {
                return;
            } else {
                if (barCode == null) {
                    barCode = "Scan fail";
                }
                
                if (iBarcodeResult != null)
                    iBarcodeResult.getBarcode(barCode);
            }
        }
    }
}