package com.chxy.usedbook.activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.XUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 求购
 * @author 范晨
 *
 */
public class WantToBuyBookActivity extends Activity implements OnClickListener {
	
	private Context mContext=this;
	private ImageView mReturnImageView;//返回
	private Button mUpLoadPhoto, mTakePhoto;
	private Uri imageUri; // 图片路径
	public static final int TAKE_PHOTO = 1;
//	private String filename; // 图片名称
	private ArrayList<String> imagePathList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_want_to_buy_book);
	}

	private void initView() {
		mReturnImageView =(ImageView)findViewById(R.id.wtobuy_return_img);
		mUpLoadPhoto = (Button) findViewById(R.id.wtobuy_upload_photos_btn);
		mTakePhoto = (Button) findViewById(R.id.wtobuy_take_photos_btn);

		mReturnImageView.setOnClickListener(this);
		mUpLoadPhoto.setOnClickListener(this);
		mTakePhoto.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.wtobuy_return_img:
			finish();
			break;
			
		case R.id.wtobuy_upload_photos_btn:
			
			uploadPiture();
			break;
			
		case R.id.wtobuy_take_photos_btn:
			
			takePhoto();
			break;
		default:
			break;
		}
	}
	private void takePhoto() {

		// 创建File对象，用于存储拍照后的图片
		File outputImage = new File(Environment.getExternalStorageDirectory(), "output_image.jpg");
		try {
			/*if (outputImage.exists()) {
				outputImage.delete();
			}*/
			outputImage.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageUri = Uri.fromFile(outputImage);
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, TAKE_PHOTO); // 启动相机程序
	}

	private void uploadPiture() {
		if (CheckNetWorkUtil.CheckNetWorkUtil(mContext)) {

			getDataFromServer();
		} else {
			Toast.makeText(mContext, "请检查网络链接", 3000).show();
		}
	}

	private void getDataFromServer() {
		
		String url = XUtil.IP + "";
		XUtil.uploadSinglePicture(url, imagePathList.get(0), new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);					
				// 解析
				String code = JsonUtils.parseResultCode(result);
				if (code.equals("0")) {
					
					Toast.makeText(mContext, "上传图片成功！", 3000).show();
					
				} else {
					Toast.makeText(mContext, "请重新上传", 3000).show();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);
				Toast.makeText(mContext, "服务器正在维护，请稍后访问", 1000).show();
			}
		});		
	}
}