import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class HOD implements ActionListener
{
	JPanel p,p1;
	JLabel l1,l2;
	CardLayout cd=new CardLayout();
	HOD()
	{
	}
	HOD(long id)
	{
		 long uid;
		 String name=null;
		 p=new JPanel();
		 p.setLayout(cd);
		 p1=new JPanel(new FlowLayout());
		 l1=new JLabel("Welcome");
        
		try
		{
      	Connection conn=Jdbc.jconn();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM users");
  		 while(rs.next())
		 {
			 
			 uid=rs.getLong("user_id");
			 if(id==uid)
			 {
				 name=rs.getString("username");
			 }
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 l2=new JLabel(name);
		 p1.add(l1);
		 p1.add(l2);
		 p.add(p1,"first");
		 p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
	}
}	