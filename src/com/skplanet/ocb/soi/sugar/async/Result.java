package com.skplanet.ocb.soi.sugar.async;

import java.util.concurrent.TimeUnit;

public interface Result<V> {
	V get(long timeout, TimeUnit unit);
	V get();
}
