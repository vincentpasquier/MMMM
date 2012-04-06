/**
 * 
 */
package ch.eiafr.mmmm.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.eiafr.mmmm.gui.panel.ControlPanel;

/**
 * @author yannickjemmely
 *
 */
public class UserInterfaceManager extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// panel
	private JPanel contentPane = new JPanel();
	private JPanel controlPanel;
	
	// settings
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterfaceManager frame = new UserInterfaceManager();
					frame.setTitle("MMMM | Magnificent Monocle Multimodal Minecraft");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInterfaceManager() {
		controlPanel = new ControlPanel();
		build();
		
	}
	
	private void build(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(controlPanel,BorderLayout.CENTER);
		
		setContentPane(contentPane);

	}

}
