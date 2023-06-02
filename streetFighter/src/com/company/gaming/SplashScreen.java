package com.company.gaming;
 
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

import jaco.mp3.player.MP3Player;

public class SplashScreen extends JWindow{
	private JLabel label = new JLabel();
	
	private MP3Player player; 
	private void playSound() {
		player = new MP3Player(SplashScreen.class.getResource("music.mp3"));
		player.play();
	}
	
	public SplashScreen() {
		setSize(1200,900);
		setLocationRelativeTo(null);
		Icon icon = new ImageIcon(SplashScreen.class.getResource("splash.jpeg"));
		label.setIcon(icon);
		this.add(label);
		setVisible(true);
		playSound();
		try {
			Thread.sleep(6000);
			setVisible(false);
			dispose();
			player.stop();
			GameFrame ob = new GameFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SplashScreen screen = new SplashScreen();
	}
}
