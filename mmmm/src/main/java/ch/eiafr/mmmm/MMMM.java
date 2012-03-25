package ch.eiafr.mmmm;

import ch.eiafr.mmmm.event.EventBusDispatcher;
import ch.eiafr.mmmm.net.EventBusServer;

public class MMMM {

	public static void main(String[] args) {

		EventBusDispatcher dispatcher = new EventBusDispatcher();
		EventBusServer server = new EventBusServer(dispatcher);
		Thread tServer = new Thread(server);
		Thread tDispatcher = new Thread(dispatcher);
		tServer.start();
		tDispatcher.start();
	}
}
