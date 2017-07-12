package com.chxy.usedbook.activity;

import java.util.HashMap;
import java.util.Map;

import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.XUtil;
import com.chxy.usedbook.view.CircleProgressDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PersonalMessageActivity extends Activity implements OnClickListener {
	private Context mContext=this;
	private Button mButton;
	private ImageView mImageView;	
	private EditText nameEdt;
	private EditText phoneEdt;
	private EditText qqEdt;
	private RadioGroup mRG;
	private EditText addressEdt;
	private EditText toSayEdt;
	private CircleProgressDialog circleProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_message);
		
		initView();		
	}
	private void initView() {

		mImageView=(ImageView) findViewById(R.id.person_message_return_img);
		mImageView.setOnClickListener(this);
		mButton=(Button) findViewById(R.id.submit_btn);
		mButton.setOnClickListener(this);
		
		nameEdt=(EditText)findViewById(R.id.name_edt);
		phoneEdt=(EditText)findViewById(R.id.phone_edt);
		qqEdt=(EditText)findViewById(R.id.qq_edt);
		addressEdt=(EditText)findViewById(R.id.place_edt);
		toSayEdt=(EditText)findViewById(R.id.mine_said_edt);
		mRG=(RadioGroup) findViewById(R.id.sex_rg);
//		bar = new ProgressBar(this);
//		bar.setVisibility(View.GONE);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.person_message_return_img:
			finish();
			break;
			
		case R.id.submit_btn:

			if (CheckNetWorkUtil.CheckNetWorkUtil(this)) {
//				bar.setVisibility(View.VISIBLE);
				circleProgressDialog = new CircleProgressDialog(mContext);
				circleProgressDialog.setText("正在登录。。。");
				circleProgressDialog.showDialog();
				getDataFromServer();
			}else {
				Toast.makeText(this, "请检查网络链接", 3000).show();
			}
			break;

		default:
			break;
		}
		
	}
	private void getDataFromServer() {

		String name=nameEdt.getText().toString().trim();
		String phone=phoneEdt.getText().toString().trim();
		String qq=qqEdt.getText().toString().trim();
		String address=addressEdt.getText().toString().trim();
		String toSay=toSayEdt.getText().toString().trim();
		String sex="男";
		if (mRG.getCheckedRadioButtonId()==R.id.sex_woman_rb) {
			 sex="女";
		}else{
			 sex="男";
		}
		String url = XUtil.IP+"user_UserMessage.action";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name",name);
		map.put("user_phone",phone);
		map.put("user_qq",qq);
		map.put("user_sex",sex);
		map.put("user_addr",address);
		map.put("user_instro",toSay);		
		XUtil.Post(url, map, new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);
//				bar.setVisibility(View.GONE);
				circleProgressDialog.dismiss();
				//解析
				String code = JsonUtils.parseResultCode(result);
				if(code.equals("0")){
					Toast.makeText(mContext, "提交成功！", 3000).show();
					finish();
				}else{//-1也是访问到了，但解析错误
					Toast.makeText(mContext, "重新填写个人信息，重发完善。", 3000).show();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);
				Toast.makeText(mContext, "服务器正在维护，稍后访问。。。", 1000).show();
			}
		});
	}	
}
