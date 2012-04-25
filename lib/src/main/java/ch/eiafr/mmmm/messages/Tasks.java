package ch.eiafr.mmmm.messages;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import ch.eiafr.mmmm.net.NetworkMessage.EventMessage;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage.Source;

public enum Tasks implements Executable {
	INVENTORY_SWIPE("SWIPE") {
		@Override
		public void execute(Robot robot) {
		}
	},
	INVENTORY_NUMBER("INV_NUMB") {
		@Override
		public void execute(Robot robot) {
		}
	},
	PICK_MINE("mine") {
		@Override
		public void execute(Robot robot) {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			System.out.println("Mined");
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
	},
	PICK_AUTO_START("begin mine", true) {
		@Override
		public void execute(Robot robot) {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
	},
	PICK_AUTO_STOP("end mine", new Tasks[] { PICK_AUTO_START }) {
		@Override
		public void execute(Robot robot) {
		}
	},
	MOVE_FORWARD_START("FORWARD_START", true) {
		@Override
		public void execute(Robot robot) {
			robot.keyPress(KeyEvent.VK_W);
		}
	},
	MOVE_FORWARD_STOP("FORWARD_STOP", new Tasks[] { MOVE_FORWARD_START }) {
		@Override
		public void execute(Robot robot) {
			robot.keyRelease(KeyEvent.VK_W);
		}
	},
	MOVE_BACKWARD_START("BACKWARD_START", true) {
		@Override
		public void execute(Robot robot) {
			robot.keyPress(KeyEvent.VK_S);
		}
	},
	MOVE_BACKWARD_STOP("BACKWARD_STOP", new Tasks[] { MOVE_BACKWARD_START }) {
		@Override
		public void execute(Robot robot) {
			robot.keyRelease(KeyEvent.VK_S);
		}
	},
	MOVE_LEFT_START("LEFT_START", true) {
		@Override
		public void execute(Robot robot) {
			robot.keyPress(KeyEvent.VK_A);
		}
	},
	MOVE_LEFT_STOP("LEFT_STOP", new Tasks[] { MOVE_LEFT_START }) {
		@Override
		public void execute(Robot robot) {
			robot.keyRelease(KeyEvent.VK_A);
		}
	},
	MOVE_RIGHT_START("RIGHT_START", true) {
		@Override
		public void execute(Robot robot) {
			robot.keyPress(KeyEvent.VK_D);
		}
	},
	MOVE_RIGHT_STOP("RIGHT_STOP", new Tasks[] { MOVE_RIGHT_START }) {
		@Override
		public void execute(Robot robot) {
			robot.keyRelease(KeyEvent.VK_D);
		}
	},
	SIGHT_UP_LEFT("S_UL") {
		@Override
		public void execute(Robot robot) {
		}
	},
	SIGHT_UP("S_U") {
		@Override
		public void execute(Robot robot) {
		}
	},
	SIGHT_UP_RIGHT("S_UR") {
		@Override
		public void execute(Robot robot) {
		}
	},
	SIGHT_LEFT("S_L") {
		@Override
		public void execute(Robot robot) {
		}
	},
	SIGHT_CENTER("S_C") {
		@Override
		public void execute(Robot robot) {
		}
	},
	SIGHT_RIGHT("S_R") {
		@Override
		public void execute(Robot robot) {
		}
	},
	SIGHT_DOWN_LEFT("S_DL") {
		@Override
		public void execute(Robot robot) {
		}
	},
	SIGHT_DOWN("S_D") {
		@Override
		public void execute(Robot robot) {
		}
	},
	SIGHT_DOWN_RIGHT("S_DR") {
		@Override
		public void execute(Robot robot) {
		}
	},
	;

	private final boolean continuous;
	private final Tasks[] cancels;
	private int value = 0;
	private long timestamp = 0;
	private int duration = 0;
	private Source source;
	private final String identifier;

	private Tasks(final String identifier) {
		this(identifier, false, new Tasks[0]);
	}

	private Tasks(final String identifier, final boolean continuous) {
		this(identifier, continuous, new Tasks[0]);
	}

	private Tasks(final String identifier, final Tasks[] cancels) {
		this(identifier, false, cancels);
	}

	private Tasks(final String identifier, final boolean continuous, final Tasks[] cancels) {
		this.cancels = cancels;
		this.identifier = identifier;
		this.continuous = continuous;
	}

	public String getIdentifier() {
		return identifier;
	}

	public int getValue() {
		return value;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getDuration() {
		return duration;
	}

	public Source getSource() {
		return source;
	}

	public boolean isContinuous() {
		return continuous;
	}

	public Tasks[] getCancels() {
		return cancels;
	}

	public static Tasks getTask(EventMessage message) {
		for (Tasks task : Tasks.values()) {
			if (message.getNamedEvent().equals(task.identifier)) {
				if (message.hasValue()) {
					task.value = message.getValue();
				}
				task.timestamp = message.getTimestamp();
				task.duration = message.getDuration();
				task.source = message.getSource();
				return task;
			}
		}
		return null;
	}

	public static EventMessage getEventMessage(Tasks task, Source source, int value) {
		EventMessage.Builder builder = EventMessage.newBuilder();
		builder.setDuration(2000).setNamedEvent(task.getIdentifier()).setSource(source).setTimestamp(System.currentTimeMillis()).setValue(0);
		return builder.build();
	}

}
