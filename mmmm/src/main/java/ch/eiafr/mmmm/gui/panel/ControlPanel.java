/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.eiafr.mmmm.gui.singleton.Console;

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
		JPanel control = new JPanel(new BorderLayout());
		control.add(networkPanel,BorderLayout.NORTH);
		control.add(wiiHandPanel,BorderLayout.WEST);
		control.add(wiiHeadPanel,BorderLayout.EAST);
		control.add(kinectPanel, BorderLayout.SOUTH);
		
		add(control,BorderLayout.CENTER);
		
		JPanel consolePanel = new JPanel(new BorderLayout());
		//consolePanel.add((new JLabel("asdfasdf")));
		Console.INSTANCE.addToComp(consolePanel);
		
		add(consolePanel,BorderLayout.SOUTH);		

	}
	

}
