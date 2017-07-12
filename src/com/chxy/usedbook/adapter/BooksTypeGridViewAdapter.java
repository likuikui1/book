package com.chxy.usedbook.adapter;

import java.util.List;
import java.util.Map;

import com.chxy.usedbook.activity.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

/**
 * 书籍分类界面
 * @author 阮飞
 *
 */
public class BooksTypeGridViewAdapter extends BaseAdapter{
	
	private Context context;
	private LayoutInflater mInflater;
	private List<Map<String, Object>> mLabel;

	public  BooksTypeGridViewAdapter(Context context,List<Map<String, Object>> labels) {
		this.context=context;
		this.mLabel=labels;
		mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
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
			 convertView=mInflater.inflate(R.layout.bookstype_gridview_item, null);
			 mHolder.bookstype_gv_btn=(CheckBox)convertView.findViewById(R.id.bookstype_gv_btn);
			 convertView.setTag(mHolder);
			 
		}else {
			mHolder=(ViewHolder) convertView.getTag();
		}
		
		mHolder.bookstype_gv_btn.setText((String)mLabel.get(position).get("title"));
		
		return convertView;
	}
	
	private static class ViewHolder{

		public CheckBox bookstype_gv_btn;
		
	}

}
