// OnDownloadListener.aidl
package com.lifeblood.download;
import com.lifeblood.download.DownloadAppInfo;
// Declare any non-default types here with import statements

interface OnDownloadListener {
    // 非基本类型的参数，必须有in, out, inout修饰
    // 如果aidl 接口要使用实体类,必须声明parcelable aidl文件，同时在方法参数中使用上面的修饰
    void onDownloadInfo(in DownloadAppInfo info);


}
