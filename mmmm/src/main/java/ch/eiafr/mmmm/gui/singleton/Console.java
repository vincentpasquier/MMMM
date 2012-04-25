/**
 * 
 */
package ch.eiafr.mmmm.gui.singleton;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author yannickjemmely
 * 
 */
public enum Console {
	INSTANCE;

	private final JScrollPane pane;
	private final JTextArea text;

	private Console() {
		pane = new JScrollPane();
		text = new JTextArea(5, 30);
		pane.setPreferredSize(new Dimension(600, 200));
		pane.getViewport().add(text);
	}

	public void addToComp(final JComponent comp) {
		comp.add(pane);
	}

	public void display(String msg) {
		text.append(msg);
	}
}
