package com.janbrus.damian.pokladna.Models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.janbrus.damian.pokladna.Network.AsyncJsonResponse;
import com.janbrus.damian.pokladna.Network.Webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Honza on 14.02.2018.
 */

public class ProductsViewModel extends ViewModel implements AsyncJsonResponse {

    private LiveData<ArrayList<Product>> products;
    public LiveData<ArrayList<Product>> getProducts() {
        return products;
    }

    public ProductsViewModel() {
    }

    public void selectProduct(Product p) {
        if (!products.getValue().contains(p)) {
            products.getValue().add(0,p);
        } else {
            int pindex = products.getValue().indexOf(p);
            Product product = products.getValue().get(pindex);
            product.increaseCount();
        }
    }

    public Product get(int position) {
        return products.getValue().get(position);
    }

    public int getProductCount(Product p) {
        int pindex = products.getValue().indexOf(p);
        return products.getValue().get(pindex).getCount();
    }

    public int length() {
        return products.getValue().size();
    }

    public void loadProducts() {
        Webservice ws = new Webservice();
        ws.getProductsJson(null,"aaaaaa");
    }

    @Override
    public void onResponse(JSONArray jsonArray) {
        if (jsonArray == null) return;
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product = new Product(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getDouble("price"));
                products.getValue().add(product);
            }
        } catch (JSONException e) {
            // swallow json exception
            System.out.println(e.toString());
        }
    }
}
