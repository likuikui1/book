package com.chxy.usedbook.adapter;

import java.util.List;
import java.util.Map;

import com.chxy.usedbook.activity.R;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 我的商品适配器
 * @author 阮飞
 *
 */
public class MyGoodsListViewAdapter extends BaseAdapter {
	

	private List<Map<String, Object>> mTitles;
	private Context mContext;
	private LayoutInflater mInflater;

	public  MyGoodsListViewAdapter(Context context,List<Map<String, Object>> titles) {
		this.mContext = context; 
		this.mTitles=titles;
		
		
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		
		return mTitles.size();
	}

	public Object getItem(int position) {
		
		return position;
	}

	public long getItemId(int position) {
		
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		
		if (convertView==null) {
			 mHolder=new ViewHolder();
			 convertView=mInflater.inflate(R.layout.mygoods_listview_item, null);
			 mHolder.mygoods_state=(TextView)convertView.findViewById(R.id.mygoods_state);
			 mHolder.mygoods_state_sell=(TextView)convertView.findViewById(R.id.mygoods_state_sell);
			 mHolder.mygoods_title=(TextView)convertView.findViewById(R.id.mygoods_title);
			 mHolder.mygoods_iv=(ImageView)convertView.findViewById(R.id.mygoods_iv);
			 
			 mHolder.mygoods_type=(TextView)convertView.findViewById(R.id.mygoods_type);
			 mHolder.mygoods_price=(TextView)convertView.findViewById(R.id.mygoods_price);
			 mHolder.mygoods_time=(TextView)convertView.findViewById(R.id.mygoods_date);
			 convertView.setTag(mHolder);
		}else {
			mHolder=(ViewHolder) convertView.getTag();
		}
		
		if (position==mTitles.size()-1) {
			mHolder.mygoods_state_sell.setVisibility(View.VISIBLE);
			mHolder.mygoods_state.setVisibility(View.INVISIBLE);
		}
		mHolder.mygoods_title.setText((String)mTitles.get(position).get("titles"));
		mHolder.mygoods_iv.setBackgroundResource((Integer) mTitles.get(position).get("img"));
		mHolder.mygoods_type.setText("");
		mHolder.mygoods_price.setText("");
		mHolder.mygoods_time.setText("");
		return convertView;
	}

	private static class ViewHolder {

		private TextView mygoods_state_sell;
		private TextView mygoods_title;
		private ImageView mygoods_iv;
		private TextView mygoods_state;
		
		private TextView mygoods_type;
		private TextView mygoods_price;
		private TextView mygoods_time;
		
	}

}
