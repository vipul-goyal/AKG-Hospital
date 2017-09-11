import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
class AdviceEquipment implements ActionListener
{
	JPanel p,p1;
	CardLayout cd;
	JButton b,b1,b2,b3;
	JLabel l1;
	JTextField t1;
	long idd;
	AdviceEquipment(long id)
	{
	    idd=id;
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		b=new JButton("Back To Previous Page");
		b1=new JButton("Back To Login Page");
		b2=new JButton("Back To Home Page");
		b3=new JButton("Advice Equipment");
		l1=new JLabel("Equipment :");
		t1=new JTextField();
		b.setBounds(50,450,200,20);
	    b1.setBounds(50,490,200,20);
	    b2.setBounds(50,530,200,20);
	    l1.setBounds(300,200,100,20);
	    t1.setBounds(400,200,200,20);
	    b3.setBounds(620,200,200,20);
		p1.add(b);
		p1.add(b1);
		p1.add(b2);
		p1.add(l1);
		p1.add(t1);
		p1.add(b3);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		p.add(p1,"first");
		p.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b)
		{
			p.add(new Doctor(idd).p,"second");
			cd.show(p,"second");
		}
		if(e.getSource()==b1)
		{
			p.add(new User("doctor",0).pl,"third");
			cd.show(p,"third");
		}
		if(e.getSource()==b2)
		{
			p.add(new Login(1).p,"fourth");
			cd.show(p,"fourth");
		}
		if(e.getSource()==b3)
		{
			try
			{
			 Connection conn=Jdbc.jconn();
             Statement stmt=conn.createStatement();
             			  Calendar cal;
			  cal=Calendar.getInstance();
			  int year= cal.get(Calendar.YEAR);
              int month= cal.get(Calendar.MONTH);
              int date = cal.get(Calendar.DAY_OF_MONTH);
			  long yearid=0,dmappingid=0;
			  month++;
			  ResultSet rs=stmt.executeQuery("select * from year");
			  while(rs.next()) 
			  {
				  if(rs.getInt("year")==year)
				  {
					  yearid=rs.getLong("year_id");
					  break;
				  }
			  }
			  ResultSet rs1=stmt.executeQuery("select * from date_mapping");
			  while(rs1.next())
			  {
				  if((rs1.getInt("year_id")==yearid) && (rs1.getInt("date_id")==date) && (rs1.getInt("month_id")==month))
				  {
					  dmappingid=rs1.getLong("date_mapping_id");
				  }
			  }
              PreparedStatement ps=conn.prepareStatement("insert into adviceequipment values(?,?)");
              ps.setString(1,t1.getText());
              ps.setLong(2,dmappingid);
              ps.execute();
              p.add(new AdviceEquipment(idd).p,"fifth");
              cd.show(p,"fifth");	  
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
}