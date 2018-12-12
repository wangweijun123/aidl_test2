// IDownloadInterface.aidl
package com.lifeblood.download;

import com.lifeblood.download.OnDownloadListener;
import com.lifeblood.download.DownloadAppInfo;
import com.lifeblood.download.Book;
// Declare any non-default types here with import statements

interface IDownloadInterface {

    void setDownloadListener(String pkgName, OnDownloadListener onDownloadListener);

    DownloadAppInfo getDownloadInfoById(long id);

    oneway void getDownloadInfoByIdAsync(long id);

    List<DownloadAppInfo> getAllDownloadInfo();



    List<Book> getAllBooks();

     //通过三种定位tag做对比试验，观察输出的结果
     // 参数tag(in,out) 是相对于服务端来说的
        Book addBookIn(in Book book);
        Book addBookOut(out Book book);
        Book addBookInout(inout Book book);
}
