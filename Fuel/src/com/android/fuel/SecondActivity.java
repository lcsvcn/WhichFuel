package com.android.fuel;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import control.Control;
import dev.android.combustivel.R;

public class SecondActivity extends ActionBarActivity {

    private TextView text_theBest;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        setTitle("Fuel");

        text_theBest = (TextView) findViewById(R.id.textView_best);

        Control control = MainActivity.getControl();

        Control.FUEL fuel = control.getBest();

        if(fuel == Control.FUEL.GAS) {
            text_theBest.setText("Gas");
            text_theBest.setTextColor(Color.parseColor("#ffbc6000"));
        } else {
            text_theBest.setText("Alcool");
            text_theBest.setTextColor(Color.parseColor("#ff208e00"));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
        //Inative
    	//getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //Inative
        /*if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.action_feedback) {
            // Open ovelapping message
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
