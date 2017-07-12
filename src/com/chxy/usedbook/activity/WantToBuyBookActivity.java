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
 * ��
 * @author ����
 *
 */
public class WantToBuyBookActivity extends Activity implements OnClickListener {
	
	private Context mContext=this;
	private ImageView mReturnImageView;//����
	private Button mUpLoadPhoto, mTakePhoto;
	private Uri imageUri; // ͼƬ·��
	public static final int TAKE_PHOTO = 1;
//	private String filename; // ͼƬ����
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

		// ����File�������ڴ洢���պ��ͼƬ
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
		startActivityForResult(intent, TAKE_PHOTO); // �����������
	}

	private void uploadPiture() {
		if (CheckNetWorkUtil.CheckNetWorkUtil(mContext)) {

			getDataFromServer();
		} else {
			Toast.makeText(mContext, "������������", 3000).show();
		}
	}

	private void getDataFromServer() {
		
		String url = XUtil.IP + "";
		XUtil.uploadSinglePicture(url, imagePathList.get(0), new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);					
				// ����
				String code = JsonUtils.parseResultCode(result);
				if (code.equals("0")) {
					
					Toast.makeText(mContext, "�ϴ�ͼƬ�ɹ���", 3000).show();
					
				} else {
					Toast.makeText(mContext, "�������ϴ�", 3000).show();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);
				Toast.makeText(mContext, "����������ά�������Ժ����", 1000).show();
			}
		});		
	}
}