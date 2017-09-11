import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class NewPatient implements ActionListener
{
  	JPanel p,p1,pbar;
	JLabel j,j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,jbar;
    JTextField t1,t2,t3,t5,t6,t7;
	JTextArea t4;
	JButton b1,b2,b3;
	JRadioButton rb1,rb2;
	JComboBox cb1,cb2,cb3;
    CardLayout cd=new CardLayout();
	ButtonGroup bg;
	DefaultComboBoxModel model1;
	String c,s,city;
	int a;
	int bar1,bar2,bar3,bar4,bar5,bar6,bar7,bar8,bar9,bar10,bar11,bar12;
	long cid,sid,pid,userid;
	NewPatient(long id)
	{
		userid=id;
		model1=new DefaultComboBoxModel();
		p=new JPanel();
		p1=new JPanel();
		p.setLayout(cd);
		pbar=new JPanel();
        pbar.setLayout(new BorderLayout());
	    MyDrawPanel drawPanel=new MyDrawPanel();
	    pbar.add(BorderLayout.CENTER,drawPanel);
		p1.setLayout(null);
		j=new JLabel("Patient Details");
		j1=new JLabel("Name");
		j2=new JLabel("Father/Husband Name");
		j3=new JLabel("Age");
		j4=new JLabel("Address");
		j5=new JLabel("Pincode");
		j6=new JLabel("Contact Number");
		j7=new JLabel("Mobile Number");
		j8=new JLabel("Gender");
		j9=new JLabel("Country");
		j10=new JLabel("State");
		j11=new JLabel("City");
		t1=new JTextField();
		t2=new JTextField();
		t3=new JTextField();
		t4=new JTextArea();
		t5=new JTextField();
		t6=new JTextField();
		t7=new JTextField();
		rb1=new JRadioButton("M");
		rb2=new JRadioButton("F");
		bg = new ButtonGroup();
		bg.add(rb1);	
		bg.add(rb2);
		j.setBounds(420,20,200,30);
		j1.setBounds(350,70,100,20);
		j2.setBounds(350,110,150,20);
		j8.setBounds(350,150,100,20);
		rb1.setBounds(500,150,40,20);
		rb2.setBounds(540,150,40,20);
		j3.setBounds(350,190,100,20);
		j4.setBounds(350,230,100,20);
	    j5.setBounds(350,300,100,20);
	    j9.setBounds(350,340,100,20);
	    j10.setBounds(350,380,100,20);
	    j11.setBounds(350,420,100,20);
		j6.setBounds(350,460,100,20);
		j7.setBounds(350,500,100,20);
		t1.setBounds(500,70,150,20);
		t2.setBounds(500,110,150,20);
		t3.setBounds(500,190,30,20);
		t4.setBounds(500,230,200,50);
		t5.setBounds(500,300,80,20);
		t6.setBounds(500,460,100,20);
		t7.setBounds(500,500,100,20);
		pbar.setBounds(350,0,400,30);
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		b1=new JButton("Proceed Ahead");
		b2=new JButton("Back to Main Registration Forum");
		b3=new JButton("Back to Home Page");
		b1.setBounds(680,580,150,20);
		b2.setBounds(400,580,250,20);
		b3.setBounds(230,580,150,20);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		try
		{
		Connection conn=Jdbc.jconn();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM country");
		int i=0;
		while(rs.next())
		{
		   model1.addElement(rs.getString("country_name"));
		}
		}
		catch(Exception e)
		{
          e.printStackTrace();
        }
		cb1=new JComboBox();
		cb2=new JComboBox();
        cb3=new JComboBox();
		cb1.setModel(model1);
		cb1.setBounds(500,340,200,20);
		cb2.setBounds(500,380,200,20);
		cb3.setBounds(500,420,200,20);
		cb1.addActionListener(this);
		cb2.addActionListener(this);
		cb3.addActionListener(this);
		t1.addActionListener(this);
		p1.add(j1);
		p1.add(t1);
		p1.add(j2);
		p1.add(t2);
		p1.add(j8);
		p1.add(rb1);
		p1.add(rb2);
		p1.add(j3);
		p1.add(t3);
		p1.add(j4);
		p1.add(t4);
		p1.add(j5);
		p1.add(t5);
		p1.add(cb1);
		p1.add(cb2);
		p1.add(cb3);
		p1.add(j6);
		p1.add(t6);
		p1.add(j7);
		p1.add(t7);
		p1.add(j9);
		p1.add(j10);
		p1.add(j11);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(j);
		p1.add(pbar);
		p1.setPreferredSize(new Dimension(1000,700));
		p.add(p1,"first");
		p.setVisible(true);
		cd.show(p,"first");
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		Connection conn=Jdbc.jconn();
		Statement stmt=conn.createStatement();
		if(e.getSource()==cb1)
		{
		  if(bar8==0)
	      {		  
		   pbar.repaint();
		   bar8++;
		   bar12+=4;
		   jbar=new JLabel(Long.toString(bar12)+"%");
		   pbar.add(BorderLayout.EAST,jbar);
		  }
		  DefaultComboBoxModel model2;
		  model2=new DefaultComboBoxModel();
          c=(String)cb1.getSelectedItem();
		  ResultSet rs=stmt.executeQuery("SELECT * FROM country");
		  while(rs.next())
		  {
			if(c.equals(rs.getString("country_name")))
			{
				cid=rs.getLong("country_id");
			}
		  }
		  ResultSet rs1=stmt.executeQuery("SELECT * FROM state");
		  while(rs1.next())
		  {
			if(cid==rs1.getLong("country_id"))
			{
				model2.addElement(rs1.getString("state_name"));
			}
		  }
		  cb2.setModel(model2);
		}
        if(e.getSource()==cb2)
		{
          if(bar9==0)
	      {		  
		   pbar.repaint();
		   bar9++;
		  }
		  DefaultComboBoxModel model3;
	      model3=new DefaultComboBoxModel();		  
		  s=(String)cb2.getSelectedItem();
		  ResultSet rs=stmt.executeQuery("SELECT * FROM state");
		  while(rs.next())
		  {
			if(s.equals(rs.getString("state_name")))
			{
				sid=rs.getLong("state_id");
			}
		  }
		  ResultSet rs1=stmt.executeQuery("SELECT * FROM city");
		  while(rs1.next())
		  {
			if(sid==rs1.getLong("state_id"))
			{
				model3.addElement(rs1.getString("city_name"));
			}
		  }
		  cb3.setModel(model3);
		}
         if(e.getSource()==cb3)
		 {
			if(bar10==0)
	         {		  
		       pbar.repaint();
		       bar10++;
		     }
			 city=(String)cb3.getSelectedItem();
		 }			 
		 if(e.getSource()==rb1 || e.getSource()==rb2)
		 {	 
		  if(e.getSource()==rb1)
		  {
			 a=1;
		  }
		  if(e.getSource()==rb2)
		  {
			  a=2;
		  }
		  if(bar11==0)
	      {		
           System.out.println("chala3");	  
		   pbar.repaint();
		   bar11++;
		  }
		 }
		  if(e.getSource()==b1)
		  {
			String s1,s2,s3,s4,s5,s6,s7;
            int i3;
			long i5,i6,i7; 			
			s1=t1.getText();
			s2=t2.getText();
			s3=t3.getText();
			s4=t4.getText();
			s5=t5.getText();
			s6=t6.getText();
			s7=t7.getText();
			i3=Integer.parseInt(s3);
			i6=Long.parseLong(s6);
			i5=Long.parseLong(s5);
			i7=Long.parseLong(s7);
			PreparedStatement ps=conn.prepareStatement("insert into patient_record values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,s1);
			ps.setString(2,s2);
            if(a==1)
             ps.setString(3,"M");
            else
             ps.setString(3,"F");
		    ps.setInt(4,i3);
			ps.setString(5,s4);
			ps.setString(6,s);
			ps.setString(7,city);
			ps.setLong(8,i5);
			ps.setString(9,c);
			ps.setLong(10,sid);
			ps.setLong(11,cid);
			ps.setLong(12,i6);
			ps.setLong(13,i7);
			ps.setLong(14,-1);	
            ps.execute();
			ResultSet rs=stmt.executeQuery("SELECT * FROM patient_record");
			while(rs.next())
			{
				pid=rs.getLong("patient_id");
			}
            p.add(new NewPatient1(pid,userid).p,"second");			
			cd.show(p,"second");
		  }
		  if(e.getSource()==b2)
		  {
			  p.add(new Reception(userid).p,"third");
			  cd.show(p,"third");
		  }
		  if(e.getSource()==b3)
		  {
			  p.add(new Login(1).p,"third");
			  cd.show(p,"third");
		  }
		  if(e.getSource()==t1)
		  {
			  System.out.println("chala");
			  if(bar1==0)
			  {
			   System.out.println("chala1");
			   pbar.repaint();
			   bar1++;
			  }
		  }
		  if(t2.getText()!=null)
		  {
			  if(bar2==0)
			  {
			   pbar.repaint();
			   bar2++;
			  }
		  }
		  if(t3.getText()!=null)
		  {
			  if(bar3==0)
			  {
			   pbar.repaint();
			   bar3++;
			  }
		  }
		  if(t4.getText()!=null)
		  {
			  if(bar4==0)
			  {
			   pbar.repaint();
			   bar4++;
			  }
		  }
		  if(t5.getText()!=null)
		  {
			  if(bar5==0)
			  {
			   pbar.repaint();
			   bar5++;
			  }
		  }
		  if(t6.getText()!=null)
		  {
			  if(bar6==0)
			  {
			   pbar.repaint();
			   bar6++;
			  }
		  }
		  if(t7.getText()!=null)
		  {
			  if(bar7==0)
			  {
			   pbar.repaint();
			   bar7++;
			  }
		  }
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}  
    }
}	