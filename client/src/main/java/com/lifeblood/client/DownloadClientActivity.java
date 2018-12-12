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

import com.lifeblood.download.Book;
import com.lifeblood.download.DownloadAppInfo;
import com.lifeblood.download.IDownloadInterface;
import com.lifeblood.download.OnDownloadListener;

import java.util.List;

public class DownloadClientActivity extends Activity implements OnClickListener{
	public static final String TAG = "wwjjjjj";

	private Button btn = null;
	private Button btn1 = null;
	private Button btn2 = null;
	private Button btn22;
	private Button btn3 = null;
	private Button btn4 = null;
	private TextView text = null;
	private IDownloadInterface iDownloadInterface = null;

	private ServiceConnection connection = new ServiceConnection(){

		public void onServiceConnected(ComponentName name, IBinder service) {
			iDownloadInterface = IDownloadInterface.Stub.asInterface(service);
			Log.i(TAG,"Bind Success:" + iDownloadInterface + ", setDownloadListener "+downloadListener);
			try {
				iDownloadInterface.setDownloadListener(getApplicationContext().getPackageName(),
						downloadListener);
				service.linkToDeath(new IBinder.DeathRecipient() {
					@Override
					public void binderDied() {
						Log.i(TAG," 服务器死了。。。");
					}
				}, 0);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		public void onServiceDisconnected(ComponentName name) {
			iDownloadInterface = null;
			Log.i(TAG,"onServiceDisconnected");
		}
	};


	private DownloadListenerImpl downloadListener;



	private class DownloadListenerImpl extends OnDownloadListener.Stub {
		@Override
		public void onDownloadInfo(DownloadAppInfo info) {
			Log.i(TAG,"client onDownloadInfo " + info);
		}
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		downloadListener = new DownloadListenerImpl();

        setContentView(R.layout.activity_download);
        
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
				Intent service = new Intent("com.lifeblood.download.DownloadService");
				service.setPackage("com.lifeblood");
				bindService(service, connection, BIND_AUTO_CREATE);
			}else if (viewId == btn1.getId()){

				DownloadAppInfo downloadAppInfo = iDownloadInterface.getDownloadInfoById(1);
				Log.i(TAG,"客户端收到downloadAppInfo:"+downloadAppInfo + ", "+downloadAppInfo.hashCode());
			}else if (viewId == btn2.getId()){
				final List<DownloadAppInfo> allDownloadInfo = iDownloadInterface.getAllDownloadInfo();
				for (int i = 0; i < allDownloadInfo.size(); i++) {
					Log.i(TAG,"客户端收到downloadAppInfo:"+allDownloadInfo.get(i) + ", "+allDownloadInfo.get(i).hashCode());
				}
			}else if (viewId == btn3.getId()){
				List<Book> allBooks = iDownloadInterface.getAllBooks();
				for (int i = 0; i < allBooks.size(); i++) {
					Log.i(TAG,""+allBooks.get(i) + ", "+allBooks.get(i).hashCode());
				}
			}else if (viewId == btn4.getId()){


			} else if (viewId == btn22.getId()) {
				iDownloadInterface.getDownloadInfoByIdAsync(1);
				Log.i(TAG,"getAccountBalance2 finish");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void testIn(View view) {
		Book info = new Book();
		info.setName("xx");
		info.setPrice(1);
		try {
			Book returnBook  = iDownloadInterface.addBookIn(info);
			Log.i(TAG,"testIn :"+returnBook);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testOut(View view) {
		Book info = new Book();
		info.setName("xx");
		info.setPrice(1);
		try {
			Book returnBook  = iDownloadInterface.addBookOut(info);
			Log.i(TAG,"testOut :"+returnBook);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void testInout(View view) {
		Book info = new Book();
		info.setName("xx");
		info.setPrice(1);
		try {
			Book returnBook  = iDownloadInterface.addBookInout(info);
			Log.i(TAG,"testInout :"+returnBook);
		} catch (RemoteException e) {
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