package com.chxy.usdbook.application;

import org.xutils.x;

import android.app.Application;

public class MyApplication extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
        x.Ext.setDebug(false); //���debug��־��������Ӱ������
	}

}
