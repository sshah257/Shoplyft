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
public class PullData {

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
        Item tempItem = new Item();

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeQuery("USE WorkingDatabase");
            
            checkoutId = st.executeQuery("SELECT ItemNumber FROM Terminal1"); // get id number from checkout
            
            while (checkoutId.next())
            {
            	idNum = checkoutId.getString(1);
            	rs = st.executeQuery("SELECT * FROM stock where id =" + idNum); //pull data based on num          
            	//tempItem.name = rs.getString(1);
            	//tempItem.price = rs.getString(1);    
            	items.add(tempItem);
            	tempItem = new Item();
            }
            
            for(int i = 0 ; i < items.size();i++) {
                System.out.println(items.get(i));
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