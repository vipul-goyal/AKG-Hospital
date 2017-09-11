import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import java.sql.*;
class OrderAdvicedMedicines implements ActionListener
{
	JPanel p,p1,p2;
	JButton b,b1,b2,b3,b4,b5;
	CardLayout cd;
	DefaultTableModel model;
	JTable table;
	JLabel l1,l2;
	JTextField t1;
	JScrollPane pane;
	long idd;
	int r;
	OrderAdvicedMedicines(long id)
	{
		idd=id;
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		p2=new JPanel(null);
		model=new DefaultTableModel();
		model.addColumn("Medicine Name");
		model.addColumn("Prime Salt");
		try
		{
			Connection conn=Jdbc.jconn();
			Statement stmt=conn.createStatement();
		    ResultSet rs=stmt.executeQuery("select * from advicemedicine");
			while(rs.next())
			{
				String s[]=new String[2];
				s[0]=rs.getString("medicinename");
				s[1]=rs.getString("main_salt");
				model.addRow(s);
				r=1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 b=new JButton("Back To Previous Page");
		 b1=new JButton("Back To Pharmaceuticals Main Page");
		 b2=new JButton("Back To Login Page");
		 b3=new JButton("Back To Home Page");
		 b.setBounds(50,450,250,20);
		 b1.setBounds(50,490,250,20);
	     b2.setBounds(50,530,250,20);
	     b3.setBounds(50,570,250,20);
		 b.addActionListener(this);
		 b1.addActionListener(this);
		 b2.addActionListener(this);
		 b3.addActionListener(this);
		if(r==0)
		{
		  l2=new JLabel("No Medicines Adviced");
		  l2.setForeground(Color.red);
		  l2.setFont(new Font("Old English Text MT", Font.ITALIC,20));
		  l2.setBounds(460,200,200,100);
		  p2.add(l2);
		  p2.add(b);
		  p2.add(b1);
		  p2.add(b2);
		  p2.add(b3);
		  p.add(p2,"first");
		}
		else
		{	
		 table=new JTable(model);
		 pane=new JScrollPane(table);
		 l1=new JLabel("Quantity :");
		 t1=new JTextField();
		 b4=new JButton("Order All The Above Medicines");
		 pane.setBounds(500,100,250,200);
		 l1.setBounds(550,320,100,20);
		 t1.setBounds(620,320,100,20);
	     b4.setBounds(500,350,250,20);
		 b4.addActionListener(this);
		 p1.add(pane);
		 p1.add(l1);
		 p1.add(t1);
		 p1.add(b);
		 p1.add(b1);
		 p1.add(b2);
		 p1.add(b3);
		 p1.add(b4);
		 p.add(p1,"first");
		}
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
		   String aname=null,primesalt=null;
		   long advicedate=0;
		   int quantity=0,check=1,a=0;
		   try
		   {
			   quantity=Integer.parseInt(t1.getText());
			   Connection conn=Jdbc.jconn();
			   Statement stmt=conn.createStatement();
			   while(check==1)
			   {
                a=0;
                check=0;
			    ResultSet rs=stmt.executeQuery("select * from advicemedicine");				
			    while(rs.next())
			    {
				   aname=rs.getString("medicinename");
				   advicedate=rs.getLong("date_mapping_id");
				   primesalt=rs.getString("main_salt");
				   check=1;
			    }
			    if(check==1)
			    {
			     ResultSet rs1=stmt.executeQuery("select * from medicine");
			     while(rs1.next())
			     {
				   if((rs1.getString("name")).equals(aname))
				   {
					   a=1;
					   break;
				   }
			     }
			     if(a==1)
			     { 
			       PreparedStatement ps=conn.prepareStatement("update medicine set quantity=quantity+? where name=?");
			       ps.setInt(1,quantity);
			       ps.setString(2,aname);
			       ps.execute();
			     }
			     else
			     {
			 	   int price=(int)((Math.random()*5000)+100);
				   PreparedStatement ps=conn.prepareStatement("insert into medicine values (?,?,?,?,?)");
				   ps.setString(1,aname);
				   ps.setLong(2,advicedate+365);
				   ps.setString(3,primesalt);
				   ps.setInt(4,quantity);
				   ps.setInt(5,price);
				   ps.execute();
			     }
			     PreparedStatement ps1=conn.prepareStatement("delete from advicemedicine where medicinename=?");
			     ps1.setString(1,aname);
				 ps1.execute();
			    }
			   }	
		   }
		   catch(Exception e1)
		   {
			   e1.printStackTrace();
		   }
		   t1.setEnabled(false);
		   b4.setVisible(false);
		   b5=new JButton("Medicine Ordered");
		   b5.setEnabled(false);
		   p1.add(b5);
		   b5.setBounds(500,350,250,20);
		   p.add(p1,"sixth");
		   cd.show(p,"sixth");
		}
	}	
}