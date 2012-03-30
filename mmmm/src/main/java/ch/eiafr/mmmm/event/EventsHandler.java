package ch.eiafr.mmmm.event;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.state.State;
import ch.eiafr.mmmm.state.inventory.InitialInventoryState;

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
		Runnable ttTasks = new EventsTaskHandler(sTasks, robot);
		scheduler.scheduleAtFixedRate(ttTasks, 0, 1000, MILLISECONDS);
		sStates.add(new InitialInventoryState());
	}

	public void handle(Tasks task) {
		sTasks.add(task);
		for (State state : sStates) {
			sStates.remove(state);
			state.execute(robot);
			sStates.add(state.update(task));
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
			for (Tasks task : tasks) {
				System.out.println(task);
				tasks.remove(task);
				task.execute(robot);
				if (task.isContinuous()) {
					tasks.add(task);
				}
			}
//			for (Tasks task : tasks) {
//				for (Tasks canceled : task.getCancels()) {
//					tasks.remove(canceled);
//				}
//			}
		}
	}
}
