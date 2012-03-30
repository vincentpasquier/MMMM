/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author yannickjemmely
 *
 */
public class KinectPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ActionListener actionListener;

	public KinectPanel(ActionListener actionListener){
		this.actionListener = actionListener;
		
		initilize();
		build();
	}
	
	private void initilize(){
		setBorder(BorderFactory.createTitledBorder("Kinect"));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	private void build(){


		JButton startButton = new JButton("start");
		add(startButton);

		JButton stopButton = new JButton("stop");
		add(stopButton);

		JPanel kinectItemPanel = new KinectItemPanel();
		add(kinectItemPanel);
	}
	
	private static class KinectItemPanel extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final int NUMBER_PANEL_SIZE = 3;
		
		private enum numbers {
			ONE,
			TWO,
			THREE,
			FOUR,
			FIVE,
			SIX,
			SEVEN,
			EIGHT,
			NINE,
		};
		
		
		
		private KinectItemPanel(){
			
			initialize();			
			build();
		}
		
		private void initialize(){
			setLayout(new GridLayout(NUMBER_PANEL_SIZE, NUMBER_PANEL_SIZE));
			setBorder(BorderFactory.createTitledBorder("Items"));
		}
		
		private void build(){
			
			
			for(int i = 0; i < NUMBER_PANEL_SIZE*NUMBER_PANEL_SIZE ; i++){
				JButton button = new JButton();
				button.setText(numbers.values()[i].toString());
				add(button);
			}
		}

	}

}
