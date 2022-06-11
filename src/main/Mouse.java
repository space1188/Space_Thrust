package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener, KeyListener {

	Game game;

	public Mouse(Game game) {
		this.game = game;
	}

	public void mouseClicked(MouseEvent mouse) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void keyPressed(KeyEvent ke) {

		if (ke.getKeyCode() == KeyEvent.VK_W) {
			game.thrusting = true;
		} else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			game.canShoot = true;
		} else if (ke.getKeyCode() == KeyEvent.VK_A) {
			game.turningLeft = true;
		} else if (ke.getKeyCode() == KeyEvent.VK_D) {
			game.turningRight = true;
		} else if (ke.getKeyCode() == KeyEvent.VK_F) {
			if (game.is == true) game.is = false; else game.is = true;
		}

	}

	public void keyReleased(KeyEvent ke) {
		
		
		if (ke.getKeyCode() == KeyEvent.VK_W) {
			game.thrusting = false;
		} else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			game.canShoot = false;
		} else if (ke.getKeyCode() == KeyEvent.VK_A) {
			game.turningLeft = false;
		} else if (ke.getKeyCode() == KeyEvent.VK_D) {
			game.turningRight = false;
		}
		

	}

	public void keyTyped(KeyEvent ke) {

	}

}
