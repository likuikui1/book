package com.chxy.usedbook.adapter;

/**
 * ��ʾ��Ϣ�б����������
 * 
 * @author ����ǿ
 * 
 */
import java.util.List;

import com.chxy.usedbook.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MymessageListViewAdapter extends BaseAdapter {
	private List<String> items;
	private LayoutInflater inflater;
	private TextView mName;
	private TextView mContent;
	private TextView mTime;
	private ImageView mImgView;
//	CircleNotifyView viewNotify;
	private TextView mTip;

	public MymessageListViewAdapter(Context context, List<String> items) {
		this.items = items;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			view = inflater.inflate(R.layout.mymessage_list_item, null);
		}
		mImgView = (ImageView) view.findViewById(R.id.img_view);
		mName = (TextView) view.findViewById(R.id.list_item_text);// Ϊ�����������Ϣ
		mContent = (TextView) view.findViewById(R.id.list_item_text2);
		mTime = (TextView) view.findViewById(R.id.list_item_time);
		mTip = (TextView) view.findViewById(R.id.my_notify);
		mName.setText(items.get(position));
		mContent.setText("���Ǳ�����¶�����������");
		mTime.setText("3-15");
		mTip.setText("3");
		return view;
	}

	/*
	 * ����б���
	 * 
	 * @param item
	 */
	public void addItem(String item) {
		items.add(item);
	}
	

}