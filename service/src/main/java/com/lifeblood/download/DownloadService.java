package com.lifeblood.download;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DownloadService extends Service {
    public static final String TAG = "wwjjjjj";

    private OnDownloadListener mOnDownloadListener;
    class DownloadInterfaceImpl extends IDownloadInterface.Stub {

        @Override
        public void setDownloadListener(OnDownloadListener onDownloadListener) throws RemoteException {
            Log.i(TAG,"DownloadService setDownloadListener  onDownloadListener:"+onDownloadListener);
            mOnDownloadListener = onDownloadListener;
        }

        @Override
        public DownloadAppInfo getDownloadInfoById(long id) {
            Log.i(TAG, "getDownloadInfoById id:"+id+", tid:"+Thread.currentThread());
            DownloadAppInfo info = new DownloadAppInfo();
            info.id = id;
            info.packageName = "xxxx";
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG,"返回 downloadAppInfo:"+info + ", "+info.hashCode());
            return info;
        }

        @Override
        public void getDownloadInfoByIdAsync(long id) {
            Log.i(TAG, "getDownloadInfoByIdAsync id:"+id+", tid:"+Thread.currentThread());
            DownloadAppInfo info = new DownloadAppInfo();
            info.id = id;
            info.packageName = "xxxx";
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.i(TAG,"返回 downloadAppInfo:"+info + ", "+info.hashCode());
                mOnDownloadListener.onDownloadInfo(info);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public List<DownloadAppInfo> getAllDownloadInfo() {
            Log.i(TAG, "getAllDownloadInfo tid:"+Thread.currentThread());
            List<DownloadAppInfo> list = new ArrayList<>(10);
            for (int i=0; i<10; i++) {
                DownloadAppInfo info = new DownloadAppInfo();
                info.id = i;
                info.packageName = "xxxx";
                list.add(info);
            }

            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        public List<Book> getAllBooks() {
            Log.i(TAG, "getAllDownloadInfo tid:"+Thread.currentThread());
            List<Book> list = new ArrayList<>(10);
            for (int i=0; i<10; i++) {
                Book info = new Book();
                info.name = ""+i;
                info.price = i;
                list.add(info);
            }

            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        public Book addBookIn(Book book) throws RemoteException {
            Log.i(TAG, "addBookIn "+book);
                    book.price = 1000;
            return book;
        }

        @Override
        public Book addBookOut(Book book) throws RemoteException {
            Log.i(TAG, "addBookOut "+book);
            book.price = 1000;
            return book;
        }

        @Override
        public Book addBookInout(Book book) throws RemoteException {
            Log.i(TAG, "addBookInout "+book);
            book.price = 1000;
            return book;
        }
    }

    private DownloadInterfaceImpl downloadInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"DownloadService onCreate:"+", tid:"+Thread.currentThread());
        downloadInterface = new DownloadInterfaceImpl();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"DownloadService onBind: downloadInterface:"+downloadInterface);
        return downloadInterface;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
