/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

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
	
	private static final int ROWS = 2;
	private static final int COLS = 1;
	
	
	private ActionListener actionListener;

	public WiiHandPanel(){
		
		initialize();
		build();
	}
	
	private void initialize(){
		setLayout(new GridLayout(ROWS,COLS));
	}
	
	private void build(){
		JButton swipeButton = new JButton();
		swipeButton.setText(String.valueOf(Tasks.INVENTORY_SWIPE));
		add(swipeButton);
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

		public WiiMoteCrossPanel(){
			initilize();
			build();
		}
		
		private void initilize(){
			setLayout(new GridLayout(ROWS, COLS));
		}
		
		private void build(){
			for(int i = 0; i < ROWS*COLS; i++){
				JButton button = new JButton();
				button.setPreferredSize(new Dimension(150, 100));
				button.setText(String.valueOf(Tasks.values()[i]));
				add(button);
			}
		}

	}


}
