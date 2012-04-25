package ch.eiafr.mmmm.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import ch.eiafr.mmmm.gui.singleton.Console;
import ch.eiafr.mmmm.gui.singleton.ServerAddress;
import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage.Source;
import ch.eiafr.mmmm.net.SocketThreadedTasksSender;

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
		jb = new JButton(task.getIdentifier() + " " + (value != 0 ? value : ""));
		jb.addActionListener(this);
	}

	public void addToComponent(final JComponent comp) {
		comp.add(jb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EventMessage msg = EventMessage.newBuilder().setDuration(4000).setTimestamp(System.currentTimeMillis()).setNamedEvent(task.getIdentifier())
				.setSource(source).setValue(value).build();
		new SocketThreadedTasksSender(ServerAddress.INSTANCE.getAddress(), ServerAddress.INSTANCE.getPort(), msg).execute();
		Console.INSTANCE.display(msg.toString());
	}
}
