package com.company.gaming.sprites;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.company.gaming.Board;

public class Camera extends Sprite{
	private int moveBlock = 0;
	public Camera() {
		x = 11;
		y = 400;
		w = 1500;
		h = 800;
		try {
			image = ImageIO.read(Board.class.getResource("bg.jpeg"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void move() {
		outOfScreen();
		if(moveBlock == 1) {
			speed = 10;
		}
		else if(moveBlock == 2) {
			speed = -10;
		}
		
		x = x+speed;
	}

	public void outOfScreen() {
		if(x<= 10) {
			moveBlock = 1;// No move in left
		}
		else if(x>=3000-1500) {
			moveBlock = 2;// No move in right
		}
		else {
			moveBlock = 0;//Allow both
		}
//		if(x>=3000-1500 || x<=10) {
//			speed = 0;
//		}
	}
	
	@Override
	public BufferedImage defaultImage() {
		BufferedImage subImage = image.getSubimage(x, y, w, h);
		return subImage;
	}

}
