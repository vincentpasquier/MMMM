package ch.eiafr.mmmm.state.mouse;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.state.State;

public class MouseState implements State {

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private final AtomicBoolean first = new AtomicBoolean(true);
	private final MouseThread mouseThread = new MouseThread();

	@Override
	public void execute(Robot robot) {
		if (first.compareAndSet(true, false)) {
			mouseThread.robot = robot;
			scheduler.scheduleAtFixedRate(mouseThread, 0, 250, MILLISECONDS);
		}
	}

	@Override
	public State update(Tasks task) {
		if (task.equals(Tasks.PICK_AUTO_START) || task.equals(Tasks.PICK_MINE) || task.equals(Tasks.PICK_AUTO_STOP)) {
			synchronized (mouseThread.LOCK) {
				mouseThread.task = task;
			}
		}
		return this;
	}

	private static final class MouseThread implements Runnable {

		private Tasks task = Tasks.PICK_AUTO_STOP;
		private final Object LOCK = new Object();
		private Robot robot;

		@Override
		public void run() {
			synchronized (LOCK) {
				switch (task) {
				case PICK_MINE:
					task = Tasks.PICK_AUTO_STOP;
				case PICK_AUTO_START:
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					break;
				}
			}
		}

	}

}
