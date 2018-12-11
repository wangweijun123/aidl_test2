package com.lifeblood;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lifeblood.ITestService.Stub;

import java.util.List;

public class TestService extends Service {
	public static final String TAG = "wwjjjjj";

	private Context mContext = null;
	
	private Stub binder = new Stub(){
		private String name = null;
		public int getAccountBalance() throws RemoteException {
			Log.i(TAG,"tid:"+Thread.currentThread().getId());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 100000;
		}

		public void getAccountBalance2() throws RemoteException {
			Log.i(TAG,"tid:"+Thread.currentThread().getId());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Log.i(TAG,"sevice getAccountBalance2 finised");
		}

		public int getCustomerList(String branch, String[] customerList)
				throws RemoteException {
			customerList[0] = name;
			Log.i(TAG,"Name:"+branch);
			return 0;
		}

		public void setOwnerNames(List<String> names) throws RemoteException {
			name = names.get(0);
			System.out.println("Size:"+names.size()+"=="+names.get(0));
		}

		public void showTest() throws RemoteException {
			System.out.println("showTest");
			Intent intent = new Intent(mContext, AidlTtest.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
	};
	@Override
	public IBinder onBind(Intent intent) {
		mContext = this;
		return binder;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
}
