package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.skplanet.ocb.soi.sugar.async.AsyncWorker;
import com.skplanet.ocb.soi.sugar.async.Result;
import com.skplanet.ocb.soi.sugar.async.threadpool.ThreadPoolWorker;

public class ThreawdPoolWorkerTest {

	// public static <T> T convertInstanceOfObject(Object obj){
	// return (T) obj.getClass().cast(obj);
	// }

	public static void main(String args[]) {

		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(1024);
		AsyncWorker worker = new ThreadPoolWorker(10, 100, 30, workQueue);
		
		//여기부터 코딩!
		Result<String> result = worker.work(new Callable<String>() {
			@Override
			public String call() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("it works!");
				return "it works! yes!";
			}
		});

		System.out.println("sleep...zzzz...");
		System.out.println(result.get(5, TimeUnit.SECONDS));
		System.out.println("finished");

		//아래엔 코딩하지 마세요!
		((ThreadPoolWorker) worker).getThreadPoolExecutor().shutdown();
	}

}
