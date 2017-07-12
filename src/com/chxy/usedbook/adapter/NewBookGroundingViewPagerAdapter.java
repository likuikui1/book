package com.chxy.usedbook.adapter;

import com.chxy.usedbook.activity.NewBookGroudingViewPagerFragment;
import com.chxy.usedbook.activity.ViewPagerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 
 * @author 李瑞
 *
 */
public class NewBookGroundingViewPagerAdapter extends FragmentPagerAdapter {
	// 设置标题栏的标题
	public static String[] Titles = { "全部", "教材", "英语", "考研", "其他" };

	public NewBookGroundingViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		NewBookGroudingViewPagerFragment fragment = new NewBookGroudingViewPagerFragment();
		if (position == 0) {
			if (fragment == null) {
				fragment = NewBookGroudingViewPagerFragment.newInstance();
			}
			return fragment;
		} else if (position == 1) {
			if (fragment == null) {
				fragment = NewBookGroudingViewPagerFragment.newInstance();
			}
			return fragment;
		} else if (position == 2) {
			if (fragment == null) {
				fragment = NewBookGroudingViewPagerFragment.newInstance();
			}
			return fragment;
		} else if (position == 3) {
			if (fragment == null) {
				fragment = NewBookGroudingViewPagerFragment.newInstance();
			}
			return fragment;
		} else if (position == 4) {
			if (fragment == null) {
				fragment = NewBookGroudingViewPagerFragment.newInstance();
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

	public CharSequence getPageTitle(int position) {
		return Titles[position];

	}
}
