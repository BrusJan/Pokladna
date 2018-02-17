package com.janbrus.damian.pokladna.Network;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.janbrus.damian.pokladna.CashRegister;
import com.janbrus.damian.pokladna.MainActivity;
import com.janbrus.damian.pokladna.Models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Honza on 21.01.2018.
 */

@Singleton
public class Repository extends LiveData implements AsyncJsonResponse {

    private Webservice webservice;
    private MainActivity mainActivity;
    public ArrayList<Product> products = new ArrayList<Product>();

    @Inject
    public Repository(Webservice webservice) {
        this.webservice = webservice;
    }

    public void getProductList(Context context, String userKey) {
        mainActivity = (MainActivity) context;
        webservice.delegate = this;
        webservice.getProductsJson(context, userKey);
    }

    @Override
    public void onResponse(JSONArray jsonArray) {
        if (jsonArray == null) return;
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product = new Product(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getDouble("price"));
                products.add(product);
            }
            mainActivity.productsFinished();
        } catch (JSONException e) {
            // swallow json exception
            System.out.println(e.toString());
        }
    }
}
