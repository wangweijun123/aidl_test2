// OnDownloadListener.aidl
package com.lifeblood.download;
import com.lifeblood.download.DownloadAppInfo;
// Declare any non-default types here with import statements

interface OnDownloadListener {
    // 非基本类型的参数，必须有in, out, inout修饰
    void onDownloadInfo(in DownloadAppInfo info);

}
