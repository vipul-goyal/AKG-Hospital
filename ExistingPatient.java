import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class ExistingPatient implements ActionListener
{
	String chk;
	JPanel pl,pl1;
	JLabel l1,l2,l3;
	JTextField t1;
	JPasswordField t2;
	JButton b;
	CardLayout c=new CardLayout();
	long userid;
	ExistingPatient(long id,int a)
	{
		userid=id;
		pl=new JPanel();
		pl.setLayout(c);
		pl1=new JPanel();
		pl1.setLayout(null);
		l1=new JLabel("UserID");
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
		pl.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{	
	   long id=0,mno=0,x,user,pass;
	   String user1,pass1,result;
	   result="false";
	   if(e.getSource()==b)
	   {
		try
		{
	    Connection conn=Jdbc.jconn();
		Statement stmt=conn.createStatement();
		ResultSet rs1=stmt.executeQuery("SELECT * FROM patient_record");
			while(rs1.next())
			{
				id=rs1.getLong("patient_id");
	            mno=rs1.getLong("mobile_no");
				user1=t1.getText();
				pass1=t2.getText();
				user=Long.parseLong(user1);
				pass=Long.parseLong(pass1);
				 if((user==id) && (pass==mno))
				 {
					 result="success";
					 break;
				 }
			}
        if(result.equals("success"))	
		{
			pl.add(new BookAppointment(id,userid).p,"third");
			c.show(pl,"third");
		}
		else
		{
			pl.add(new ExistingPatient(userid,1).pl,"second");
			c.show(pl,"second");
		}			
		}
		catch (Exception e1) {
         e1.printStackTrace();
        }
	   }
	}
}