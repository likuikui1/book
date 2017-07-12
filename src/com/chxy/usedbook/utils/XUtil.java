package com.chxy.usedbook.utils;

import java.io.File;
import java.util.Map;

import org.xutils.x;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

public class XUtil {
	/**
	 * 发送get请求
	 * @param <T>
	 */	
	public static String IP="http://222.195.117.18:8080/Used_Book/";
	public static <T> Cancelable Get(String url,Map<String,String> map,CommonCallback<T> callback){
		RequestParams params=new RequestParams(url);
		if(null!=map){
			for(Map.Entry<String, String> entry : map.entrySet()){
				params.addQueryStringParameter(entry.getKey(), entry.getValue());
			}
		}
		Cancelable cancelable = x.http().get(params, callback);
		return cancelable;
	}
	
	/**
	 * 发送post请求
	 * @param <T>
	 */
	public static <T> Cancelable Post(String url,Map<String,Object> map,CommonCallback<T> callback){
		RequestParams params=new RequestParams(url);
		if(null!=map){
			for(Map.Entry<String, Object> entry : map.entrySet()){
				params.addParameter(entry.getKey(), entry.getValue());
			}
		}
		Cancelable cancelable = x.http().post(params, callback);
		return cancelable;
	}
	
	/**
	 * 上传文件
	 * @param <T>
	 */
	public static <T> Cancelable UpLoadFile(String url,Map<String,Object> map,CommonCallback<T> callback){
		RequestParams params=new RequestParams(url);
		if(null!=map){
			for(Map.Entry<String, Object> entry : map.entrySet()){
				params.addParameter(entry.getKey(), entry.getValue());
				
			}
		}
		params.setMultipart(true);
		Cancelable cancelable = x.http().post(params, callback);
		return cancelable;
	}
	
	/**
	 * 下载文件
	 * @param <T>
	 */
	public static <T> Cancelable DownLoadFile(String url,String filepath,CommonCallback<T> callback){
		RequestParams params=new RequestParams(url);
		//设置断点续传
		params.setAutoResume(true);
		params.setSaveFilePath(filepath);
		Cancelable cancelable = x.http().get(params, callback);
		return cancelable;
	}
	/**
	 * 上传单张图片
	 */
	public static <T> Cancelable uploadSinglePicture(String url,String filepath,CommonCallback<T> callback)  
    {  
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("upload",new File(filepath),"multipart/form-data"); 
       
        Cancelable cancelable = x.http().post(params, callback);
       
        return cancelable;
    }  

	/**
	 * 上传多张图片
	 */
	public static <T> Cancelable uploadMutilPicture(String url,String[] filepaths,CommonCallback<T> callback)  
    {  	
		File[] files = null;
		for(int i=0;i<filepaths.length;i++){
			files[i] = new File(filepaths[i]);
    	}
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
   
        for (int i = 0; i < 9; i++) {  
            params.addBodyParameter("upload", files[i],"multipart/form-data");  
        } 
      
        Cancelable cancelable = x.http().post(params, callback);
       
        return cancelable;
    }  

}
