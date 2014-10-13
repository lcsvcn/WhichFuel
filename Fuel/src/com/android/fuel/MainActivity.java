package com.android.fuel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import control.Control;
import dev.android.combustivel.R;
import essential.ToastControl;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "DEVMODE";
    private EditText editText_gasolina;
    private EditText editText_alcool;
    private ImageButton imageButton_gas;
    private ImageButton imageButton_alcool;
    private Button button_submit;
    private Toast toast;
    private static Control control;
    private ToastControl tc;
    /*
        Default methods of activity
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate() called...");
        
        setContentView(R.layout.activity_main);
      

        
        control = new Control(getApplicationContext());
        tc = new ToastControl(getApplicationContext());

        button_submit = (Button) findViewById(R.id.button_submit);
        
        editText_gasolina = (EditText) findViewById(R.id.editText_gasolina);
        editText_alcool = (EditText) findViewById(R.id.editText_alcool);
        
        imageButton_gas = (ImageButton) findViewById(R.id.imageButton_gas);
        imageButton_alcool = (ImageButton) findViewById(R.id.imageButton_alcool);

        button_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "button_bottom clicked...");

                    tc.destroyToast();

                    control.setGasFieldEmpty(editText_gasolina.length() == 0);
                    control.setAlcoolFieldEmpty(editText_alcool.length() == 0);

                    if(!control.isAlcoolFieldEmpty() && !control.isGasFieldEmpty()) {
                        float gas = Float.parseFloat(editText_gasolina
                                .getText().toString());
                        float alcool = Float.parseFloat(editText_alcool.getText()
                                .toString());

                        whichIsBetter(gas, alcool);

                        Intent intent = new Intent(v.getContext(), SecondActivity.class);
                        startActivityForResult(intent, 0);
                    } else {
                        showToast(toast, getMessage(), Toast.LENGTH_SHORT);
                    }

                }
        });

        imageButton_gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tc.destroyToast();
                editText_gasolina.setText("");
                editText_gasolina.requestFocus();
            }
        });

        imageButton_alcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tc.destroyToast();
                editText_alcool.setText("");
                editText_alcool.requestFocus();
            }
        });

        editText_gasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	tc.destroyToast();
            }
        });

        editText_alcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	tc.destroyToast();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tc.destroyToast();     // Cancel toast when called onDestroy()
        Log.d(TAG, "onDestroy() called...");
    }


    @Override
    protected void onStop() {
        super.onStop();
        tc.destroyToast();     // Cancel toast when called onStop()
        Log.d(TAG, "onStop() called...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        tc.destroyToast();
        Log.d(TAG, "onStart() called...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        tc.destroyToast();
        Log.d(TAG, "onPause() called...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        
    	// Inative
    	//getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        /*if (id == R.id.action_settings) {
            tc.destroyToast();
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.action_feedback) {
            // Open ovelapping message
        	tc.destroyToast();
        	return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed() called...");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /*
    *    Methods create to use in this class only
    */

    // Which fuel is the better price x performance
    private void whichIsBetter(float gas, float alcool) {
            control.comparePrices(gas, alcool, toast);
    }

    private void showToast(Toast toast, String message, int time) {
        Log.d(TAG, "showToast() called...");
        tc.setToast(getMessage(), time);
     }

    private String getMessage() {
        if (control.isGasFieldEmpty() && control.isAlcoolFieldEmpty()) {
            return "Fill both prices above";
        } else if (control.isGasFieldEmpty() && !control.isAlcoolFieldEmpty()) {
            return "Put the gas price";
        } else {
            return "Put the alcool price";
        }
    }

    /*
    *    Methods that I create to be accessible for all classes
    */

    // Return Control class to be used at SecondActivity
    public static Control getControl() {
        return control;
    }
}
