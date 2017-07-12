package com.chxy.usedbook.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chxy.usedbook.vo.Book;
import com.chxy.usedbook.vo.User;
import com.chxy.usedbook.vo.Wish;
import com.chxy.usedbook.vo.WishUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

public class JsonUtils {
	public static String parseResultCode(String paramString) {
		try {
			String code = new JSONObject(paramString).getString("code");//
			return code;//
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
		return null;
	}

	//
	public static String parseResultData(String paramString) {

		try {
			String text = new JSONObject(paramString).getString("data");// data
			return text;//
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
		return null;
	}

	//
	public static User parseResultLoginUserInfo(String paramString) {
		User user = new User();
		try {
			JSONObject userJSONObject = new JSONObject(paramString);// data
			if(!userJSONObject.isNull("user_id")){
				user.setUserId(userJSONObject.getInt("user_id"));
			}
			if(!userJSONObject.isNull("user_age")){
				user.setUserAge(userJSONObject.getInt("user_age"));
			}
			if(!userJSONObject.isNull("user_name")){
				user.setUserName(userJSONObject.getString("user_name"));
			}
			if(!userJSONObject.isNull("user_password")){
				user.setUserPassword(userJSONObject.getString("user_password"));
			}
			if(!userJSONObject.isNull("user_phone")){
				//
				Log.e("zfang", userJSONObject.getString("user_phone"));
				user.setUser_phone(userJSONObject.getString("user_phone"));
			}
			if(!userJSONObject.isNull("user_qq")){
				user.setUserQQ(userJSONObject.getString("user_qq"));
			}
			if(!userJSONObject.isNull("user_sex")){
				user.setUserSex(userJSONObject.getString("user_sex"));
			}
			if(!userJSONObject.isNull("user_instroduce")){
				user.setUserSelfIntro(userJSONObject.getString("user_instroduce"));
			}
			if(!userJSONObject.isNull("state")){
				user.setUserState(userJSONObject.getInt("state"));
			}
			return user;
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
		return null;
	}

	// 解析List列表
	public static List<Book> parseBookData(String paramString) {
		List<Book> bookList = new ArrayList<Book>();
		try {
			JSONArray BookListJSONArray = new JSONObject(paramString)
					.getJSONArray("data1");//

			for (int i = 0;; i++) {
				if (i >= BookListJSONArray.length()) {
					break;
				}
				JSONObject localJSONObject = BookListJSONArray.getJSONObject(i);
				Book mBook = new Book();
				// 和服务器端仔细核对字段
				if (!localJSONObject.isNull("book_main_img")) {
					mBook.setBookImage(localJSONObject
							.getString("book_main_img"));
				}
				if (!localJSONObject.isNull("book_name")) {
					mBook.setBookName(localJSONObject.getString("book_name"));
				}
				// 书籍详情用到的
				if (!localJSONObject.isNull("book_degree")) {
					mBook.setNewOldDegree(localJSONObject
							.getString("book_degree"));
				}
				if (!localJSONObject.isNull("book_time")) {
					mBook.setTime(localJSONObject.getString("book_time"));
				}
				if (!localJSONObject.isNull("origina_price")) {
					mBook.setOriginalPrice(localJSONObject
							.getString("origina_price"));
				}

				if (!localJSONObject.isNull("qq")) {
					mBook.setQq(localJSONObject.getString("qq"));
				}
				if (!localJSONObject.isNull("tel")) {
					mBook.setPhone(localJSONObject.getString("tel"));
				}
				if (!localJSONObject.isNull("vx")) {
					mBook.setWeChat(localJSONObject.getString("vx"));
				}

				if (!localJSONObject.isNull("addition")) {
					mBook.setPersonSay(localJSONObject.getString("addition"));
				}

				if (!localJSONObject.isNull("userInfo")) {
					User user = new User();
					JSONObject userJSONObject = localJSONObject
							.getJSONObject("userInfo");
					user.setUserName(userJSONObject.getString("user_name"));
					user.setUserSex(userJSONObject.getString("user_sex"));
					mBook.setUser(user);
				}
				if (!localJSONObject.isNull("book_kind")) {
					mBook.setBookKind(localJSONObject.getString("book_kind"));
				}
				if (!localJSONObject.isNull("price")) {
					mBook.setBookMoney(localJSONObject.getString("price"));
				}
				bookList.add(mBook);
			}
			return bookList;
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
		return bookList;
	}
	// 解析心愿单List列表
	public static List<Wish> parseWishData(String paramString) {
		List<Wish> wishList = new ArrayList<Wish>();
		try {
			JSONArray WishListJSONArray = new JSONObject(paramString)
					.getJSONArray("data2");//

			for (int i = 0;; i++) {
				if (i >= WishListJSONArray.length()) {
					break;
				}
				JSONObject localJSONObject = WishListJSONArray.getJSONObject(i);
				Wish mWish = new Wish();
				//
				if (!localJSONObject.isNull("userInfo")) {
					WishUser wUser = new WishUser();
					JSONObject wUserJSONObject = localJSONObject
							.getJSONObject("userInfo");
					wUser.setUserName(wUserJSONObject.getString("user_name"));
					wUser.setUserSex(wUserJSONObject.getString("user_sex"));
					wUser.setUserTel(wUserJSONObject.getString("user_phone"));
					wUser.setUserqq(wUserJSONObject.getString("user_"));//qq
					wUser.setUserWC(wUserJSONObject.getString("user_"));//wc
					mWish.setUser(wUser);
				}
				if (!localJSONObject.isNull("wish_time")) {
					mWish.setWishTime(localJSONObject.getString("wish_time"));
				}
				if (!localJSONObject.isNull("wish_name")) {
					mWish.setBookName(localJSONObject.getString("wish_name"));
				}
				/*书的价格，对字段
				 * if(!localJSONObject.isNull("wish_")){
				 * mWish.setBookPrice(localJSONObject.getString("")); }
				 */
				if (!localJSONObject.isNull("wish_repay")) {
					mWish.setWishContent(localJSONObject
							.getString("wish_repay"));
				}
				//8成新
				if (!localJSONObject.isNull(" ")) {
					mWish.setNewDegree(localJSONObject
							.getString(" "));
				}
				//报答方式
				if (!localJSONObject.isNull(" ")) {
					mWish.setWishRmb(localJSONObject
							.getString(" "));
				}
				wishList.add(mWish);
			}
			return wishList;
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
		return null;
	}

}
