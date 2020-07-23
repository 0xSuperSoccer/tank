package com.ssoccer.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;




public class TankFrame extends Frame{
	
	Tank myTank = new Tank(200, 200, Dir.DOWN);
	Bullet b = new Bullet(300, 300, Dir.DOWN);
	
	
	
	
	
	public TankFrame() {
		setSize(1600, 1000);
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
	
	
	
	
	
	
	@Override
	public void paint(Graphics g) {
		
		myTank.paint(g);
		b.paint(g);
		
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
			int x = myTank.getX();
			int y = myTank.getY();
			if(!bL && !bU && !bD && !bR) myTank.setMoving(false);
			else {
				myTank.setMoving(true);
				if(bL) {
					myTank.setDir(Dir.LEFT);
					myTank.setX(x -= Tank.getSpeed());
				}
				if(bU) {
					myTank.setDir(Dir.UP);
					myTank.setY(y -= Tank.getSpeed());
				}
				if(bD) {
					myTank.setDir(Dir.DOWN);
					myTank.setY(y += Tank.getSpeed());
				}
				if(bR) {
					myTank.setDir(Dir.RIGHT);
					myTank.setX(x += Tank.getSpeed());
				}
			}
		}
	}
}
