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
import javax.swing.JToggleButton;

import ch.eiafr.mmmm.messages.Tasks;

/**
 * @author yannickjemmely
 *
 */
public class WiiHeadPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DIRECTION_PANEL_SIZE = 3;

	private ActionListener actionListener;

	public WiiHeadPanel(ActionListener actionListener){

		this.actionListener = actionListener;

		initialize();
		build();
	}

	private void initialize(){
		setLayout(new GridLayout(DIRECTION_PANEL_SIZE,DIRECTION_PANEL_SIZE));
		setBorder(BorderFactory.createTitledBorder("WiiHead"));
	}

	private void build(){
		for(int i = 0 ; i < DIRECTION_PANEL_SIZE*DIRECTION_PANEL_SIZE ; i++){
			JToggleButton button = new JToggleButton();
			button.setText(String.valueOf(Tasks.values()[i]));
			add(button);
		}
	}

}
