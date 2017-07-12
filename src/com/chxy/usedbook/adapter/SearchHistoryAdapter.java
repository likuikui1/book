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
 * ������¼������ (listView)
 * 
 * @author �ŷ�
 */

public class SearchHistoryAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> mAllItem; // ���е�Item
	private List<String> mItem; // ���˺��item
	private final Object mLock = new Object();
	private int mMaxMatch = 5; // �����ʾ���ٸ�ѡ�������ʾȫ��

	public SearchHistoryAdapter(Context context, int maxMatch) {
		this.mContext = context;
		this.mMaxMatch = maxMatch;
		initSearchHistory();
		mItem = mAllItem;
	}

	// �õ���������
	@Override
	public int getCount() {
		// һ��ʼ���е�Item��Ϊnull
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

	// �õ�ÿһ������
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

	// �õ�Item��λ��
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * ListView������ʾ��item����ͨ������Adapter�����getView�������õ�һ��View����
	 * Ȼ������View����������item�У�������һ�����̣������ListView��Adapter֮��Ĺ�ϵ
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
										Toast.makeText(mContext, "�Ѿ����������ʷ",
												Toast.LENGTH_SHORT).show();
									} else {
										cleanHistory();
										mItem.clear();
										mAllItem.clear();
										notifyDataSetChanged();

										Toast.makeText(mContext, "�ɹ����������ʷ",
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

	// ���������¼
	public void cleanHistory() {
		SharedPreferences sp = mContext.getSharedPreferences(
				SearchPageActivity.SEARCH_HISTORY, 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}

	/*
	 * ɾ��Item
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
		// ������
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
	 * ��ȡ��ʷ������¼
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
	 * ƥ�������������(����������������)
	 */

	public void performFiltering(CharSequence prefix) {
		if (prefix == null || prefix.length() == 0) { // ����������Ϊ�յ�ʱ����ʾ������ʷ��¼
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
				if (valueText.contains(prefixString)) // valueText���Ƿ������prefixString�ֶ�
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
