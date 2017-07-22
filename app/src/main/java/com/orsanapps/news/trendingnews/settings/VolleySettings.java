package com.orsanapps.news.trendingnews.settings;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by Home on 1/26/2017.
 */

public class VolleySettings {
    public static int VOLLEY_TIMEOUT = 30000;

    public static int DEFAULT_MAX_RETRIES = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;

    public static float DEFAULT_BACKOFF_MULT = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

}
