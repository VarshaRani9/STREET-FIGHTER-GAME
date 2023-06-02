package com.company.gaming;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.company.gaming.utils.GameConstants;

public class GameFrame extends JFrame implements GameConstants{
	public GameFrame() throws IOException{
		setTitle(TITLE);
		setSize(GWIDTH,GHEIGHT);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		Board board = new Board();
		add(board);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	public static void main(String[] args) {
//		GameFrame ob = null;
//		try {
//			ob = new GameFrame();
//		}
//		catch(IOException ex) {
//			JOptionPane.showMessageDialog(ob, "Something Went Wrong...");
//			ex.printStackTrace();
//		}
//	}

}
