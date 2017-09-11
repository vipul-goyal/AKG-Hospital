import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class User implements ActionListener
{
	String chk;
	JPanel pl,pl1;
	JLabel l1,l2,l3;
	JTextField t1;
	JPasswordField t2;
	JButton b;
	CardLayout c=new CardLayout();
	User()
	{
	}
	User(String check,int a)
	{
		
		pl=new JPanel();
		pl.setLayout(c);
		pl1=new JPanel();
		pl1.setLayout(null);
		l1=new JLabel("Username");
		l2=new JLabel("Password");
		t1=new JTextField(20);
		t2=new JPasswordField(20);
		b=new JButton("Submit");
		if(a==1)
		{
			l3=new JLabel("Invalid Username/Password");
			l3.setForeground(Color.red);
		    l3.setFont(new Font("Old English Text MT", Font.ITALIC,15));
			l3.setBounds(400,280,200,20);
			pl1.add(l3);
		}
		l1.setBounds(350,200,100,20);
		l2.setBounds(350,240,100,20);
		t1.setBounds(500,200,200,20);
		t2.setBounds(500,240,200,20);
		b.setBounds(600,280,100,20);
		pl1.add(l1);
	    pl1.add(t1);
		pl1.add(l2);
		pl1.add(t2);
		pl1.add(b);
		pl1.setPreferredSize(new Dimension(1000,700));
		pl.add(pl1,"first");
		b.addActionListener(this);
		chk=check;
		pl.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{	
	   long ucid=0,id,uid=0;
	   String x=null,name,pass,name1,pass1,result;
	   result="false";
	   if(e.getSource()==b)
	   {
		try
		{
	    Connection conn=Jdbc.jconn();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM user_category");
			while(rs.next())
			{
				x=rs.getString("user_category");
				if(x.equals(chk))
				{
				   ucid=rs.getInt("user_category_id");
				   break;
				}   
			}
		ResultSet rs1=stmt.executeQuery("SELECT * FROM users");
			while(rs1.next())
			{
				id=rs1.getInt("user_category_id");
				if(ucid==id)
				{
				 uid=rs1.getInt("user_id");
				 name=rs1.getString("username"); 
                 pass=rs1.getString("password");
				 name1=t1.getText();
				 pass1=t2.getText();
				 if(name.equals(name1) && pass.equals(pass1))
				 {
					 result="success";
					 break;
				 }
				}   
			}	
		if(result.equals("success"))	
		{
			if(x.equals("reception"))	
			pl.add(new Reception(uid).p,"second");
		    else if(x.equals("doctor"))	
			pl.add(new Doctor(uid).p,"second");
		    else if(x.equals("pharmaceuticals"))	
			pl.add(new Pharmaceuticals(uid).p,"second");
		    else	
			pl.add(new HOD(uid).p,"second");
			c.show(pl,"second");
		}
		else
		{
			pl.add(new User(chk,1).pl,"second");
			c.show(pl,"second");
		}
		}
		catch (Exception e1) {
         e1.printStackTrace();
        }
	   }
	}
}