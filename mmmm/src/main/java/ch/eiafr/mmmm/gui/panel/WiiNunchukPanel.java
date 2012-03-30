/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import ch.eiafr.mmmm.messages.Tasks;

/**
 * @author yannickjemmely
 *
 */
public class WiiNunchukPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int DIRECTION_PANEL_SIZE = 3;

	public WiiNunchukPanel(){
		initilize();
		build();
	}
	
	private void initilize(){
		setLayout(new GridLayout(DIRECTION_PANEL_SIZE, DIRECTION_PANEL_SIZE));
		setBorder(BorderFactory.createTitledBorder("WiiHand - Nunchuk"));
	}
	
	private void build(){
		for(int i = 0; i < DIRECTION_PANEL_SIZE*DIRECTION_PANEL_SIZE; i++){
			JButton button = new JButton();
			button.setText(String.valueOf(Tasks.values()[i]));
			add(button);
		}
	}

}
