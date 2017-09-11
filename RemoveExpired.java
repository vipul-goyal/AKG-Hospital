import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
class RemoveExpired implements ActionListener
{
	JPanel p,p1;
	JScrollPane pane;
	JTable table;
	DefaultTableModel model;
	JLabel l1;
	CardLayout cd;
	JButton b1,b2,b3,b4;
	long idd,check,dmappingid,a;
	RemoveExpired(long id)
	{
		idd=id;
		long dateid=0,yearid=0,monthid=0;
		Calendar cal;
	    cal = Calendar.getInstance();
	    int year= cal.get(Calendar.YEAR);
        int month= cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DAY_OF_MONTH);
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		model=new DefaultTableModel();
		model.addColumn("Medicine Name");
		model.addColumn("Quantity");
		try
		{
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
			if(rs3.getLong("expiry_date")<dmappingid) 
			{
				String s[]=new String[2];
				s[0]=rs3.getString("name");
				s[1]=Integer.toString(rs3.getInt("quantity"));
				model.addRow(s);
				a=1;
			}				
		  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(a==1)
		{
			table=new JTable(model);
			pane=new JScrollPane(table);
			b4=new JButton("Remove Medicines");
			pane.setBounds(400,100,300,200);
			b4.setBounds(450,350,200,20);
			p1.add(pane);
			p1.add(b4);
		}
		else
		{	
		    l1=new JLabel("No Medicines To Display");
		    l1.setForeground(Color.red);
		    l1.setFont(new Font("Old English Text MT", Font.ITALIC,20));
			l1.setBounds(400,250,250,100);
			p1.add(l1);
		}	
		b1=new JButton("Back To Previous Page");
		b2=new JButton("Back To Login Page");
		b3=new JButton("Back To Home Page");
		b1.setBounds(50,490,250,20);
	    b2.setBounds(50,530,250,20);
	    b3.setBounds(50,570,250,20);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		if(a==1)	
	 	  b4.addActionListener(this);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p.add(p1,"first");
		p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
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
			try
			{
				Connection conn=Jdbc.jconn();
		        PreparedStatement ps=conn.prepareStatement("delete from medicine where expiry_date<?");
				ps.setLong(1,dmappingid);
				ps.execute();
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			p.add(new Pharmaceuticals(idd).p,"fifth");
			cd.show(p,"fifth");
		}
	}
}