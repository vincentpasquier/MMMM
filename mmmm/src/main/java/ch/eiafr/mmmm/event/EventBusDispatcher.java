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

	@Override
	public void enqueue(final EventMessage message) {
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
				final EventMessage message = qEvents.take();
				final Tasks task = Tasks.getTask(message);
				if (task != null) {
					hEvents.handle(task);
				}
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
