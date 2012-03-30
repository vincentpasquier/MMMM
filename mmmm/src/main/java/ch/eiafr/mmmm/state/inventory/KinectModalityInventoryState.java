package ch.eiafr.mmmm.state.inventory;

import java.awt.Robot;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.state.State;

public final class KinectModalityInventoryState extends FirstInventoryState {

	public KinectModalityInventoryState(Tasks task) {
		super(task);
	}

	@Override
	public State update(Tasks task) {
		if (isValid(task)) {
			if (task.equals(Tasks.INVENTORY_NUMBER)) {
				return new SecondStateModality(this.getTask().getValue());
			} else if (task.equals(Tasks.INVENTORY_SWIPE)) {
				return new SecondStateModality();
			} else {
				return this;
			}
		} else {
			return new InitialInventoryState().update(task);
		}
	}

	@Override
	public void execute(Robot robot) {
	}

}
