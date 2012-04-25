package ch.eiafr.mmmm.event;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.state.State;
import ch.eiafr.mmmm.state.inventory.InitialInventoryState;
import ch.eiafr.mmmm.state.sight.SightState;

public final class EventsHandler {

	private final Set<State> sStates;
	private final Set<Tasks> sTasks;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private Robot robot;

	public EventsHandler() {
		sStates = new CopyOnWriteArraySet<State>();
		sTasks = new CopyOnWriteArraySet<Tasks>();

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		robot.setAutoDelay(10);
		final Runnable ttTasks = new EventsTaskHandler(sTasks, robot);
		scheduler.scheduleAtFixedRate(ttTasks, 0, 150, MILLISECONDS);
		sStates.add(new InitialInventoryState());
		sStates.add(new SightState());
	}

	public void handle(final Tasks task) {
		System.out.println(task.getIdentifier());
		sTasks.add(task);
		for (State state : sStates) {
			sStates.remove(state);
			State update = state.update(task);
			update.execute(robot);
			sStates.add(update);
		}

	}

	private final static class EventsTaskHandler implements Runnable {

		private final Set<Tasks> tasks;
		private final Robot robot;

		private EventsTaskHandler(final Set<Tasks> tasks, final Robot robot) {
			this.tasks = tasks;
			this.robot = robot;
		}

		@Override
		public void run() {
			Set<Tasks> copiedTasks = new HashSet<Tasks>(tasks);
			for (Tasks task : copiedTasks) {
				task.execute(robot);
				copiedTasks.remove(task);
				if (task.isContinuous()) {
					copiedTasks.add(task);
				} else {
					for (Tasks canceled : task.getCancels()) {
						copiedTasks.remove(canceled);
					}
				}
			}
			tasks.clear();
			tasks.addAll(copiedTasks);
		}
	}
}
