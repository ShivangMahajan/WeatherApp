package com.sabbey.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static EditText cityName;
    public static Button checkWeather;
    public static TextView temp;
    public static TextView weather;
    public static TextView humidity;
    public static TextView pressure;
    private Button moreDetails;
    private APIRequest newRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.city_name);
        checkWeather = findViewById(R.id.button);
        temp = findViewById(R.id.temp);
        weather = findViewById(R.id.weather);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        moreDetails = findViewById(R.id.details);
        newRequest = new APIRequest();


        checkWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityName.getText().toString().isEmpty())
                    cityName.setError("Empty");
                else {
                    Details.cardViewArrayList.clear();
                    newRequest.sendAPIRequest(MainActivity.this);
                }
                //closing softkeyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(cityName.getWindowToken(), 0);
            }
        });

        moreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityName.getText().toString().isEmpty())
                    cityName.setError("Empty");
                else {
                    Intent intent = new Intent(MainActivity.this, Details.class);
                    startActivity(intent);
                }
            }
        });

    }


}
