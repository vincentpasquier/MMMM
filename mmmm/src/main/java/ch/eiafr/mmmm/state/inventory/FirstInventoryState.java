package ch.eiafr.mmmm.state.inventory;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.state.State;

public abstract class FirstInventoryState implements State {

	private final Tasks task;

	public FirstInventoryState(final Tasks task) {
		this.task = task;
	}

	protected Tasks getTask() {
		return task;
	}

	protected boolean isValid(final Tasks task) {
		long ttl = this.task.getTimestamp() + this.task.getDuration();
		return (ttl - task.getTimestamp()) > 0;
	}

}
