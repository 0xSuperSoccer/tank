package com.ssoccer.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	
	private int x,y;
	
	
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	
	

	private Dir dir = Dir.DOWN;
	
	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	
	

	private static final int SPEED = 10;
	
	public static int getSpeed() {
		return SPEED;
	}

	
	
	
	
	private boolean moving = false;
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	
	
	
	
	private TankFrame tf = null;
	
	
	
	
	
	public Tank(int x, int y, Dir dir, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
		
	public void paint(Graphics g) {
		
		move();
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 50, 50);
		g.setColor(c);
		
	}
	
	
	private void move() {
		
		if(!moving) return;
		
		switch(dir) {
		case LEFT:
			x -= 1;
			break;
		case RIGHT:
			x += 1;
			break;
		case UP:
			y -= 1;
			break;
		case DOWN:
			y += 1;
			break;
		}		
	}

	public void fire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.dir, tf));
	}
	
	
}
