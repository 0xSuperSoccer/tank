package com.ssoccer.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;




public class TankFrame extends Frame{
	
	Tank myTank = new Tank(200, 200, Dir.DOWN, this);
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	static final int GAME_WIDTH = 1600, GAME_HEIGHT = 1000;
	
	
	
	
	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("Tank War");
		setVisible(true);
		
		this.addKeyListener(new MyKeyListener());
		
		addWindowListener(new WindowAdapter() {
	
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	
	
	
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	
	
	
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量：" + bullets.size(), 10, 60);
		g.setColor(c);
		
		myTank.paint(g);
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		for(int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		
//		for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
//			Bullet b = it.next();
//			if(!b.live) it.remove();
//		}
	}


	
	
	
	
	class MyKeyListener extends KeyAdapter{
		
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			
			switch (key) {
			
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
				
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;
				
			default:
				break;
			}
			
			setMainTankDir();
			
		}

		
		
		
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
				
			default:
				break;
			}
			
			setMainTankDir();
			
		}

		
		
		
		
		private void setMainTankDir(){
//			int x = myTank.getX();
//			int y = myTank.getY();
//			if(!bL && !bU && !bD && !bR) myTank.setMoving(false);
//			else {
//				myTank.setMoving(true);
//				if(bL) {
//					myTank.setDir(Dir.LEFT);
//					myTank.setX(x -= Tank.getSpeed());
//				}
//				if(bU) {
//					myTank.setDir(Dir.UP);
//					myTank.setY(y -= Tank.getSpeed());
//				}
//				if(bD) {
//					myTank.setDir(Dir.DOWN);
//					myTank.setY(y += Tank.getSpeed());
//				}
//				if(bR) {
//					myTank.setDir(Dir.RIGHT);
//					myTank.setX(x += Tank.getSpeed());
//				}
//			}
			
			if(!bL && !bU && !bD && !bR) myTank.setMoving(false);
			else {
				myTank.setMoving(true);
				if(bL) myTank.setDir(Dir.LEFT);
				if(bR) myTank.setDir(Dir.RIGHT);
				if(bU) myTank.setDir(Dir.UP);
				if(bD) myTank.setDir(Dir.DOWN);
			}
		}
	}
}
