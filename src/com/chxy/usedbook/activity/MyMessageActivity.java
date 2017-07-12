package com.chxy.usedbook.activity;

import java.util.ArrayList;

import com.chxy.usedbook.adapter.MymessageListViewAdapter;
import com.chxy.usedbook.view.MyMessageListView;
import com.chxy.usedbook.view.MyMessageListView.OnRefreshListener;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 显示消息列表界面
 * 
 * @author 董唐强
 * 
 */
public class MyMessageActivity extends Activity {
	private ImageView mReturnImageView;//返回
	private MyMessageListView mListView;//我的消息列表
	private MymessageListViewAdapter adapter;//我的消息适配器
	private int longClickPosition;//长按的位置
	private PopupWindow popupWindow;//提示框
	private TextView mTextViewTop;//提示框---置顶
	private TextView mTextViewDelte;//提示框---删除
	private ArrayList<String> items;//ListView列表项
	private boolean judge = true;//判断提示框的显示

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_message);
		initView();
		items = new ArrayList<String>();
		items.add("小明");

		mListView = (MyMessageListView) findViewById(R.id.listView); // 获取list的ListView

		adapter = new MymessageListViewAdapter(this, items);

		mListView.setAdapter(adapter); // 为ListView设置适配器

		mListView.setonRefreshListener(new OnRefreshListener() {//下拉刷新
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						handleList();
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						adapter.notifyDataSetChanged();
						mListView.onRefreshComplete();
					}
				}.execute(null, null, null);
			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {// 消息点击事件

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Toast.makeText(MyMessageActivity.this, "Hello",
								Toast.LENGTH_SHORT).show();
					}

				});
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {// 消息长按事件
					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						longClickPosition = position;
						if (popupWindow == null) {
							View popView = getLayoutInflater().inflate(
									R.layout.mymessage_delete_item, null);
							mTextViewDelte = (TextView) popView
									.findViewById(R.id.tv_delete);
							mTextViewDelte
									.setOnClickListener(new OnClickListener() {//删除
										@Override
										public void onClick(View v) {
											items.remove(longClickPosition - 1);
											adapter.notifyDataSetChanged();
											popupWindow.dismiss();
										}
									});
							mTextViewTop = (TextView) popView
									.findViewById(R.id.tv_top);
							mTextViewTop
									.setOnClickListener(new OnClickListener() {//置顶
										@Override
										public void onClick(View v) {
											// judge=false;
											setTop(longClickPosition - 1);
											adapter.notifyDataSetChanged();
											popupWindow.dismiss();
										}

									});
							popupWindow = new PopupWindow(popView, 200, 150);
							// popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_corners_pop));
							// popupWindow.setAnimationStyle(R.style.PopAnimStyle);
							popupWindow.setOutsideTouchable(true);
							popupWindow
									.setBackgroundDrawable(new BitmapDrawable());
							backgroundAlpha(0.5f);
							// 添加pop窗口关闭事件
							popupWindow
									.setOnDismissListener(new poponDismissListener());
						}
						backgroundAlpha(0.5f);//设置背景透明度
						if (popupWindow.isShowing()) {
							popupWindow.dismiss();
						}
						int[] location = new int[2];
						view.getLocationOnScreen(location);
						// if(judge){popupWindow.showAtLocation(view,
						// Gravity.TOP, 0, location[1]
						// - view.getHeight());} //popWindow在点击位置的上方显示
						if (judge) {
							popupWindow.showAtLocation(view, Gravity.CENTER, 0,
									0);
						}// popWindow在屏幕中央显示
						return true;
					}
				});
	}
	public void initView() {
		mReturnImageView =(ImageView)findViewById(R.id.mymessage_return_img);
		mReturnImageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
	}

	/*
	 * 初始化适配器
	 */
	private void initAdapter() {
		// items = new ArrayList<String>();
		// for (int i = 0; i < 20; i++) {
		// items.add("小明" + i);
		// }
		items.add("小明");
	}

	/*
	 * 根据需要将新内容添加在最上位置；
	 * 
	 */
	private void handleList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Doo");
		for (int i = 0; i < items.size(); i++) {
			list.add(items.get(i));
		}
		items.clear();
		for (int i = 0; i < list.size(); i++) {
			items.add(list.get(i));
		}
	}

	/*
	 * 置顶
	 */
	private void setTop(int position) {
		items.add(0, items.get(position));
		// 置顶后list.size增加一 所以要position+1
		items.remove(position + 1);
	}

	/*
	 * 注册popWindow消失事件
	 */
	class poponDismissListener implements PopupWindow.OnDismissListener {
		@Override
		public void onDismiss() {
			backgroundAlpha(1f);
		}
	}

	/*
	 * 设置popWindow背景透明度
	 */
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		getWindow().setAttributes(lp);
	}

}