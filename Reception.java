import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class Reception implements ActionListener
{
	JPanel p,p1,p2;
	JLabel l1;
	JButton b,b1,b2,b3,b4,b5,b6,b7;
	CardLayout cd=new CardLayout();
	Long userid;
	Reception()
	{
	}
	Reception(long id)
	{
		 long uid;
		 String name=null;
		 userid=id;
		 p=new JPanel();
		 p.setLayout(cd);
		 p1=new JPanel(null);
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
		 b=new JButton(name);
		 b1=new JButton("Online Registration");
		 b2=new JButton("Offline Registration");
		 b5=new JButton("Back to Home Page");
		 b7=new JButton("Back to Login Page");
		 b1.addActionListener(this);
		 b2.addActionListener(this);
		 b5.addActionListener(this);
		 b7.addActionListener(this);
		 l1.setBounds(760,10,100,20);
		 b.setBounds(830,10,100,20);
		 b1.setBounds(400,200,250,20);
		 b2.setBounds(400,250,250,20);
		 b5.setBounds(50,490,200,20);
		 b7.setBounds(50,450,200,20);
		 p1.add(l1);
		 p1.add(b);
		 p1.add(b1);
		 p1.add(b2);
		 p1.add(b5);
		 p1.add(b7);
		 p1.setPreferredSize(new Dimension(1000,700));
		 b1.setEnabled(false);
		 p.add(p1,"first");
		 p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			new OnlinePatientRegistration();
		}
		if(e.getSource()==b2)
		{
			p2=new JPanel();
			p2.setLayout(null);
			p2.setPreferredSize(new Dimension(1000,700));
			b3=new JButton("New Patient");
			b4=new JButton("Existing Patient");
			b6=new JButton("Back to Previous Page");
			b7.setVisible(false);
			b3.setBounds(400,200,250,20);
		    b4.setBounds(400,250,250,20);
			b6.setBounds(50,450,200,20);
			b3.addActionListener(this);
			b4.addActionListener(this);
			b6.addActionListener(this);
			p2.add(b3);
			p2.add(b4);
			p2.add(b5);
			p2.add(b6);
			p.add(p2,"second");
			cd.show(p,"second");
		}
		if(e.getSource()==b3)
		{
			p.add(new NewPatient(userid).p,"third");
			cd.show(p,"third");
                        
		}
		if(e.getSource()==b4)
		{
			p.add(new ExistingPatient(userid,0).pl,"third");
			cd.show(p,"third");
		}
		if(e.getSource()==b5)
		{
			p.add(new Login(1).p,"fourth");
			cd.show(p,"fourth");
		}
		if(e.getSource()==b6)
		{
			p.add(new Reception(userid).p,"fifth");
			cd.show(p,"fifth");
		}
		if(e.getSource()==b7)
		{
			p.add(new User("reception",0).pl,"sixth");
			cd.show(p,"sixth");
		}
	}
}	