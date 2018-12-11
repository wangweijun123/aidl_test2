package com.lifeblood.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lifeblood.ITestService;

import java.util.ArrayList;
import java.util.List;

public class AidlTestClient extends Activity implements OnClickListener{
	public static final String TAG = "wwjjjjj";

	private Button btn = null;
	private Button btn1 = null;
	private Button btn2 = null;
	private Button btn22;
	private Button btn3 = null;
	private Button btn4 = null;
	private TextView text = null;
	private ITestService tService = null;

	private ServiceConnection connection = new ServiceConnection(){

		public void onServiceConnected(ComponentName name, IBinder service) {
			//
			tService = ITestService.Stub.asInterface(service);
			Log.i(TAG,"Bind Success:" + tService);
		}

		public void onServiceDisconnected(ComponentName name) {
			tService = null;
			Log.i(TAG,"onServiceDisconnected");
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn = (Button)findViewById(R.id.Button01);
        btn1 = (Button)findViewById(R.id.Button02);
		btn22 = (Button)findViewById(R.id.Button022);
		btn22.setOnClickListener(this);
        btn2 = (Button)findViewById(R.id.Button03);
        btn3 = (Button)findViewById(R.id.Button04);
        btn4 = (Button)findViewById(R.id.Button05);
        text = (TextView)findViewById(R.id.TextView01);
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
    
	public void onClick(View v) {
		int viewId = v.getId();
		try{
			if (viewId == btn.getId()){
				Intent service = new Intent("com.wangweijun.xxxx");
				service.setPackage("com.lifeblood");
				bindService(service, connection, BIND_AUTO_CREATE);
			}else if (viewId == btn1.getId()){
				try{
					int si = tService.getAccountBalance();
					text.setText(String.valueOf(si));
				}catch(RemoteException e){
					
				}
			}else if (viewId == btn2.getId()){
				List<String> names = new ArrayList<String>();
				names.add("wangweijunxxxx");
				tService.setOwnerNames(names);
			}else if (viewId == btn3.getId()){
				String[] customerList = new String[1];
				tService.getCustomerList("xx", customerList);
				text.setText("customer : "+customerList[0]);
			}else if (viewId == btn4.getId()){
				tService.showTest();
			} else if (viewId == btn22.getId()) {
				tService.getAccountBalance2();
				Log.i(TAG,"getAccountBalance2 finish");
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		unbindService(connection);
	}
}