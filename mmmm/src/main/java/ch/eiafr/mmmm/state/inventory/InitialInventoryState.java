package ch.eiafr.mmmm.state.inventory;

import java.awt.Robot;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.state.State;

public final class InitialInventoryState implements State {

	@Override
	public State update(final Tasks task) {
		if (task.equals(Tasks.INVENTORY_NUMBER)) {
			return new KinectModalityInventoryState(task);
		} else if (task.equals(Tasks.INVENTORY_SWIPE)) {
			return new WiiModalityInventoryState(task);
		}
		return this;
	}

	@Override
	public void execute(final Robot robot) {
	}

}
