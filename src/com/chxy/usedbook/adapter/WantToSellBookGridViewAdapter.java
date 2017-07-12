package com.chxy.usedbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chxy.usedbook.activity.R;

/**
 * 我要卖书选择数据种类适配器
 * @author 蒋治国
 *
 */
public class WantToSellBookGridViewAdapter extends BaseAdapter {

	private Context mContext;
	private String[] data;

	public WantToSellBookGridViewAdapter(Context context,String[] data) {
		this.mContext = context;
		this.data=data;
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override  
    public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
        if (convertView== null) {
        	 mHolder=new ViewHolder();
        	convertView=LayoutInflater.from(mContext).inflate(R.layout.wtosell_book_grid_view_item, null);
        	mHolder.book_kinds=(TextView) convertView.findViewById(R.id.book_classify);
        	convertView.setTag(mHolder);
        } else { 
        	mHolder=(ViewHolder) convertView.getTag();
        }  
        
        mHolder.book_kinds.setText(data[position]);
        return convertView; // 
    }

	
	public class ViewHolder {
		
		public TextView book_kinds;//
	}


}