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
 * 书籍分类界面
 * @author 阮飞
 *
 */
public class BooksTypeActivity extends Activity {
	private ImageView mReturnImageView;//返回

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
		 //取得数据源
        mlist = getData();
        //将数据源对应给list
        myAdapter = new BooksTypeListViewAdapter(getApplicationContext(), mlist);
        //将自定义的listview界面匹配给listview
        bookstype_lv.setAdapter(myAdapter);
		
	}
	
	  private List<Map<String, Object>> getData() {

	        mmlist1 = getDetailData1();
	        mmlist2 = getDetailData2();
	        mmlist3 = getDetailData3();

	        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("label", "教材类");
	        map.put("info", mmlist1);
	        data.add(map);
	        map = new HashMap<String, Object>();
	        map.put("label", "考试类");
	        map.put("info", mmlist2);
	        data.add(map);
	        map = new HashMap<String, Object>();
	        map.put("label", "课外读物");
	        map.put("info", mmlist3);
	        data.add(map);

	        return data;
	    }

	    //对应list view中第一个item的数据
	    public List<Map<String,Object>> getDetailData1() {
	    	
	        List<Map<String,Object>> img_data = new ArrayList<>();
	        Map<String,Object> map = new HashMap<>();
	        map.put("title","必修类");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","财经");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","计算器");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","经济");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","旅管");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","文教");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","数学");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","艺术");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","机电");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","化材");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","外语");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","其他");
	        img_data.add(map);
	       
	        return img_data;
	    }

	    //对应list view中第二个item的数据
	    public List<Map<String,Object>> getDetailData2() {
	    	
	        List<Map<String,Object>> img_data = new ArrayList<>();
	        Map<String,Object> map = new HashMap<>();
	        map.put("title","外语");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","教师资格证");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","计算机等级");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","会计");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","考研");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","公务员");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","导游证");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","艺术/体育");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","医药");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","普通话");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","建筑工程");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","其他");
	        img_data.add(map);
	       
	        return img_data;
	    }
	    
	    //对应list view中第三个item的数据
	    public List<Map<String,Object>> getDetailData3() {
	    	
	        List<Map<String,Object>> img_data = new ArrayList<>();
	        Map<String,Object> map = new HashMap<>();
	        map.put("title","文学");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","散文/诗集");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","小说");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","励志/成功");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","科技");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","历史");
	        img_data.add(map);
	        
	        map = new HashMap<>();
	        map.put("title","生活");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","文艺");
	        img_data.add(map);
	        map = new HashMap<>();
	        map.put("title","其他");
	        img_data.add(map);

	        return img_data;
	    }

	
}