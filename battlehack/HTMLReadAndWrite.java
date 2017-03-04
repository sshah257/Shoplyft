import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import java.applet.*;

public class HTMLReadAndWrite {

    public static void main(String [] args)throws IOException {
    double total = 300.00;
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
    
    
}