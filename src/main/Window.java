package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	int width = 1080, height = 810;
	String name = "";
	Game game;
	Mouse mouse;

	public Window(int WIDTH, int HEIGHT, String NAME, Game game, Mouse mouse) {

		//width = WIDTH;
		//height = HEIGHT;
		name = NAME;
		this.game = game;
		this.mouse = mouse;

		createWindow();

	}

	private void createWindow() {

		JFrame frame = new JFrame(name);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(this.width, this.height);
		frame.setPreferredSize(new Dimension(this.width, this.height));
		frame.setMaximumSize(new Dimension(this.width, this.height));
		frame.setMinimumSize(new Dimension(this.width, this.height));
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
		frame.add(game);
		game.addMouseListener(mouse);
		game.addKeyListener(mouse);
		game.setSize(width, height);
		game.setSize(new Dimension(this.width, this.height));
		game.setPreferredSize(new Dimension(this.width, this.height));
		game.setMaximumSize(new Dimension(this.width, this.height));
		game.setMinimumSize(new Dimension(this.width, this.height));
		frame.pack();
		
		

		frame.setVisible(true);
		game.start();
		

	}

}
