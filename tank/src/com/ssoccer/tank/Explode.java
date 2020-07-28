package com.ssoccer.tank;

import java.awt.Graphics;

public class Explode {

	//private static int WIDTH = ResourceMgr.explodes[0].getWidth();
	//private boolean living = true;
	//private static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	private int x, y;
	TankFrame tf = null;
	private int step = 0;
	
	
	//爆炸的构造方法
	public Explode(int x, int y, TankFrame tf) {
//		this.x = x + Tank.width/2 - WIDTH/2;
//		this.y = y + Tank.height/2 - HEIGHT/2 + 3;
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	
	
	
	public void paint(Graphics g) {
		//爆炸开始
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		//爆炸结束
		if(step >= ResourceMgr.explodes.length)
			tf.explodes.remove(this);
	}
	
}
