package com.skplanet.ocb.soi.sugar.async.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.skplanet.ocb.soi.sugar.async.Result;

public class ThreadPoolResult<V> implements Result<V> {

	FutureTask<V> fTask;

	public ThreadPoolResult(FutureTask<V> fTask) {
		this.fTask = fTask;
	}

	@Override
	public V get(long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		V result = null;
		try {
			result = fTask.get(timeout, unit);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public V get() {
		// TODO Auto-generated method stub
		V result = null;
		try {
			result = fTask.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
