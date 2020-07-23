package com.ssoccer.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	
	public static int width = 50;
	public static int height = 50;
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
	
	
	

	private static final int SPEED = 5;
	
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
		
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		}
		move();
	}
	
	
	
	
	
	private void move() {
		
		if(!moving) return;
		
		switch(dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}		
	}

	
	
	
	
	
	public void fire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.dir, tf));
	}
	
	
}
