import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
class DoctorViewAppointment implements ActionListener
{
	JPanel p,p1,p2;
	CardLayout cd;
    JComboBox cb1,cb2,cb3;
	DefaultComboBoxModel m1,m2,m3;
	Calendar cal;
	JLabel l1,l2;
	JButton b1,b2,b3,b4,b5;
	DefaultTableModel model;
	JTable table;
	JScrollPane pane;
	Long doctorid,ailmentid,patientid,timeid,dmappingid,pvisit,idd;
	String patientname,ailment,time,name;
	DoctorViewAppointment(String sname,long id)
	{
		idd=id;
		name=sname;
		cd=new CardLayout();
		p=new JPanel();
        p.setLayout(cd);
        p1=new JPanel(null);
		l1=new JLabel("Date");
		l2=new JLabel("No Appointments For The Day");
		l2.setForeground(Color.red);
		l2.setFont(new Font("Old English Text MT", Font.ITALIC,23));
		b1=new JButton("View");
		b2=new JButton("Back to Previous Page");
		b3=new JButton("Back to Login Page");
		b4=new JButton("Back to Home Page");
		b5=new JButton("View Appointment For Any Other Day");
		cal = Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
        int month= cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
		cb1=new JComboBox();
		cb2=new JComboBox();
		cb3=new JComboBox();
		m1=new DefaultComboBoxModel();
		m2=new DefaultComboBoxModel();
		m3=new DefaultComboBoxModel();
        try
        {
          Connection conn=Jdbc.jconn();
          Statement stmt=conn.createStatement();
          ResultSet rs=stmt.executeQuery("select * from date");
	      while(rs.next())
	      {
		   m1.addElement(rs.getInt("date"));
		   if(rs.getInt("date")==day)
		      day=rs.getInt("date_id");
	      }
	      ResultSet rs1=stmt.executeQuery("select * from month");
	      while(rs1.next())
	      {
		   m2.addElement(rs1.getString("month"));
	      }
	      ResultSet rs2=stmt.executeQuery("select * from year");
	      while(rs2.next())
	     {
	      m3.addElement(rs2.getInt("year"));
		  if(rs2.getInt("year")==year)
		      year=rs2.getInt("year_id");
	     }
	     cb1.setModel(m1);
	     cb2.setModel(m2);
	     cb3.setModel(m3);
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
		cb1.setSelectedIndex(day-1);
		cb2.setSelectedIndex(month);
		cb3.setSelectedIndex(year-1);
		l1.setBounds(350,50,50,20);
		b1.setBounds(640,50,80,20);
		b2.setBounds(50,410,200,20);
		b3.setBounds(50,450,200,20);
		b4.setBounds(50,490,200,20); 
		b5.setBounds(640,50,250,20); 
		cb1.setBounds(400,50,40,20);
	    cb2.setBounds(440,50,100,20);
	    cb3.setBounds(540,50,80,20);
        p1.add(l1); 		 
        p1.add(b1); 		 
        p1.add(b2); 		 
        p1.add(b3); 		 
        p1.add(b4); 		 
        p1.add(cb1); 		 
        p1.add(cb2); 		 
        p1.add(cb3);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
        p.add(p1,"first");
        p.setVisible(true);
        cd.show(p,"first");		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
		 int date=0,year=0,check=0,i=0,j=-1,a=0;
		 long dateid=0,monthid=0,yearid=0;
		 String month=null;
		 p2=new JPanel(null);
		 p2.setBounds(300,100,500,300);
		 date=(Integer)cb1.getSelectedItem();
         month=(String)cb2.getSelectedItem();
         year=(Integer)cb3.getSelectedItem();
		 model=new DefaultTableModel();
	     model.addColumn("Patient Id");
	     model.addColumn("Patient Name");
	     model.addColumn("Ailment");
		 model.addColumn("Previous Visit");	
		 model.addColumn("Time");
		 table=new JTable(model);
		 pane=new JScrollPane(table);
		 String s[]=new String[5];
		 try
		 {
			Connection conn=Jdbc.jconn();
            Statement stmt=conn.createStatement();
			ResultSet r=stmt.executeQuery("select * from date");
            while(r.next())
            {
            if(date==r.getInt("date"))
		    {
		      dateid=r.getLong("date_id");
		      break;
		    }
            }
            ResultSet r1=stmt.executeQuery("select * from year");
            while(r1.next())
            {
            if(year==r1.getInt("year"))
		    {
		     yearid=r1.getLong("year_id");
		     break;
		    }
            }	  
	        ResultSet r2=stmt.executeQuery("select * from month");
            while(r2.next())
            {
            if(month.equals(r2.getString("month")))
		    {
		     monthid=r2.getLong("month_id");
		     break;
		    }
            }
	        ResultSet r3=stmt.executeQuery("select * from date_mapping");
            while(r3.next())
            {
            if(dateid==r3.getLong("date_id") && monthid==r3.getLong("month_id") && yearid==r3.getLong("year_id"))
		    {
		      dmappingid=r3.getLong("date_mapping_id");
		    }
            }
			ResultSet rs=stmt.executeQuery("select * from doctor_information");
			while(rs.next())
			{
				if((rs.getString("doctor_name")).equals(name))
				{
					doctorid=rs.getLong("doctor_id");
					break;
				}
					
			}
			while(check==0)
			{	
			ResultSet rs1=stmt.executeQuery("select * from appointment");
			while(rs1.next())
			{
				if(i<=j)
					i++;
				else
				{
				i++;
				j++;
				if(((rs1.getLong("doctor_id"))==doctorid) && ((rs1.getLong("date_mapping_id"))==dmappingid))
				{
					ailmentid=rs1.getLong("ailment_id");
					patientid=rs1.getLong("patient_id");
					timeid=rs1.getLong("time_id");
					pvisit=rs1.getLong("previous_visit_id");
					check=0;
					a++;
					break;
				}
				else
					check=-1;	
				}
			}
			if(check==0)
			{
			ResultSet rs2=stmt.executeQuery("select * from patient_record");
			while(rs2.next())
			{
				if(rs2.getLong("patient_id")==patientid)
				{
					patientname=rs2.getString("name");
				}
			}
			ResultSet rs3=stmt.executeQuery("select * from ailment");
			while(rs3.next())
			{
				if(rs3.getLong("ailment_id")==ailmentid)
				{
					ailment=rs3.getString("ailment");
				}
			}
			ResultSet rs4=stmt.executeQuery("select * from time");
			while(rs4.next())
			{
				if(rs4.getLong("time_id")==timeid)
				{
					time=rs4.getString("time");
				}
			}
			s[0]=patientid.toString();
		    s[1]=patientname;
		    s[2]=ailment;
		    if(pvisit!=-1)
     		   s[3]="yes";
		    else 
			   s[3]="no";
		    s[4]=time;
		    model.addRow(s);
			}
			i=0;
			}
		 }
		 catch(Exception e1)
		 {
			 e1.printStackTrace();
		 }
		 if(a!=0)
		 { 
		 p2.setVisible(true);
		 l2.setVisible(false);
		 cb1.setEnabled(false);
		 cb2.setEnabled(false);
		 cb3.setEnabled(false);
		 b1.setVisible(false);
		 p2.add(pane);
		 p1.add(p2);
		 if(a<5)
		    pane.setBounds(0,0,500,100);
	     else if(a<10)
 		    pane.setBounds(0,0,500,200);
	     else if(a<15)
 		    pane.setBounds(0,0,500,(20*a));
	     else
			pane.setBounds(0,0,500,300);
 
		 p1.add(b5);
		 p.add(p1,"second");
		 cd.show(p,"second");
		 }
		 else
		 {
			 p2.setVisible(false);
			 l2.setVisible(true);
			 l2.setBounds(380,100,400,50);
			 p1.add(l2);
			 p.add(p1,"third");
			 cd.show(p,"third");
		 }
		}
		if(e.getSource()==b2)
		{
			p.add(new Doctor(idd).p,"fourth");
			cd.show(p,"fourth");
		}
		if(e.getSource()==b4)
		{
			p.add(new Login(1).p,"third");
			cd.show(p,"third");
		}
		if(e.getSource()==b3)
		{
			p.add(new User("doctor",0).pl,"second");
			cd.show(p,"second");
		}
		if(e.getSource()==b5)
		{
			p.add(new DoctorViewAppointment(name,idd).p,"fifth");
			cd.show(p,"fifth");
		}
	}
}