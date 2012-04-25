package ch.eiafr.mmmm.state.inventory;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.state.State;

public final class SecondStateModality implements State {

	private int inventoryNumber;

	public SecondStateModality() {
	}

	public SecondStateModality(final int inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	@Override
	public State update(final Tasks task) {
		return new InitialInventoryState().update(task);
	}

	@Override
	public void execute(final Robot robot) {
		robot.keyPress(KeyEvent.VK_0 + inventoryNumber);
		robot.keyRelease(KeyEvent.VK_0 + inventoryNumber);
		System.out.println(SecondStateModality.class.getName() + "[" + inventoryNumber + "]");
	}

}
