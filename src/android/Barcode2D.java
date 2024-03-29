package com.al.chainwaylibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.rscja.barcode.BarcodeUtility;

/**
 * Created by wskyo on 2021-6-18.
 */

public class Barcode2D {
    private String TAG = "Scanner_barcodeTest";
    private BarcodeUtility barcodeUtility = null;
    private BarcodeDataReceiver barcodeDataReceiver = null;
    private IBarcodeResult iBarcodeResult = null;
    private Context context;

    public Barcode2D(Context context) {
        barcodeUtility = BarcodeUtility.getInstance();
        this.context = context;
    }

    public void startScan(Context context) {
        Log.d(TAG, "StartScan BCD2");
        if (barcodeUtility != null) {
            Log.i(TAG, "ScanBarcode");
            barcodeUtility.startScan(context, BarcodeUtility.ModuleType.BARCODE_2D);
        }
    }

    public void stopScan(Context context) {
        Log.d(TAG, "StopScan BCD2");
        if (barcodeUtility != null) {
            Log.i(TAG, "stopScan");
            barcodeUtility.stopScan(context, BarcodeUtility.ModuleType.BARCODE_2D);
        }
    }

    public void open(Context context, IBarcodeResult iBarcodeResult) {
        Log.d(TAG, "Open BCD2");
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
        Log.d(TAG, "Close BCD2");
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