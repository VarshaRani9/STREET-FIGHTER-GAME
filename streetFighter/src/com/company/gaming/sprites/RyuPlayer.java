package com.company.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RyuPlayer extends Sprite{
   private BufferedImage walkImages[] = new BufferedImage[6];
   private BufferedImage standingImages[] = new BufferedImage[8];
   private BufferedImage punchImages[] = new BufferedImage[8];
   private BufferedImage kickImages[] = new BufferedImage[11];
   private BufferedImage damageImages[] = new BufferedImage[5];
   private BufferedImage powerEffectImages[] = new BufferedImage[9];
   public RyuPlayer() throws IOException{
	   x=100;
	   y = FLOOR - h;
	   speed = 0;
	   currentMove = STANDING;
	   image = ImageIO.read(RyuPlayer.class.getResource(RYU_IMAGE));
	   loadWalkImages();
	   loadStandingImages();
	   loadPunchImages();
	   loadKickImages();
	   loadDamageImages();
	   loadPowerEffectImages();
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
	   walkImages[0] = image.getSubimage(68,235,64,99);
	   walkImages[1] = image.getSubimage(147,236,68,95);
	   walkImages[2] = image.getSubimage(224,235,64,97);
	   walkImages[3] = image.getSubimage(307,234,57,99);
	   walkImages[4] = image.getSubimage(376,234,60,100);
	   walkImages[5] = image.getSubimage(450,240,71,93);
   }
   
   private void loadStandingImages() {
	   standingImages[0] = image.getSubimage(18,4,63,105);
	   standingImages[1] = image.getSubimage(94,5,62,102);
	   standingImages[2] = image.getSubimage(164,4,71,104);
	   standingImages[3] = image.getSubimage(244,2,71,106);
	   standingImages[4] = image.getSubimage(324,5,64,103);
	   standingImages[5] = image.getSubimage(392,4,66,105);
	   standingImages[6] = image.getSubimage(464,6,65,104);
	   standingImages[7] = image.getSubimage(534,8,65,102);
   }
   
   private void loadPunchImages() {
	   punchImages[0] = image.getSubimage(28,820,64,99);
	   punchImages[1] = image.getSubimage(108,819,72,99);
	   punchImages[2] = image.getSubimage(188,820,113,100);
	   punchImages[3] = image.getSubimage(311,822,81,94);
	   punchImages[4] = image.getSubimage(403,821,105,95);
	   punchImages[5] = image.getSubimage(100,708,70,91);
	   punchImages[6] = image.getSubimage(185,699,63,112);
	   punchImages[7] = image.getSubimage(434,698,78,97);
   }
   
   private void loadKickImages() {
	   kickImages[0] = image.getSubimage(40,1046,66,96);
	   kickImages[1] = image.getSubimage(121,1046,66,97);
	   kickImages[2] = image.getSubimage(200,1046,117,96);
	   kickImages[3] = image.getSubimage(328,1047,68,97);
	   kickImages[4] = image.getSubimage(407,1049,66,94);
	   kickImages[5] = image.getSubimage(481,1048,91,95);
	   kickImages[6] = image.getSubimage(4,1157,114,102);
	   kickImages[7] = image.getSubimage(124,1166,92,91);
	   kickImages[8] = image.getSubimage(233,1163,55,95);
	   kickImages[9] = image.getSubimage(394,1168,70,71);
	   kickImages[10] = image.getSubimage(482,1168,127,72);
   }
   private void loadDamageImages() {
		damageImages[0]=image.getSubimage(11,709,73,94);
		damageImages[1]=image.getSubimage(32,2313,76,93);
		damageImages[2]=image.getSubimage(246,2535,76,91);
		damageImages[3]=image.getSubimage(331,2533,76,92);
		damageImages[4]=image.getSubimage(115,2327,89,80);
	}
   private void loadPowerEffectImages() {
	   powerEffectImages[0] = image.getSubimage(30,1706,106,86);
	   powerEffectImages[1] = image.getSubimage(152,1700,89,94);
	   powerEffectImages[2] = image.getSubimage(373,1707,91,86);
	   powerEffectImages[3] = image.getSubimage(478,1705,113,87);
	   powerEffectImages[4] = image.getSubimage(12,1812,108,90);
	   powerEffectImages[5] = image.getSubimage(141,1813,101,88);
	   powerEffectImages[6] = image.getSubimage(261,1813,110,91);
	   powerEffectImages[7] = image.getSubimage(387,1817,96,86);
	   powerEffectImages[8] = image.getSubimage(492,1818,141,85);
   }
   private BufferedImage powerEffectImage() {
	   if(imageIndex>8) {
		   imageIndex = 0;
		   currentMove = STANDING;
	   }
	   BufferedImage img = powerEffectImages[imageIndex];
	   imageIndex++;
	   return img;
   }

   private BufferedImage walkImage() {
//	   BufferedImage img = image.getSubimage(188,122,74,99);
	   if(imageIndex>5) {
		   imageIndex = 0;
		   currentMove = STANDING;
	   }
	   BufferedImage img = walkImages[imageIndex];
	   imageIndex++;
	   return img;
   }
   
   private BufferedImage standingImage() {
	   if(imageIndex>7)imageIndex = 0;
	   BufferedImage img = standingImages[imageIndex];
	   imageIndex++;
	   return img;
   }
   
   private BufferedImage punchImage() {
	   if(imageIndex>7) {
		   imageIndex = 0;
		   currentMove = STANDING;
		   isAttacking = false;
	   }
	   BufferedImage img = punchImages[imageIndex];
	   imageIndex++;
	   return img;
   }
   
   private BufferedImage kickImage() {
	   if(imageIndex>10){
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
   
   private ArrayList<PowerEffect> powers = new ArrayList<>();
   
   public ArrayList<PowerEffect> getPowers(){
	   return powers;
   }
   public void addPower() {
	   powers.add(new PowerEffect(x+w, y+h/2-40, image));
   }
   
   @Override
   public BufferedImage defaultImage() {
	   if(currentMove == WALK) {
		   return walkImage();
	   }
	   else if(currentMove == PUNCH) {
		   return punchImage();
	   }
	   else if(currentMove == KICK) {
		   return kickImage();
	   }
	   else if(currentMove == DAMAGE) {
		   return damageImage();
	   }
	   else if(currentMove == POWER_EFFECT) {
		   return powerEffectImage();
	   }
	   return standingImage();
   }

}
