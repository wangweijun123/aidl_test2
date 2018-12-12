// IDownloadInterface.aidl
package com.lifeblood.download;

import com.lifeblood.download.OnDownloadListener;
import com.lifeblood.download.DownloadAppInfo;
import com.lifeblood.download.Book;
// Declare any non-default types here with import statements

interface IDownloadInterface {
     // 这里OnDownloadListener没有tag修饰，因为他是interface，不是 parcelable数据
     // 只有基本数据与要使用的module才有tag修饰
    void setDownloadListener(OnDownloadListener onDownloadListener);

    DownloadAppInfo getDownloadInfoById(long id);

    oneway void getDownloadInfoByIdAsync(long id);

    List<DownloadAppInfo> getAllDownloadInfo();

    List<Book> getAllBooks();

     //通过三种定位tag做对比试验，观察输出的结果
        Book addBookIn(in Book book);
        Book addBookOut(out Book book);
        Book addBookInout(inout Book book);
}
