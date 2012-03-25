package ch.eiafr.mmmm.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ch.eiafr.mmmm.event.IEventBusDispatcher;

public final class EventBusServer implements Runnable {

	public static final int LISTEN_PORT = 4444;
	private final IEventBusDispatcher dispatcher;
	private boolean stopped = false;
	private final ExecutorService threadPool = Executors.newFixedThreadPool(10);
	private ServerSocket server;

	public EventBusServer(IEventBusDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public void run() {
		openServerSocket();
		while (!isStopped()) {
			try {
				Socket client = server.accept();
				threadPool.execute(new SocketWorker(client, dispatcher));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized boolean isStopped() {
		return stopped;
	}

	public synchronized void stop() {
		stopped = true;
	}

	private void openServerSocket() {
		try {
			server = new ServerSocket(LISTEN_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
