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
 * ��������
 * @author �ŷ�
 *
 */
public class SearchPageActivity extends Activity implements OnClickListener {
	
	private ImageView mReturnView;
	private HotSearchFlowLayout mFlowLayout;
	private LayoutInflater mInflater;
	private String[] mHotSearchs = new String[] { "������", "����", "�����", "���", "����",
			"��ʦ�ʸ�֤" };// ����ģ�⣬ʵ��Ӧ�������ȡ������

	/************
	 * ����Ϊ��ʽ��ǩ���
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
	 * �����ݷ�����ʽ����
	 */
	private void initFlowViewData() {
		for (int i = 0; i < mHotSearchs.length; i++) {
			TextView tv = (TextView) mInflater.inflate(
					R.layout.search_label_textview, mFlowLayout, false);
			tv.setText(mHotSearchs[i]);
			final String str = tv.getText().toString();
			// ����¼�
			tv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// ����������ʷ��¼��¼
					Toast.makeText(SearchPageActivity.this, str, Toast.LENGTH_SHORT)
							.show();
				}
			});
			mFlowLayout.addView(tv);
		}
	}

	/************
	 * ����Ϊ��ʽ��ǩ���
	 ************/
	
	
	/*
	 * ����
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
		 * ����������
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
		if (id == R.id.iv_search) { //�������
			saveSearchHistory();
			mSearchHistoryAdapter.initSearchHistory();
			Toast.makeText(this, "��������ɹ�", Toast.LENGTH_SHORT).show();
		}
		if (id == R.id.search_back_iv) { //����
			this.finish();
		}
	}
	
	/*
	 * ����������¼
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
		//�����ʷ��¼�Ƿ��Ѿ����ڵ�ǰ�����text�������������ɾ��
		if (history.size() > 0) {
			int i;
			for (i = 0; i < history.size(); i++) {
				if (text.equals(history.get(i))) {
					history.remove(i);
					break;
				}
			}
			//�����¼����4�������Ƴ����һ����������ǰ������һ������
			if (history.size() > 4) {
				history.remove(history.size() - 1);
			}
			history.add(0, text);
		}
		//���¼ӣ��ύ
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
