package com.sabbey.weatherapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class APIRequest {

    private String baseUrl = "https://api.openweathermap.org/data/2.5/forecast?q=";

    //api keys

    private String apiKeys = "&appid=047db90c45f2740fe32b8a6ef7f679e3";

    private RequestQueue requestQueue;

    public void sendAPIRequest(final Context context) {

        requestQueue = new VolleySingleton(context).requestQueue();
        String url = baseUrl + MainActivity.cityName.getText().toString() + apiKeys;

        //requesting api information

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray lists = response.getJSONArray("list");

                    Calendar calendar = Calendar.getInstance();
                    Date dateTime = calendar.getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    String formatedTime = dateFormat.format(dateTime);

                    JSONObject list = null;
                    for (int i = 0; i < lists.length() - 1; i++) {

                        list = lists.getJSONObject(i);
                        if (Integer.parseInt(list.getString("dt_txt").substring(11, 13)) <= Integer.parseInt(formatedTime.substring(0, 2))
                                && Integer.parseInt(lists.getJSONObject(i + 1).getString("dt_txt").substring(11, 13)) > Integer.parseInt(formatedTime.substring(0, 2))) {

                            list = lists.getJSONObject(i+1);
                            break;
                        }
                    }
                    JSONObject main = list.getJSONObject("main");
                    double tempCel = Double.parseDouble(main.getString("temp")) - 273.15;
                    MainActivity.temp.setText("Temperature: " + String.format("%.2f", tempCel) + "°C");
                    MainActivity.humidity.setText("Humidity: " + main.getString("humidity") + "%");

                    MainActivity.pressure.setText("Pressure: " + main.getString("pressure") + " mBar");

                    JSONArray mweather = list.getJSONArray("weather");
                    JSONObject cweather = mweather.getJSONObject(0);
                    MainActivity.weather.setText("Weather: " + cweather.getString("description"));


                    for (int i = 0; i < lists.length(); i++) {
                        JSONObject list1 = lists.getJSONObject(i);
                        JSONObject main1 = list1.getJSONObject("main");
                        double tempCel1 = Double.parseDouble(main1.getString("temp")) - 273.15;
                        int image = 0;
                        JSONArray mWeather1 = list1.getJSONArray("weather");
                        JSONObject cWeather1 = mWeather1.getJSONObject(0);


                        String month = "";
                        String time = "";
                        int t;

                        switch (list1.getString("dt_txt").substring(5, 7)) {
                            case "01":
                                month = "Jan";
                                break;
                            case "02":
                                month = "Feb";
                                break;
                            case "03":
                                month = "Mar";
                                break;
                            case "04":
                                month = "Apr";
                                break;
                            case "05":
                                month = "May";
                                break;
                            case "06":
                                month = "Jun";
                                break;
                            case "07":
                                month = "Jul";
                                break;
                            case "08":
                                month = "Aug";
                                break;
                            case "09":
                                month = "Sep";
                                break;
                            case "10":
                                month = "Oct";
                                break;
                            case "11":
                                month = "Nov";
                                break;
                            case "12":
                                month = "Dec";
                                break;
                        }

                        if (Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) > 12) {
                            t = Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) - 12;
                            time = "0" + String.valueOf(t) + " pm";
                        } else if (Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) == 0)
                            time = "12 am";
                        else if (Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) == 12)
                            time = list1.getString("dt_txt").substring(11, 13) + " pm";
                        else
                            time = list1.getString("dt_txt").substring(11, 13) + " am";

                        int preTime = Integer.parseInt(time.substring(0, 2)) - 3;
                        if (Integer.parseInt(time.substring(0, 2)) == 12 && time.substring(3).equals("am"))
                            time = String.valueOf(preTime) + " pm - " + time;
                        else
                        if (Integer.parseInt(time.substring(0, 2)) == 12 && time.substring(3).equals("pm"))
                            time = String.valueOf(preTime) + " am - " + time;
                        else
                        if (preTime == 0)
                            time = "12 " + time.substring(3) + " - " + time.substring(1);
                        else
                            time = String.valueOf(preTime) + " " + time.substring(3) + " - " + time.substring(1);


                        String date = list1.getString("dt_txt").substring(8, 10) + ", " + month;

                        if (cWeather1.getInt("id") >= 200 && cWeather1.getInt("id") < 300)
                            image = R.raw.thunderstrom;
                        else if (cWeather1.getInt("id") >= 300 && cWeather1.getInt("id") < 600)
                            image = R.raw.rain;
                        else if (cWeather1.getInt("id") >= 600 && cWeather1.getInt("id") < 700)
                            image = R.raw.snow;
                        else if (cWeather1.getInt("id") >= 700 && cWeather1.getInt("id") < 800)
                            image = R.raw.mist;
                        else if (cWeather1.getInt("id") == 800 && Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) <= 18 && Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) >= 7)
                            image = R.raw.sunny;
                        else if (cWeather1.getInt("id") == 800 && (Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) > 18 || Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) < 7))
                            image = R.raw.night;
                        else if (cWeather1.getInt("id") > 800 && Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) <= 18 && Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) >= 7)
                            image = R.raw.cloudy;
                        else if (cWeather1.getInt("id") > 800 && (Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) > 18 || Integer.parseInt(list1.getString("dt_txt").substring(11, 13)) < 7))
                            image = R.raw.cloudy_night;

                        Details.cardViewArrayList.add(new CardView(String.format("%.2f", tempCel1) + "°C", date, time, cWeather1.getString("description"), image));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setCancelable(false);
                alert.create();
                alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.setMessage("Something went wrong!!");

                alert.show();
            }
        });

        requestQueue.add(request);
    }

}
