package com.chxy.usedbook.callback;

import org.xutils.common.Callback;

public class MyCallBack<ResultType> implements Callback.CommonCallback<ResultType>{

	@Override
	public void onSuccess(ResultType result) {
		//���Ը��ݹ�˾���������ͳһ������ɹ����߼�����
	}

	@Override
	public void onError(Throwable ex, boolean isOnCallback) {
		//���Ը��ݹ�˾���������ͳһ����������ʧ�ܵ��߼�����
	}

	@Override
	public void onCancelled(CancelledException cex) {
		
	}

	@Override
	public void onFinished() {
		
	}

}
