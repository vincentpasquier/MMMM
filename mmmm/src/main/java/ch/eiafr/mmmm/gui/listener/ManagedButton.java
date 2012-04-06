/**
 * 
 */
package ch.eiafr.mmmm.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComponent;

import ch.eiafr.mmmm.gui.ServerAddress;
import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.net.EventBusServer;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage.Source;

/**
 * @author yannickjemmely
 * 
 */
public class ManagedButton implements ActionListener {

	private final Tasks task;
	private final int value;
	private final Source source;
	private final JButton jb;

	public ManagedButton(final Tasks task) {
		this(task, 0);
	}
	
	public ManagedButton(final Tasks task, final int value) {
		this(task, value, Source.WII_HAND);
	}

	public ManagedButton(final Tasks task, final int value, final Source source) {
		this.task = task;
		this.value = value;
		this.source = source;
		jb = new JButton(task.getIdentifier());
		jb.addActionListener(this);
	}
	
	public void addToComponent(final JComponent comp) {
		comp.add(jb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		EventMessage msg = EventMessage.newBuilder().setDuration(2000)
				.setTimestamp(System.currentTimeMillis())
				.setNamedEvent(task.getIdentifier())
				.setSource(source).setValue(value)
				.build();
		send(msg);
		System.out.println(msg.toString());
	}

	private static void send(EventMessage message) {
		try {
			Socket s = new Socket(ServerAddress.INSTANCE.getAddress(), ServerAddress.INSTANCE.getPort());
			message.writeTo(s.getOutputStream());
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
