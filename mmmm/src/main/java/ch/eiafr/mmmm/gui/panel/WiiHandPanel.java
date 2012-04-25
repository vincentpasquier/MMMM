/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.eiafr.mmmm.gui.listener.ManagedButton;
import ch.eiafr.mmmm.gui.listener.ManagedToggleButton;
import ch.eiafr.mmmm.messages.Tasks;

/**
 * @author yannickjemmely
 * 
 */
public class WiiHandPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int ROWS = 2;
	private static final int COLS = 1;

	public WiiHandPanel() {

		initialize();
		build();
	}

	private void initialize() {
		setLayout(new GridLayout(ROWS, COLS));
		setBorder(BorderFactory.createTitledBorder("Wiihand"));
	}

	private void build() {

		ManagedButton swipeButton = new ManagedButton(Tasks.INVENTORY_SWIPE);
		swipeButton.addToComponent(this);

		JPanel crossPanel = new WiiMoteCrossPanel();
		add(crossPanel);
	}

	class WiiMoteCrossPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private static final int ROWS = 3;
		private static final int COLS = 3;

		private final Tasks[][] moveTasks = { { Tasks.MOVE_FORWARD_START, Tasks.MOVE_FORWARD_STOP }, { Tasks.MOVE_LEFT_START, Tasks.MOVE_LEFT_STOP },
				{ Tasks.MOVE_RIGHT_START, Tasks.MOVE_RIGHT_STOP }, { Tasks.MOVE_BACKWARD_START, Tasks.MOVE_BACKWARD_STOP } };

		public WiiMoteCrossPanel() {
			initilize();
			build();
		}

		private void initilize() {
			setLayout(new GridLayout(ROWS, COLS));
		}

		private void build() {
			for (int i = 0; i < ROWS * COLS; i++) {
				if (i % 2 != 0) {
					ManagedToggleButton button = new ManagedToggleButton(moveTasks[(i / 2)][0], moveTasks[(i / 2)][1]);
					button.addToComponent(this);
				} else {
					add(new JLabel());
				}
			}
		}
	}
}
