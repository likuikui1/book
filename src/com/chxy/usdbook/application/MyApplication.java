package com.chxy.usdbook.application;

import org.xutils.x;

import android.app.Application;

public class MyApplication extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
	}

}
