package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.chxy.usedbook.adapter.NewBookGroundingViewPagerAdapter;
import com.chxy.usedbook.adapter.ViewPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;
/**
 * �����ϼ�
 * @author ����
 *
 */
public class NewBookGroundingActivity extends FragmentActivity {
	private ViewPager mnewbookViewPager;
	private TabPageIndicator mnewbookPageIndicator;
	private ImageView newbook_top_img;
//	��������Դ
	private List<Fragment> mnewbookFragments;
//	����������
	private NewBookGroundingViewPagerAdapter madapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_newbook_grounding);

		newbook_top_img=(ImageView) findViewById(R.id.newbook_top_img);
		newbook_top_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent(NewBookGroundingActivity.this,BooksmarketActivity.class);
//				startActivity(intent);
				finish();
			}
		});
		initView();
	}

	// ��ʼ���ؼ�
	private void initView() {
		// TODO Auto-generated method stub
		mnewbookViewPager = (ViewPager) findViewById(R.id.newbook_viewpager);
		mnewbookPageIndicator = (TabPageIndicator) findViewById(R.id.newbook_indicator);
		madapter = new NewBookGroundingViewPagerAdapter(getSupportFragmentManager());
		mnewbookViewPager.setAdapter(madapter);
		mnewbookPageIndicator.setViewPager(mnewbookViewPager, 0);//��ָʾ������viewpager
		mnewbookFragments=new ArrayList<Fragment>();
	}
	
}