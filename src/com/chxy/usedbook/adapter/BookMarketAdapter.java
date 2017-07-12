package com.chxy.usedbook.adapter;

import com.chxy.usedbook.activity.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;


public class BookMarketAdapter extends BaseAdapter{
	private Context mcontext;
	public String[] name={"书籍分类:","全部分类","考试类","英语","文学/小说","IT/工业","经济/管理","其他","适合人群:","不限","教材资料","课外","出售类别:","不限","出售","出借"};
		public BookMarketAdapter( Context mcontext) {
			this.mcontext=mcontext;
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return name.length;
		}

		@Override
		public Object getItem(int position) {
			
		
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
	     ViewHolder mHolder;
			
			if (convertView==null) {
				 mHolder=new ViewHolder();
				 convertView = LayoutInflater.from(mcontext).inflate(R.layout.bookmarket_gridview_item, parent, false);
				 mHolder.bookstype_gv_btn=(CheckBox)convertView.findViewById(R.id.checkBox1);
				 convertView.setTag(mHolder);
				 
			}else {
				mHolder=(ViewHolder) convertView.getTag();
			}
			
			mHolder.bookstype_gv_btn.setText(name[position]);
			if (position==0) {
				mHolder.bookstype_gv_btn.setChecked(false);
				mHolder.bookstype_gv_btn.setBackgroundDrawable(null);
				mHolder.bookstype_gv_btn.setText("书籍分类:");
				mHolder.bookstype_gv_btn.setTextColor(Color.rgb(139, 139, 139)); 
			}else if(position==8){
				mHolder.bookstype_gv_btn.setChecked(false);
				mHolder.bookstype_gv_btn.setBackgroundDrawable(null);
				mHolder.bookstype_gv_btn.setText("适合人群:");
				mHolder.bookstype_gv_btn.setTextColor(Color.rgb(139, 139, 139));
			}else if(position==12){
				mHolder.bookstype_gv_btn.setChecked(false);
				mHolder.bookstype_gv_btn.setBackgroundDrawable(null);
				mHolder.bookstype_gv_btn.setText("出售类别:");
				mHolder.bookstype_gv_btn.setTextColor(Color.rgb(139, 139, 139));
			}
			
			return convertView;
		}
		
		private static class ViewHolder{

			public CheckBox bookstype_gv_btn;
			
		}
			
		

	}