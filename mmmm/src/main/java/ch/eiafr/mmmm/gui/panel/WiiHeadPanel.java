/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ch.eiafr.mmmm.gui.listener.ManagedButton;
import ch.eiafr.mmmm.messages.Tasks;

/**
 * @author yannickjemmely
 *
 */
public class WiiHeadPanel extends JPanel {
	
	private final Tasks[] moveTasks = {
			Tasks.SIGHT_UP_LEFT,
			Tasks.SIGHT_UP,
			Tasks.SIGHT_UP_RIGHT,
			Tasks.SIGHT_LEFT,
			Tasks.SIGHT_CENTER,
			Tasks.SIGHT_RIGHT,
			Tasks.SIGHT_DOWN_LEFT,
			Tasks.SIGHT_DOWN,
			Tasks.SIGHT_DOWN_RIGHT
	};

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DIRECTION_PANEL_SIZE = 3;

	public WiiHeadPanel(){
		initialize();
		build();
	}

	private void initialize(){
		setLayout(new GridLayout(DIRECTION_PANEL_SIZE,DIRECTION_PANEL_SIZE));
		setBorder(BorderFactory.createTitledBorder("WiiHead"));
	}

	private void build(){
		for(int i = 0 ; i < DIRECTION_PANEL_SIZE*DIRECTION_PANEL_SIZE ; i++){

			ManagedButton button = new ManagedButton(moveTasks[i]);
			button.addToComponent(this);

		}
	}

}
