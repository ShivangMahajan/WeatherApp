package com.sabbey.weatherapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public VolleySingleton(Context context) {

        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }

    public static synchronized VolleySingleton getInstance(Context context){
        if (volleySingleton == null)
            volleySingleton = new VolleySingleton(context);
        return volleySingleton;
    }

    public RequestQueue requestQueue(){
        return requestQueue;
    }



}
