package ch.eiafr.mmmm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.wiigee.control.WiimoteWiigee;
import org.wiigee.device.Wiimote;
import org.wiigee.event.ButtonListener;
import org.wiigee.event.ButtonPressedEvent;
import org.wiigee.event.ButtonReleasedEvent;
import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;
import org.wiigee.filter.HighPassFilter;
import org.wiigee.filter.RotationThresholdFilter;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;
import ch.eiafr.mmmm.net.SocketThreadedTasksSender;

public class WiiHand {
	public static void main(String[] args) {

		// E84ECECD5999 127.0.0.1 4444
		if (args.length < 3) {
			System.err.println("usage: wiihand wii-mac-address ipadress port");
			System.exit(0);
		}

		try {
			new WiimoteWiigee();
			System.out.println(args[0]);
			Wiimote mote = new Wiimote(args[0], true, true);
			WiiHandHandler handler = new WiiHandHandler(mote, args[1], Integer.parseInt(args[2]));
			mote.setTrainButton(Wiimote.BUTTON_HOME);
			mote.setAccelerationEnabled(true);
			mote.addAccelerationFilter(new HighPassFilter());
			mote.addRotationFilter(new RotationThresholdFilter(0.5));
			mote.addGestureListener(handler);
			mote.addButtonListener(handler);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Don't forget to use -d32 JVM option for Bluetooth!");
		}

		try {
			Thread.sleep(600000000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static final class WiiHandHandler implements GestureListener, ButtonListener {

		private final Wiimote mote;
		private final String ipadress;
		private final int port;
		private final List<String> gestureMeanings;
		private final AtomicBoolean automaticButton = new AtomicBoolean(false);

		private WiiHandHandler(final Wiimote mote, final String ipadress, final int port) {
			this.mote = mote;
			this.ipadress = ipadress;
			this.port = port;
			gestureMeanings = loadGestures();
			for (String s : gestureMeanings) {
				System.out.println(s);
			}
		}

		private List<String> loadGestures() {
			List<String> gestures = new ArrayList<String>();
			try {
				// open the gesture set file
				File f = new File(WiiHandHandler.class.getResource("../../../wiihand.wgs").getFile());
				BufferedReader in = new BufferedReader(new FileReader(f));

				// load the single gestures
				String line;
				while (in.ready()) {
					line = in.readLine();
					mote.loadGesture(f.getParent() + "/" + line);
					gestures.add(line);
				}

				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return gestures;
		}

		@Override
		public void gestureReceived(GestureEvent event) {
			if (event.isValid()) {
				String gesture = gestureMeanings.get(event.getId());
				if (gesture.equals(Tasks.PICK_MINE.getIdentifier())) {
					gesture = automaticButton.get() ? Tasks.PICK_AUTO_START.getIdentifier() : gesture;
				}
				EventMessage.Builder builder = EventMessage.newBuilder().setDuration(4000).setNamedEvent(gesture)
						.setSource(EventMessage.Source.WII_HAND).setTimestamp(System.currentTimeMillis());
				new SocketThreadedTasksSender(ipadress, port, builder.build()).execute();
				System.out.println(gesture);
			}
		}

		@Override
		public void buttonPressReceived(ButtonPressedEvent event) {
			EventMessage.Builder builder = EventMessage.newBuilder();
			builder.setDuration(4000).setSource(EventMessage.Source.WII_HAND).setTimestamp(System.currentTimeMillis());
			switch (event.getButton()) {
			case Wiimote.BUTTON_UP:
				builder.setNamedEvent(Tasks.MOVE_FORWARD_START.getIdentifier());
				break;
			case Wiimote.BUTTON_LEFT:
				builder.setNamedEvent(Tasks.MOVE_LEFT_START.getIdentifier());
				break;
			case Wiimote.BUTTON_RIGHT:
				builder.setNamedEvent(Tasks.MOVE_RIGHT_START.getIdentifier());
				break;
			case Wiimote.BUTTON_DOWN:
				builder.setNamedEvent(Tasks.MOVE_BACKWARD_START.getIdentifier());
				break;
			case Wiimote.BUTTON_MINUS:
				builder.setNamedEvent(Tasks.PUT_BLOCK.getIdentifier());
				break;
			case Wiimote.BUTTON_A:
				automaticButton.set(true);
				break;
			default:
				builder.clear();
				return;
			}
			new SocketThreadedTasksSender(ipadress, port, builder.build()).execute();
		}

		@Override
		public void buttonReleaseReceived(ButtonReleasedEvent event) {
			EventMessage.Builder builder = EventMessage.newBuilder();
			builder.setDuration(4000).setSource(EventMessage.Source.WII_HAND).setTimestamp(System.currentTimeMillis());
			switch (event.getButton()) {
			case Wiimote.BUTTON_UP:
				builder.setNamedEvent(Tasks.MOVE_FORWARD_STOP.getIdentifier());
				break;
			case Wiimote.BUTTON_LEFT:
				builder.setNamedEvent(Tasks.MOVE_LEFT_STOP.getIdentifier());
				break;
			case Wiimote.BUTTON_RIGHT:
				builder.setNamedEvent(Tasks.MOVE_RIGHT_STOP.getIdentifier());
				break;
			case Wiimote.BUTTON_DOWN:
				builder.setNamedEvent(Tasks.MOVE_BACKWARD_STOP.getIdentifier());
				break;
			case Wiimote.BUTTON_A:
				automaticButton.set(false);
				break;
			default:
				builder.clear();
				return;
			}
			new SocketThreadedTasksSender(ipadress, port, builder.build()).execute();
		}

	}
}
