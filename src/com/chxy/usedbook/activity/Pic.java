package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xutils.x;
import org.xutils.view.annotation.ContentView;

import com.chxy.usedbook.adapter.MainGVAdapter;
import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.ScreenUtils;
import com.chxy.usedbook.utils.Utility;
import com.chxy.usedbook.utils.XUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class Pic extends Activity {
	private Context mContext=this;
	private MainGVAdapter adapter;
	private ArrayList<String> imagePathList;

	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pic);

		ScreenUtils.initScreen(this);

		TextView titleTV = (TextView) findViewById(R.id.topbar_title_tv);
		Button selectImgBtn = (Button) findViewById(R.id.main_select_image);
		GridView mainGV = (GridView) findViewById(R.id.main_gridView);
		//新增的上传
		mButton = (Button) findViewById(R.id.topbar_right_btn);
		mButton.setText("开始上传");
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent mIntent = new Intent(mContext, WantToSellBookActivity.class);
				mIntent.putExtra("img", imagePathList.get(0));
				startActivity(mIntent);
				/*if (CheckNetWorkUtil.CheckNetWorkUtil(mContext)) {

					String url = XUtil.Server1 + "book_sell_Book.action";
					XUtil.uploadPicture(url, imagePathList.get(0), new MyCallBack<String>() {

						@Override
						public void onSuccess(String result) {
							super.onSuccess(result);
							
							// 解析
							String code = JsonUtils.parseResultCode(result);
							if (code.equals("0")) {//
								
								Toast.makeText(mContext, "发布成功！", 3000).show();
								
							} else {// -1也是访问到了，但解析错误
								Toast.makeText(mContext, "解析错误", 3000).show();
							}
						}

						@Override
						public void onError(Throwable ex, boolean isOnCallback) {
							super.onError(ex, isOnCallback);
							Toast.makeText(mContext, "访问失败", 3000).show();
						}
					});
				} else {
					Toast.makeText(mContext, "请检查网络链接", 3000).show();
				}*/
				
			}

		});
		titleTV.setText(R.string.app_name);
		imagePathList = new ArrayList<String>();

		adapter = new MainGVAdapter(this, imagePathList);
		mainGV.setAdapter(adapter);

		selectImgBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 璺宠浆鑷虫渶缁堢殑閫夋嫨鍥剧墖椤甸潰
				Intent intent = new Intent(Pic.this, PhotoWallActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		int code = intent.getIntExtra("code", -1);
		if (code != 100) {
			return;
		}

		ArrayList<String> paths = intent.getStringArrayListExtra("paths");

		// 娣诲姞锛屽幓閲�
		boolean hasUpdate = false;
		for (String path : paths) {
			Utility.showToast(this, path);
			if (!imagePathList.contains(path)) {
				// 鏈€澶�9寮�
				if (imagePathList.size() == 9) {
					Utility.showToast(this, "鏈€澶氬彲娣诲姞9寮犲浘鐗囥€�");
					break;
				}

				imagePathList.add(path);
				hasUpdate = true;
			}
		}

		if (hasUpdate) {
			adapter.notifyDataSetChanged();
			mButton.setVisibility(View.VISIBLE);
		}
	}
}
