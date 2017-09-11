import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
class OrderOtherMedicines implements ActionListener
{
	JPanel p,p1;
	JButton b,b1,b2,b3,b4;
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	CardLayout cd;
	long idd;
	OrderOtherMedicines(long id)
	{
		idd=id;
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		l1=new JLabel("Medicine Name :");
		l2=new JLabel("Prime Salt :");
		l3=new JLabel("Quantity :");
		t1=new JTextField();
		t2=new JTextField();
		t3=new JTextField();
		b=new JButton("Back To Previous Page");
		b1=new JButton("Back To Pharmaceuticals Main Page");
		b2=new JButton("Back To Login Page");
		b3=new JButton("Back To Home Page");
		b4=new JButton("Order");
		l1.setBounds(350,150,150,20);
		t1.setBounds(500,150,150,20);
		l2.setBounds(350,190,150,20);
		t2.setBounds(500,190,150,20);
		l3.setBounds(350,230,150,20);
		t3.setBounds(500,230,150,20);
		b.setBounds(50,450,250,20);
		b1.setBounds(50,490,250,20);
	    b2.setBounds(50,530,250,20);
	    b3.setBounds(50,570,250,20);
	    b4.setBounds(550,270,100,20);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(t1);
		p1.add(t2);
		p1.add(t3);
		p1.add(b);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p.add(p1,"first");
		p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b)
		{
			p.add(new BuyMedicines(idd).p,"fifth");
			cd.show(p,"fifth");
		}
		if(e.getSource()==b1)
		{
			p.add(new Pharmaceuticals(idd).p,"second");
			cd.show(p,"second");
		}
		if(e.getSource()==b2)
		{
			p.add(new User("pharmaceuticals",0).pl,"third");
			cd.show(p,"third");
		}
		if(e.getSource()==b3)
		{
			p.add(new Login(1).p,"fourth");
			cd.show(p,"fourth");
		}
		if(e.getSource()==b4)
		{
		 long yearid=0,monthid=0,dateid=0,dmappingid=0,a=0;
		 String name=t1.getText();
		 String primesalt=t2.getText();
		 int quantity=Integer.parseInt(t3.getText());
		 try
         {		
			 Calendar cal;
			 cal = Calendar.getInstance();
		     int year= cal.get(Calendar.YEAR);
             int month= cal.get(Calendar.MONTH);
             int date = cal.get(Calendar.DAY_OF_MONTH);
			Connection conn=Jdbc.jconn();
            Statement stmt=conn.createStatement();
	        ResultSet rs=stmt.executeQuery("select * from date");
            while(rs.next())
            {
             if(date==rs.getInt("date"))
		     {
		      dateid=rs.getLong("date_id");
		      break;
		     }
            }
            ResultSet rs1=stmt.executeQuery("select * from year");
            while(rs1.next())
            {
             if(year==rs1.getInt("year"))
	         {
		      yearid=rs1.getLong("year_id");
		      break;
		     }
            }	  
	        monthid=month+1;
	        ResultSet rs2=stmt.executeQuery("select * from date_mapping");
            while(rs2.next())
            {
             if(dateid==rs2.getLong("date_id") && monthid==rs2.getLong("month_id") && yearid==rs2.getLong("year_id"))
		     {
		      dmappingid=rs2.getLong("date_mapping_id");
		     }
            }
     	    ResultSet rs3=stmt.executeQuery("select * from medicine");
			while(rs3.next())
			{
		     if((rs3.getString("name")).equals(name))
		     {
			  a=1;
			  break;
		     }
	        }
			if(a==1)
		    { 
			 PreparedStatement ps=conn.prepareStatement("update medicine set quantity=quantity+? where name=?");
			 ps.setInt(1,quantity);
			 ps.setString(2,name);
			 ps.execute();
		    }
			else
	        {
			 int price=(int)((Math.random()*5000)+100);
		     PreparedStatement ps=conn.prepareStatement("insert into medicine values (?,?,?,?,?)");
		     ps.setString(1,name);
		     ps.setLong(2,dmappingid+365);
		     ps.setString(3,primesalt);
	         ps.setInt(4,quantity);
	         ps.setInt(5,price);
		     ps.execute();
    	    }
		 }
		 catch(Exception e1)
		 {
			 e1.printStackTrace();
		 }
		 p.add(new OrderOtherMedicines(idd).p,"sixth");
		 cd.show(p,"sixth");
		}
	}	
}