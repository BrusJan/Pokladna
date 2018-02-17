package com.janbrus.damian.pokladna;

/**
 * Created by Honza on 20.07.2017.
 */

public class GlobalProperties {
    private static GlobalProperties mInstance= null;

    public int someValueIWantToKeep;

    protected GlobalProperties(){}

    public static synchronized GlobalProperties getInstance(){
        if(null == mInstance){
            mInstance = new GlobalProperties();
        }
        return mInstance;
    }
}
