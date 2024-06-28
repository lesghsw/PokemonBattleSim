package com.PokemonBattleSim.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyHandler implements KeyListener, MouseListener, MouseMotionListener {
	
	GamePanel gp;
	
	private Position mousePosition;
	private boolean mouseClicked, mousePressed;
	
	public boolean upPressed, downPressed, rightPressed, leftPressed, spPressed, spPrevPressed;
	
	public KeyHandler(GamePanel gp) {
		
		this.gp = gp;
		mousePosition = new Position(0, 0);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_F2) {
			spPressed = true;
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
		
		if (code == KeyEvent.VK_F2) {
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

	public void clearMouseClick() { mouseClicked = false; }
	
	public Position getMousePosition() {
		return mousePosition;
	}

	public boolean isMouseClicked() {
		return mouseClicked;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		mousePosition = new Position((int) e.getPoint().getX(), (int) e.getPoint().getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		mousePosition = new Position((int) e.getPoint().getX(), (int) e.getPoint().getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) { mousePressed = true; }

	@Override
	public void mouseReleased(MouseEvent e) {
		
		mouseClicked = true;
		mousePressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public boolean isPrevKeyPressed(int keyCode) {
		
		switch (keyCode) {
			case KeyEvent.VK_F2:
				return spPrevPressed;
			default:
				return false;
		}
	}
	
	public void updatePrevKeyState() { spPrevPressed = spPressed; }
}
