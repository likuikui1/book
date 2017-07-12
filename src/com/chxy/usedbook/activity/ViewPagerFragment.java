package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chxy.usedbook.adapter.GoodBookAdapter;
import com.chxy.usedbook.view.XListView;
import com.chxy.usedbook.vo.GoodBook;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 
 * @author ���������
 * 
 */
public class ViewPagerFragment extends Fragment {
	private XListView flistView;
	List<GoodBook> goodbookList;
	private Context mContext;
	private GoodBookAdapter adapter;
	private Handler mHandler;
	private GoodBook goodbook;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_goodbook, container,
				false);// �����ּ��ؽ���
		flistView = (XListView) view.findViewById(R.id.fragment_goodnewbook_list);
		mHandler = new Handler();
		initView();
		
		return view;
	}

	private void initView() {
		// TODO Auto-generated method stub
		mContext = getActivity();
		goodbookList = getData();
		flistView.setPullLoadEnable(true);// ��������������FALSEΪ�����������㲻���ظ�������
		adapter = new GoodBookAdapter(goodbookList, mContext);
		flistView.setAdapter(adapter);
	}

	// ��ʼ������
	private List<GoodBook> getData() {
		List<GoodBook> goodbookList=new ArrayList<GoodBook>();
		for (int i = 0; i < 20; i++) {
			goodbook=new GoodBook();
			goodbook.setBook_name("���ݽṹ");
			goodbook.setBook_see_num("1000");
			goodbook.setBook_trade_num("666");
			goodbook.setBook_wantbuy_num("888");
			goodbookList.add(goodbook);
		}
		System.out.println();
		return goodbookList;
	}
	/** ֹͣˢ�£� */
	private void onLoad() {
		flistView.stopRefresh();
		flistView.stopLoadMore();
		flistView.setRefreshTime("�ո�");
	}

	// ˢ��
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {

//				flistView.setAdapter(mAdapter1);
				onLoad();
			}
		}, 2000);
	}

	// ���ظ���
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
//				mAdapter1.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	public static ViewPagerFragment newInstance() {
		ViewPagerFragment fragment = new ViewPagerFragment();
		return fragment;
	}

}
