package com.al.chainwaylibrary;

import com.infra.entregas.MainActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class ChainwayPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("IResult")) {
            this.obtenerMensaje(callbackContext);
            return true;
        }
        return false;
    }

    private void obtenerMensaje(CallbackContext callbackContext) {
        String barcode = MainActivity.getInstance().obtenerDatoDesdeMainActivity();
        if (barcode != null) {
            callbackContext.success(barcode);
        } else {
            callbackContext.error("No se pudo escanear.");
        }
    }
}