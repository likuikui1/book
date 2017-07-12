package com.chxy.usedbook.utils;

import com.chxy.usedbook.vo.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class SharedPreferencesUtil {

	/*
	 * ���ܣ����������¼���������صĵ�½���ƺ�uid
	 */
	public static void savePwdLoginInfo(Context context, User user) {
		SharedPreferences sharedPre = context.getSharedPreferences("loginUser",
				context.MODE_PRIVATE);
		
		
		Editor editor = sharedPre.edit();
		editor.putString("user_phone", user.getUser_phone());//.getUserName()
		editor.putInt("user_state", user.getUserState());
		editor.commit();
	}
	
	
	/*
	 * ���ܣ���ȡ�����¼����
	 */
	public static String getPwdLoginToken(Context context) {
		// ���Զ�ȡ��½����
		String userToken;
		SharedPreferences sharedPre = context.getSharedPreferences("loginUser",
				context.MODE_PRIVATE);
		userToken = sharedPre.getString("userToken", "");
		return userToken;

	}

	/*
	 * ���ܣ���ȡ�����¼uid
	 */
	public static String getPwdLoginPhone(Context context) {
		// ���Զ�ȡ��½����
		String userPhone;
		SharedPreferences sharedPre = context.getSharedPreferences("loginUser",
				context.MODE_PRIVATE);
		userPhone = sharedPre.getString("user_phone", "");
		return userPhone;
	}
	
	/*
	 * ���ܣ���ȡ�û��Ƿ����Ƹ�����Ϣ
	 */
	public static int getPwdLoginUserState(Context context) {
		// ���Զ�ȡ������Ϣ�Ƿ����Ƶı�ʶ
		int user_state;
		SharedPreferences sharedPre = context.getSharedPreferences("loginUser",
				context.MODE_PRIVATE);
		user_state = sharedPre.getInt("user_state", -1);
		return user_state;
	}
}
