package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.List;

import com.chxy.usedbook.adapter.BookMarketAdapter;
import com.chxy.usedbook.adapter.BookMarketViewPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
/**
 * 书籍市场
 * @author 蒋治国
 *
 */
public class BookMarketActivity extends FragmentActivity implements
		OnClickListener {

	private TabPageIndicator mTabPageIndicator;
	private PopupWindow mPopupWindow;
	private ViewPager mViewPager;
	// 设置适配器
	private BookMarketViewPagerAdapter mViewPagerAdapter;
	// 主题内容部分，设置数据源
	private List<Fragment> mFragment;
	private ImageView books_market_title_img;
	private RelativeLayout rLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_book_market);
		initView();		
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.books_market_viewpager);
		mTabPageIndicator = (TabPageIndicator) findViewById(R.id.books_market_indicator);
		rLayout = (RelativeLayout) findViewById(R.id.booksmarket_select_layout);
		mViewPagerAdapter = new BookMarketViewPagerAdapter(
				getSupportFragmentManager());//
		mViewPager.setAdapter(mViewPagerAdapter);

		mTabPageIndicator.setViewPager(mViewPager, 0);// 给指示器设置ViewPager

		// 主题内容部分
		mFragment = new ArrayList<Fragment>();
		
		ImageView mImageView = (ImageView) findViewById(R.id.books_market_title_return_img);
		ImageView screenImageView = (ImageView) findViewById(R.id.books_market_title_img);
		screenImageView.setOnClickListener(this);
		mImageView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.books_market_title_return_img:
			finish();
			break;
		case R.id.books_market_title_img:
			initpopwindow(v);
			break;

		default:
			
			break;
		}
	}
	private void initpopwindow(View v) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		/* 设置显示menu布局 view子VIEW */
		View sub_view = inflater.inflate(R.layout.bookmarket_gridview, null);
		/* 第一个参数弹出显示view 后两个是窗口大小 */
		mPopupWindow = new PopupWindow(sub_view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
		mPopupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
		mPopupWindow.setTouchable(true);// 设置PopupWindow可触摸
		
		GridView gridView=(GridView)sub_view.findViewById(R.id.gridView1);
		gridView.setAdapter(new BookMarketAdapter(this));
		mPopupWindow.setBackgroundDrawable(new ColorDrawable(-00000));
		mPopupWindow.showAsDropDown(rLayout, 10, 10);
	}

}
