package com.lifeblood.download;

import android.os.Parcel;
import android.os.Parcelable;

public class DownloadAppInfo implements Parcelable {
	public String packageName;
	public long id;



	public static final Creator<DownloadAppInfo> CREATOR =
			new Creator<DownloadAppInfo>() {

		@Override
		public DownloadAppInfo createFromParcel(Parcel source) {
			return new DownloadAppInfo(source);
		}

		@Override
		public DownloadAppInfo[] newArray(int size) {
			return new DownloadAppInfo[size];
		}
	};

	public DownloadAppInfo() {

	}

	public DownloadAppInfo(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {
		packageName = source.readString();
		id = source.readLong();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(packageName);
		dest.writeLong(id);
	}

	@Override
	public String toString() {
		return "packageName:"+packageName+", id:"+id;
	}
}