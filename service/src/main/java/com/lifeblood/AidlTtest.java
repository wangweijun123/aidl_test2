package com.lifeblood;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.lifeblood.download.IDownloadInterface;

public class AidlTtest extends Activity {
    /** Called when the activity is first created. */
    public static final String TAG = "wwjjjjj";
    private IDownloadInterface iDownloadInterface = null;

    private ServiceConnection connection = new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder service) {
            iDownloadInterface = IDownloadInterface.Stub.asInterface(service);
            Log.i(TAG,"onServiceConnected Bind Success:" + iDownloadInterface + ", setDownloadListener service:"+service);

            try {
                iDownloadInterface.getDownloadInfoById(1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            iDownloadInterface = null;
            Log.i(TAG,"onServiceDisconnected");
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        /*Intent service = new Intent(this, TestService.class);
        startService(service);*/

        getSystemService(Context.ALARM_SERVICE);
    }

    public void localBindService(View view) {
        Intent service = new Intent("com.lifeblood.download.DownloadService");
        service.setPackage("com.lifeblood");
        bindService(service, connection, BIND_AUTO_CREATE);

    }
}