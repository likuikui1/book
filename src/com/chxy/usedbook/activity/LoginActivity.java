package com.chxy.usedbook.activity;

import java.util.HashMap;
import java.util.Map;

import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.SharedPreferencesUtil;
import com.chxy.usedbook.utils.XUtil;
import com.chxy.usedbook.view.CircleProgressDialog;
import com.chxy.usedbook.vo.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 用户登录界面
 * @author 高仕锦
 *
 */
public class LoginActivity extends Activity implements OnClickListener{
	private Context mContext=this;
	private Button login_sumbit_bt; // 登陆按钮
	private Button login_register_bt; // 注册按钮
	private EditText userNameEdt; 
	private EditText passWordEdt;	
	private CircleProgressDialog circleProgressDialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		initView();
    }
    
    public void initView() {
    	login_sumbit_bt = (Button) findViewById(R.id.login_sumbit_bt);
    	login_register_bt=(Button)findViewById(R.id.login_register_bt);
    	login_sumbit_bt.setOnClickListener(this);
    	login_register_bt.setOnClickListener(this);
    	passWordEdt=(EditText) findViewById(R.id.login_password_edit);		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_sumbit_bt:
			String user_name = userNameEdt.getText().toString();
			String user_password = passWordEdt.getText().toString();
			
			if(user_name.equals("")||user_password.equals("")){
				Toast.makeText(this, "用户名和密码不能为空", 3000).show();
			}else{
				if (CheckNetWorkUtil.CheckNetWorkUtil(this)) {
					circleProgressDialog = new CircleProgressDialog(mContext);
					circleProgressDialog.setText("正在登录。");
					circleProgressDialog.showDialog();
					login();
				}else {
					Toast.makeText(this, "请检查网络链接", 3000).show();
				}
			}
			
			break;
		case R.id.login_register_bt:
			Intent registerIntent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(registerIntent);
			break;
		default:
			break;
		}
	}

	public void login() {
		String url = XUtil.IP+"user_login.action";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_phone",userNameEdt.getText().toString());
		map.put("user_password",passWordEdt.getText().toString());
		
		XUtil.Post(url, map, new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);
				circleProgressDialog.dismiss();
				if(JsonUtils.parseResultCode(result).equals("0")){
					User user=JsonUtils.parseResultLoginUserInfo(result);
					//登录成功，保存个人信息
					SharedPreferencesUtil.savePwdLoginInfo(mContext,user);
					
					Intent loginIntent = new Intent(mContext,
							MainTabActivity.class);
					startActivity(loginIntent);
				}else{//-1也是访问到了，但解析错误
					Toast.makeText(LoginActivity.this, "请重新输入用户名、密码！", 1000).show();
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
