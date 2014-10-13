package control;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Control {
    private final String TAG = "DevMode";
    private float alcool_price = 0;
    private float gas_price = 0;
    private boolean isFieldGasEmpty;
    private boolean isFieldAlcoolEmpty;
    
    public enum FUEL {
        GAS, ALCOOL
    }

    private FUEL best;

    public Control(Context context) {
        isFieldAlcoolEmpty = true;
        isFieldGasEmpty = true;
    }

    public FUEL getBest() {
        return best;
    }
    
    private boolean isFieldEmpty(FUEL fuel) {
        switch (fuel) {
            case GAS:
                return isFieldGasEmpty;

            case ALCOOL:
                return isFieldAlcoolEmpty;
        }
        Log.e(TAG, "ERROR...");
        return true;
    }

    public void setGasFieldEmpty(boolean empty) {
        isFieldGasEmpty = empty;
    }

    public boolean isGasFieldEmpty() {
        return isFieldGasEmpty;
    }


    public void setAlcoolFieldEmpty(boolean empty) {
        isFieldAlcoolEmpty = empty;
    }

    public boolean isAlcoolFieldEmpty() {
        return isFieldAlcoolEmpty;
    }

    public boolean isAbleToContinue() {
        return (!isFieldGasEmpty && !isFieldAlcoolEmpty);
    }

    public void comparePrices(float gas, float alcool, Toast toast) {
            best = calculate(gas, alcool);
            if(best == FUEL.ALCOOL) {
                Log.i(TAG, "Alcool is best!");
            } else {
                Log.i(TAG, "Gas is best!");
            }
    }

    public float getPercentage() {
        Log.i(TAG, ((alcool_price / gas_price) * 100)+"%");
        return ((alcool_price / gas_price) * 100);
    }

    private FUEL calculate(float gas, float alcool) {
        alcool_price = alcool;
        gas_price = gas;
        return ((alcool / gas) * 100 > 70) ? FUEL.GAS : FUEL.ALCOOL;
    }
}
