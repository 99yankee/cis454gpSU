package com.cis454.gpsu;

/**
 * Created by 99yankee on 2/8/15.
 */
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WeatherServiceAsync extends AsyncTask<String, Void, String> {
    private final gpSU WeatherActivity;

// this constructor takes the activity as the parameter.
// that way we can use the activity later to populate the weather value fields
// on the screen


    public WeatherServiceAsync(gpSU weatherActivity) {
        this.WeatherActivity = weatherActivity;
    }

    @Override
    protected String doInBackground(String urls) {

// this weather service method will be called after the service executes.
// it will run as a seperate process, and will populate the activity in the onPostExecute
// method below

        String response = "";
// loop through the urls (there should only be one!) and call an http Get using the URL passed
// to this service

        for (String url : urls) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {

// make the http request for the weather data
                HttpResponse execute = client.execute(httpGet);

// get the content of the result returned when the response comes back
// it should be a json object
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";

// populate the response string which will be passed later into the post execution
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        String test = result;
        try {
// parse the json result returned from the service
            JSONObject jsonResult = new JSONObject(test);

// parse out the temperature from the JSON result
            double temperature = jsonResult.getJSONObject("main").getDouble("temp");
            temperature = ConvertTemperatureToFahrenheit(temperature);

            // parse out the pressure from the JSON Result
            double pressure = jsonResult.getJSONObject("main").getDouble("pressure");

// parse out the humidity from the JSON result
            double humidity = jsonResult.getJSONObject("main").getDouble("humidity");

// parse out the description from the JSON result
            String description = jsonResult.getJSONArray("weather").getJSONObject(0).getString("description");

// set all the fields in the activity from the parsed JSON
            this.WeatherActivity.SetDescription(description);
            this.WeatherActivity.SetTemperature(temperature);
            this.WeatherActivity.SetPressure(pressure);
            this.WeatherActivity.SetHumidity(humidity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private double ConvertTemperatureToFahrenheit(double temperature) {
        return (temperature - 273)* (9/5) + 32;
    }


}