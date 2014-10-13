package dev.android.combustivel;

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
import android.widget.TextView;
import android.widget.Toast;

import control.Control;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    private EditText editText_gasolina;
    private EditText editText_alcool;
    private ImageButton imageButton_gas;
    private ImageButton imageButton_alcool;
    private TextView text_alcool;
    private TextView text_gasolina;
    private TextView text_preco_alcool;
    private TextView text_preco_gasolina;
    private Button button_submit;
    private Toast toast;
    private static Control control;

    /*
        Default methods of activity
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate() called...");

        setContentView(R.layout.activity_main);

        control = new Control(this);

        button_submit = (Button) findViewById(R.id.button_submit);
        editText_gasolina = (EditText) findViewById(R.id.editText_gasolina);
        editText_alcool = (EditText) findViewById(R.id.editText_alcool);
        text_preco_gasolina = (TextView) findViewById(R.id.textView_preco_gasolina);
        text_preco_alcool = (TextView) findViewById(R.id.textView_preco_alcool);
        text_gasolina = (TextView) findViewById(R.id.textView_gasolina);
        text_alcool = (TextView) findViewById(R.id.textView_alcool);
        imageButton_gas = (ImageButton) findViewById(R.id.imageButton_gas);
        imageButton_alcool = (ImageButton) findViewById(R.id.imageButton_alcool);


        button_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "button_bottom clicked...");

                    cancelToast(toast);

                    control.setGasFieldEmpty(editText_gasolina.length() == 0);
                    control.setAlcoolFieldEmpty(editText_alcool.length() == 0);

                    if(!control.isAlcoolFieldEmpty() && !control.isGasFieldEmpty()) {
                        float gas = Float.parseFloat(editText_gasolina
                                .getText().toString());
                        float alcool = Float.parseFloat(editText_alcool.getText()
                                .toString());

                        whichIsBetter(gas, alcool);

                        Intent intent = new Intent(v.getContext(), MyActivity.class);
                        startActivityForResult(intent, 0);
                    } else {
                        showToast(toast, getMessage(), Toast.LENGTH_SHORT);
                    }

                }
        });

        imageButton_gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelToast(toast);
                editText_gasolina.setText("");
                editText_gasolina.requestFocus();
            }
        });

        imageButton_alcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelToast(toast);
                editText_alcool.setText("");
                editText_alcool.requestFocus();
            }
        });

        editText_gasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelToast(toast);
            }
        });

        editText_alcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelToast(toast);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelToast(toast);     // Cancel toast when called onDestroy()
        Log.d(TAG, "onDestroy() called...");
    }


    @Override
    protected void onStop() {
        super.onStop();
        cancelToast(toast);     // Cancel toast when called onStop()
        Log.d(TAG, "onStop() called...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        cancelToast(toast);
        Log.d(TAG, "onStart() called...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelToast(toast);
        Log.d(TAG, "onPause() called...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            cancelToast(toast);
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.action_feedback) {
            // Open ovelapping message
            return true;
        }
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
        cancelToast(toast);    // Avoid toast overlapping
        try {
            toast.makeText(this, message, time).show();     // Make new toast text and show
        } catch(NullPointerException e) {
            toast = new Toast(getApplicationContext());     // sh
            showToast(toast, message, time);
        } catch(Exception e) {
            Log.e(TAG, "Uncaught exception on showToast()");
        } finally {
            Log.i(TAG, "showToast() is okay...");
        }
    }

    private void cancelToast(Toast toast) {
        Log.d(TAG, "cancelToast(toast) called...");
        try {
            toast.cancel();
            Log.i(TAG, "Toast canceled");
        } catch(Exception e) {
            Log.i(TAG, "No toast to cancel");
        } finally {
            toast = null;
        }
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
    * Method setToast(m, d, g, x, y, h, v);
    * Meaning of each var :
    *   m - message
    *   d - duration
    *   g - gravity
    *   x - xOffset
    *   y - yOffset
    *   h - horizontalMargin
    *   v - verticalMargin
    * */
    private void setToast(String m, int d, int g, int x, int y, int h, int v) {
        try {
            cancelToast(toast);
            toast.setText(m);
            toast.setDuration(d);
            toast.show();
            toast.setGravity(g, x, y);
            toast.setMargin(h, v);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, "Error at setToast!");
        }
    }


    /*
    *    Methods that I create to be accessible for all classes
    */

    // Return Control class to be used at MyActivity
    public static Control getControl() {
        return control;
    }
}
