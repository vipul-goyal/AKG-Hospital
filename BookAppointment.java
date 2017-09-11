import javax.swing.*;
import java.lang.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
class BookAppointment implements ActionListener
{
	JPanel p,p1,p2;
	JButton b,b1,b2,b3;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t1;
    JComboBox cb1,cb2,cb3,cb4,cb5,cb6;
	CardLayout cd=new CardLayout();
    DefaultComboBoxModel m1,m2,m3,m4,m5;
	long doctorid,dmappingid,pid,ailmentid,timeid,userid,pvisitid;
	int flag;
	Calendar cal;
	BookAppointment()
	{
	}
	BookAppointment(long id,long usid)
	{
		 userid=usid;
		 long uid;
		 pid=id;
		 String name=null;
		 cal = Calendar.getInstance();
		 int year= cal.get(Calendar.YEAR);
         int month= cal.get(Calendar.MONTH);
         int day = cal.get(Calendar.DAY_OF_MONTH);
		 p=new JPanel();
		 p.setLayout(cd);
		 p1=new JPanel(null);
		 p2=new JPanel(null);
		 b=new JButton("Book Appointment");
		 b1=new JButton("Click here to continue");
		 b2=new JButton("Go Back to Registration Forum");
		 b3=new JButton("Go Back to Home Page");
		 l1=new JLabel("Current Ailment");
		 l2=new JLabel("Doctor Name");
		 l3=new JLabel("Appointment Date");
		 l4=new JLabel("Appiontment Time");
		 t1=new JTextField();
	     l5=new JLabel("Best Fit");
	     l6=new JLabel("Sorry the time slot has already been booked");
	     l7=new JLabel("Sorry no appointments are available for the selected date");
	     l8=new JLabel("Appointment Booked");
         cb1=new JComboBox();
         cb2=new JComboBox();
         cb3=new JComboBox();
         cb4=new JComboBox();
         cb5=new JComboBox();
         cb6=new JComboBox();
         m1=new DefaultComboBoxModel();
         m2=new DefaultComboBoxModel();
         m3=new DefaultComboBoxModel();
         m4=new DefaultComboBoxModel();
         m5=new DefaultComboBoxModel();
         try
         {
          Connection conn=Jdbc.jconn();
          Statement stmt=conn.createStatement();
          ResultSet rs=stmt.executeQuery("select * from ailment");
          while(rs.next())
          {
            String s;
            s=rs.getString("ailment");
            m1.addElement(s); 
          }
          cb1.setModel(m1);
          m2.addElement("Best Fit");
          ResultSet rs1=stmt.executeQuery("select * from doctor_information");
          while(rs1.next())
          {
            String s;
            s=rs1.getString("doctor_name");
            m2.addElement(s); 
          }
          cb2.setModel(m2);
          ResultSet rs2=stmt.executeQuery("select * from date");
	      while(rs2.next())
	      {
		   m3.addElement(rs2.getInt("date"));
		   if(rs2.getInt("date")==day)
		      day=rs2.getInt("date_id");
	      }
	      ResultSet rs3=stmt.executeQuery("select * from month");
	      while(rs3.next())
	      {
		   m4.addElement(rs3.getString("month"));
	      }
	      ResultSet rs4=stmt.executeQuery("select * from year");
	      while(rs4.next())
	     {
	      m5.addElement(rs4.getInt("year"));
		  if(rs4.getInt("year")==year)
		      year=rs4.getInt("year_id");
	     }
	     cb3.setModel(m3);
	     cb4.setModel(m4);
	     cb5.setModel(m5);
         }
         catch(Exception e)
         {
          e.printStackTrace();
         }
		 cb3.setSelectedIndex(day-1);
		 cb4.setSelectedIndex(month);
		 cb5.setSelectedIndex(year-1);
         l1.setBounds(350,100,200,20);
         l2.setBounds(350,140,200,20);
         cb1.setBounds(500,100,100,20);
         cb2.setBounds(500,140,100,20);
		 l3.setBounds(350,180,200,20);
	     cb3.setBounds(500,180,40,20);
	     cb4.setBounds(540,180,100,20);
	     cb5.setBounds(640,180,80,20);
	     l4.setBounds(350,220,200,20);
	     cb6.setBounds(500,220,100,20);
	     b.setBounds(465,350,200,20);
	     b2.setBounds(300,390,250,20);
	     b3.setBounds(580,390,200,20);
		 b.setEnabled(false);
		 l3.setEnabled(false);
		 cb3.setEnabled(false);
		 cb4.setEnabled(false);
		 cb5.setEnabled(false);
		 l4.setEnabled(false);
		 cb6.setEnabled(false);
         p1.setPreferredSize(new Dimension(1000,700));
         p1.add(l1);
         p1.add(l2);
         p1.add(l3);
         p1.add(l4);
         p1.add(cb1);
         p1.add(cb2);
         p1.add(cb3);
         p1.add(cb4);
         p1.add(cb5);
         p1.add(cb6);
         p1.add(b);
         p1.add(b2);
         p1.add(b3);
         cb1.addActionListener(this);
         cb2.addActionListener(this);
         cb3.addActionListener(this);
         cb4.addActionListener(this);
         cb5.addActionListener(this);
         cb6.addActionListener(this);
         b.addActionListener(this);
         b1.addActionListener(this);
         b2.addActionListener(this);
         b3.addActionListener(this);
		 p.add(p1,"first");
		 p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
	 if(e.getSource()==cb1)
     {
	  long aid=0;
	  String s,c,dn=null;
      s=(String)cb2.getSelectedItem();
      c=(String)cb1.getSelectedItem();
      if(s=="Best Fit")
      {
       try
       {    
       Connection conn=Jdbc.jconn();
       Statement stmt=conn.createStatement();
	   ResultSet r=stmt.executeQuery("select * from ailment");
       while(r.next())
       {
        if(c.equals(r.getString("ailment")))
		{
		 ailmentid=r.getLong("ailment_id");
		 break;
		}
       }
	   ResultSet rs=stmt.executeQuery("select * from specialization");
       while(rs.next())
       {
        if(c.equals(rs.getString("specialization")))
		{
		 aid=rs.getLong("specialization_id");
		 break;
		}
       }
       ResultSet rs1=stmt.executeQuery("select * from specialization_mapping");
       while(rs1.next())
       {        
         if(aid==rs1.getLong("specialization_id"))
		 {
            aid=rs1.getLong("doctor_id");
             break;			
		 }
       }
	   ResultSet rs2=stmt.executeQuery("select * from doctor_information");
       while(rs2.next())
       {        
         if(aid==rs2.getLong("doctor_id"))
		 {
            dn=rs2.getString("doctor_name");
             break;			
		 }
       }
	   doctorid=aid;
	   t1.setVisible(true);
	   l5.setVisible(true);
	   t1.setText(dn);
	   t1.setEditable(false);
       t1.setBounds(500,180,100,20);
       l5.setBounds(350,180,200,20);
	   l3.setBounds(350,220,200,20);
	   cb3.setBounds(500,220,40,20);
	   cb4.setBounds(540,220,100,20);
	   cb5.setBounds(640,220,80,20);
	   l4.setBounds(350,260,200,20);
	   cb6.setBounds(500,260,100,20);
       p1.add(t1);
       p1.add(l3);
       p1.add(l4);
       p1.add(l5);
       p.add(p1,"second");
       cd.show(p,"second"); 	   
       }
       catch(Exception e1)
       {
         e1.printStackTrace();
       }
      }
	  else
	  {
		try
       {    
       Connection conn=Jdbc.jconn();
       Statement stmt=conn.createStatement();
	   ResultSet r=stmt.executeQuery("select * from ailment");
       while(r.next())
       {
        if(c.equals(r.getString("ailment")))
		{
		 ailmentid=r.getLong("ailment_id");
		 break;
		}
       }
	   ResultSet rs=stmt.executeQuery("select * from doctor_information");
       while(rs.next())
       {
        if(s.equals(rs.getString("doctor_name")))
		{
		 doctorid=rs.getLong("doctor_id");
		 break;
		}
       }
	   }
       catch(Exception e1)
       {
		e1.printStackTrace();   
	   }	   
		t1.setVisible(false);
		l5.setVisible(false);
		l3.setBounds(350,180,200,20);
		cb3.setBounds(500,180,40,20);
	    cb4.setBounds(540,180,100,20);
	    cb5.setBounds(640,180,80,20);
	    l4.setBounds(350,220,200,20);
	    cb6.setBounds(500,220,100,20);
		p1.add(l3);
        p1.add(l4);
        p.add(p1,"third");
        cd.show(p,"third");
	  }
	  l3.setEnabled(true);
	  cb3.setEnabled(true);
	  cb4.setEnabled(true);
	  cb5.setEnabled(true);
	  l4.setEnabled(true);
	  cb6.setEnabled(true);
     } 	 
     if(e.getSource()==cb2)
     {
      long aid=0;
	  String s,c,dn=null;
      s=(String)cb2.getSelectedItem();
      c=(String)cb1.getSelectedItem();
      if(s=="Best Fit")
      {
       try
       {    
       Connection conn=Jdbc.jconn();
       Statement stmt=conn.createStatement();
	   ResultSet r=stmt.executeQuery("select * from ailment");
       while(r.next())
       {
        if(c.equals(r.getString("ailment")))
		{
		 ailmentid=r.getLong("ailment_id");
		 break;
		}
       }
	   ResultSet rs=stmt.executeQuery("select * from specialization");
       while(rs.next())
       {
        if(c.equals(rs.getString("specialization")))
		{
		 aid=rs.getLong("specialization_id");
		 break;
		}
       }
       ResultSet rs1=stmt.executeQuery("select * from specialization_mapping");
       while(rs1.next())
       {        
         if(aid==rs1.getLong("specialization_id"))
		 {
            aid=rs1.getLong("doctor_id");
             break;			
		 }
       }
	   ResultSet rs2=stmt.executeQuery("select * from doctor_information");
       while(rs2.next())
       {        
         if(aid==rs2.getLong("doctor_id"))
		 {
            dn=rs2.getString("doctor_name");
             break;			
		 }
       }
	   doctorid=aid;
	   t1.setVisible(true);
	   l5.setVisible(true);
	   t1.setText(dn);
	   t1.setEditable(false);
       t1.setBounds(500,180,100,20);
       l5.setBounds(350,180,200,20);
	   l3.setBounds(350,220,200,20);
	   cb3.setBounds(500,220,40,20);
	   cb4.setBounds(540,220,100,20);
	   cb5.setBounds(640,220,80,20);
	   l4.setBounds(350,260,200,20);
	   cb6.setBounds(500,260,100,20);
       p1.add(t1);
       p1.add(l3);
       p1.add(l4);
       p1.add(l5);
       p.add(p1,"second");
       cd.show(p,"second"); 	   
       }
       catch(Exception e1)
       {
         e1.printStackTrace();
       }
      }
	  else
	  {
		try
        {    
        Connection conn=Jdbc.jconn();
        Statement stmt=conn.createStatement();
	    ResultSet r=stmt.executeQuery("select * from ailment");
       while(r.next())
       {
        if(c.equals(r.getString("ailment")))
		{
		 ailmentid=r.getLong("ailment_id");
		 break;
		}
       }
		ResultSet rs=stmt.executeQuery("select * from doctor_information");
        while(rs.next())
        {
         if(s.equals(rs.getString("doctor_name")))
		 {
		  doctorid=rs.getLong("doctor_id");
		  break;
		 }
        }
		}
        catch(Exception e1)
        {
		 e1.printStackTrace();   
	    }
		t1.setVisible(false);
		l5.setVisible(false);
		l3.setBounds(350,180,200,20);
		cb3.setBounds(500,180,40,20);
	    cb4.setBounds(540,180,100,20);
	    cb5.setBounds(640,180,80,20);
	    l4.setBounds(350,220,200,20);
	    cb6.setBounds(500,220,100,20);
		p1.add(l3);
        p1.add(l4);
        p.add(p1,"third");
        cd.show(p,"third");
	  }
	  l3.setEnabled(true);
	  cb3.setEnabled(true);
	  cb4.setEnabled(true);
	  cb5.setEnabled(true);
	  l4.setEnabled(true);
	  cb6.setEnabled(true);
     }	 
	 if(e.getSource()!=b)
     {
	  long aid=0,dateid=0,monthid=0,yearid=0,stime=0,etime=0;
	  int date=0,year=0,i=0;
	  DefaultComboBoxModel m6;
	  String s,c,month,time;
	  flag=0;
	  m6=new DefaultComboBoxModel();
      s=(String)cb2.getSelectedItem();
      c=(String)cb1.getSelectedItem();
      date=(Integer)cb3.getSelectedItem();
      month=(String)cb4.getSelectedItem();
      year=(Integer)cb5.getSelectedItem();
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
	   ResultSet rs2=stmt.executeQuery("select * from month");
       while(rs2.next())
       {
        if(month.equals(rs2.getString("month")))
		{
		 monthid=rs2.getLong("month_id");
		 break;
		}
       }
	   ResultSet rs3=stmt.executeQuery("select * from date_mapping");
       while(rs3.next())
       {
        if(dateid==rs3.getLong("date_id") && monthid==rs3.getLong("month_id") && yearid==rs3.getLong("year_id"))
		{
		  dmappingid=rs3.getLong("date_mapping_id");
		}
       }
	   ResultSet rs4=stmt.executeQuery("select * from doctor_time_start_mapping");
	   while(rs4.next())
	   {
		if(rs4.getLong("doctor_id")==doctorid)
		{
			stime=rs4.getLong("time_id");
		}
	   }
	   ResultSet rs5=stmt.executeQuery("select * from doctor_time_end_mapping");
       while(rs5.next())
	   {
		if(rs5.getLong("doctor_id")==doctorid)
		{
			etime=rs5.getLong("time_id");
		}
	   }
	   ResultSet rs6=stmt.executeQuery("select * from time");
	   while(rs6.next())
	   {
		if(rs6.getLong("time_id")>=stime && rs6.getLong("time_id")<etime)
		{
			m6.addElement(rs6.getString("time"));			
		}
	   }
	   if(e.getSource()!=cb6)
	   {   
	   cb6.setModel(m6);
	   }
	   time=(String)cb6.getSelectedItem();
	    ResultSet rs7=stmt.executeQuery("select * from time");
	   while(rs7.next())
	   {
		   if(time.equals(rs7.getString("time")))
		   {
			   timeid=rs7.getLong("time_id");
		   }
	   }
	   int tidcheck=0,n1;
	   long n;
	   n=etime-stime;
	   n1=(int)n;
	   long tcheck[]=new long[n1+1];
	   for(i=0;i<=n;i++)
	   {
		   tcheck[i]=stime+i;
	   }
	   ResultSet rs8=stmt.executeQuery("select * from appointment");
	   while(rs8.next())
	   {
		   if(dmappingid==rs8.getLong("date_mapping_id") && doctorid==rs8.getLong("doctor_id"))
		   {
			  long t=rs8.getLong("time_id");
			  if(timeid==t)
			   {
				   flag=1;
			   }
			   for(i=0;i<=n;i++)
			   {
				   if(tcheck[i]==t)
					   tidcheck++;
			   }
		   }
	   }
	   if(tidcheck==(n+1))
		   flag=2;
	   if(flag==1)
	   {
		l6.setForeground(Color.red);
		l6.setFont(new Font("Old English Text MT", Font.ITALIC,18));
		cb6.setEnabled(true);
		l7.setVisible(false);
		l6.setVisible(true);
		if(s.equals("Best Fit")) 
			l6.setBounds(350,300,500,20);
		else
			l6.setBounds(350,260,500,20);
		p1.add(l6);
		b.setEnabled(false);
		p.add(p1,"fourth");
		cd.show(p,"fourth");
	   }
	   else if(flag==2)
	   {
		l7.setForeground(Color.red);
		l7.setFont(new Font("Old English Text MT", Font.ITALIC,18));
		l6.setVisible(false);
		l7.setVisible(true);
		if(s.equals("Best Fit")) 
		{
			l7.setBounds(350,260,500,20);
			cb6.setBounds(500,300,100,20);
			l4.setBounds(350,300,200,20);
		}	
		else
		{
			l7.setBounds(350,220,500,20);
			cb6.setBounds(500,260,100,20);
			l4.setBounds(350,260,200,20);
		}	
		cb6.setEnabled(false);
		p1.add(l7);
		b.setEnabled(false);
		p.add(p1,"fifth");
		cd.show(p,"fifth");
	   }
	   else
	   {
		   if(s.equals("Best Fit"))
		   {
			   cb6.setBounds(500,260,100,20);
		       l4.setBounds(350,260,200,20);
		   }
	       else
           {			   
		       cb6.setBounds(500,220,100,20);
		       l4.setBounds(350,220,200,20);
		   }
		   b.setEnabled(true);
		   cb6.setEnabled(true);
		   l6.setVisible(false);
		   l7.setVisible(false);
		   p.add(p1,"sixth");
		   cd.show(p,"sixth");
	   }
      }
      catch(Exception e1)
       {
         e1.printStackTrace();
       }
     }	 
	 if(e.getSource()==b)
	 {
		 try
		 {
			Connection conn=Jdbc.jconn();
            Statement stmt=conn.createStatement();
			pvisitid=-1;
			ResultSet rs=stmt.executeQuery("select * from appointment order by date_mapping_id");
			while(rs.next())
			{
				if(rs.getLong("date_mapping_id")<dmappingid)
				{
				  if((rs.getLong("doctor_id")==doctorid) && (rs.getLong("patient_id")==pid))
				  {
					  pvisitid=rs.getLong("appointment_id");
				  }
				}
				else
				    break;
			}
			PreparedStatement ps=conn.prepareStatement("insert into appointment values(?,?,?,?,?,?)");
			ps.setLong(1,pvisitid);
			ps.setLong(2,doctorid);
			ps.setLong(3,timeid);
			ps.setLong(4,ailmentid);
			ps.setLong(5,pid);
			ps.setLong(6,dmappingid);
			ps.execute();
			b1.setBounds(400,220,200,20);
			l8.setBounds(450,180,150,20);
			p2.add(b1);
			p2.add(l8);
			p.add(p2,"seventh");
			cd.show(p,"seventh");
		 }
		 catch(Exception e1)
		 {	
            e1.printStackTrace();		 
		 }
	 }
	 if(e.getSource()==b1)
	 {
		p.add(new Reception(userid).p,"eighth");
		cd.show(p,"eighth");
	 }
	 if(e.getSource()==b2)
	 {
		p.add(new Reception(userid).p,"ninth");
		cd.show(p,"ninth");
     }
	 if(e.getSource()==b3)
     {
		p.add(new Login(1).p,"tenth");
		cd.show(p,"tenth");
	 }
	}
}	