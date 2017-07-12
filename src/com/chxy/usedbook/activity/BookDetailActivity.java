package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chxy.usedbook.adapter.BookDetailAdapter;
import com.chxy.usedbook.adapter.ViewPagerFragmentAdapter;
import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.XUtil;
import com.chxy.usedbook.vo.Comment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


/**
 * �鼮�������
 * @author �����
 *
 */
public class BookDetailActivity extends Activity implements OnPageChangeListener, OnClickListener{

	private ViewPager mViewPage;
	private LinearLayout ll_point_container;
	private int[] imageResIds;
	private ArrayList<ImageView> imageViewList;
	private View pView;
	private LayoutParams layoutParams;
	private int previousSelectedPosition;
	private Context mContext=this;
	private ListView mListview;	
	private TextView bookNameTextView;
	private TextView newOldDegreeTxt;
	private TextView originalPriceTxt;
	private TextView newPriceTxt;
	private TextView phoneTxt;
	private TextView qqTxt;
	private TextView weChatTxt;
	private TextView timeTxt;
	private TextView publishNameTxt;	
	private EditText et_fabusay;	
	private String data_bookName;
	private String data_bookDegree;
	private String data_publishTime;
	private String data_originPrice;
	private String data_nowPrice;
	private String data_personName;
	private String data_phone;
	private String data_qq;
	private String data_weChat;
	private String data_publishSay;	
	private ImageView returnImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_book_detail);
				
		Intent dataIntent=getIntent();
		data_bookName=dataIntent.getStringExtra("data_3");
		data_bookDegree=dataIntent.getStringExtra("data_1");
		data_publishTime=dataIntent.getStringExtra("data_4");
		data_originPrice=dataIntent.getStringExtra("data_2");
		data_nowPrice=dataIntent.getStringExtra("data_5");
		data_personName=dataIntent.getStringExtra("data_6");
		data_phone=dataIntent.getStringExtra("data_7");
		data_qq=dataIntent.getStringExtra("data_8");
		data_weChat=dataIntent.getStringExtra("data_9");
		data_publishSay=dataIntent.getStringExtra("data_10");
		
		initView();
		initData();
		initAdapter();
		// ������ѯ
		new Thread() {
			private boolean isRunning;

			public void run() {
				isRunning = true;
				while (isRunning) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// ������һλ
					runOnUiThread(new Runnable() {
						public void run() {
							mViewPage
									.setCurrentItem(mViewPage.getCurrentItem() + 1);
						}
					});
				}
			};
		}.start();
	}

	private void initData() {
		imageResIds = new int[] { R.drawable.book_detail_dynamic_img1, R.drawable.book_detail_dynamic_img2,
				R.drawable.book_detail_home_banner1,R.drawable.book_detail_dynamic_img1};

		imageViewList = new ArrayList<ImageView>();
		// ��ʼ���ֲ�ͼƬ
		for (int i = 0; i < imageResIds.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(imageResIds[i]);
			imageViewList.add(imageView);

			pView = new View(this);
			pView.setBackgroundResource(R.drawable.selector_bg_point);
			layoutParams = new LinearLayout.LayoutParams(12, 12);
			if (i != 0)
				layoutParams.leftMargin = 12;
			// ����Ĭ�����ж�������
			pView.setEnabled(false);
			ll_point_container.addView(pView, layoutParams);
		}	
		
		/**
		 * listView���ݳ�ʼ��
		 */
		List<Comment> cList=new ArrayList<Comment>();
		for (int i = 0; i < 3; i++) {
			Comment c=new Comment();
			c.setName("С��");
			c.setTime("���� 10:00");
			c.setComment_chat("����Һ�����������");
			
			cList.add(c);
		}
		mListview.setAdapter(new BookDetailAdapter(cList, mContext));
		
	}

	private void initView() {
		et_fabusay=(EditText) findViewById(R.id.et_fabusay);
		et_fabusay.clearFocus();
		
		mListview=(ListView) findViewById(R.id.listview_sjxq_comment_message);
		mViewPage = (ViewPager)findViewById(R.id.vp);
		mViewPage = (ViewPager) findViewById(R.id.vp);
		mViewPage.setOnPageChangeListener(this);// ����ҳ����¼���
		ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
		
		bookNameTextView=(TextView) findViewById(R.id.book_detailedinfo_name);
		newOldDegreeTxt=(TextView) findViewById(R.id.new_old_degree_txt);
		originalPriceTxt=(TextView) findViewById(R.id.original_price_txt);
		newPriceTxt=(TextView) findViewById(R.id.new_price_txt);
		phoneTxt=(TextView) findViewById(R.id.phone_number_txt);
		qqTxt=(TextView) findViewById(R.id.qq_number_txt);
		weChatTxt=(TextView) findViewById(R.id.we_chat_number_txt);
		timeTxt=(TextView)findViewById(R.id.time_txt);
		publishNameTxt=(TextView)findViewById(R.id.sjxq_faburen_name);
		
		returnImg=(ImageView)findViewById(R.id.newbook_top_img);
		returnImg.setOnClickListener(this);
		
		bookNameTextView.setText(data_bookName);
		newOldDegreeTxt.setText(data_bookDegree);
		originalPriceTxt.setText(data_originPrice);
		newPriceTxt.setText(data_nowPrice);
		timeTxt.setText(data_publishTime);
		
		publishNameTxt.setText(data_personName);
		phoneTxt.setText(data_phone);
		qqTxt.setText(data_qq);
		weChatTxt.setText(data_weChat);
		
		et_fabusay.setText(data_publishSay);
	}

	private void initAdapter() {
		ll_point_container.getChildAt(0).setEnabled(true);
		previousSelectedPosition = 0;
		// ����������
		mViewPage.setAdapter(new MyAdapter());
		mViewPage.setCurrentItem(5000000); // ���õ�ĳ��λ��
	}
	
	class MyAdapter extends PagerAdapter {
		@Override
		public int getCount() {

			return Integer.MAX_VALUE;
		}

		@Override
		// 3. ָ�����õ��ж��߼�, �̶�д��
		public boolean isViewFromObject(View arg0, Object arg1) {
			// �������µ���Ŀ, �ַ�����, view�Ƿ���Ա�����.
			// �����жϹ���
			return arg0 == arg1;
		}

		// 1. ����Ҫ��ʾ����Ŀ����, ������Ŀ
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			System.out.println("instantiateItem��ʼ��: " + position);
			// container: ����: ViewPager
			// position: ��ǰҪ��ʾ��Ŀ��λ�� 0 -> 4

			int newPosition = position % imageViewList.size();

			ImageView imageView = imageViewList.get(newPosition);
			// a. ��View������ӵ�container��
			container.addView(imageView);
			// b. ��View���󷵻ظ����, ������
			return imageView; // ������д, �����쳣
		}

		// 2. ������Ŀ
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// object Ҫ���ٵĶ���
			System.out.println("destroyItem����: " + position);
			container.removeView((View) object);
		}
	}
		
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		// ����״̬�仯ʱ����
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		// ����ʱ����
	}

	@Override
	public void onPageSelected(int position) {

		// �µ���Ŀ��ѡ��ʱ����
				int newPosition = position % imageViewList.size();

				ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
				ll_point_container.getChildAt(newPosition).setEnabled(true);

				// ��¼֮ǰ��λ��
				previousSelectedPosition = newPosition;
	}

	@Override
	public void onClick(View v) {

		Intent mIntent=new Intent(BookDetailActivity.this,BookMarketActivity.class);
		startActivity(mIntent);
	}
}

