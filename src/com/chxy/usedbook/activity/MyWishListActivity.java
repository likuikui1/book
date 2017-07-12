package com.chxy.usedbook.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * �ҵ���Ը��
 * @author ������
 *
 */
public class MyWishListActivity extends Activity implements OnClickListener {

	private ImageView myWishListReturn;
	private ImageView myWishListEdit;
	private ListView myWishListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_wishlist);

		// ��ʼ���ؼ�
		initview();

		// ���������
		myWishListView.setAdapter(new MyAdapter());
	}

	private void initview() {
		// ��ʼ���ؼ�
		myWishListReturn = (ImageView) findViewById(R.id.mylistview_return_img);
		myWishListEdit = (ImageView) findViewById(R.id.mylistview_edit_img);
		myWishListView = (ListView) findViewById(R.id.wishlist_listview);

		// ���ü�����
		myWishListReturn.setOnClickListener(this);
		myWishListEdit.setOnClickListener(this);

	}


	
	// ���õ���¼�
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ���ذ�ť
		case R.id.mylistview_return_img:
			finish();
			break;
		// �༭��ť
		case R.id.mylistview_edit_img:
			Intent wtobuyIntent = new Intent(MyWishListActivity.this, WantToBuyBookActivity.class);
			startActivity(wtobuyIntent);
			break;

		default:
			break;
		}

	}
	//�Զ���һ��������
    private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 20;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view;
			if(convertView==null){
				view=View.inflate(getApplicationContext(), R.layout.wishlist_listview_item, null);
			}else{
				view=convertView;
			}
			return view;
		}
    	
    	
    }
	
	
}