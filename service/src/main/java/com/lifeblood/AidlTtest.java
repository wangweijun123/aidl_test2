package com.lifeblood;

import android.app.Activity;
import android.os.Bundle;

public class AidlTtest extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        /*Intent service = new Intent(this, TestService.class);
        startService(service);*/
    }
}