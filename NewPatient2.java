import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class NewPatient2 implements ActionListener
{
	JPanel p,p1;
	JLabel l1,l2,l3,l4;
    JButton b,b1,b2;
	CardLayout cd=new CardLayout();
    long pid,userid;  
	NewPatient2()
	{
	}
	NewPatient2(long id,long usid)
	{
		 long chk,mno=0;
		 String name=null;
         userid=usid;
		 pid=id;
		 p=new JPanel();
		 p.setLayout(cd);
		 p1=new JPanel(null);
		 l1=new JLabel("User ID");
		 l2=new JLabel("Password");
		try
		{
      	Connection conn=Jdbc.jconn();
		Statement stmt=conn.createStatement();
		 ResultSet rs=stmt.executeQuery("SELECT * FROM patient_record");
  		 while(rs.next())
		 {
			 
			 chk=rs.getLong("patient_id");
			 if(id==chk)
			 {
				 mno=rs.getLong("mobile_no");
			 }
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 l3=new JLabel(Long.toString(id));
		 l4=new JLabel(Long.toString(mno));
         b=new JButton("Proceed to Book Appointment");
         b1=new JButton("Go Back To Registration Forum");
         b2=new JButton("Go Back to Home Page");
         l1.setBounds(400,200,100,20);
         l2.setBounds(400,240,100,20);
         l3.setBounds(500,200,100,20);
         l4.setBounds(500,240,100,20);
         b.setBounds(400,290,250,20);
         b1.setBounds(250,330,250,20);
         b2.setBounds(550,330,200,20);
		 p1.add(l1);
		 p1.add(l2);
		 p1.add(l3);
		 p1.add(l4);
		 p1.add(b);
		 p1.add(b1);
		 p1.add(b2);
         p1.setPreferredSize(new Dimension(1000,700));
         b.addActionListener(this);
         b1.addActionListener(this);
         b2.addActionListener(this);
		 p.add(p1,"first");
		 p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
     if(e.getSource()==b)
     {
      p.add(new BookAppointment(pid,userid).p,"second");
      cd.show(p,"second");
     }
	 if(e.getSource()==b1)
     {
	  p.add(new Reception(userid).p,"third");
	  cd.show(p,"third");
	 }
    if(e.getSource()==b2)
	{			  
     p.add(new Login(1).p,"third");
	 cd.show(p,"third");
    }
	}
}	