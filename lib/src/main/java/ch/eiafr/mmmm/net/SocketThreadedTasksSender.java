package ch.eiafr.mmmm.net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;

public class SocketThreadedTasksSender {

	private final String ipAdress;
	private final int port;
	private final EventMessage msg;

	public SocketThreadedTasksSender(final String ipAdress, final int port, final EventMessage msg) {
		this.ipAdress = ipAdress;
		this.port = port;
		this.msg = msg;
	}

	public void execute() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Socket socket = new Socket(ipAdress, port);
					msg.writeTo(socket.getOutputStream());
					socket.close();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public String toString() {
		return msg.toString();
	}
}
