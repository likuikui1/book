package com.chxy.usedbook.adapter;

import com.chxy.usedbook.activity.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 我的心愿单适配器
 * @author 胡楚涵
 *
 */
public class MyWishListAdapter extends BaseAdapter {

	private Context mContext;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if (convertView == null) {
			view = View.inflate(mContext, R.layout.mywishlist_listview_item,null);
		} else {
			view = convertView;
		}
		return view;
	}

}