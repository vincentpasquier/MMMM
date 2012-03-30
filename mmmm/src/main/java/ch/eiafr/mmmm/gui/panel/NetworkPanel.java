/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JList;

/**
 * @author yannickjemmely
 *
 */
public class NetworkPanel extends JPanel {
	
	private static final String labelAddressText = "Address";
	private static final String labelPortText = "Port";
	private static final String labelSaveText = "Save";
	

	/**
	 * Create the panel.
	 */
	public NetworkPanel() {
		
		initilize();
		build();
	}
	
	private void initilize(){
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createTitledBorder("Network"));
	}
	
	private void build(){
		
		JLabel labelAdresse = new JLabel(labelAddressText);
		JTextField fieldAddress = new JTextField(10);
		JLabel labelPort = new JLabel(labelPortText);
		JTextField fieldPort = new JTextField(10);
		JButton buttonSave = new JButton(labelSaveText);
	
		add(labelAdresse);
		add(fieldAddress);
		add(labelPort);
		add(fieldPort);
		add(buttonSave);
		
	}

}
