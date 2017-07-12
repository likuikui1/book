package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chxy.usedbook.adapter.ViewPagerFragmentAdapter;
import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.XUtil;
import com.chxy.usedbook.view.CircleProgressDialog;
import com.chxy.usedbook.vo.Book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BookMarketViewPagerFragment extends Fragment{

	private List<Book> bookList ;
	private static Context mContext;
	private int postion;
	private ListView mlistView;
	private CircleProgressDialog circleProgressDialog;
	
	public BookMarketViewPagerFragment(int position){
		this.postion = position;
	}
	//判断发布类型：最新、低价优先...
	private String getType(){
		String type = "total";
		switch(postion){
		case 0:
			type = "total";
			break;
		case 1:
			type = "time";
			break;
		case 2:
			type = "price";
			break;
		}
		return type;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view;
		view = inflater.inflate(R.layout.books_market_content, container, false);
		
		mlistView= (ListView) view.findViewById(R.id.list_view);
		mContext = getActivity();// 这个必须加，因为Fragment继承的是Activity；下一行上下文也必须得改
		mlistView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			Book book = bookList.get(position);
			Intent mIntent=new Intent(mContext,BookDetailActivity.class);
			mIntent.putExtra("data_1", book.getNewOldDegree());//传值
			mIntent.putExtra("data_2", book.getOriginalPrice());
			mIntent.putExtra("data_3", book.getBookName());
			mIntent.putExtra("data_4", book.getTime());
			mIntent.putExtra("data_5", book.getBookMoney());
			mIntent.putExtra("data_6", book.getUser().getUserName());
			mIntent.putExtra("data_7", book.getPhone());
			mIntent.putExtra("data_8", book.getQq());
			mIntent.putExtra("data_9", book.getWeChat());
			mIntent.putExtra("data_10", book.getPersonSay());
			startActivity(mIntent);
		}
	  });
		//progressBar = new ProgressBar(view.getContext());
		initBook();
		return view;
	}

	private void initBook() {
		/*
		 * for (int i = 0; i < 12; i++) { books = new Book();
		 * books.setBookName("2017年版全国计算机等级考试二级教程Office实际应用");
		 * books.setBookAnnounce("发布人："); books.setBookPerson("***");
		 * books.setBookKind("分类：计算机"); books.setBookPrice("价格：");
		 * books.setBookMoney("¥10"); bookList.add(books);
		 * }
		 */
		if (CheckNetWorkUtil.CheckNetWorkUtil(mContext)) {
			circleProgressDialog = new CircleProgressDialog(mContext);
			circleProgressDialog.setText("正在登录。。。");
			circleProgressDialog.showDialog();
			getDataFromServer();
		} else {
			Toast.makeText(mContext, "请检查网络链接", 3000).show();
		}
	}

	private void getDataFromServer() {
		String url = XUtil.IP+"book_findAllBooks";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", getType());//

		XUtil.Post(url, map, new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);
				circleProgressDialog.dismiss();
				//解析
				String code = JsonUtils.parseResultCode(result);
				if (code.equals("0")) {
					
					bookList=new ArrayList<>();
					bookList = JsonUtils.parseBookData(result);					
					if (!bookList.isEmpty()&&bookList.size()>0) {
						
						ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(
								getActivity(), bookList);
						mlistView.setAdapter(adapter);
					}
					
				} else {
					Toast.makeText(mContext, "请重新加载。", 1000).show();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);
				Toast.makeText(mContext, "服务器正在维护，请稍等。", 1000).show();
			}
		});
	}

	public static ViewPagerFragment newInstance() {
		ViewPagerFragment fragment = new ViewPagerFragment();
		return fragment;
	}
	
}
