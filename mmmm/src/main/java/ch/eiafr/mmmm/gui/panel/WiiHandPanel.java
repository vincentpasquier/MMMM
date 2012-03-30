/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
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
	
	private ActionListener actionListener;
	
	private static final int ROWS = 3;
	private static final int COLS = 2;

	public WiiHandPanel(ActionListener actionListener){
		
		this.actionListener = actionListener;
		
		initialize();
		build();
	}
	
	private void initialize(){
		setLayout(new GridLayout(ROWS, COLS));
		setBorder(BorderFactory.createTitledBorder("WiiHand - Wiimote"));
	}
	
	private void build(){
		JButton swipeButton = new JButton();
		swipeButton.setText(String.valueOf(Tasks.INVENTORY_SWIPE));
		add(swipeButton);
	}

}
