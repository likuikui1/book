package com.chxy.usedbook.callback;

import org.xutils.common.Callback;

public class MyProgressCallBack<ResultType> implements Callback.ProgressCallback<ResultType>{

	@Override
	public void onSuccess(ResultType result) {
		//���ݹ�˾ҵ�����󣬴�����Ӧҵ���߼�
	}

	@Override
	public void onError(Throwable ex, boolean isOnCallback) {
		//���ݹ�˾ҵ�����󣬴�����Ӧҵ���߼�
	}

	@Override
	public void onCancelled(CancelledException cex) {
		
	}

	@Override
	public void onFinished() {
		
	}

	@Override
	public void onWaiting() {
		
	}

	@Override
	public void onStarted() {
		
	}

	@Override
	public void onLoading(long total, long current, boolean isDownloading) {
		
	}

}
