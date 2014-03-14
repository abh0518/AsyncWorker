package com.skplanet.ocb.soi.sugar.async.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class SimpleActorPool implements ActorPool {

	private static String WORKER_NAME_PREFIX = "worker";

	private int poolSize;
	private int workerIndex;
	private ActorSystem actorSystem;
	private ActorRef[] actorRefs;

	public SimpleActorPool(ActorSystem actorSystem, int poolSize) {
		this.actorSystem = actorSystem;
		this.poolSize = poolSize;
		this.workerIndex = poolSize;
		this.actorRefs = new ActorRef[poolSize];
		for (int i = 0; i < poolSize; i++) {
			actorRefs[i] = this.actorSystem.actorOf(
					Props.create(WorkActor.class),
					WORKER_NAME_PREFIX + Integer.toString(i));
		}
	}

	public synchronized ActorRef getActorRef() {
		// TODO Auto-generated method stub
		workerIndex = ++workerIndex % poolSize;
		return this.actorRefs[workerIndex];
	}

}
