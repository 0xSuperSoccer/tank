package com.ssoccer.tank;

import java.awt.Graphics;

public class Bullet {
	private static final int SPEED = 20;
	private static int WIDTH = ResourceMgr.bulletD.getWidth();
	private static int HEIGHT = ResourceMgr.bulletD.getHeight();
	private int x, y;
	private Dir dir;
	public boolean live = true;
	TankFrame tf = null;
	
	
	
	//子弹构造方法
	public Bullet(int x, int y, Dir dir, TankFrame tf) {
		this.x = x + Tank.width/2 - WIDTH/2;
		this.y = y + Tank.height/2 - HEIGHT/2 + 3;
		this.dir = dir;
		this.tf = tf;
	}
	
	
	
	
	public void paint(Graphics g) {
		
		if(!live) {
			tf.bullets.remove(this);
		}
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		}
		move();
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
