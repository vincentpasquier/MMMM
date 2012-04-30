/**
 * 
 */
package ch.eiafr.mmmm.gui.panel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.eiafr.mmmm.gui.listener.ManagedButton;
import ch.eiafr.mmmm.messages.Tasks;

/**
 * @author yannickjemmely
 *
 */
public class KinectPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	public KinectPanel(){
		initilize();
		build();
	}
	
	private void initilize(){
		setBorder(BorderFactory.createTitledBorder("Kinect"));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	private void build(){

		ManagedButton mine = new ManagedButton(Tasks.PICK_MINE);
		mine.addToComponent(this);
		
		ManagedButton block = new ManagedButton(Tasks.PUT_BLOCK);
		block.addToComponent(this);
		
		ManagedButton startButton = new ManagedButton(Tasks.PICK_AUTO_START);
		startButton.addToComponent(this);
		
		ManagedButton stopButton = new ManagedButton(Tasks.PICK_AUTO_STOP);
		stopButton.addToComponent(this);

		JPanel kinectItemPanel = new KinectItemPanel();
		add(kinectItemPanel);
	}
	
	private static class KinectItemPanel extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final int NUMBER_PANEL_SIZE = 3;		
		
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
				ManagedButton button = new ManagedButton(Tasks.INVENTORY_NUMBER, i+1);
				button.addToComponent(this);
			}
		}

	}

}
