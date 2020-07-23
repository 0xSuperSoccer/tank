package com.ssoccer.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private static final int SPEED = 20;
	private static int WIDTH = 15, HEIGHT = 15;
	private int x, y;
	private Dir dir;
	public boolean live = true;
	TankFrame tf = null;
	
	
	public Bullet(int x, int y, Dir dir, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	
	
	
	
	public void paint(Graphics g) {
		
		if(!live) {
			tf.bullets.remove(this);
		}
		
		move();
		
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
	}
	
	
	
	
	private void move() {
		
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
		
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
	}
}
