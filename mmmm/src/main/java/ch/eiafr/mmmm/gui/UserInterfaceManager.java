/**
 * 
 */
package ch.eiafr.mmmm.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.eiafr.mmmm.gui.panel.ControlPanel;

/**
 * @author yannickjemmely
 * 
 */
public class UserInterfaceManager {

	private final JFrame frame = new JFrame();;

	// panel
	private final JPanel contentPane = new JPanel();
	private final JPanel controlPanel;

	// settings
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;

	/**
	 * Create the frame.
	 */
	public UserInterfaceManager() {
		controlPanel = new ControlPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());

		contentPane.add(controlPanel, BorderLayout.CENTER);

		frame.setContentPane(contentPane);
		frame.setTitle("MMMM | Magnificent Monocle Multimodal Minecraft");
		frame.setVisible(true);
	}

}
