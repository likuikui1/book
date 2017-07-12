package com.chxy.usedbook.activity;

import com.chxy.usedbook.view.OnChangedListener;
import com.chxy.usedbook.view.SlipButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 设置界面
 * 
 * @author 胡楚涵
 * 
 */
public class SettingsActivity extends Activity implements OnChangedListener,
		OnClickListener {

	// 声明控件相关变量
	private ImageView returnImg;
	private SlipButton noticeSlipButton;
	private TextView personSet;
	private TextView accountSet;
	private TextView aboutUs;
	private Button returnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_settings);

		// 初始化控件
		initView();

	}

	private void initView() {
		// 获得指定控件，并为控件设置监听器
		// 返回按钮
		returnImg = (ImageView) findViewById(R.id.set_return_img);
		returnImg.setOnClickListener(this);
		// 消息滑动按钮
		noticeSlipButton = (SlipButton) findViewById(R.id.set_notice_slipButton);
		noticeSlipButton.SetOnChangedListener(this);
		// 个人设置
		personSet = (TextView) findViewById(R.id.set_person_txt);
		personSet.setOnClickListener(this);
		// 账号设置
		accountSet = (TextView) findViewById(R.id.set_account_txt);
		accountSet.setOnClickListener(this);
		// 关于我们
		aboutUs = (TextView) findViewById(R.id.set_aboutus_txt);
		aboutUs.setOnClickListener(this);
		// 退出登录
		returnLogin = (Button) findViewById(R.id.set_returnlogin_btn);
		returnLogin.setOnClickListener(this);

	}

	@Override
	public void OnChanged(boolean CheckState) {
		// 当按钮状态被改变时
		if (CheckState)
			Toast.makeText(this, "打开了...", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "关闭了...", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		// 设置相关点击事件
		switch (v.getId()) {
		// 返回按钮
		case R.id.set_return_img:
			finish();
			break;
		// 消息滑动按钮
		case R.id.set_notice_slipButton:

			break;
		// 个人设置
		case R.id.set_person_txt:

			break;
		// 账号设置
		case R.id.set_account_txt:

			break;
		// 关于我们
		case R.id.set_aboutus_txt:

			break;
		// 退出登录
		case R.id.set_returnlogin_btn:
			Intent loginInIntent = new Intent(this, LoginActivity.class);
            startActivity(loginInIntent);
			break;

		default:
			break;
		}

	}
}