package com.skplanet.ocb.soi.sugar.async;

import java.util.concurrent.Callable;

public interface AsyncWorker {
	<V> Result<V> work(Callable<V> job);
}
