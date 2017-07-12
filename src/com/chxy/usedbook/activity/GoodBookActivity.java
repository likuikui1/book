package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.List;
import com.chxy.usedbook.adapter.ViewPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;


/**
 * �������
 * 
 * @author �����
 * 
 */
public class GoodBookActivity extends FragmentActivity {
	private ImageView mReturnImageView;//����
	private ViewPager mnewbookViewPager;
	private TabPageIndicator mnewbookPageIndicator;
	// ��������Դ
	private List<Fragment> mnewbookFragments;
	// ����������
	private ViewPagerAdapter madapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goodbook);
		initView();
	}

	// ��ʼ���ؼ�
	private void initView() {
		mReturnImageView =(ImageView)findViewById(R.id.newbook_top_img);
		mReturnImageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		// TODO Auto-generated method stub
		mnewbookViewPager = (ViewPager) findViewById(R.id.goodnewbook_viewpager);
		mnewbookPageIndicator = (TabPageIndicator) findViewById(R.id.goodnewbook_indicator);
		madapter = new ViewPagerAdapter(getSupportFragmentManager());
		mnewbookViewPager.setAdapter(madapter);
		mnewbookPageIndicator.setViewPager(mnewbookViewPager, 0);// ��ָʾ������viewpager
		mnewbookFragments = new ArrayList<Fragment>();
	}
}