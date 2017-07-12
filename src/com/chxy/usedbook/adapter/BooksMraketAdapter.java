package com.chxy.usedbook.adapter;

import java.util.List;
import java.util.Map;

import com.chxy.usedbook.activity.R;
import com.chxy.usedbook.vo.Book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 书籍市场适配器
 * @author 李瑞
 *
 */
public class BooksMraketAdapter extends BaseAdapter{

	private List<Book> data;
	private Context mContext;

	public BooksMraketAdapter(Context mContext,
			List<Book> data) {
		this.mContext = mContext;
		this.data = data;
		
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Book map=data.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			// 根据自定义的Item布局加载布局
			convertView = LayoutInflater.from(mContext).inflate(R.layout.book_listview_item, null);
			holder.newbook_item_img = (ImageView) convertView
					.findViewById(R.id.newbook_item_img);
			holder.newbook_listview_simg = (ImageView) convertView
					.findViewById(R.id.newbook_listview_simg);
			holder.newbook_listview_chprice = (TextView) convertView
					.findViewById(R.id.newbook_listview_chprice);
			holder.newbook_item_bookname = (TextView) convertView
					.findViewById(R.id.newbook_item_bookname);
			holder.newbook_listview_people = (TextView) convertView
					.findViewById(R.id.newbook_listview_people);
			holder.newbook_listview_peoplename = (TextView) convertView
					.findViewById(R.id.newbook_listview_peoplename);
			holder.newbook_listview_select = (TextView) convertView
					.findViewById(R.id.newbook_listview_select);
			holder.newbook_listview_comname = (TextView) convertView
					.findViewById(R.id.newbook_listview_comname);
			holder.newbook_listview_price = (TextView) convertView
					.findViewById(R.id.newbook_listview_price);
			holder.newbook_listview_number = (TextView) convertView
					.findViewById(R.id.newbook_listview_number);
			convertView.setTag(holder); // 将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

//		holder.newbook_item_img.setBackgroundResource((Integer) data.get(
//				position).get("newbook_item_img"));
//		holder.newbook_listview_simg.setBackgroundResource((Integer) data.get(
//				position).get("newbook_listview_simg"));
//		holder.newbook_listview_chprice.setText((String) data.get(position)
//				.get("newbook_listview_chprice"));
/*		holder.newbook_item_bookname.setText((String) data.get(position).);
*///		holder.newbook_listview_people.setText((String) data.get(position).get(
//				"newbook_listview_people"));
//		holder.newbook_listview_peoplename.setText((String) data.get(position)
//				.get("newbook_listview_peoplename"));
//		holder.newbook_listview_select.setText((String) data.get(position).get(
//				"newbook_listview_select"));
//		holder.newbook_listview_comname.setText((String) data.get(position)
//				.get("newbook_listview_comname"));
/*		holder.newbook_listview_price.setText((String) data.get(position).);
*///		holder.newbook_listview_number.setText((String) data.get(position).get(
//				"newbook_listview_number"));
		return convertView;
	}

	static class ViewHolder {
		public ImageView newbook_item_img;
		public ImageView newbook_listview_simg;
		public TextView newbook_listview_chprice;
		public TextView newbook_item_bookname;
		public TextView newbook_listview_people;
		public TextView newbook_listview_peoplename;
		public TextView newbook_listview_select;
		public TextView newbook_listview_comname;
		public TextView newbook_listview_price;
		public TextView newbook_listview_number;
	}

}