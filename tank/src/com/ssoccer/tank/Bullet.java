package com.ssoccer.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private int x, y;
	private Group group = Group.BAD;
	private static final int SPEED = 10;
	private boolean living = true;
	private static int WIDTH = ResourceMgr.bulletD.getWidth();
	private static int HEIGHT = ResourceMgr.bulletD.getHeight();
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public static int getWIDTH() {
		return WIDTH;
	}
	public static void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}
	public static int getHEIGHT() {
		return HEIGHT;
	}
	public static void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	Rectangle rect = new Rectangle();
	private Dir dir;
	public boolean inside = true;
	TankFrame tf = null;
	//子弹构造方法
	public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		this.x = x + Tank.width/2 - WIDTH/2 + 4;
		this.y = y + Tank.height/2 - HEIGHT/2 + 3;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
	}
	
	public void paint(Graphics g) {

			if(!inside) {
				tf.bullets.remove(this);
			}
		if(living) {
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
	}
	
	
	public void collideWith(Tank tank) {
		if(this.group == tank.getGroup()) return;
		
		if(rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			tf.explodes.add(new Explode(x, y, tf));
		}
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
		
		//update rect
		rect.x = this.x;
		rect.y = this.y;
		
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) inside = false;
	}

	public void die() {
		this.living = false;
		tf.bullets.remove(this);
	}
}
