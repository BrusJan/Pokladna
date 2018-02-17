package com.janbrus.damian.pokladna

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.janbrus.damian.pokladna.Models.ProductsViewModel
import android.arch.lifecycle.ViewModelProviders
import com.janbrus.damian.pokladna.Models.Product
import java.util.ArrayList


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var producstsDownloaded = false
    private var certLoaded = false
    var productsVM: ProductsViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productsVM = ProductsViewModel.create(this);
        // Create the observer which updates the UI.
        val productsObserver = Observer<ArrayList<Product>> {
            // TODO load products from viewmodel (or maybe repository i dunno) into GUI
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        productsVM.getProducts().observe(this, productsObserver)
        productsVM.loadProducts();

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<Button>(R.id.butCashRegister)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, CashRegister::class.java)
            startActivity(intent)
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // TODO: load certificate into repository or somewhere instead of calling this
            certFinished()
    }

    fun productsFinished() {
        val productsProgress = findViewById<ProgressBar>(R.id.productsProgress)
        productsProgress.visibility = View.INVISIBLE
        val productsProgressText = findViewById<TextView>(R.id.productsProgressText)
        productsProgressText.text = "Products downloaded"
        producstsDownloaded = true
        setButCashRegisterVisibility()
    }

    fun certFinished() {
        val productsProgress = findViewById<ProgressBar>(R.id.certProgress)
        productsProgress.visibility = View.INVISIBLE
        val productsProgressText = findViewById<TextView>(R.id.certProgressText)
        productsProgressText.text = "Certificate loaded"
        certLoaded = true
        setButCashRegisterVisibility()
    }

    private fun setButCashRegisterVisibility() {
        if (producstsDownloaded && certLoaded) {
            val butCashRegister = findViewById<Button>(R.id.butCashRegister)
            butCashRegister.isEnabled = true
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_cash_register) {
            // Handle the camera action
        } else if (id == R.id.nav_synchro) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {

        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
