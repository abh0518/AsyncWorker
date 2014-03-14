package com.skplanet.ocb.soi.sugar.async.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.skplanet.ocb.soi.sugar.async.AsyncWorker;
import com.skplanet.ocb.soi.sugar.async.Result;

public class ThreadPoolWorker implements AsyncWorker {

	private static TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
	ThreadPoolExecutor executer;

	public ThreadPoolWorker(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, BlockingQueue<Runnable> workQueue) {
		this.executer = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, DEFAULT_TIME_UNIT, workQueue);
	}

	@Override
	public <V> Result<V> work(Callable<V> job) {
		FutureTask<V> fTask = new FutureTask<V>(job);
		executer.execute(fTask);
		return new ThreadPoolResult<V>(fTask);
	}

	public ThreadPoolExecutor getThreadPoolExecutor() {
		return this.executer;
	}
}
