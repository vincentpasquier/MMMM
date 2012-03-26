package ch.eiafr.mmmm.event;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;

public final class EventBusDispatcher implements Runnable, IEventBusDispatcher {

	private final BlockingQueue<EventMessage> qEvents;
	private boolean stopped = false;

	public EventBusDispatcher() {
		qEvents = new ArrayBlockingQueue<EventMessage>(10);
	}

	public void enqueue(EventMessage message) {
		try {
			qEvents.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!isStopped()) {
			try {
				EventMessage message = qEvents.take();
				System.out.println(message.getSource().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized boolean isStopped() {
		return stopped;
	}

	public void stop() {
		stopped = true;
	}
}
