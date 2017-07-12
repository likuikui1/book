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
 * ���ý���
 * 
 * @author ������
 * 
 */
public class SettingsActivity extends Activity implements OnChangedListener,
		OnClickListener {

	// �����ؼ���ر���
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

		// ��ʼ���ؼ�
		initView();

	}

	private void initView() {
		// ���ָ���ؼ�����Ϊ�ؼ����ü�����
		// ���ذ�ť
		returnImg = (ImageView) findViewById(R.id.set_return_img);
		returnImg.setOnClickListener(this);
		// ��Ϣ������ť
		noticeSlipButton = (SlipButton) findViewById(R.id.set_notice_slipButton);
		noticeSlipButton.SetOnChangedListener(this);
		// ��������
		personSet = (TextView) findViewById(R.id.set_person_txt);
		personSet.setOnClickListener(this);
		// �˺�����
		accountSet = (TextView) findViewById(R.id.set_account_txt);
		accountSet.setOnClickListener(this);
		// ��������
		aboutUs = (TextView) findViewById(R.id.set_aboutus_txt);
		aboutUs.setOnClickListener(this);
		// �˳���¼
		returnLogin = (Button) findViewById(R.id.set_returnlogin_btn);
		returnLogin.setOnClickListener(this);

	}

	@Override
	public void OnChanged(boolean CheckState) {
		// ����ť״̬���ı�ʱ
		if (CheckState)
			Toast.makeText(this, "����...", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "�ر���...", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		// ������ص���¼�
		switch (v.getId()) {
		// ���ذ�ť
		case R.id.set_return_img:
			finish();
			break;
		// ��Ϣ������ť
		case R.id.set_notice_slipButton:

			break;
		// ��������
		case R.id.set_person_txt:

			break;
		// �˺�����
		case R.id.set_account_txt:

			break;
		// ��������
		case R.id.set_aboutus_txt:

			break;
		// �˳���¼
		case R.id.set_returnlogin_btn:
			Intent loginInIntent = new Intent(this, LoginActivity.class);
            startActivity(loginInIntent);
			break;

		default:
			break;
		}

	}
}