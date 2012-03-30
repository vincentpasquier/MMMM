package ch.eiafr.mmmm.event;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;

public final class EventBusDispatcher implements Runnable, IEventBusDispatcher {

	private final BlockingQueue<EventMessage> qEvents;
	private final EventsHandler hEvents;
	private boolean stopped = false;

	public EventBusDispatcher() {
		qEvents = new ArrayBlockingQueue<EventMessage>(10);
		hEvents = new EventsHandler();
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
				Tasks task = Tasks.getTask(message);
				hEvents.handle(task);
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
