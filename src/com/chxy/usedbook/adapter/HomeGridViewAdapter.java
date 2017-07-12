package com.chxy.usedbook.adapter;

import com.chxy.usedbook.activity.LoginActivity;
import com.chxy.usedbook.activity.MainTabActivity;
import com.chxy.usedbook.activity.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author »Ó∑…
 * 
 */
public class HomeGridViewAdapter extends BaseAdapter {

	private int[] mDrawables;
	private int[] mBackgroundImgs;
	private Context mContext;
	private String[] mTitles;
	private LayoutInflater mInflater;

	public HomeGridViewAdapter(Context context, int[] drawables,
			String[] title, int[] backgroundImgs) {
		this.mContext = context;
		this.mDrawables = drawables;
		this.mTitles = title;
		this.mBackgroundImgs = backgroundImgs;

		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {

		return mDrawables.length;
	}

	public Object getItem(int position) {

		return position;
	}

	public long getItemId(int position) {

		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {

			holder = new ViewHolder();

			convertView = mInflater.inflate(R.layout.home_gridview_item, null);

			holder.mIng = (ImageView) convertView.findViewById(R.id.gv_iv);
			holder.title = (TextView) convertView.findViewById(R.id.gv_title);
			holder.backGround = (LinearLayout) convertView
					.findViewById(R.id.gv_ll_background);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.mIng.setBackgroundResource(mDrawables[position]);
		holder.title.setText(mTitles[position]);
		holder.backGround.setBackgroundResource(mBackgroundImgs[position]);
		holder.mIng.setBackgroundResource(mDrawables[position]);
		holder.title.setText(mTitles[position]);
		holder.backGround.setBackgroundResource(mBackgroundImgs[position]);


		return convertView;
	}

	private static class ViewHolder {

		public LinearLayout backGround;
		public TextView title;
		public ImageView mIng;

	}
}
