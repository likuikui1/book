package com.chxy.usedbook.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetWorkUtil {
	public static Boolean CheckNetWorkUtil(Context mContext) {
		try {
			ConnectivityManager localConnectivityManager = (ConnectivityManager) mContext
					.getSystemService("connectivity");
			boolean mIsConnect = false;
			if (localConnectivityManager != null) {
				NetworkInfo localNetworkInfo = localConnectivityManager
						.getActiveNetworkInfo();
				mIsConnect = false;
				if (localNetworkInfo != null) {
					boolean mConnect = localNetworkInfo.isConnected();
					mIsConnect = false;
					if (mConnect) {
						NetworkInfo.State localState1 = localNetworkInfo
								.getState();
						NetworkInfo.State localState2 = NetworkInfo.State.CONNECTED;
						mIsConnect = false;
						if (localState1 == localState2) {
							mIsConnect = true;
						}
					}
				}
			}
			return mIsConnect;
		} catch (Exception localException) {
		}
		return false;
	}
}
