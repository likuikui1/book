package com.chxy.usedbook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
/**
 * 我的未登录界面
 * @author 高仕锦
 *
 */
public class MineNotLoginActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mine_notlogin);
		
		initView();
	}
	public void initView() {
    	
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 1:
			
			break;
		case 0:
			
			break;
		default:
			break;
		}

	}

	
}
