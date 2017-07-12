package com.chxy.usedbook.adapter;

import com.chxy.usedbook.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * @author »Ó∑…
 *
 */
public class HomeListViewAdapter extends BaseAdapter{
	
	
	private String[] mTitles;
	private String[] mPrice;
	private String[] mPeople;
	private String[] mType;
	private Context mContext;
	private LayoutInflater mInflater;

	public  HomeListViewAdapter(Context context,String[] titles,String[] people,String[] type,String[] price) {
		this.mContext = context; 
		this.mTitles=titles;
		this.mPeople=people;
		this.mType=type;
		this.mPrice=price;
		
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		
		return mTitles.length;
	}

	public Object getItem(int position) {
		
		return position;
	}

	public long getItemId(int position) {
		
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
         ViewHolder holder; 
        
        if(convertView==null){ 
        	
            holder = new ViewHolder();
            
            convertView = mInflater.inflate(R.layout.home_listview_item, null); 
           
            
            holder.title=(TextView)convertView.findViewById(R.id.title);
            holder.type=(TextView)convertView.findViewById(R.id.type);
            holder.people=(TextView)convertView.findViewById(R.id.people);
            holder.price=(TextView)convertView.findViewById(R.id.price);
            holder.lv_iv_boy=(ImageView)convertView.findViewById(R.id.lv_iv_boy);
            holder.lv_iv_girl=(ImageView)convertView.findViewById(R.id.lv_iv_girl);
            convertView.setTag(holder);  
        }else{  
            holder=(ViewHolder)convertView.getTag();  
        } 
        
        
        holder.title.setText(mTitles[position]);
        holder.type.setText(mType[position]);
        holder.people.setText(mPeople[position]);
        holder.price.setText(mPrice[position]);
        
        if (position==mTitles.length-1) {
        	holder.lv_iv_boy.setVisibility(View.INVISIBLE);
        	holder.lv_iv_girl.setVisibility(View.VISIBLE);
		}
        
        return convertView;
	}

	
	private static class  ViewHolder {

		public ImageView lv_iv_girl;
		public ImageView lv_iv_boy;
		public TextView type;
		public TextView price;
		public TextView people;
		public TextView title;
		
	} 
}
