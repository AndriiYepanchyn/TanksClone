package main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Timer;

import display.Display;

public class Main {

	public static void main(String[] args) {
		Display.create(800, 600, "Tanks 1990 Clone");
		
		Timer timer = new Timer(1000/60, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Display.render();
			}
			
		});
		
		timer.setRepeats(true);
		timer.start();
	}

}
