package com.chxy.usedbook.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.chxy.usedbook.activity.R;
import com.chxy.usedbook.activity.SearchPageActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 搜索记录设配器 (listView)
 * 
 * @author 张飞
 */

public class SearchHistoryAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> mAllItem; // 所有的Item
	private List<String> mItem; // 过滤后的item
	private final Object mLock = new Object();
	private int mMaxMatch = 5; // 最多显示多少个选项，负数表示全部

	public SearchHistoryAdapter(Context context, int maxMatch) {
		this.mContext = context;
		this.mMaxMatch = maxMatch;
		initSearchHistory();
		mItem = mAllItem;
	}

	// 得到数据总数
	@Override
	public int getCount() {
		// 一开始所有的Item不为null
		if (mItem != null) {
			if (mItem.isEmpty()) {
				return 0;
			} else {
				return mItem.size() + 1;
			}
		} else {
			return 0;
		}
	}

	// 得到每一条数据
	@Override
	public Object getItem(int position) {
		if (mItem != null) {
			if (!mItem.isEmpty()) {
				if (position == mItem.size()) {
					return 0;
				} else {
					return mItem.get(position);
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// 得到Item的位置
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * ListView中所显示的item都是通过调用Adapter对象的getView方法来得到一个View对象
	 * 然后把这个View对象放在这个item中，这样的一个过程，这就是ListView和Adapter之间的关系
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AutoHolder holder;
		RelativeLayout cleanHistoryLayout;
		final int location = position;

		if (mItem != null) {
			if (!mItem.isEmpty()) {
				if (position == mItem.size()) {
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.search_history_clean_all, parent,
							false);
					cleanHistoryLayout = (RelativeLayout) convertView
							.findViewById(R.id.clean_history_layout);
					cleanHistoryLayout
							.setOnClickListener(new OnClickListener() {

								@SuppressLint("NewApi")
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									SharedPreferences sp = mContext
											.getSharedPreferences(
													SearchPageActivity.SEARCH_HISTORY,
													0);
									String longhistory = sp.getString(
											SearchPageActivity.SEARCH_HISTORY, "");
									if (longhistory.isEmpty()) {
										Toast.makeText(mContext, "已经清除所有历史",
												Toast.LENGTH_SHORT).show();
									} else {
										cleanHistory();
										mItem.clear();
										mAllItem.clear();
										notifyDataSetChanged();

										Toast.makeText(mContext, "成功清除所有历史",
												Toast.LENGTH_SHORT).show();
									}
								}
							});
				} else {

					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.search_clean_list_item, parent, false);
					holder = new AutoHolder();
					holder.content = (TextView) convertView
							.findViewById(R.id.auto_content);
					holder.addButton = (ImageView) convertView
							.findViewById(R.id.auto_add);

					holder.content.setText(mItem.get(position));
					holder.addButton.setTag(mItem.get(position));

					holder.addButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							deleteItem(location);
						}
					});
				}
			}
		}

		return convertView;
	}

	// 清除搜索记录
	public void cleanHistory() {
		SharedPreferences sp = mContext.getSharedPreferences(
				SearchPageActivity.SEARCH_HISTORY, 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}

	/*
	 * 删除Item
	 */
	protected void deleteItem(int position) {
		String data = mItem.get(position);
		mItem.remove(position);
		SharedPreferences sp = mContext.getSharedPreferences(
				SearchPageActivity.SEARCH_HISTORY, 0);
		String longhistory = sp.getString(SearchPageActivity.SEARCH_HISTORY, "");
		String[] tmpHistory = longhistory.split(",");
		ArrayList<String> history = new ArrayList<String>(
				Arrays.asList(tmpHistory));
		// 数组检查
		if (history.size() > 0) {
			int i;
			for (i = 0; i < history.size(); i++) {
				if (data.equals(history.get(i))) {
//					Log.i("data.equals(history.get(i))", "ok");
					history.remove(i);
					break;
				}
			}

			mAllItem = history;

			StringBuilder sb = new StringBuilder();
			for (i = 0; i < history.size(); i++) {
				sb.append(history.get(i) + ",");
			}
			sp.edit().putString(SearchPageActivity.SEARCH_HISTORY, sb.toString())
					.commit();
//			Log.i("xianhua tag", "sp ok");
		}

		notifyDataSetChanged();
	}

	/*
	 * 读取历史搜索记录
	 */
	public void initSearchHistory() {
		SharedPreferences sp = mContext.getSharedPreferences(
				SearchPageActivity.SEARCH_HISTORY, 0);
		String longhistory = sp.getString(SearchPageActivity.SEARCH_HISTORY, "");
		String[] hisArrays = longhistory.split(",");
		mAllItem = new ArrayList<String>();
		if (!longhistory.contains(",")) {
			return;
		}
		if (hisArrays.length < 1) {
			return;
		}
		if (hisArrays.length > 5) {
			for (int i = 0; i < 5; i++) {
				mAllItem.add(hisArrays[i]);
			}
		} else {
			for (int i = 0; i < hisArrays.length; i++) {
				mAllItem.add(hisArrays[i]);
			}
		}

	}

	/*
	 * 匹配过滤搜索内容(输入框中输入的内容)
	 */

	public void performFiltering(CharSequence prefix) {
		if (prefix == null || prefix.length() == 0) { // 搜索框内容为空的时候显示所有历史记录
			synchronized (mLock) {
				mItem = mAllItem;
			}
		} else {
			String prefixString = prefix.toString();
			int count = mAllItem.size();
			ArrayList<String> newValues = new ArrayList<String>(count);
			for (int i = 0; i < count; i++) {
				final String value = mAllItem.get(i);
				final String valueText = value;
				if (valueText.contains(prefixString)) // valueText中是否包含有prefixString字段
				{
				}
				if (valueText.startsWith(prefixString)) {
					newValues.add(valueText);
				} else {
					final String[] words = valueText.split(" ");
					final int wordCount = words.length;
					for (int k = 0; k < wordCount; k++) {
						if (words[k].startsWith(prefixString)) {
							newValues.add(value);
							break;
						}
					}
				}
				if (mMaxMatch > 0) {
					if (newValues.size() > mMaxMatch - 1) {
						break;
					}
				}
			}
			mItem = newValues;
		}
		notifyDataSetChanged();
	}

	private class AutoHolder {
		TextView content;
		ImageView addButton;
	}
}
