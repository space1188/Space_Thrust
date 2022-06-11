package main;

public class Particle {
	
	Vector position;
	Vector velocity;
	Vector gravity;
	
	public Particle(double x, double y, double speed, double direction, double grav) {
		
		position = new Vector(x, y);
		velocity = new Vector(0, 0);
		
		velocity.setLength(speed);
		velocity.setAngle(direction);
		
		gravity = new Vector(0, grav);
		
		
	}
	
	public void accelerate(double accel) {
		this.velocity.addTo(accel);
	}
	public void accelerate(Vector accel) {
		this.velocity.addTo(accel);
	}
	
	public void update() {
		velocity.addTo(gravity);
		position.addTo(velocity);
	}
	
	
}
