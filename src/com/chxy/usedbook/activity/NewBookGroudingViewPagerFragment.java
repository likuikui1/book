package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chxy.usedbook.adapter.NewBookGroudingViewPagerFragmentAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NewBookGroudingViewPagerFragment extends Fragment {
	private ListView flistView;
	private List<Map<String, Object>> data;
	private Context mContext;
	private NewBookGroudingViewPagerFragmentAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.newbook_listview, container,
				false);// �����ּ��ؽ���
		flistView = (ListView) view.findViewById(R.id.fragment_newbook_list);
		initView();
		
		return view;
	}

	private void initView() {
		// TODO Auto-generated method stub
		mContext = getActivity();
		data = getData();
		adapter = new NewBookGroudingViewPagerFragmentAdapter(mContext,data);
		flistView.setAdapter(adapter);
	}

	// ��ʼ������
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		String bookname = "2017���ȫ��������ȼ����Զ����̳�MS Office�߼�Ӧ��";
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("newbook_item_img", R.drawable.home_img_book);
			map.put("newbook_item_bookname", bookname);
			map.put("newbook_listview_people", "������:");
			map.put("newbook_listview_peoplename", "XXX");
			map.put("newbook_listview_simg", R.drawable.home_icon_boy);
			map.put("newbook_listview_select", "����:");
			map.put("newbook_listview_comname", "�����");
			map.put("newbook_listview_price", "�۸�:");
			map.put("newbook_listview_chprice", "��");
			map.put("newbook_listview_number", "10");
			list.add(map);
		}
		return list;
	}
	

	public static NewBookGroudingViewPagerFragment newInstance() {
		NewBookGroudingViewPagerFragment fragment = new NewBookGroudingViewPagerFragment();
		return fragment;
	}

}
