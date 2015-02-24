package com.cis454.gpsu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DecimalFormat;


public class gpSU extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_main);
        setContentView(R.layout.activity_gp_su);

        try{
            RetrieveWeather();
        }
        catch (IOException ioe){
            System.out.println("Error!");
        }
    }

    private void RetrieveWeather() throws IOException {

        String url = "http://api.openweathermap.org/data/2.5/weather?q=syracuse,ny";

        WeatherServiceAsync task = new WeatherServiceAsync(this);

        task.execute(url);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gp_su, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Maps.class);
        startActivity(intent);
    }

    public void SetDescription(String description) {
        //Parses in the JSON info for weather description
        TextView viewDescription = (TextView) this.findViewById(R.id.textDescriptionValue);
        viewDescription.setText(description);
;
    }

    public void SetTemperature(double temperature) {

        //Parses in JSON info for temperature
        TextView view = (TextView) this.findViewById(R.id.textTemperatureValue);

        //Sets decimal format
        DecimalFormat df = new DecimalFormat("###");
        String formattedTemperature = df.format(temperature);

        view.setText(formattedTemperature + " °F");
    }


    public void SetPressure(double pressure) {

        //Parses in JSON info for pressure

        TextView viewPressure = (TextView) this.findViewById(R.id.textPressureValue);

        //Sets decimal format
        DecimalFormat df = new DecimalFormat("###.#");
        String formattedPressure = df.format(pressure);

        viewPressure.setText(formattedPressure + " mb");

    }

    public void SetHumidity(double humidity) {

        //Parses in JSON info for humidity

        TextView viewHumidity = (TextView) this.findViewById(R.id.textHumidityValue);

        //Sets decimal format
        DecimalFormat df = new DecimalFormat("###.#");
        String formattedHumidity = df.format(humidity);

        viewHumidity.setText(formattedHumidity + " %");
    }
}

