/**
 * 
 */
package ch.eiafr.mmmm.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;

/**
 * @author yannickjemmely
 *
 */
public class NetworkPanel extends JPanel {
	
	private static final String name = "Network";

	/**
	 * Create the panel.
	 */
	public NetworkPanel() {

		setName(name);
		build();
	}
	
	private void build(){
		setLayout(new BorderLayout(0, 0));
	}

}
