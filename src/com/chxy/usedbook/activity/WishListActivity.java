package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xutils.x;

import com.chxy.usedbook.adapter.ViewPagerFragmentAdapter;
import com.chxy.usedbook.callback.MyCallBack;
import com.chxy.usedbook.utils.CheckNetWorkUtil;
import com.chxy.usedbook.utils.JsonUtils;
import com.chxy.usedbook.utils.XUtil;
import com.chxy.usedbook.view.CircleProgressDialog;
import com.chxy.usedbook.vo.Wish;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 心愿单界面
 * 
 * @author 胡楚涵
 * 
 */
public class WishListActivity extends Activity {

	private Context mContext=this;
	
	private ImageView wishEdit;
	private ListView wishListView;
	
	private List<Wish> wishList;
	private Wish wish;
	private CircleProgressDialog circleProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wishlist);

		// 初始化控件
		initview();

		initData();
		// 添加适配器
//		wishListView.setAdapter(new MyAdapter());
	}

	private void initData() {

		if (CheckNetWorkUtil.CheckNetWorkUtil(mContext)) {
//			progressBar.setVisibility(View.VISIBLE);
			circleProgressDialog = new CircleProgressDialog(mContext);
			circleProgressDialog.setText("正在登录。");
			circleProgressDialog.showDialog();
			getDataFromServer();
		} else {
			Toast.makeText(mContext, "请检查网络链接", 3000).show();
		}
	}

	private void getDataFromServer() {

		String url = XUtil.IP+"book_findAllBooks";//
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "total");

		XUtil.Post(url, map, new MyCallBack<String>() {

			@Override
			public void onSuccess(String result) {
				super.onSuccess(result);
				circleProgressDialog.dismiss();
				//解析
				String code = JsonUtils.parseResultCode(result);
				if (code.equals("0")) {//
					
					wishList=new ArrayList<>();
					wishList =JsonUtils.parseWishData(result);					
					if (!wishList.isEmpty()&&wishList.size()>0) {
						
						/*ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(
								getActivity(), bookList);
						mlistView.setAdapter(adapter);
						progressBar.setVisibility(View.GONE);*/
						wishListView.setAdapter(new MyAdapter());
					}
					
				} else {//解析错误
					Toast.makeText(mContext, "请重新打开心愿单", 1000).show();
				}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				super.onError(ex, isOnCallback);
				Toast.makeText(mContext, "服务器正在维护，稍后访问", 1000).show();
			}
		});
	}

	private void initview() {
		wishEdit = (ImageView) findViewById(R.id.wishlist_edit_img);
		wishEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent wtobuyIntent = new Intent(mContext, WantToBuyBookActivity.class);
				startActivity(wtobuyIntent);
			}

		});
		wishListView = (ListView) findViewById(R.id.wishlist_listview);
		wishListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				wish=wishList.get(position);
				Intent mIntent=new Intent(mContext,WishListDetailActivity.class);
				mIntent.putExtra("data_bookName", wish.getBookName());
				mIntent.putExtra("data_newDegree", wish.getNewDegree());
				mIntent.putExtra("data_publishTime", wish.getWishTime());
				mIntent.putExtra("data_rmb", wish.getWishRmb());
				mIntent.putExtra("data_wishContent", wish.getWishContent());
				mIntent.putExtra("data_userName", wish.getUser().getUserName());
				mIntent.putExtra("data_userSex", wish.getUser().getUserSex());
				mIntent.putExtra("data_userTel", wish.getUser().getUserTel());
				mIntent.putExtra("data_userQQ", wish.getUser().getUserqq());
				mIntent.putExtra("data_userWeChat", wish.getUser().getUserWC());
				startActivity(mIntent);
			}			
		});
	}
	//自定义一个适配器
	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return wishList.size();
		}

		@Override
		public Object getItem(int position) {
			return wishList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			viewHolder holder=new viewHolder();
			if (convertView == null) {
				view = View.inflate(getApplicationContext(),
						R.layout.wishlist_listview_item, null);
				holder.userName=((TextView)view.findViewById(R.id.wish_username_txt));
				holder.userSex=(ImageView) (view.findViewById(R.id.wish_boy_img));
				holder.userTel=((TextView)view.findViewById(R.id.wish_phone_txt));
				holder.wishTime=((TextView)view.findViewById(R.id.wish_publish_time_txt));
				holder.bookName=((TextView)view.findViewById(R.id.wish_course_txt));
				holder.bookPrice=((TextView)view.findViewById(R.id.wish_price_txt));
				holder.wishContent=((TextView)view.findViewById(R.id.wish_content_txt));
			} else {
				view = convertView;
				holder=(viewHolder) view.getTag();
			}			
			
			if(wish.getUser()!=null){
				if(wish.getUser().getUserName()!=null&&!wish.getUser().getUserName().equals("")){
					holder.userName.setText(wish.getUser().getUserName());
				}
			}
			if(wish.getUser()!=null){
				if(wish.getUser().getUserSex()!=null&& !wish.getUser().getUserSex().equals("")){
					if (wish.getUser().getUserSex().equals("男")){//
			        	holder.userSex.setImageResource(R.drawable.home_icon_boy);
					} else {
						holder.userSex.setImageResource(R.drawable.home_icon_girl);
					}
				}
			}
			if(wish.getUser()!=null){
				if(wish.getUser().getUserTel()!=null&&!wish.getUser().getUserTel().equals("")){
					holder.userTel.setText(wish.getUser().getUserTel());
				}
			}
			if(wish.getWishTime()!=null&&!wish.getWishTime().equals("")){
				holder.wishTime.setText(wish.getWishTime());
			}
			if(wish.getBookName()!=null&&!wish.getBookName().equals("")){
				holder.bookName.setText(wish.getBookName());
			}
			if(wish.getBookPrice()!=null&&!wish.getBookPrice().equals("")){
				holder.bookPrice.setText(wish.getBookPrice());
			}
			if(wish.getWishContent()!=null&&!wish.getWishContent().equals("")){
				holder.wishContent.setText(wish.getWishContent());
			}
			
			/*if(wish.getBookImage()!=null&&!wish.getBookImage().equals("")){
				x.image().bind(holder.bookImage,wish.getBookImage());
			}*/
			return view;
		}

	}
	class viewHolder{
		private TextView userName;
		private ImageView userSex;
		private TextView userTel;
		private TextView wishTime;
		private TextView bookName;
		private TextView bookPrice;
		private TextView wishContent;
		private ImageView userPhoto;
	}
}