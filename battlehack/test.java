package battlehack;


import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.*;
public class test {

    public static void main(String [] args)  {
    	System.out.println("start");
    	Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet checkoutId = null;
        String url = "jdbc:mysql://104.131.22.253:3306/WorkingDatabase"; 
        String user = "user1";
        String password = "n1f2c3";
        String idNum = "";
        LinkedList<Item> items = new LinkedList<Item>();
        int newQuant;
       
        int listSize;

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeQuery("USE WorkingDatabase");
            
            checkoutId = st.executeQuery("SELECT * FROM Terminal1"); // get id number from checkout
          
          //SETS ID VALUES TO ALL ITEMS IN CART : DATABASE RETRIEVAL
          while (checkoutId.next())
            	
            {
        	  if (checkoutId.getInt(3) == 1 )
            	{
        		  idNum = checkoutId.getString(2);
        		  items.add(new Item(idNum));
            	}
        	  else
        	  {
        		  for (int i = 0; i < items.size(); i++)
        		  {
        			  if (checkoutId.getString(2).equals(items.get(i).id))
        			  {
        				  items.remove(i);
        				  break;
        			  }
        		  }
        	  }
        	  checkoutId.next();
            }
          	listSize = items.size();
            for (int i = 0; i < listSize; i++) // Sets items on linkedlist name/price
            {
            	rs = st.executeQuery("SELECT * FROM stock where id =" + items.get(i).id); //pull data based on num          
            	rs.next();
            	items.get(i).name = rs.getString(2);
            	items.get(i).price = rs.getString(3);   
            	
            
            } 
            
            
            
            //UPDATE QUANTITY
            for(int i = 0 ; i < items.size();i++) 
            {
            	rs = st.executeQuery("SELECT * FROM stock where id =" + items.get(i).id); //pull data based on num          
            	rs.next();
            	newQuant = rs.getInt(4) - 1 ;
            	st.executeUpdate("UPDATE stock SET  quantity =" + newQuant + " WHERE id =" + items.get(i).id );
            	//UPDATE table_messages SET  numbers_msg = 50 WHERE id_msg = 4 ;
            }
            
            
            
          //PRINTS LINKEDLIST CHECKOUT SCREEN
            for(int i = 0 ; i < items.size();i++) {
                System.out.println(items.get(i).checkoutScreen());
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(PullData.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (ClassNotFoundException e){
            	e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(PullData.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            } 
        }
        System.out.println("end");
    }
}