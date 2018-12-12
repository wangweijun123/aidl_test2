// IDownloadInterface.aidl
package com.lifeblood.download;

import com.lifeblood.download.OnDownloadListener;
import com.lifeblood.download.DownloadAppInfo;
import com.lifeblood.download.Book;
// Declare any non-default types here with import statements

interface IDownloadInterface {

    void setDownloadListener(OnDownloadListener onDownloadListener);

    DownloadAppInfo getDownloadInfoById(long id);

    oneway void getDownloadInfoByIdAsync(long id);

    List<DownloadAppInfo> getAllDownloadInfo();

    List<Book> getAllBooks();
}
