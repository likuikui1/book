package com.chxy.usedbook.view;

import android.widget.GridView;
/**
 *
 * @author ���
 *
 */
public class BooksTypeGridView extends GridView {

	 public BooksTypeGridView(android.content.Context context,    
	            android.util.AttributeSet attrs)    
	    {    
	        super(context, attrs);    
	    }    
	  
	    /** 
	     * ��д��onMeasure���� 
	     */  
	    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)    
	    {    
	        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,    
	                MeasureSpec.AT_MOST);    
	        super.onMeasure(widthMeasureSpec, expandSpec);    
	    
	    }    

}
