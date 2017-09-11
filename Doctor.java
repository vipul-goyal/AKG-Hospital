import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class Doctor implements ActionListener
{
	JPanel p,p1;
	JLabel l1;
	JButton b,b1,b2,b3,b4,b5,b6,b7;
	CardLayout cd=new CardLayout();
	String name=null;
	long idd;
	Doctor()
	{
	}
	Doctor(long id)
	{
		long uid;
		idd=id;
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
		 b1=new JButton("View Appointments");
		 b2=new JButton("Advice Medicines to Pharmaceuticals");
		 b3=new JButton("Request for new Equipments");
		 b4=new JButton("Complaints and Suggestions");
		 b5=new JButton("Back to Login Page");
		 b6=new JButton("Back to Home Page");
		 b7=new JButton("Prescribe Medicines To Patient");
		 p1.add(l1);
		 p1.add(b);
		 p1.add(b1);
		 p1.add(b2);
		 p1.add(b3);
		 p1.add(b4);
		 p1.add(b5);
		 p1.add(b6);
		 p1.add(b7);
		 l1.setBounds(760,10,100,20);
		 b.setBounds(830,10,100,20);
		 b7.setBounds(400,150,250,20);
		 b1.setBounds(400,200,250,20);
		 b2.setBounds(400,250,250,20);
		 b3.setBounds(400,300,250,20);
		 b4.setBounds(400,350,250,20);
		 b5.setBounds(50,450,200,20);
		 b6.setBounds(50,490,200,20);
		 b1.addActionListener(this);
		 b2.addActionListener(this);
		 b3.addActionListener(this);
		 b5.addActionListener(this);
		 b6.addActionListener(this);
		 b7.addActionListener(this);
		 p.add(p1,"first");
		 b4.setEnabled(false);
		 p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			p.add(new DoctorViewAppointment(name,idd).p,"third");
			cd.show(p,"third");
		}
		if(e.getSource()==b2)
		{
		  p.add(new AdviceMedicine(idd).p,"fifth");
          cd.show(p,"fifth");		  
		}
		if(e.getSource()==b3)
		{
		  p.add(new AdviceEquipment(idd).p,"fifth");
          cd.show(p,"fifth");		  
		}
		if(e.getSource()==b5)
		{
			p.add(new User("doctor",0).pl,"first");
			cd.show(p,"first");
		}
		if(e.getSource()==b6)
		{
			p.add(new Login(1).p,"second");
			cd.show(p,"second");
		}
		if(e.getSource()==b7)
		{
			p.add(new PrescribeMedicine(idd).p,"fourth");
			cd.show(p,"fourth");
		}
	}
}	