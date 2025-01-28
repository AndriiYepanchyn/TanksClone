package io;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Input extends JComponent{

	private static final long serialVersionUID = 1L;
	private boolean[] map;
	
	public Input() {
		map = new boolean[256];
		for(int i = 0; i< map.length; i++) {
			final int KEY_CODE = i;
			
			
//			Following block operate with key pressed
			getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(i,0, false),
					i*2);
			getActionMap().put(i*2, new AbstractAction() {
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					map[KEY_CODE]=true;
				}	
			});
			
//			Following block operates with key released
			getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(i,0, true),
					i*2 + 1);
			getActionMap().put(i*2 +1, new AbstractAction() {
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					map[KEY_CODE]=false;
				}	
			});
			
		}
	}
	
	public boolean[] getMap() {
		return Arrays.copyOf(map, map.length);
	}
	
	public boolean getKey(int keyCode) {
		return map[keyCode];
	}
}
