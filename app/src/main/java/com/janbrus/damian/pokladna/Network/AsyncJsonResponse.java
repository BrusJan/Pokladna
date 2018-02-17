package com.janbrus.damian.pokladna.Network;

import org.json.JSONArray;

/**
 * Created by jbrus on 30.1.2018.
 */

public interface AsyncJsonResponse {
        void onResponse(JSONArray output);
}
