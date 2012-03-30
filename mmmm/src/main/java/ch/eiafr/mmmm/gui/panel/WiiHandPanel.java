/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

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

	public WiiHandPanel(ActionListener actionListener){
		
		this.actionListener = actionListener;
		
		initialize();
		build();
	}
	
	private void initialize(){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("WiiHand"));
	}
	
	private void build(){
		
	}

}
