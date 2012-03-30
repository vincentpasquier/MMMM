package ch.eiafr.mmmm.state.inventory;

import java.awt.Robot;

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
		return new InitialInventoryState();
	}

	@Override
	public void execute(final Robot robot) {
		System.out.println("BLU THE FUCK OUT ! [" + inventoryNumber + "]");
	}

}
