package com.company.gaming;

import java.awt.Color;
import java.awt.Font;
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.company.gaming.sprites.Camera;
import com.company.gaming.sprites.KenPlayer;
import com.company.gaming.sprites.Power;
import com.company.gaming.sprites.PowerEffect;
import com.company.gaming.sprites.RyuPlayer;
import com.company.gaming.utils.GameConstants;
import com.company.gaming.utils.PlayerConstants;

public class Board extends JPanel implements GameConstants,PlayerConstants{
	BufferedImage backgroundImage;
	private RyuPlayer ryuPlayer;
	private KenPlayer kenPlayer;
	private Timer timer;
	private Power ryuPower;
	private Power kenPower;
	private boolean isGameOver;
	private Camera camera;
	private void loadPower() {
		ryuPower = new Power(100, "Ryu".toUpperCase());
		kenPower = new Power(GWIDTH/2+150, "ken".toUpperCase());
	}
	
	private void paintPower(Graphics pen) {
		ryuPower.printBox(pen);
		kenPower.printBox(pen);
	}
	public Board() throws IOException{
		camera = new Camera();
//		loadBackgroundImage();
		ryuPlayer = new RyuPlayer();
		kenPlayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();	
		loadPower();
		}
	
	@Override
	public void paintComponent(Graphics pen) {
		// Rendering / Painting
		super.paintComponent(pen);
		printBackgroundImage(pen);
		ryuPlayer.printPlayer(pen);
		kenPlayer.printPlayer(pen);
		paintPower(pen);
		printPower(pen);
		if(isGameOver) {
			printMsg(pen);
			timer.stop();
		}
	}
	
	public void collision() {
		if(isCollide()) {
			if(ryuPlayer.isAttacking()) {
				kenPlayer.setCurrentMove(DAMAGE);
				kenPower.setHealth();
			}
			if(kenPlayer.isAttacking()) {
			   ryuPlayer.setCurrentMove(DAMAGE);
			   ryuPower.setHealth();
			}
			if(ryuPower.getHealth()<=0 || kenPower.getHealth()<=0) {
				isGameOver = true;
			}
			ryuPlayer.setCollide(true);
			ryuPlayer.setSpeed(0);
			kenPlayer.setCollide(true);
			kenPlayer.setSpeed(0);
		}
		else {
			ryuPlayer.setSpeed(SPEED);
			kenPlayer.setSpeed(SPEED);
		}
	}
	
	private void printMsg(Graphics pen) {
		pen.setColor(Color.RED);
		pen.setFont(new Font("times",Font.BOLD, 60));
		pen.drawString("GAME OVER ...", GWIDTH/2-200, GHEIGHT/2);
	}
	private boolean isCollide() {
		int xDistance = Math.abs(ryuPlayer.getX()-kenPlayer.getX());
		int yDistance = Math.abs(ryuPlayer.getY()-kenPlayer.getY());
		int maxW = Math.max(ryuPlayer.getW(), kenPlayer.getW());
		int maxH = Math.max(ryuPlayer.getH(), kenPlayer.getH());
		return xDistance<=maxW-30 && yDistance<=maxH;
	}
	private void gameLoop() {
//		Thread trigger
		timer = new Timer(160, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				ryuPlayer.fall();
				kenPlayer.fall();
				collision();
			}
		});
		timer.start();
	}
	
	private void printPower(Graphics pen) {
		for(PowerEffect power : ryuPlayer.getPowers()) {
			power.printPower(pen);
		}
	}
	
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				ryuPlayer.setSpeed(-SPEED);
				ryuPlayer.setCurrentMove(WALK);
				ryuPlayer.move();
				camera.setSpeed(-SPEED);
				camera.move();
				ryuPlayer.setCollide(false);
//				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(ryuPlayer.isCollide()) {
					ryuPlayer.setSpeed(0);
					camera.setSpeed(0);
				}
				else {
				ryuPlayer.setCollide(false);
				ryuPlayer.setSpeed(SPEED);
				camera.setSpeed(SPEED);
				}
				ryuPlayer.setCurrentMove(WALK);
				ryuPlayer.move();
				camera.move();
//				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				ryuPlayer.setAttacking(true);
				ryuPlayer.setCurrentMove(KICK);
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				ryuPlayer.setAttacking(true);
				ryuPlayer.setCurrentMove(PUNCH);
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				ryuPlayer.jump();
			}
			else if(e.getKeyCode() == KeyEvent.VK_ALT) {
				ryuPlayer.setAttacking(true);
				ryuPlayer.setCurrentMove(POWER_EFFECT);
				ryuPlayer.addPower();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_A) {
				if(kenPlayer.isCollide()) {
					kenPlayer.setSpeed(0);
				}
				else {
				kenPlayer.setCollide(false);
				kenPlayer.setSpeed(-SPEED);
				}
				kenPlayer.setCurrentMove(WALK);
				kenPlayer.move();
//				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_D) {
				kenPlayer.setSpeed(SPEED);
				kenPlayer.setCurrentMove(WALK);
				kenPlayer.setCollide(false);
				kenPlayer.move();
//				repaint();
			}
			else if(e.getKeyCode() == KeyEvent.VK_W) {
				kenPlayer.setAttacking(true);
				kenPlayer.setCurrentMove(PUNCH);
			}
			else if(e.getKeyCode() == KeyEvent.VK_Z) {
				kenPlayer.setAttacking(true);
				kenPlayer.setCurrentMove(KICK);
			}
			else if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
				kenPlayer.jump();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			ryuPlayer.setSpeed(0);
		}
			
		});
	}
	private void printBackgroundImage(Graphics pen) {
//		pen.drawImage(backgroundImage, 0,0, GWIDTH,GHEIGHT,null);
		pen.drawImage(camera.defaultImage(), 0,0, GWIDTH,GHEIGHT,null);
	}
	private void loadBackgroundImage() {
		try {
			backgroundImage = ImageIO.read(Board.class.getResource("bg.jpeg"));
			}
			catch(Exception ex) {
				System.out.println("Background Image Loading Fail...");
				System.exit(0);
			
			}
	}	
	
}
