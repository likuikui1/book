package com.chxy.usedbook.view;

import android.widget.GridView;
/**
 *
 * @author 阮飞
 *
 */
public class BooksTypeGridView extends GridView {

	 public BooksTypeGridView(android.content.Context context,    
	            android.util.AttributeSet attrs)    
	    {    
	        super(context, attrs);    
	    }    
	  
	    /** 
	     * 重写的onMeasure方法 
	     */  
	    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)    
	    {    
	        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,    
	                MeasureSpec.AT_MOST);    
	        super.onMeasure(widthMeasureSpec, expandSpec);    
	    
	    }    

}
