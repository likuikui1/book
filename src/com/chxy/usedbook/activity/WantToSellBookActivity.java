package com.chxy.usedbook.activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chxy.usedbook.adapter.WantToSellBookGridViewAdapter;
import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.SharedPreferencesUtil;
import com.chxy.usedbook.utils.XUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 我要卖书界面
 * 
 * @author 蒋治国
 * 
 */
public class WantToSellBookActivity extends Activity implements OnClickListener {

	private Context mContext = this;
	private ImageView mReturnImg;// 返回
	private TextView mTextView;
	private TextView nTextView;
	private ImageView searchImageView;
	private ImageView mImageView;
	private LinearLayout mLinearLayout;
	private GridView gridview;
	private ImageView mtenImageView;
	private ImageView meightImageView;
	private ImageView mfiveImageView;
	private ImageView uploadImageView;
	private ImageView takephotoImageView;
	private Uri imageUri;
	private Button mButton;
	private EditText bookNameEdt;
	private TextView selectTxt;
	private EditText originalPriceEdt;
	private EditText transferPriceEdt;
	private EditText bookNumberEdt;
	private EditText phoneNumberEdt;
	private EditText qqNumberEdt;
	private EditText weChatEdt;
	private EditText publisherSaidEdt;
	private TextView tenNewTxt;
	private TextView eightNewTxt;
	private TextView fiveNewTxt;
	private String imgData;
	public static final int TAKE_PHOTO = 1;
	public static int tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_want_to_sell_book);

		Intent imgIntent = getIntent();
		imgData = imgIntent.getStringExtra("img");
		
		initView();
	}

	private void initView() {

		mReturnImg = (ImageView) findViewById(R.id.books_market_title_return_img);
		mReturnImg.setOnClickListener(this);
		searchImageView = (ImageView) findViewById(R.id.books_market_title_search_img);
		mImageView = (ImageView) findViewById(R.id.books_market_title_img);
		nTextView = (TextView) findViewById(R.id.main_title);
		mTextView = (TextView) findViewById(R.id.please_select);

		mLinearLayout = (LinearLayout) findViewById(R.id.select_linear);
		mLinearLayout.setOnClickListener(this);

		mtenImageView = (ImageView) findViewById(R.id.icon_radio_ten_btn);
		mtenImageView.setOnClickListener(this);
		meightImageView = (ImageView) findViewById(R.id.icon_radio_eight_btn);
		meightImageView.setOnClickListener(this);
		mfiveImageView = (ImageView) findViewById(R.id.icon_radio_five_btn);
		mfiveImageView.setOnClickListener(this);

		uploadImageView = (ImageView) findViewById(R.id.upload_picture);
		uploadImageView.setOnClickListener(this);
		takephotoImageView = (ImageView) findViewById(R.id.take_photo);
		takephotoImageView.setOnClickListener(this);

		mButton = (Button) findViewById(R.id.publish_btn);
		mButton.setOnClickListener(this);

		bookNameEdt = (EditText) findViewById(R.id.book_name_edt);
		selectTxt = (TextView) findViewById(R.id.please_select);
		originalPriceEdt = (EditText) findViewById(R.id.original_price_edt);
		transferPriceEdt = (EditText) findViewById(R.id.transfer_price_edt);
		bookNumberEdt = (EditText) findViewById(R.id.book_number_edt);
		phoneNumberEdt = (EditText) findViewById(R.id.phone_number_edt);
		qqNumberEdt = (EditText) findViewById(R.id.qq_number_edt);
		weChatEdt = (EditText) findViewById(R.id.we_chat_edt);
		publisherSaidEdt = (EditText) findViewById(R.id.publisher_said_edt);

		tenNewTxt = (TextView) findViewById(R.id.new_degree);
		eightNewTxt = (TextView) findViewById(R.id.eight_new_degree);
		fiveNewTxt = (TextView) findViewById(R.id.five_new_degree);

		searchImageView.setVisibility(View.GONE);
		mImageView.setVisibility(View.GONE);
		nTextView.setText("我要卖书");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.books_market_title_return_img:
			finish();
			break;
		case R.id.take_photo:
			takephotoImageView
					.setImageResource(R.drawable.icon_take_photo_pressed);

			takePhoto();
			uploadImageView
					.setImageResource(R.drawable.icon_upload_photo_normal);
			break;

		case R.id.upload_picture:
			uploadImageView
					.setImageResource(R.drawable.icon_upload_photo_pressed);
			takephotoImageView
					.setImageResource(R.drawable.icon_take_photo_normal);
			// 新加的_图片选择
			Intent uPIntent = new Intent(WantToSellBookActivity.this, Pic.class);
			startActivity(uPIntent);
			break;

		case R.id.select_linear:
			uploadImageView
					.setImageResource(R.drawable.icon_upload_photo_normal);

			selectAlertDialog();
			break;

		case R.id.icon_radio_ten_btn:
			mtenImageView.setImageResource(R.drawable.icon_radio_p);
			meightImageView.setImageResource(R.drawable.icon_radio_n);
			mfiveImageView.setImageResource(R.drawable.icon_radio_n);
			tag = 10;
			break;

		case R.id.icon_radio_eight_btn:
			mtenImageView.setImageResource(R.drawable.icon_radio_n);
			meightImageView.setImageResource(R.drawable.icon_radio_p);
			mfiveImageView.setImageResource(R.drawable.icon_radio_n);
			tag = 8;
			break;

		case R.id.icon_radio_five_btn:
			mtenImageView.setImageResource(R.drawable.icon_radio_n);
			meightImageView.setImageResource(R.drawable.icon_radio_n);
			mfiveImageView.setImageResource(R.drawable.icon_radio_p);
			// tag=5;
			break;
		case R.id.publish_btn:
			
			if (!SharedPreferencesUtil.getPwdLoginPhone(mContext).equals("")) {
				if (SharedPreferencesUtil.getPwdLoginUserState(mContext) == 1) {
					String bookName = bookNameEdt.getText().toString().trim();
					String originalPrice = originalPriceEdt.getText()
							.toString().trim();
					String transferPrice = transferPriceEdt.getText()
							.toString().trim();
					String bookNumber = bookNumberEdt.getText().toString()
							.trim();
					String phoneNumber = phoneNumberEdt.getText().toString()
							.trim();
					String qqNumber = qqNumberEdt.getText().toString().trim();
					String weChat = weChatEdt.getText().toString().trim();
					String publisherSaid = publisherSaidEdt.getText()
							.toString().trim();
					if (bookName.equals("")) {
						Toast.makeText(this, "书名不能为空", 3000).show();
					} else if (originalPrice.equals("")
							|| transferPrice.equals("")
							|| bookNumber.equals("")) {
						Toast.makeText(this, "原价、转让价和数量不能为空", 3000).show();
					} else if (phoneNumber.equals("") || qqNumber.equals("")
							|| weChat.equals("")) {
						Toast.makeText(this, "手机号、QQ和微信不能为空", 3000).show();
					} else if (publisherSaid.equals("")) {
						Toast.makeText(this, "发布者不能为空", 3000).show();
					} else {
						publisher();
					}
				} else {
					Intent mIntent = new Intent(this,
							PersonalMessageActivity.class);
					startActivity(mIntent);
				}

			} else {
				Intent mIntent = new Intent(this, LoginActivity.class);
				startActivity(mIntent);
			}
			break;

		default:
			break;
		}
	}

	private void selectAlertDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate(
				R.layout.fragment_sellbook_select_kinds_gridview, null);// 获取自定义布局
		builder.setView(layout);
		// 确认按钮
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Toast.makeText(getApplication(), "ok", Toast.LENGTH_SHORT)
						.show();
			}
		});
		// 取消
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Toast.makeText(getApplication(), "取消", Toast.LENGTH_SHORT)
						.show();
			}
		});
		final AlertDialog dlg = builder.create();
		dlg.show();

		final String[] data = { "教材类:", "语文", "数学", "英语", "读物类:", "报纸", "期刊",
				"杂志", "考试类:", "试卷", "答案", "解析", "其他类" };
		gridview = (GridView) layout.findViewById(R.id.grid_view);// 必须实例化，而且是layout的
		final WantToSellBookGridViewAdapter gridViewAdapter = new WantToSellBookGridViewAdapter(
				WantToSellBookActivity.this, data);
		gridview.setAdapter(gridViewAdapter);// 写完适配器的继承在加载
		gridview.setOnItemClickListener(new OnItemClickListener() {
			//
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 从数组中获取数据
				mTextView.setText(data[position]);
			}
		});
	}

	private void takePhoto() {
		// 创建File对象，用于存储拍照后的图片
		Date nowTime = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");

		String dateString = time.format(nowTime);
		Toast.makeText(this, dateString, 0).show();
		File outputImage = new File(Environment.getExternalStorageDirectory(),
				"ngzf.jpg");
		try {
			if (outputImage.exists()) {
				outputImage.delete();
			}
			outputImage.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageUri = Uri.fromFile(outputImage);
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, TAKE_PHOTO); // 启动相机程序
	}

	private void publisher() {

		final String url = XUtil.IP + "book_sell_Book.action";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("book_name", bookNameEdt.getText().toString());// book_id
		map.put("book_kind", selectTxt.getText().toString());
		if (tag == 10) {
			map.put("book_degree", tenNewTxt.getText().toString());
		} else if (tag == 8) {
			map.put("book_degree", eightNewTxt.getText().toString());
		} else {
			map.put("book_degree", fiveNewTxt.getText().toString());
		}
		map.put("original_price", originalPriceEdt.getText().toString().trim());
		map.put("price", transferPriceEdt.getText().toString().trim());
		map.put("number", bookNumberEdt.getText().toString().trim());
		map.put("tel", phoneNumberEdt.getText().toString().trim());
		map.put("qq", qqNumberEdt.getText().toString().trim());
		map.put("vx", weChatEdt.getText().toString().trim());
		map.put("addition", publisherSaidEdt.getText().toString().trim());

		XUtil.Post(url, map, new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);

				String code = JsonUtils.parseResultCode(result);
				if (code.equals("0")) {// result.getCode()==0
					XUtil.uploadSinglePicture(url, imgData,
							new MyCallBack<String>() {

							@Override
							public void onSuccess(String result) {
								super.onSuccess(result);
								Toast.makeText(mContext, "上传图片发布成功！", 3000)
										.show();
								/*
								 * String code =
								 * JsonUtils.parseResultCode(result); if
								 * (code.equals("0")) {
								 * Toast.makeText(mContext, "上传图片发布成功！",
								 * 3000).show(); } else {// -1也是访问到了，但解析错误
								 * Toast.makeText(mContext, "上传图片失败",
								 * 3000).show(); }
								 */
							}
						});
					// Toast.makeText(mContext, "发布成功！", 3000).show();
				} else {
					Toast.makeText(mContext, "解析结果错误", 3000).show();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);
				Toast.makeText(mContext, "服务器正在维护，稍后访问。。。", 3000).show();
			}
		});
	}
}