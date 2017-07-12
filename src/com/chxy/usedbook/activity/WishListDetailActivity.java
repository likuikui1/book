package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.List;

import com.chxy.usedbook.adapter.WishListDetailAdapter;
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
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * ��Ը������
 * @author ����
 *
 */
public class WishListDetailActivity extends Activity implements OnPageChangeListener {
	private Context mContext = this;
	private ViewPager mViewPage;
	private LinearLayout ll_point_container;
	private int[] imageResIds;
	private ArrayList<ImageView> imageViewList;
	private View pView;
	private LayoutParams layoutParams;
	private int previousSelectedPosition;
	private ListView mListview;
	private ImageView mImageView;
	//�ҵ�10���ؼ�
	private TextView bookNameTxt;
	private TextView newDegreeTxt;
	private TextView publishTimeTxt;
	private TextView rmbTxt;
	private TextView userNameTxt;
	private ImageView userSexImg;
	private TextView telTxt;
	private TextView qqTxt;
	private TextView weChatTxt;
	private String bookName;
	private String newDegree;
	private String publishTime;
	private String wishContent;
	private String userName;
	private String userSex;
	private String tel;
	private String qq;
	private String weChat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wishlist_detail_base);
		initView();
		initData();
		
		Intent dataIntent=getIntent();
		bookName=dataIntent.getStringExtra("data_bookName");
		newDegree=dataIntent.getStringExtra("data_newDegree");
		publishTime=dataIntent.getStringExtra("data_publishTime");
		wishContent=dataIntent.getStringExtra("data_wishContent");
		userName=dataIntent.getStringExtra("data_userName");
		userSex=dataIntent.getStringExtra("data_userSex");
		tel=dataIntent.getStringExtra("data_userTel");
		qq=dataIntent.getStringExtra("data_userQQ");
		weChat=dataIntent.getStringExtra("data_userWeChat");
		
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
		imageResIds = new int[] {  R.drawable.book_detail_dynamic_img1, R.drawable.book_detail_dynamic_img2,
				R.drawable.book_detail_home_banner1,R.drawable.book_detail_home_banner1};
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
		List<Comment> cList = new ArrayList<Comment>();
		for (int i = 0; i < 10; i++) {
			Comment c = new Comment();
			c.setName("С��");
			c.setTime("���� 12:00");
			c.setComment_chat("�Ȿ�����У���������͸���Ŷ");

			cList.add(c);
		}
		mListview.setAdapter(new WishListDetailAdapter(cList, mContext));

	}

	private void initView() {
		EditText et_fabusay = (EditText) findViewById(R.id.et_fabusay);
		et_fabusay.clearFocus();
		mListview = (ListView) findViewById(R.id.listview_xydxq_comment_message);
		mViewPage = (ViewPager) findViewById(R.id.vp);
		mViewPage = (ViewPager) findViewById(R.id.vp);
		mViewPage.setOnPageChangeListener(this);// ����ҳ����¼���
		// viewPager.setOffscreenPageLimit(1);// ���Ҹ�������������
		ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
		//
		bookNameTxt=(TextView) findViewById(R.id.book_detailedinfo_name);
		bookNameTxt.setText(bookName);
		newDegreeTxt=(TextView) findViewById(R.id.book_new_degree);
		newDegreeTxt.setText(newDegree);
		publishTimeTxt=(TextView) findViewById(R.id.book_publish_time);
		publishTimeTxt.setText(publishTime);
		rmbTxt=(TextView) findViewById(R.id.rmb_way_content);
		rmbTxt.setText(wishContent);
		userNameTxt=(TextView) findViewById(R.id.sjxq_faburen_name);
		userNameTxt.setText(userName);
		//userSexImg=findViewById(R.id.username_sex_img);
		if (userSex=="01") {//��
			//userSexImg.setImageResource(R.drawable.boy);
		} else {
			//userSexImg.setImageResource(R.drawable.girl);
		}
		telTxt=(TextView) findViewById(R.id.tel_txt);
		telTxt.setText(tel);
		qqTxt=(TextView) findViewById(R.id.qq_txt);
		qqTxt.setText(qq);
		weChatTxt=(TextView) findViewById(R.id.wechat_txt);
		weChatTxt.setText(weChat);
		et_fabusay.setText(wishContent);
		mImageView=(ImageView) findViewById(R.id.wishlistdetail_top_img);
		mImageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				Intent mIntent=new Intent(mContext,WishListActivity.class);
				startActivity(mIntent);
			}
			
		});
	}

	private void initAdapter() {
		ll_point_container.getChildAt(0).setEnabled(true);
		previousSelectedPosition = 0;
		// ����������
		mViewPage.setAdapter(new MyAdapter());
		// Ĭ�����õ��м��ĳ��λ��
		// int pos = Integer.MAX_VALUE / 2- (Integer.MAX_VALUE / 2 %
		// imageViewList.size());
		// 2147483647 / 2 = 1073741823 - (1073741823 % 5)
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

			// newPosition = position % 5
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

		ll_point_container.getChildAt(previousSelectedPosition).setEnabled(
				false);
		ll_point_container.getChildAt(newPosition).setEnabled(true);

		// ��¼֮ǰ��λ��
		previousSelectedPosition = newPosition;
	}
}
