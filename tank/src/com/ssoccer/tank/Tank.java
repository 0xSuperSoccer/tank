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
	}
		
	
	
	
	
	
	public void paint(Graphics g) {
		if(living) {
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
		
		if(random.nextInt(100) > 97) this.fire();
	}

	
	
	
	
	
	public void fire() {
		tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.group, tf));
	}
	
	
	
	
	public void collideWith(Bullet bullet) {
		if(this.group == bullet.getGroup()) return;
		
		Rectangle rect1 = new Rectangle(this.x, this.y, width,height);
		Rectangle rect2 = new Rectangle(bullet.getX(), bullet.getY(), Bullet.getWIDTH(),Bullet.getHEIGHT());
		if(rect1.intersects(rect2)) {
			bullet.die();
			this.die();
		}
	}
	
	
	
	
	private void die() {
		this.living = false;
		tf.tanks.remove(this);
	}
	
	
	
	
	
}
