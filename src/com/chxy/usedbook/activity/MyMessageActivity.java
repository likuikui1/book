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
 * ��ʾ��Ϣ�б����
 * 
 * @author ����ǿ
 * 
 */
public class MyMessageActivity extends Activity {
	private ImageView mReturnImageView;//����
	private MyMessageListView mListView;//�ҵ���Ϣ�б�
	private MymessageListViewAdapter adapter;//�ҵ���Ϣ������
	private int longClickPosition;//������λ��
	private PopupWindow popupWindow;//��ʾ��
	private TextView mTextViewTop;//��ʾ��---�ö�
	private TextView mTextViewDelte;//��ʾ��---ɾ��
	private ArrayList<String> items;//ListView�б���
	private boolean judge = true;//�ж���ʾ�����ʾ

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_message);
		initView();
		items = new ArrayList<String>();
		items.add("С��");

		mListView = (MyMessageListView) findViewById(R.id.listView); // ��ȡlist��ListView

		adapter = new MymessageListViewAdapter(this, items);

		mListView.setAdapter(adapter); // ΪListView����������

		mListView.setonRefreshListener(new OnRefreshListener() {//����ˢ��
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

		mListView.setOnItemClickListener(new OnItemClickListener() {// ��Ϣ����¼�

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Toast.makeText(MyMessageActivity.this, "Hello",
								Toast.LENGTH_SHORT).show();
					}

				});
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {// ��Ϣ�����¼�
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
									.setOnClickListener(new OnClickListener() {//ɾ��
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
									.setOnClickListener(new OnClickListener() {//�ö�
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
							// ���pop���ڹر��¼�
							popupWindow
									.setOnDismissListener(new poponDismissListener());
						}
						backgroundAlpha(0.5f);//���ñ���͸����
						if (popupWindow.isShowing()) {
							popupWindow.dismiss();
						}
						int[] location = new int[2];
						view.getLocationOnScreen(location);
						// if(judge){popupWindow.showAtLocation(view,
						// Gravity.TOP, 0, location[1]
						// - view.getHeight());} //popWindow�ڵ��λ�õ��Ϸ���ʾ
						if (judge) {
							popupWindow.showAtLocation(view, Gravity.CENTER, 0,
									0);
						}// popWindow����Ļ������ʾ
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
	 * ��ʼ��������
	 */
	private void initAdapter() {
		// items = new ArrayList<String>();
		// for (int i = 0; i < 20; i++) {
		// items.add("С��" + i);
		// }
		items.add("С��");
	}

	/*
	 * ������Ҫ�����������������λ�ã�
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
	 * �ö�
	 */
	private void setTop(int position) {
		items.add(0, items.get(position));
		// �ö���list.size����һ ����Ҫposition+1
		items.remove(position + 1);
	}

	/*
	 * ע��popWindow��ʧ�¼�
	 */
	class poponDismissListener implements PopupWindow.OnDismissListener {
		@Override
		public void onDismiss() {
			backgroundAlpha(1f);
		}
	}

	/*
	 * ����popWindow����͸����
	 */
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		getWindow().setAttributes(lp);
	}

}