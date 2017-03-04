public class Item {
	
	public String id = "";
	public String name = "";
	public String price = "";
	public String quantity = "";
	
	public Item(){
		
		 id = "0";
		 name = "0";
		 price = "0";
		 quantity = "0";
	}
	
	public Item(String a,String b,String c,String d) {
		 id = a;
		 name = b;
		 price = c;
		 quantity = d;
	}
	public String toString(){
		
		String s = "";
		
		s+="id:" + id + " ,"; 
		s+="name:" + name + " ,"; 
		s+="price:" + price + " ,"; 
		
		
		return s;
	}
	public String checkoutScreen(){
		String s = "";
		
		s+= name + ":\t" + price;
		
		return s;
	}

}
