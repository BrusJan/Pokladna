/*
package com.janbrus.damian.pokladna.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

*/
/**
 * Created by Honza on 20.07.2017.
 *//*


// class representing
//    1) list of all available products to select in cash register
//    2) list of selected products and their count
public class ProductList {
    private Map<Product,Integer> selectedProducts = new LinkedHashMap<Product,Integer>();
    private ArrayList<Product> allProducts;

    public ProductList() {
        allProducts = new ArrayList<Product>();
    }
    public ProductList(ArrayList<Product> products) {
        allProducts = products;
    }

    public void selectProduct(Product p) {
        if (!selectedProducts.containsKey(p)) {
            selectedProducts.put(p,1);
        } else {
            int count = selectedProducts.get(p);
            selectedProducts.put(p,count+1);
        }
    }

    public Product get(int position) {
        List<Product> l = new ArrayList<Product>(selectedProducts.keySet());
        return l.get(position);
    }

    public int getProductCount(Product p) {
        return selectedProducts.get(p).intValue();
    }

    public int length() {
        return selectedProducts.size();
    }
}
*/
