package com.company.gaming.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PowerEffect extends Sprite{

	PowerEffect(int x, int y, BufferedImage img){
		this.image = img;
		speed = 50;
		this.x = x;
		this.y = y;
		w = 80;
		h = 80;
	}
	
	@Override
	public BufferedImage defaultImage() {
		return image.getSubimage(14,3111,59,31);
	}
	
	public void printPower(Graphics pen) {
		   pen.drawImage(defaultImage(), x, y, w,h, null);
		   move();
	   }

}
