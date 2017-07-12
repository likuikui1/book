package com.chxy.usedbook.adapter;

import java.util.List;
import java.util.Map;

import com.chxy.usedbook.activity.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
/**
 * 书籍分类界面
 * @author 阮飞
 *
 */
public class BooksTypeListViewAdapter extends BaseAdapter{
	
	private List<Map<String, Object>> mLabel;
	private Context mContext;
	private LayoutInflater mInflater;

	public  BooksTypeListViewAdapter(Context context,List<Map<String, Object>> labels) {
		this.mLabel=labels;
		this.mContext=context;
		
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return mLabel.size();
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
			 convertView=mInflater.inflate(R.layout.bookstype_listview_item, null);
			 
			 mHolder.bookstype_label=(TextView)convertView.findViewById(R.id.bookstype_label);
			 mHolder.bookstype_gv=(GridView)convertView.findViewById(R.id.bookstype_gv);
			 
			 convertView.setTag(mHolder);
			 
		}else {
			mHolder=(ViewHolder) convertView.getTag();
		}
		
		mHolder.bookstype_label.setText((String) mLabel.get(position).get("label"));
		List<Map<String, Object>>grid_label=(List<Map<String, Object>>) mLabel.get(position).get("info");
		
		BooksTypeGridViewAdapter mGridViewAdapter=new BooksTypeGridViewAdapter(mContext, grid_label);
		mHolder.bookstype_gv.setAdapter(mGridViewAdapter);
		
		
		return convertView;
	}
	
	private static class ViewHolder {

		public GridView bookstype_gv;
		public TextView bookstype_label;
		
	}

}
