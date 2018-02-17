package com.janbrus.damian.pokladna.Network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.janbrus.damian.pokladna.CashRegister;

import org.json.JSONArray;

/**
 * Created by Honza on 21.01.2018.
 */

public class Webservice {

    private static final String API_URL = "http://janbrus.cz/eet2/pages/apitest.php";
    public AsyncJsonResponse delegate = null;

    public void getProductsJson(final Context context, String userKey) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("---", "hikizgtf");
        JsonArrayRequest jsonRequest = new JsonArrayRequest
                (Request.Method.GET, getUrlWithParams(), null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("RESPONSE:");
                        System.out.println(response);
                        delegate.onResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });


        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }

    private static String getUrlWithParams() {
        return API_URL + "?products&key=bbbbbbbb";
    }

}
