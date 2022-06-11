package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;
	public static final int WIDTH = 1080, HEIGHT = 810;
	public static final String NAME = "Space Thrust Example";
	public Random random = new Random();

	Thread thread;
	public boolean running = false;

	static Window window;
	public static Mouse mouse;

	Particle ship;
	Vector thrust;
	Color color = Color.RED;
	boolean is = false;

	public boolean shoot = false, canShoot = false;

	double angle = 0;
	public boolean turningLeft = false, turningRight = false, thrusting = false;
	ArrayList<Particle> bullets;

	public Game() {

		mouse = new Mouse(this);
		window = new Window(WIDTH, HEIGHT, NAME, this, mouse);

		ship = new Particle(WIDTH / 2, HEIGHT / 2, 0, 0, 0);
		bullets = new ArrayList<Particle>();
		thrust = new Vector(0, 0);

	}

	public void update() {

		if (turningLeft)
			angle -= 0.05;
		if (turningRight)
			angle += 0.05;

		thrust.setAngle(angle);

		if (thrusting == true) {
			thrust.setLength(0.1);
			color = Color.GREEN;
		}

		if (thrusting == false) {
			thrust.setLength(0);
			color = Color.RED;
		}

		if (canShoot) {
			bullets.add(new Particle(ship.position.getX(), ship.position.getY(), 20, angle, 0));
		}

		for (int i = 0; i < bullets.size(); i++) {
			Particle b = bullets.get(i);
			b.update();

			int x = (int) b.position.getX();
			int y = (int) b.position.getY();

			

			if (is) {

				if (x + 10 > WIDTH)
					b.velocity.multBy(-1);
				if (x < 0)
					b.velocity.multBy(-1);

				if (y + 10 > HEIGHT)
					b.velocity.multBy(-1);
				if (y < 0)
					b.velocity.multBy(-1);

			}

			if (!is) {

				if (x + 10 > WIDTH)
					bullets.remove(b);
				if (x < 0)
					bullets.remove(b);

				if (y + 10 > HEIGHT)
					bullets.remove(b);
				if (y < 0)
					bullets.remove(b);

			}

		}

		ship.accelerate(thrust);
		ship.update();

		if (ship.position.getX() > WIDTH)
			ship.position.setX(0);
		if (ship.position.getX() < 0)
			ship.position.setX(WIDTH);
		if (ship.position.getY() > HEIGHT)
			ship.position.setY(0);
		if (ship.position.getY() < 0)
			ship.position.setY(HEIGHT);

	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);

		/////////////////////////////////////////

		AffineTransform old = g2d.getTransform();

		g2d.translate(ship.position.getX(), ship.position.getY());
		g2d.rotate(angle);

		g2d.setColor(Color.RED);

		GeneralPath path = new GeneralPath();

		path.moveTo(0, -10);
		path.lineTo(0, 10);
		path.lineTo(40, 0);
		path.lineTo(0, -10);

		if (thrusting) {

			path.moveTo(-5, -7);
			path.lineTo(-5, 7);
			path.lineTo(0, 7);
			path.lineTo(0, -7);
			path.lineTo(-5, -7);

		}

		g2d.fill(path);

		g2d.setTransform(old);

		g2d.setColor(Color.GRAY);

		for (int i = 0; i < bullets.size(); i++) {
			Particle b = bullets.get(i);
			g2d.fillOval((int) b.position.getX(), (int) b.position.getY(), 10, 10);
		}

		/////////////////////////////////////////

		g2d.dispose();
		bs.show();

	}

	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			if (running) {
				render();
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " UPDATES: " +
				// updates);
				// frames = 0;
				updates = 0;
			}

		}

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public static void main(String[] args) {

		new Game();

	}

}
