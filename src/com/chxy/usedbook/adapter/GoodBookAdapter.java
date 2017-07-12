package com.chxy.usedbook.adapter;

import java.util.ArrayList;
import java.util.List;

import com.chxy.usedbook.activity.R;
import com.chxy.usedbook.vo.GoodBook;



import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 好书界面
 * @author 李魁魁
 *
 */
public class GoodBookAdapter extends BaseAdapter {

	List<GoodBook> bookList=new ArrayList<GoodBook>();
	private Context mContext;
	
	public GoodBookAdapter(List<GoodBook> bookList, Context mContext) {
		
		System.out.println(bookList.size()+"dddddddddddddddddddd");
		this.bookList = bookList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		System.out.println(bookList.size());
		return bookList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return bookList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=new ViewHolder();
		
		GoodBook goodbook=bookList.get(position);
		if (convertView==null) {
			LayoutInflater inflater=LayoutInflater.from(mContext);
			convertView=inflater.inflate(R.layout.goodbook_listview_item, null);
			viewHolder.list_num=(TextView) convertView.findViewById(R.id.book_list_num);
			viewHolder.goodbook_name=(TextView) convertView.findViewById(R.id.goodbook_name);
			viewHolder.goodbook_see_num=(TextView) convertView.findViewById(R.id.goodbook_see_num);
			viewHolder.goodbook_trade_bun=(TextView) convertView.findViewById(R.id.goodbook_trade_num);
			viewHolder.goodbook_wantbuy_num=(TextView) convertView.findViewById(R.id.goodbook_wantbuy_num);
			viewHolder.goodbook_img=(ImageView) convertView.findViewById(R.id.goodbook_item_img);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
		System.out.println(position+"--------------------");
		if (position>2) {
			viewHolder.list_num.setTextColor(Color.parseColor("#000000"));
		}else {
//			Typeface mtypeface=Typeface.createFromAsset(mContext.getAssets(),"kaiti.TTF");
//			viewHolder.list_num.setTypeface(mtypeface);
//			viewHolder.list_num.setTextSize((float) 22.0);
			viewHolder.list_num.setTextColor(Color.parseColor("#ED6709"));
		}
		viewHolder.list_num.setText(""+(position+1)+".");
		viewHolder.goodbook_name.setText(goodbook.getBook_name());
		viewHolder.goodbook_see_num.setText(goodbook.getBook_see_num());
		viewHolder.goodbook_trade_bun.setText(goodbook.getBook_trade_num());
		viewHolder.goodbook_wantbuy_num.setText(goodbook.getBook_wantbuy_num());
		return convertView;
	}
	
	class ViewHolder{
		private TextView list_num;
		private TextView goodbook_name;
		private TextView goodbook_see_num;
		private TextView goodbook_trade_bun;
		private TextView goodbook_wantbuy_num;
		private ImageView goodbook_img;
	}

}
