package com.PokemonBattleSim.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyHandler implements KeyListener, MouseListener, MouseMotionListener {
	
	GamePanel gp;
	
//	private Position pointerPoisiton;
	
	public boolean upPressed, downPressed, rightPressed, leftPressed, spPressed;
	
	public KeyHandler(GamePanel gp) {
		
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_F2) {
			spPressed = true;
			System.out.println("F2");
		}
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_F) {
			spPressed = false;
		}
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
	
	public boolean isKeyPressed(int KeyCode) {
		switch(KeyCode) {
			case KeyEvent.VK_F2:
				return spPressed;
			case KeyEvent.VK_W:
				return upPressed;
			case KeyEvent.VK_A:
				return downPressed;
			case KeyEvent.VK_S:
				return leftPressed;
			case KeyEvent.VK_D:
				return rightPressed;
			default:
				return false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
