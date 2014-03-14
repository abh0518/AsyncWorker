package com.skplanet.ocb.soi.sugar.async.akka;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;

import com.skplanet.ocb.soi.sugar.async.AsyncWorker;


public class ActorWorker implements AsyncWorker {

	private ActorPool actorPool = null;

	public ActorWorker(ActorPool actorPool) {
		this.actorPool = actorPool;
	}

	@Override
	public <V> ActorWorkResult<V> work(Callable<V> job){
		ActorRef actor = actorPool.getActorRef();
		Future<Object> result = Patterns.ask(actor, job, new Timeout(Duration.create(100, TimeUnit.HOURS)));
		return new ActorWorkResult<V>(result);
	}
	
}

//
//ActorRef actor = actorPool.getActorRef();
//Future<Object> result = Patterns.ask(actor, job,
//		new Timeout(Duration.create(time, timeUnit)));
//return new ActorResult<Object>(result);