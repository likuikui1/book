package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.Arrays;

import com.chxy.usedbook.adapter.SearchHistoryAdapter;
import com.chxy.usedbook.view.HotSearchFlowLayout;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 搜索界面
 * @author 张飞
 *
 */
public class SearchPageActivity extends Activity implements OnClickListener {
	
	private ImageView mReturnView;
	private HotSearchFlowLayout mFlowLayout;
	private LayoutInflater mInflater;
	private String[] mHotSearchs = new String[] { "必修类", "考研", "计算机", "会计", "外语",
			"教师资格证" };// 数据模拟，实际应从网络获取此数据

	/************
	 * 以上为流式标签相关
	 ************/
	

	public static final String SEARCH_HISTORY = "search_history";
	private ListView mHistoryListView;
	private ImageView mSearchImageView;
	private TextView mSearchTextView;
	private SearchHistoryAdapter mSearchHistoryAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search_page);
		initFlowView();
		initSearch();
	}

	private void initFlowView() {
		mInflater = LayoutInflater.from(this);
		mFlowLayout = (HotSearchFlowLayout) findViewById(R.id.flowlayout);
		initFlowViewData();
	}

	/*
	 * 将数据放入流式布局
	 */
	private void initFlowViewData() {
		for (int i = 0; i < mHotSearchs.length; i++) {
			TextView tv = (TextView) mInflater.inflate(
					R.layout.search_label_textview, mFlowLayout, false);
			tv.setText(mHotSearchs[i]);
			final String str = tv.getText().toString();
			// 点击事件
			tv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// 加入搜索历史纪录记录
					Toast.makeText(SearchPageActivity.this, str, Toast.LENGTH_SHORT)
							.show();
				}
			});
			mFlowLayout.addView(tv);
		}
	}

	/************
	 * 以上为流式标签相关
	 ************/
	
	
	/*
	 * 搜索
	 */

	private void initSearch() {
		
		mSearchHistoryAdapter = new SearchHistoryAdapter(this, 5);
		mHistoryListView = (ListView) findViewById(R.id.search_history_lv);
		mHistoryListView.setAdapter(mSearchHistoryAdapter);
		mHistoryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				String data = (String) mSearchHistoryAdapter.getItem(position);
				mSearchTextView.setText(data);
				mSearchImageView.performClick();
			}
		});
		mReturnView=(ImageView)findViewById(R.id.search_back_iv);
		mReturnView.setOnClickListener(this);
		mSearchImageView = (ImageView) findViewById(R.id.iv_search);
		mSearchImageView.setOnClickListener(this);
		mSearchTextView = (TextView) findViewById(R.id.search_editText);
		/*
		 * 监听搜索框
		 */
		mSearchTextView.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mSearchHistoryAdapter.performFiltering(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

		});

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.iv_search) { //点击搜索
			saveSearchHistory();
			mSearchHistoryAdapter.initSearchHistory();
			Toast.makeText(this, "点击搜索成功", Toast.LENGTH_SHORT).show();
		}
		if (id == R.id.search_back_iv) { //返回
			this.finish();
		}
	}
	
	/*
	 * 保存搜索记录
	 */
	private void saveSearchHistory() {
		String text = mSearchTextView.getText().toString().trim();
		if (text.length() < 1) {
			return;
		}
		SharedPreferences sp = getSharedPreferences(SEARCH_HISTORY, 0);
		String longhistory = sp.getString(SEARCH_HISTORY, "");
		String[] tmpHistory = longhistory.split(",");
		ArrayList<String> history = new ArrayList<String>(
				Arrays.asList(tmpHistory));
		//检查历史记录是否已经存在当前输入的text，如果不存在则删除
		if (history.size() > 0) {
			int i;
			for (i = 0; i < history.size(); i++) {
				if (text.equals(history.get(i))) {
					history.remove(i);
					break;
				}
			}
			//如果记录大于4个，则移除最后一个数据在最前面增加一个数据
			if (history.size() > 4) {
				history.remove(history.size() - 1);
			}
			history.add(0, text);
		}
		//重新加，提交
		if (history.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < history.size(); i++) {
				sb.append(history.get(i) + ",");
			}
			sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
		} else {
			sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
		}
	}

}
