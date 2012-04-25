package ch.eiafr.mmmm;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.net.EventBusServer;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage.Source;

public final class SocketClientTest {

//	private static final Random random = new Random();

	// public static void main(String[] args) throws UnknownHostException,
	// IOException, InterruptedException {
	// for (int i = 0; i < 10000; i++) {
	// EventMessage msg = buildRandomEventMessages();
	// send(msg);
	// }
	// }

	@Test
	public void testSwipeOne() throws UnknownHostException, IOException {
		long timestamp = System.currentTimeMillis();
		EventMessage msg = buildSpecifiedMessage(Tasks.INVENTORY_SWIPE, Source.WII_HAND, timestamp, 1000, 0);
		send(msg);
		msg = buildSpecifiedMessage(Tasks.INVENTORY_NUMBER, Source.KINECT, timestamp + 100, 1000, 1);
		send(msg);
	}

	private static void send(EventMessage message) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", EventBusServer.LISTEN_PORT);
		s.getOutputStream().write(message.toByteArray());
		s.close();
	}

	// private static EventMessage buildRandomEventMessages() {
	// Tasks task = Tasks.values()[random.nextInt(Tasks.values().length)];
	// EventMessage.Builder builder = EventMessage.newBuilder();
	// builder.setDuration(random.nextInt(10000));
	// builder.setNamedEvent(task.getIdentifier());
	// builder.setSource(Source.values()[random.nextInt(Source.values().length)]);
	// builder.setTimestamp(System.currentTimeMillis());
	// builder.setValue(random.nextInt(10));
	// return builder.build();
	// }

	private static EventMessage buildSpecifiedMessage(Tasks task, Source source, long timestamp, int duration, int value) {
		EventMessage.Builder builder = EventMessage.newBuilder();
		builder.setDuration(duration);
		builder.setNamedEvent(task.getIdentifier());
		builder.setSource(source);
		builder.setTimestamp(timestamp);
		builder.setValue(value);
		return builder.build();
	}
}
