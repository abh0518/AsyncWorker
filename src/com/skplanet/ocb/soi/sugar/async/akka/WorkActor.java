package com.skplanet.ocb.soi.sugar.async.akka;

import java.util.concurrent.Callable;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WorkActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object message) throws Exception {
		// TODO Auto-generated method stub
		if (message instanceof Callable) {
			try {
				Object result = ((Callable) message).call();
				getSender().tell(result, getSelf());
			} catch (Exception e) {
				getSender().tell(new akka.actor.Status.Failure(e), getSelf());
				throw e;
			}
		} else
			unhandled(message);
	}

}
