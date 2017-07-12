package com.chxy.usedbook.adapter;

import com.chxy.usedbook.activity.BookMarketViewPagerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class BookMarketViewPagerAdapter extends FragmentPagerAdapter{

	//���õ�����������
	public static String[] titles=new String[]{"ȫ��","���·���","�ͼ�����"};
	
	public BookMarketViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return new BookMarketViewPagerFragment(position);
	}

	@Override
	public int getCount() {
		return 3;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}
	
}