/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.event.ActionListener;

/**
 * @author yannickjemmely
 *
 */
public class ControlPanel extends JPanel {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String name = "Control";
	

	private JPanel networkPanel;
	private JPanel wiiHandPanel;
	private JPanel wiiHeadPanel;
	private JPanel kinectPanel;


	/**
	 * Create the panel.
	 */
	public ControlPanel() {
		networkPanel = new NetworkPanel();
		wiiHandPanel = new WiiHandPanel();
		wiiHeadPanel = new WiiHeadPanel();
		kinectPanel = new KinectPanel();
		
		initilize();		
		build();
	}
	
	private void initilize(){
		setName(name);
		setLayout(new BorderLayout());
	}
	
	private void build(){
		add(networkPanel,BorderLayout.NORTH);
		add(wiiHandPanel,BorderLayout.WEST);
		add(wiiHeadPanel,BorderLayout.EAST);
		add(kinectPanel, BorderLayout.SOUTH);

	}
	

}
