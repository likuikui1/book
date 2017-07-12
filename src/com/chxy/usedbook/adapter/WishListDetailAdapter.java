package com.chxy.usedbook.adapter;

import java.util.ArrayList;
import java.util.List;

import com.chxy.usedbook.activity.R;
import com.chxy.usedbook.vo.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * 心愿单详情详情适配器
 * @author 李瑞
 *
 */
public class WishListDetailAdapter extends BaseAdapter {
	private List<Comment> commentList=new ArrayList<Comment>();
	private Context mContext;
	
	
	public WishListDetailAdapter(List<Comment> commentList, Context mContext) {
		super();
		this.commentList = commentList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return commentList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return commentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=new ViewHolder();
		Comment comment=commentList.get(position);
		System.out.println(commentList.size()+"------------------------------");
		if (convertView==null) {
			LayoutInflater inflater=LayoutInflater.from(mContext);
			convertView=inflater.inflate(R.layout.wish_list_listview_comment_item, null);
			viewHolder.list_name=(TextView) convertView.findViewById(R.id.listview_xydxq_comment_message_item_name);
			viewHolder.list_time=(TextView) convertView.findViewById(R.id.listview_xydxq_comment_message_item_time);
			viewHolder.list_comment_chat=(TextView) convertView.findViewById(R.id.listview_xydxq_comment_message_item_chat);
			
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
		
		viewHolder.list_name.setText(comment.getName());
		viewHolder.list_time.setText(comment.getTime());
		viewHolder.list_comment_chat.setText(comment.getComment_chat());
		
		return convertView;
	}
	
	class ViewHolder{
		private TextView list_name;
		private TextView list_time;
		private TextView list_comment_chat;
	}

}
