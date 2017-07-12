package com.chxy.usedbook.activity;

import java.util.ArrayList;

import com.chxy.usedbook.adapter.HomeGridViewAdapter;
import com.chxy.usedbook.adapter.HomeListViewAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

/**
 * 主页
 * 
 * @author 阮飞
 * 
 */
public class HomeActivity extends Activity implements OnPageChangeListener {

	private ListView mListView;
	private GridView mGridView;
	private ViewPager mViewPage;
	private LinearLayout ll_point_container;
	private ArrayList<ImageView> imageViewList;
	private View pView;
	private LayoutParams layoutParams;
	private int previousSelectedPosition;
	private boolean isRunning = false;
	private int[] imageResIds;
	private ImageView addMore;//更多
	private ImageView searchPage;//搜索
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);

		initView();
		initData();
		initAdapter();

		// 开启轮询
		new Thread() {
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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isRunning = false;
	}

	private void initView() {
		searchPage=(ImageView)findViewById(R.id.home_search_img);
		searchPage.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,
						SearchPageActivity.class);
				startActivity(intent);
			}
			
		});
		addMore=(ImageView)findViewById(R.id.iv_more);
		addMore.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,
						NewBookGroundingActivity.class);
				startActivity(intent);
			}
			
		});

		mListView = (ListView) findViewById(R.id.lv);
		mGridView = (GridView) findViewById(R.id.gv);
		mViewPage = (ViewPager) findViewById(R.id.vp);
		//
		mViewPage.setOnPageChangeListener(this);
		ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);

		mViewPage = (ViewPager) findViewById(R.id.vp);
		mViewPage.setOnPageChangeListener(this);// 设置页面更新监听
		// viewPager.setOffscreenPageLimit(1);// 左右各保留几个对象
		ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);

	}

	private void initData() {
		

		String[] titles = { "Dota2众神回忆录", "论补刀的艺术", "抢人头的真谛" };

		String[] price = { "价格：¥997", "价格：¥998", "价格：¥999" };

		String[] people = { "发布人：咖喱给给", "发布人：咖喱蹦蹦", "发布人：咖喱逮逮" };

		String[] type = { "分类：电子竞技", "分类：文学", "分类：自然科学" };

		HomeListViewAdapter listViewAdapter = new HomeListViewAdapter(
				getApplicationContext(), titles, people, type, price);
		mListView.setAdapter(listViewAdapter);

		int[] mDrawables = { R.drawable.home_icon_bookshop,
				R.drawable.home_icon_sale, R.drawable.home_icon_booklist,
				R.drawable.home_icon_classify };

		int[] mBackgroundImgs = { R.drawable.linearlayout_bookshop_background,
				R.drawable.linearlayout_sellbook_background,
				R.drawable.linearlayout_goodbook_background,
				R.drawable.linearlayout_type_background };

		String[] title = { "书籍市场", "我要卖书", "好书榜", "书籍分类" };

		HomeGridViewAdapter gridViewAdapter = new HomeGridViewAdapter(
				getApplicationContext(), mDrawables, title, mBackgroundImgs);
		mGridView.setAdapter(gridViewAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				switch (position) {
				case 0:
					Intent intent = new Intent(HomeActivity.this,
							BookMarketActivity.class);
					startActivity(intent);
					break;
				case 1:
					Intent intent1 = new Intent(HomeActivity.this,
							WantToSellBookActivity.class);
					startActivity(intent1);
					break;
				case 2:
					Intent goodBookIntent = new Intent(HomeActivity.this,
							GoodBookActivity.class);
					startActivity(goodBookIntent);
					break;
				case 3:
					Intent booksTypeIntent = new Intent(HomeActivity.this,
							BooksTypeActivity.class);
					startActivity(booksTypeIntent);
					break;
				default:
					break;
				}
			}
		});

		imageResIds = new int[] { R.drawable.book_detail_dynamic_img1,
				R.drawable.book_detail_dynamic_img2,
				R.drawable.book_detail_home_banner1,
				R.drawable.book_detail_dynamic_img1 };

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

	public void onPageScrollStateChanged(int arg0) {
		// 滚动状态变化时调用
	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// 滚动时调用
	}

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
