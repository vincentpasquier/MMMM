package ch.eiafr.mmmm.state.sight;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import ch.eiafr.mmmm.messages.Direction;
import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.state.State;

public class SightState implements State {

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private final SightThread sightThread = new SightThread();
	private final AtomicBoolean first = new AtomicBoolean(true);
	private Direction currentDirection = Direction.CENTER;

	@Override
	public void execute(final Robot robot) {
		if (first.compareAndSet(true, false)) {
			sightThread.robot = robot;
			scheduler.scheduleAtFixedRate(sightThread, 0, 50, MILLISECONDS);
		}
	}

	@Override
	public State update(final Tasks task) {
		Direction newDirection = Direction.getDirection(task);
		if (newDirection != null && !newDirection.equals(currentDirection)) {
			currentDirection = newDirection;
		}
		synchronized (sightThread.LOCK) {
			sightThread.direction = currentDirection;
		}
		return this;
	}

	private static final class SightThread implements Runnable {

		private Direction direction;
		private Robot robot;
		private final Object LOCK = new Object();
		private final static int SENSITIVITY = 10;

		private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		private final int width = (int) (screenSize.getWidth());
		private final int heigth = (int) (screenSize.getHeight());

		@Override
		public void run() {
			PointerInfo info = MouseInfo.getPointerInfo();
			synchronized (LOCK) {
				Point point = info.getLocation();
				point.translate(direction.getX() * SENSITIVITY, direction.getY() * SENSITIVITY);
				if (isPointValid(point)) {
					robot.mouseMove(point.x, point.y);
				}
			}
		}

		private boolean isPointValid(final Point point) {
			if (point.x < 0.0)
				return false;
			if (point.x >= width)
				return false;
			if (point.y < 0.0)
				return false;
			if (point.y >= heigth)
				return false;
			return true;
		}
	}

}
