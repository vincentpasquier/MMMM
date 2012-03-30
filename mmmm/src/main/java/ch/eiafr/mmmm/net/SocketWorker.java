package ch.eiafr.mmmm.net;

import java.io.IOException;
import java.net.Socket;

import ch.eiafr.mmmm.event.IEventBusDispatcher;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;

public final class SocketWorker implements Runnable {

	private final Socket client;
	private final IEventBusDispatcher dispatcher;

	public SocketWorker(final Socket clientSocket, final IEventBusDispatcher dispatcher) {
		this.client = clientSocket;
		this.dispatcher = dispatcher;
	}

	@Override
	public void run() {
		try {
			EventMessage message = EventMessage.parseFrom(client.getInputStream());
			dispatcher.enqueue(message);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}