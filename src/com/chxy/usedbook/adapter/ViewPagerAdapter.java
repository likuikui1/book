package com.chxy.usedbook.adapter;


import com.chxy.usedbook.activity.ViewPagerFragment;

import android.R.integer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 
 * @author �����
 * 
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
//	���ñ������ı���
	public static String[] Titles = { "�ۺ�", "������", "������" };
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	} 

	@Override
	public Fragment getItem(int position) {
		ViewPagerFragment fragment = new ViewPagerFragment();
		if (position == 0){
			if (fragment == null){
				fragment = ViewPagerFragment.newInstance();
			}
			return fragment;
	}else if (position == 1){
		if (fragment == null){
			fragment = ViewPagerFragment.newInstance();
		}
		return fragment;
	}else if (position == 2){
		if (fragment == null){
			fragment = ViewPagerFragment.newInstance();
		}
		return fragment;
	}else if (position == 3){
		if (fragment == null){
			fragment = ViewPagerFragment.newInstance();
		}
		return fragment;
	}else if (position == 4){
		if (fragment == null){
			fragment = ViewPagerFragment.newInstance();
		}
		return fragment;
	}
		return null;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Titles.length;
	}
public CharSequence getPageTitle(int position){
	return Titles[position];
	
}
}
