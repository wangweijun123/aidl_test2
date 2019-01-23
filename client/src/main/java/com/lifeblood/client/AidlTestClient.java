package com.lifeblood.client;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.lifeblood.ITestService;

import java.lang.reflect.Field;
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
        setContentView(R.layout.actvity_aidl_test);
        
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

	public void startDownload(View view) {
    	startActivity(new Intent(this, DownloadClientActivity.class));
	}

	public void testToken(View view) {
    	// 获取当前activity token(Ibinder对象传给dialog弹出，相当于在应用内部弹出的dialog一样)
		IBinder token = getActivityToken();
		getToken(getWindow());
		// 这里为什么传context也可以弹出窗口,因为activity的token传给了dialog,不然的话只能用this(activity)
		Dialog dialog = new Dialog(getApplicationContext());
		dialog.setTitle("xxxxx");
		// 这里很重要
		Window window = dialog.getWindow();
		WindowManager.LayoutParams l = window.getAttributes();
		l.token = token;
		window.setAttributes(l);
		dialog.show();

		getTokenFromDialog(dialog.getWindow());
	}

	private IBinder getActivityToken() {
		try {
			Field mTokenField = this.getClass().getSuperclass().getDeclaredField("mToken");
			mTokenField.setAccessible(true);
			IBinder mToken = (IBinder) mTokenField.get(this);
			Log.i(TAG, "getActivityToken mToken:"+mToken);
			return mToken;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void getToken(Window window) {

		try {
			Field mTokenField = window.getClass().getSuperclass().getDeclaredField("mAppToken");
			mTokenField.setAccessible(true);
			IBinder mToken = (IBinder) mTokenField.get(window);
			Log.i(TAG, "getToken  mToken:"+mToken);
		} catch (Exception e) {
			e.printStackTrace();
			Log.i(TAG, "Exception:");
		}
	}

	private void getTokenFromDialog(Window window) {
		WindowManager.LayoutParams l = window.getAttributes();;
		Log.i(TAG, "获取dialog mToken:"+l.token);
	}
}