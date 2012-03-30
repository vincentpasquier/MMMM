/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

import ch.eiafr.mmmm.gui.listener.DefaultActionListener;

import java.awt.GridLayout;
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
	
	private ActionListener defaultActionListener;;

	private JPanel networkPanel = new NetworkPanel();
	private JPanel wiiHandPanel = new WiiHandPanel(defaultActionListener);
	private JPanel wiiHeadPanel = new WiiHeadPanel(defaultActionListener);
	private JPanel kinectPanel = new KinectPanel(defaultActionListener);


	/**
	 * Create the panel.
	 */
	public ControlPanel(ActionListener actionListener) {
		
		defaultActionListener = actionListener;
		
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
