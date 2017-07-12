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
 * 心愿单详情
 * @author 李瑞
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
	//找到10个控件
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

		// 开启轮询
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
					// 往下跳一位
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
		// 初始化轮播图片
		for (int i = 0; i < imageResIds.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(imageResIds[i]);
			imageViewList.add(imageView);

			pView = new View(this);
			pView.setBackgroundResource(R.drawable.selector_bg_point);
			layoutParams = new LinearLayout.LayoutParams(12, 12);
			if (i != 0)
				layoutParams.leftMargin = 12;
			// 设置默认所有都不可用
			pView.setEnabled(false);
			ll_point_container.addView(pView, layoutParams);
		}
		/**
		 * listView数据初始化
		 */
		List<Comment> cList = new ArrayList<Comment>();
		for (int i = 0; i < 10; i++) {
			Comment c = new Comment();
			c.setName("小红");
			c.setTime("今天 12:00");
			c.setComment_chat("这本书我有，可以免费送给你哦");

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
		mViewPage.setOnPageChangeListener(this);// 设置页面更新监听
		// viewPager.setOffscreenPageLimit(1);// 左右各保留几个对象
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
		if (userSex=="01") {//男
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
		// 设置适配器
		mViewPage.setAdapter(new MyAdapter());
		// 默认设置到中间的某个位置
		// int pos = Integer.MAX_VALUE / 2- (Integer.MAX_VALUE / 2 %
		// imageViewList.size());
		// 2147483647 / 2 = 1073741823 - (1073741823 % 5)
		mViewPage.setCurrentItem(5000000); // 设置到某个位置

	}

	class MyAdapter extends PagerAdapter {
		@Override
		public int getCount() {

			return Integer.MAX_VALUE;
		}

		@Override
		// 3. 指定复用的判断逻辑, 固定写法
		public boolean isViewFromObject(View arg0, Object arg1) {
			// 当划到新的条目, 又返回来, view是否可以被复用.
			// 返回判断规则
			return arg0 == arg1;
		}

		// 1. 返回要显示的条目内容, 创建条目
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			System.out.println("instantiateItem初始化: " + position);
			// container: 容器: ViewPager
			// position: 当前要显示条目的位置 0 -> 4

			// newPosition = position % 5
			int newPosition = position % imageViewList.size();

			ImageView imageView = imageViewList.get(newPosition);
			// a. 把View对象添加到container中
			container.addView(imageView);
			// b. 把View对象返回给框架, 适配器
			return imageView; // 必须重写, 否则报异常
		}

		// 2. 销毁条目
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// object 要销毁的对象
			System.out.println("destroyItem销毁: " + position);
			container.removeView((View) object);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		// 滚动状态变化时调用
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		// 滚动时调用
	}

	@Override
	public void onPageSelected(int position) {

		// 新的条目被选中时调用
		int newPosition = position % imageViewList.size();

		ll_point_container.getChildAt(previousSelectedPosition).setEnabled(
				false);
		ll_point_container.getChildAt(newPosition).setEnabled(true);

		// 记录之前的位置
		previousSelectedPosition = newPosition;
	}
}
