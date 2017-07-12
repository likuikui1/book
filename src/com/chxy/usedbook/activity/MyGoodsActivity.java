package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chxy.usedbook.adapter.MyGoodsListViewAdapter;
import com.chxy.usedbook.adapter.ViewPagerFragmentAdapter;
import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.XUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * 我的商品界面
 * @author 阮飞
 *
 */
public class MyGoodsActivity extends Activity {
	private Context mContext=this;
	private ImageView mReturnImageView;//返回
	
	private ListView mygoods_lv;
	private TextView mTop;
	private TextView mDelete;
	private View mView;
	List<Map<String, Object>> mlist ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mygoods);

		initView();
		initData();
	}

	private void initView() {
		mReturnImageView =(ImageView)findViewById(R.id.bookstype_back);
		mReturnImageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		mygoods_lv = (ListView) findViewById(R.id.mygoods_lv);
		
	}

	private void initData() {
		
		mlist = new ArrayList<Map<String, Object>>();
		mlist = getData();
		 
		final MyGoodsListViewAdapter  myGoodsListViewAdapter = new MyGoodsListViewAdapter(getApplicationContext(),mlist);
		mygoods_lv.setAdapter(myGoodsListViewAdapter);

		mygoods_lv.setOnItemLongClickListener(new OnItemLongClickListener() {


			@SuppressLint("ShowToast")
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				
			
				// 创建对话框构建器
				AlertDialog.Builder builder = new AlertDialog.Builder(MyGoodsActivity.this);
				
				// 获取布局
				mView = LayoutInflater.from(MyGoodsActivity.this).inflate(R.layout.mygoods_alertdialog, null);
				
				// 获取布局中的控件
				mTop = (TextView) mView.findViewById(R.id.mygoods_dialog_top);
				mDelete = (TextView) mView.findViewById(R.id.mygoods_dialog_delete);
				
				// 设置参数
				builder.setView(mView);
				
				// 创建对话框
				final AlertDialog dialog = builder.create();
				
				mTop.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						mlist.get(position);
						mlist.add(0, mlist.get(position));  
					   // 置顶后list.size增加一 所以要position+1  
						mlist.remove(position + 1);  
						myGoodsListViewAdapter.notifyDataSetChanged();
						Toast.makeText(MyGoodsActivity.this, "你顶我或者不顶我~我都在那里不离不弃！！", 0).show();
						dialog.dismiss();
					}
				});

				
				
				mDelete.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						
						int item = (Integer) mygoods_lv.getItemAtPosition(position);
						mlist.remove(item);
		                myGoodsListViewAdapter.notifyDataSetChanged();
		                Toast.makeText(MyGoodsActivity.this, "你伤害了我！还一笑而过！！",0).show();
		                dialog.dismiss();
						
					}
				});
				
				// 先调用这个方法，然后才设置dialog的长宽
				dialog.show();
				// 获取屏幕的长宽
				WindowManager window = getWindowManager();
				Display display = window.getDefaultDisplay();
				int screenheight = display.getHeight();
				int screenWidth = display.getWidth();
				// 设置弹出框的长宽
				dialog.getWindow().setLayout(screenWidth / 2, screenheight / 4);

				return false;
			}
		});

	}

	private List<Map<String, Object>> getData() {
		
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	     Map<String, Object> map = new HashMap<String, Object>();
	     map.put("titles", "考研英语词汇");
	     map.put("img", R.drawable.img_book1);
	     data.add(map);
	     
	     map = new HashMap<String, Object>();
	     map.put("titles", "百年孤独");
	     map.put("img", R.drawable.img_book2);
	     data.add(map);
	     
	     map = new HashMap<String, Object>();
	     map.put("titles", "考研英语词汇");
	     map.put("img", R.drawable.img_book1);
	     data.add(map);
	     
	     map = new HashMap<String, Object>();
	     map.put("titles", "百年孤独");
	     map.put("img", R.drawable.img_book2);
	     data.add(map);
	     
		return data;
		
		/*if (CheckNetWorkUtil.CheckNetWorkUtil(mContext)) {
			getDataFromServer();
		} else {
			Toast.makeText(mContext, "请检查网络链接", 3000).show();
		}*/
	}

	private void getDataFromServer() {
		String url = XUtil.IP+"book_findAllBooks";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "");//getType()

		XUtil.Post(url, map, new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);
				
				//解析
				String code = JsonUtils.parseResultCode(result);
				if (code.equals("0")) {//
					
					/*bookList=new ArrayList<>();
					bookList = JsonUtils.parseBookData(result);					
					if (!bookList.isEmpty()&&bookList.size()>0) {
						
						ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(
								getActivity(), bookList);
						mlistView.setAdapter(adapter);
					}*/
					
				} else {
					Toast.makeText(mContext, "已访问到网络，请检查code！", 3000).show();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);
				Toast.makeText(mContext, "没有访问服务器", 1000).show();
			}
		});
	}

}
