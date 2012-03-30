package ch.eiafr.mmmm.event;

import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;

public interface IEventBusDispatcher {
	void enqueue(final EventMessage msg);
}
