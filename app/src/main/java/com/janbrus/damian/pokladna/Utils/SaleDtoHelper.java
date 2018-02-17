package com.janbrus.damian.pokladna.Utils;

import openeet.lite.EetSaleDTO;

/**
 * Created by Honza on 11.02.2018.
 */

public class SaleDtoHelper {
    public static EetSaleDTO createSaleDto() {
        EetSaleDTO retval = new EetSaleDTO();
        retval.bkp = "jb nejaky bkp";
        retval.celk_trzba = "777.7";
        return retval;
    }
}
