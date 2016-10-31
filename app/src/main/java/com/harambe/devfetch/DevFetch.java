package com.harambe.devfetch;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by Sandeep on 01-11-2016.
 */

public class DevFetch extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName("DevFetchPrefs")
                .setUseDefaultSharedPreference(true)
                .build();
    }
}
