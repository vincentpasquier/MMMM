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
import javax.swing.JToggleButton;

import ch.eiafr.mmmm.gui.singleton.Console;
import ch.eiafr.mmmm.gui.singleton.ServerAddress;
import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage.Source;

/**
 * @author yannickjemmely
 * 
 */
public class ManagedToggleButton implements ActionListener {

	private final Tasks start;
	private final Tasks stop;
	private final int value;
	private final Source source;
	private final JToggleButton jb;

	public ManagedToggleButton(final Tasks start, final Tasks stop) {
		this(start, stop, 0);
	}

	public ManagedToggleButton(final Tasks start, final Tasks stop,
			final int value) {
		this(start, stop, value, Source.WII_HAND);
	}

	public ManagedToggleButton(final Tasks start, final Tasks stop,
			final int value, final Source source) {
		this.start = start;
		this.stop = stop;
		this.value = value;
		this.source = source;
		jb = new JToggleButton(start.getIdentifier() + " "
				+ (value != 0 ? value : ""));
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

		EventMessage msg;
		String id = jb.isSelected() ? start.getIdentifier() : stop
				.getIdentifier();
		jb.setText(id);
		msg = EventMessage.newBuilder().setDuration(2000)
				.setTimestamp(System.currentTimeMillis()).setNamedEvent(id)
				.setSource(source).setValue(value).build();

		send(msg);
		Console.INSTANCE.display(msg);
		//System.out.println(msg.toString());
	}

	private static void send(EventMessage message) {
		try {
			Socket s = new Socket(ServerAddress.INSTANCE.getAddress(),
					ServerAddress.INSTANCE.getPort());
			message.writeTo(s.getOutputStream());
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
