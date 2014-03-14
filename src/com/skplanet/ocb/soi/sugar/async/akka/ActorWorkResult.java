package com.skplanet.ocb.soi.sugar.async.akka;

import java.util.concurrent.TimeUnit;

import scala.concurrent.CanAwait;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import com.skplanet.ocb.soi.sugar.async.Result;

public class ActorWorkResult<V> implements Result<V> {

	public static CanAwait DEFAULT_CAN_AWAIT = new CanAwait() {
	};

	Future<Object> future;

	public ActorWorkResult(Future<Object> result) {
		this.future = result;
	}

	@SuppressWarnings("unchecked")
	public V get(long n, TimeUnit unit) {
		// TODO Auto-generated method stub
		V result = null;
		try {
			result = (V) future.result(Duration.create(n, unit),
					DEFAULT_CAN_AWAIT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public V get() {
		// TODO Auto-generated method stub
		return this.get(0, TimeUnit.SECONDS);
	}

}
