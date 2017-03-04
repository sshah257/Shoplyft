import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{
	static GamePanel game;
	javax.swing.Timer myTimer;// timer
    public GUI() {
    	game = new GamePanel();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	setSize(800,600);
    	setLayout(new BorderLayout());
    	setResizable(false);
    	myTimer = new javax.swing.Timer(1000/70,this);
    	myTimer.start();
    	add(game);
    	setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent e){
    	Object source=e.getSource(); 
    	if(source==myTimer){
    		game.repaint();
    		game.setVisible(true);
    	}
    }
    
    public static void main (String [] args)throws IOException {
    	GUI a = new GUI();
    	
    }
    
}