package com.chxy.usedbook.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chxy.usedbook.adapter.BooksTypeListViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * �鼮�������
 * @author ���
 *
 */
public class BooksTypeActivity extends Activity {
	private ImageView mReturnImageView;//����

	private ListView bookstype_lv;
	 List<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
	 List<Map<String, Object>> mmlist1 = new ArrayList<Map<String, Object>>();
	 List<Map<String, Object>> mmlist2 = new ArrayList<Map<String, Object>>();
	 List<Map<String, Object>> mmlist3 = new ArrayList<Map<String, Object>>();
	private BooksTypeListViewAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_bookstype);
		init();
	}

	private void init() {
		mReturnImageView =(ImageView)findViewById(R.id.bookstype_back);
		mReturnImageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		bookstype_lv = (ListView) findViewById(R.id.bookstype_lv);
		 //ȡ������Դ
        mlist = getData();
        //������Դ��Ӧ��list
        myAdapter = new BooksTypeListViewAdapter(getApplicationContext(), mlist);
        //���Զ����listview����ƥ���listview
        bookstype_lv.setAdapter(myAdapter);
		
	}
	
	  private List<Map<String, Object>> getData() {

	        mmlist1 = getDetailData1();
	        mmlist2 = getDetailData2();
	        mmlist3 = getDetailData3();

	        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("label", "�̲���");
	        map.put("info", mmlist1);
	        data.add(map);
	        map = new HashMap<String, Object>();
	        map.put("label", "������");
	        map.put("info", mmlist2);
	        data.add(map);
	        map = new HashMap<String, Object>();
	        map.put("label", "�������");
	        map.put("info", mmlist3);
	        data.add(map);

	        return data;
	    }

	    //��Ӧlist view�е�һ��item������
	    public List<Map<String,Object>> getDetailData1() {
	    	
	        List<Map<String,Object>> img_data = new ArrayList<>();
	        Map<String,Object> map = new HashMap<>();
	        map.put("title","������");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","�ƾ�");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","������");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","�ù�");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","�Ľ�");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","��ѧ");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	       
	        return img_data;
	    }

	    //��Ӧlist view�еڶ���item������
	    public List<Map<String,Object>> getDetailData2() {
	    	
	        List<Map<String,Object>> img_data = new ArrayList<>();
	        Map<String,Object> map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","��ʦ�ʸ�֤");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","������ȼ�");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","���");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����Ա");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","����֤");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����/����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","ҽҩ");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","��ͨ��");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","��������");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	       
	        return img_data;
	    }
	    
	    //��Ӧlist view�е�����item������
	    public List<Map<String,Object>> getDetailData3() {
	    	
	        List<Map<String,Object>> img_data = new ArrayList<>();
	        Map<String,Object> map = new HashMap<>();
	        map.put("title","��ѧ");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","ɢ��/ʫ��");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","С˵");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","��־/�ɹ�");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","�Ƽ�");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","��ʷ");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","����");
	        img_data.add(map);

	        return img_data;
	    }

	
}