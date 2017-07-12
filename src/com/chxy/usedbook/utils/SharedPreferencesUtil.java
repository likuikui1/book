package com.chxy.usedbook.utils;

import com.chxy.usedbook.vo.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class SharedPreferencesUtil {

	/*
	 * 功能：保存密码登录服务器返回的登陆令牌和uid
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
	 * 功能：获取密码登录令牌
	 */
	public static String getPwdLoginToken(Context context) {
		// 尝试读取登陆令牌
		String userToken;
		SharedPreferences sharedPre = context.getSharedPreferences("loginUser",
				context.MODE_PRIVATE);
		userToken = sharedPre.getString("userToken", "");
		return userToken;

	}

	/*
	 * 功能：获取密码登录uid
	 */
	public static String getPwdLoginPhone(Context context) {
		// 尝试读取登陆令牌
		String userPhone;
		SharedPreferences sharedPre = context.getSharedPreferences("loginUser",
				context.MODE_PRIVATE);
		userPhone = sharedPre.getString("user_phone", "");
		return userPhone;
	}
	
	/*
	 * 功能：获取用户是否完善个人信息
	 */
	public static int getPwdLoginUserState(Context context) {
		// 尝试读取个人信息是否完善的标识
		int user_state;
		SharedPreferences sharedPre = context.getSharedPreferences("loginUser",
				context.MODE_PRIVATE);
		user_state = sharedPre.getInt("user_state", -1);
		return user_state;
	}
}
