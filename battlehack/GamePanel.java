import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import java.applet.*;


public class GamePanel extends JPanel implements MouseListener,MouseMotionListener,KeyListener{
	private boolean []keys;
	private int mx,my;
	private double total;
	
	private Image payNowButton = new ImageIcon("PayNowButton.jpg").getImage();
	private Rectangle payNowButtonRect;
	private ArrayList<Item> items = new ArrayList<Item>();
	
    public GamePanel() {
    	super();
    	keys=new boolean[KeyEvent.KEY_LAST+1];
    	addMouseListener(this); 
		addMouseMotionListener(this);
		addKeyListener(this);
		payNowButtonRect = new Rectangle(550,475,207,66);
		items.add(new Item("0001","Item1","15.00","10"));
		items.add(new Item("0002","Item2","25.00","20"));
		items.add(new Item("0003","Item3","35.00","30"));
		
    }
    public void addNotify(){ 
		super.addNotify();
		requestFocus();
	}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode()<keys.length){
			keys[e.getKeyCode()]=true;
		}
		
	}
	public void keyReleased(KeyEvent e){//if the key is released set it to false
		if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE){ 
			System.out.println("BRUH");
		}
	}
	
	
	
	public void HTMLIO(double total) throws IOException{
    
    BufferedReader br = new BufferedReader(new FileReader("webpage.html"));
    ArrayList<String> sb = new ArrayList();
    try {

        String line = br.readLine();

        while (line != null) {
            sb.add(line);
            line = br.readLine();
        }
    } finally {
        br.close();
    }
    
    String l = sb.get(5).substring(0,42)+total+sb.get(5).substring(42,44);
    
    
    
    
    BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("blah.html"));
            for (int i=0;i<15;i++){
            	if (i==5){
            		writer.write(l);
            	}else{
            		writer.write(sb.get(i));
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
	
	
	
	
	
	public void paintComponent(Graphics g){ //where the magic happens...
		g.drawImage(payNowButton,550,475,this);  //draw the first layer	
		System.out.println(""+mx+"-"+my);
		g.setColor(Color.WHITE);
		g.fill3DRect(10,10,500,450,true);
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g.drawString("ID",20,40);
		g.drawString("NAME",120,40);
		g.drawString("PRICE",220,40);
		g.drawLine(15,50,450,50);
		total = 0.;
		for (int i = 0;i<items.size();i++){
			Item obj = items.get(i);
			g.drawString(obj.id,20,90+30*i);
			g.drawString(obj.name,120,90+30*i);
			g.drawString(obj.price,220,90+30*i);
			total = total + Double.parseDouble(obj.price);
		}
		g.drawLine(15,400,450,400);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 35)); 
		g.drawString("Total:  "+String.format("%.2f", total),220,450);
		
		
	}
    public void mouseEntered( MouseEvent e ) {}
  	public void mouseExited( MouseEvent e ) {}
  	public void mouseClicked( MouseEvent e ) {
  		 switch(e.getModifiers()) {
      case InputEvent.BUTTON1_MASK: {  
        break;
        }
      case InputEvent.BUTTON2_MASK: {    
        break;
        }
      case InputEvent.BUTTON3_MASK: {   
      	
        break;
        }
      }
    }
    public void mousePressed( MouseEvent e ) { //if mouse is pressed
      	
    }
    public void mouseReleased( MouseEvent e ){//when the mouse is releaesd. a lot of of our code is based here
      if(payNowButtonRect.contains(mx,my)){
      	 System.out.println(true);
      	 try{
      	 	HTMLIO(total);
      	 }catch(IOException jfk){
      	 }
      	 
      	 String url_open = "blah.html";
      	 try{
      	 	java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
      	 }
		 catch(IOException ioe) {
            System.out.println("The system cannot find the " + url_open + " file specified");
        }
      }
      e.consume();
    }
    public void mouseMoved( MouseEvent e ) {//MOUSE METHOD
      mx = e.getX(); 
      my = e.getY();
      e.consume();
    
    }
    public void mouseDragged( MouseEvent e ) { //MOUSE METHOD - they should probably be grouped together..oh well
      mx = e.getX();
      my = e.getY();
      e.consume();
   }
}