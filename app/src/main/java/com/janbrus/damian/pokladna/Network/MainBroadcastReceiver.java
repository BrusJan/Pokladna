package com.janbrus.damian.pokladna.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.janbrus.damian.pokladna.CashRegister;

/**
 * Created by rasekl on 8/14/16.
 */
public class MainBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_SALE_REGISTERED_CHANGE="com.github.openeet.openeet.action.SaleRegisteredChange";
    public static final String ACTION_SALE_EXTRA_BKP_LIST="com.github.openeet.openeet.action.extra.BkpList";

    protected CashRegister cashRegisterActivity;

    protected MainBroadcastReceiver(CashRegister activity){
        this.cashRegisterActivity=activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        cashRegisterActivity.processBroadcast(context,intent);
    }
}
