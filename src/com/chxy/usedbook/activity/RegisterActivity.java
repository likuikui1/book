package com.chxy.usedbook.activity;

import java.util.HashMap;
import java.util.Map;

import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.XUtil;
import com.chxy.usedbook.view.CustomDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �û�ע�����
 * @author ����
 *
 */
public class RegisterActivity extends Activity implements OnClickListener {
	private Context mContext=this;
    private EditText phoneNumEdt;//ע���ֻ���
    private EditText identifyNumEdt;//ע����֤��
    private TextView sendIdentifyNumTxt;//����ע����֤��
    private EditText pwdEdt;  //ע������
    private Button mRegisterSubmitButton;  //ע�ᰴť
    private CustomDialog mCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        phoneNumEdt =(EditText) findViewById(R.id.register_phone_number);
        identifyNumEdt =(EditText) findViewById(R.id.register_identify_number);
        sendIdentifyNumTxt = (TextView) findViewById(R.id.register_send_identify_number);
        pwdEdt =(EditText) findViewById(R.id.register_password);
        mRegisterSubmitButton = (Button)findViewById(R.id.register_submit);
        mRegisterSubmitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register_send_identify_number:

                break;

            case R.id.register_submit:
            	
            	String phonenumber = phoneNumEdt.getText().toString();
    			String password = pwdEdt.getText().toString();
    			if(phonenumber.equals("")||password.equals("")){
    				Toast.makeText(this, "�ֻ��ź����벻��Ϊ��", 3000).show();
    			}else{
    				if (CheckNetWorkUtil.CheckNetWorkUtil(this)) {
    					dialog();
    					register();
    				}else {
    					Toast.makeText(this, "������������", 3000).show();
    				}
    			}               
               
                break;

            default:
                break;
        }

    }
    /**
     * ��ʾע��ɹ�����
     */
    private void dialog() {

    	mCustomDialog = new CustomDialog(RegisterActivity.this);
        mCustomDialog.show();
        mCustomDialog.setYesOnclickListener(new CustomDialog.onYesOnclickListener() {
        @Override
        public void onYesClick() {
             mCustomDialog.dismiss();
           Toast.makeText(mContext,"�����--ȷ��--��ť", Toast.LENGTH_SHORT).show();
        }
       });
	}

	public void register() {
    	String url = XUtil.IP+"user_regist.action";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_phone",phoneNumEdt.getText().toString());
		map.put("user_password",pwdEdt.getText().toString());
		
		XUtil.Post(url, map, new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);
				
				Log.e("result", result.toString());
//				String code = JsonUtils.parseResultCode(result);
				if(JsonUtils.parseResultCode(result).equals("0")){
					Intent registerIntent = new Intent(mContext,LoginActivity.class);
					startActivity(registerIntent);
				}else{
					Toast.makeText(mContext, "��������д��", 1000).show();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);
				Toast.makeText(mContext, "����������ά�����Ժ���ʡ�����", 1000).show();
			}
		});
	
	}
}