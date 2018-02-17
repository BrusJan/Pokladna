package com.janbrus.damian.pokladna;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import com.janbrus.damian.pokladna.Adapters.ProductsSummaryAdapter;
import com.janbrus.damian.pokladna.Models.Product;
import com.janbrus.damian.pokladna.Adapters.ProductsAdapter;
import com.janbrus.damian.pokladna.Models.ProductsViewModel;
import com.janbrus.damian.pokladna.Network.Repository;
import com.janbrus.damian.pokladna.Network.Webservice;
import com.janbrus.damian.pokladna.Utils.RegisterSaleTask;
import com.janbrus.damian.pokladna.Utils.SaleDtoHelper;


public class CashRegister extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TextView mTextMessage;
    public GridView productsView;
    RecyclerView orderSummary;
    ProductsAdapter productsAdapter;
    ProductsSummaryAdapter summaryAdapter;
    ProductsViewModel productsVM;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_products:
                    //mTextMessage.setText(R.string.title_products_list);
                    productsView.setVisibility(View.VISIBLE);
                    orderSummary.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_products_summary:
                    //mTextMessage.setText(R.string.title_order_summary);
                    productsView.setVisibility(View.GONE);
                    orderSummary.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_print:
                    //mTextMessage.setText(R.string.title_receipt);
                    sendReceiptToEet();
                    return true;
            }
            return false;
        }

    };

    private void sendReceiptToEet() {
        new RegisterSaleTask(getApplicationContext()).execute(SaleDtoHelper.createSaleDto());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the ViewModel.
        productsVM = ViewModelProviders.of(this).get(ProductsViewModel.class);
        // Create the observer which updates the UI.
        final Observer<ArrayList<Product>> productsObserver = new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Product> products) {
                // TODO load products from viewmodel (or maybe repository i dunno) into GUI
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        productsVM.getProducts().observe(this, productsObserver);

        setContentView(R.layout.activity_cash_register);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initView(); // Initialize the GUI Components
        fillData(); // Insert The Data
        setDataAdapters(); // Set the Data Adapters
    }

    // Initialize the GUI Components
    private void initView()
    {
        productsView = (GridView) findViewById(R.id.productsView);
        productsView.setOnItemClickListener(this);
        orderSummary = (RecyclerView) findViewById(R.id.orderSummary);
    }

    // Insert The Data
    private void fillData()
    {
        //repository.getProductList(this,"aaaaaa");
    }

    // Set the Data Adapter
    private void setDataAdapters()
    {
        productsAdapter = new ProductsAdapter(getApplicationContext(), R.layout.product, products);
        productsView.setAdapter(productsAdapter);
        summaryAdapter = new ProductsSummaryAdapter(productsSummary);
        orderSummary.setAdapter(summaryAdapter);
    }

    @Override
    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id)
    {
        String message = "Clicked : " + products.get(position).getName();
        productsSummary.selectProduct(products.get(position));
        summaryAdapter.swapData(productsSummary);
        //Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
    }

    // copies downloaded products into selected products list
    public void initSelectedProducts() {
        productsSummary = new ProductList(products);
    }

    public void processBroadcast(Context context, Intent intent) {
        // got response from eet server
        // refresh list or something that dispays eet messages status
    }
}
