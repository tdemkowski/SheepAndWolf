package main;

import java.awt.*;

public class Main {

	static Stage stage = Stage.getInstance();

	public static void gameLoop() {
		long lastStartTime = System.currentTimeMillis();
		while (!stage.result()) {
			lastStartTime = System.currentTimeMillis();
			if (stage.readyToStep()){
				stage.step();
			}
			stage.repaint();
			long endTime = System.currentTimeMillis();
			try {
				Thread.sleep(20 - (endTime - lastStartTime));
			} catch (Exception e) {
				System.out.println("thread was interrupted, but really, who cares?");
			}
		}
	}
	
	public static void main(String[] args){
		System.out.println("Sheep and Wolves");

    	javax.swing.JFrame frame = new javax.swing.JFrame("Sheep and Wolves");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
  		stage.setPreferredSize(new Dimension(1280,720));
		frame.add(stage);
		frame.pack();
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();

		gameLoop();
	}
}