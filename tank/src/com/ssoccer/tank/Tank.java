package com.ssoccer.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {
	
	public static int width = 50;
	public static int height = 50;
	private boolean living = true;
	private Random random = new Random();
	private Group group = Group.BAD;
	private boolean moving = true;
	private int x,y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 5;
	
	Rectangle rect = new Rectangle();
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
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
	public Dir getDir() {
		return dir;
	}
	public void setDir(Dir dir) {
		this.dir = dir;
	}
	public static int getSpeed() {
		return SPEED;
	}
	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}	
	
	private TankFrame tf = null;
	//Tank类的构造方法
	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = width;
		rect.height = height;
	}
	
	public void paint(Graphics g) {
		if(living) {
			switch(dir) {
			case LEFT:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
				break;
			case UP:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
				break;
			}
			move();
		}
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
		
		if(this.group == Group.BAD && random.nextInt(100) > 97)
			this.fire();
		
		if(this.group == Group.BAD && random.nextInt(100) > 95)
			randomDir();
		
		boundsCheck();
		//update rect
		rect.x = this.x;
		rect.y = this.y;
	}
	
	private void boundsCheck() {
		if(this.x<0)
			x = 0;
		if (this.y<=30)
			y = 30;
		if (this.x> TankFrame.GAME_WIDTH - Tank.width)
			x = TankFrame.GAME_WIDTH - Tank.width;
		if (this.y> TankFrame.GAME_HEIGHT - Tank.height)
			y = TankFrame.GAME_HEIGHT - Tank.height;
	}
	
	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void fire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.group, tf));
		
		if(this.group == Group.GOOD) 
			new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}
	
	
	public void die() {
		this.living = false;
		tf.tanks.remove(this);
	}
	
	
	
	
	
}
