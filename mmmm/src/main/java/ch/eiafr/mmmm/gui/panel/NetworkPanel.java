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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import ch.eiafr.mmmm.gui.singleton.ServerAddress;

/**
 * @author yannickjemmely
 *
 */
public class NetworkPanel extends JPanel {
	
	private static final String labelAddressText = "Address";
	private static final String labelPortText = "Port";
	private static final String labelSaveText = "Save";
	
	private ActionListener actionListener;
	

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
		final JTextField fieldAddress = new JTextField(10);
		fieldAddress.setText(ServerAddress.INSTANCE.getAddress());
		JLabel labelPort = new JLabel(labelPortText);
		final JTextField fieldPort = new JTextField(10);
		fieldPort.setText(String.valueOf(ServerAddress.INSTANCE.getPort()));
		JButton buttonSave = new JButton(labelSaveText);
		buttonSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerAddress.INSTANCE.setAddress(fieldAddress.getText());
				ServerAddress.INSTANCE.setPort(Integer.parseInt(fieldPort.getText()));
			}
		});
	
		add(labelAdresse);
		add(fieldAddress);
		add(labelPort);
		add(fieldPort);
		add(buttonSave);
		
	}

}
