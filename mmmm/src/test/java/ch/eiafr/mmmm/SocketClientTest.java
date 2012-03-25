package ch.eiafr.mmmm;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ch.eiafr.mmmm.net.EventBusServer;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage.Source;

public class SocketClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		EventMessage.Builder builder = EventMessage.newBuilder();
		builder.setSource(Source.KINECT).setTimestamp(System.currentTimeMillis()).setNamedEvent("Blu").setDuration(12);
		EventMessage msg = builder.build();
		send(msg);
		Thread.sleep(1000);
		send(msg);
	}

	private static void send(EventMessage message) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", EventBusServer.LISTEN_PORT);
		message.writeTo(s.getOutputStream());
		s.close();
	}
}
