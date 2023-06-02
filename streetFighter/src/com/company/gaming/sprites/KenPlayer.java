package com.company.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KenPlayer extends Sprite{
	private BufferedImage walkImages[] = new BufferedImage[6];
	private BufferedImage standingImages[] = new BufferedImage[8];
	private BufferedImage punchImages[] = new BufferedImage[6];
	private BufferedImage kickImages[] = new BufferedImage[6];
	private BufferedImage damageImages[] = new BufferedImage[5];
	public KenPlayer() throws IOException{
		   x=GWIDTH-370;
		   y = FLOOR - h;
		   speed = 0;
		   currentMove = STANDING;
		   image = ImageIO.read(KenPlayer.class.getResource(KEN_IMAGE));
		   loadWalkImages();
		   loadStandingImages();
		   loadPunchImages();
		   loadKickImages();
		   loadDamageImages();
	   }
	public void jump() {
		   if(!isJump) {
		   force = DEFAULTFORCE;
		   y = y +force;
		   isJump = true;
		   }
	   }
	   public void fall() {
		   if(y>=(FLOOR-h)) {
			   isJump = false;
			   return;
			   }
		   force = force+ GRAVITY;
		   y = y + force;
		   
	   }
	
	private void loadWalkImages() {
		   walkImages[0] = image.getSubimage(1265,866,60,91);
		   walkImages[1] = image.getSubimage(1334,865,64,90);
		   walkImages[2] = image.getSubimage(1409,863,59,92);
		   walkImages[3] = image.getSubimage(1551,866,61,89);
		   walkImages[4] = image.getSubimage(1621,868,60,88);
		   walkImages[5] = image.getSubimage(1967,868,60,87);
		   
	   }
	
	private void loadStandingImages() {
		standingImages[0] = image.getSubimage(1692,3619,64,98);
		standingImages[1] = image.getSubimage(1767,3615,60,103);
		standingImages[2] = image.getSubimage(1897,3613,62,103);
		standingImages[3] = image.getSubimage(1832,3598,60,117);
		standingImages[4] = image.getSubimage(1965,3623,61,92);
		standingImages[5] = image.getSubimage(2045,3630,52,84);
		standingImages[6] = image.getSubimage(1690,1565,64,98);
		standingImages[7] = image.getSubimage(1354,2203,72,100);
	   }
	
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(1049,1413,62,76);
		punchImages[1] = image.getSubimage(1121,1392,73,95);
		punchImages[2] = image.getSubimage(1204,1362,59,125);
		punchImages[3] = image.getSubimage(1276,1395,76,93);
		punchImages[4] = image.getSubimage(1580,1424,92,63);
		punchImages[5] = image.getSubimage(1693,1487,55,69);
	   }
	
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(1104,1565,123,98);
		kickImages[1] = image.getSubimage(989,1582,103,80);
		kickImages[2] = image.getSubimage(1465,1668,118,95);
		kickImages[3] = image.getSubimage(1427,1561,67,97);
		kickImages[4] = image.getSubimage(1624,1560,67,98);
		kickImages[5] = image.getSubimage(1494,1564,118,95);
	   }
 
	private void loadDamageImages() {
		damageImages[0]=image.getSubimage(361,973,73,89);
		damageImages[1]=image.getSubimage(1628,3278,70,89);
		damageImages[2]=image.getSubimage(1439,3274,81,95);
		damageImages[3]=image.getSubimage(1876,3287,72,80);
		damageImages[4]=image.getSubimage(1956,3283,68,81);
	}
	public BufferedImage walkImage() {
		   if(imageIndex>5) {
			   imageIndex = 0;
			   currentMove = STANDING;
		   }
		   BufferedImage img = walkImages[imageIndex];
		   imageIndex++;
		   return img;
	   }
	public BufferedImage standingImage() {
		 if(imageIndex>5)imageIndex = 0;
		   BufferedImage img = standingImages[imageIndex];
		   imageIndex++;
		   return img;
	   }
	public BufferedImage punchImage() {
		 if(imageIndex>5) {
			 imageIndex = 0;
		   currentMove = STANDING;
		   isAttacking = false;
		 }
		   BufferedImage img = punchImages[imageIndex];
		   imageIndex++;
		   return img;
	   }
	public BufferedImage kickImage() {
		 if(imageIndex>5) {
			 imageIndex = 0;
		   currentMove = STANDING;
		   isAttacking = false;
	}
		   BufferedImage img = kickImages[imageIndex];
		   imageIndex++;
		   return img;
	   }
	public BufferedImage damageImage() {
		 if(imageIndex>4){
			   imageIndex = 0;
			   currentMove = STANDING;
		   }
		   BufferedImage img = damageImages[imageIndex];
		   imageIndex++;
		   return img;
	   }
	
	@Override
	public BufferedImage defaultImage() {
		   if(currentMove == WALK) {
			   return walkImage();
		   }
		   else if(currentMove == DAMAGE) {
			   return damageImage();
		   }
		   else if(currentMove == PUNCH) {
			   return punchImage();
		   }
		   else if(currentMove == KICK) {
			   return kickImage();
		   }
		   return standingImage();
	   }
}
