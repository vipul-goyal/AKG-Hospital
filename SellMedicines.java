import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
class SellMedicines implements ActionListener
{
	JPanel p,p1;
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JButton b,b1,b2,b3,b4,b5,b6,b7;
	JTextField t1,t3;
	JTable table;
	JTextArea t2;
	JScrollPane pane;
	JComboBox cb1,cb2,cb3,cb4,cb5;
	DefaultComboBoxModel model1,model2,m3,m4,m5,m6;
	DefaultTableModel tm1;
	CardLayout cd;
	Calendar cal;
	long idd,price,r;
	SellMedicines(long id)
	{
		idd=id;
		table=new JTable();
		tm1=new DefaultTableModel();
		tm1.addColumn("Medicine Name");
		tm1.addColumn("Quantity");
		cal = Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
        int month= cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		t1=new JTextField();
		t3=new JTextField();
		l1=new JLabel("Medicine Name");
		l2=new JLabel("Quantity");
		l3=new JLabel("Total Amount Payable:");
		l4=new JLabel("Patient Id");
		l5=new JLabel("Date");
		l6=new JLabel("                                              Prescription Not Found");
		b=new JButton("Back To Previous Page");
		b1=new JButton("Back To Pharmaceuticals Main Page");
		b2=new JButton("Back To Login Page");
		b3=new JButton("Back To Home Page");
		b4=new JButton("Get Prescription");
		b5=new JButton("Add Medicine");
		b6=new JButton("Refresh Page");
		b7=new JButton("Sell Medicines");
		cb2=new JComboBox();
		cb3=new JComboBox();
        cb4=new JComboBox();
        cb5=new JComboBox();
		model1=new DefaultComboBoxModel();
		m3=new DefaultComboBoxModel();
		m4=new DefaultComboBoxModel();
		m5=new DefaultComboBoxModel();
		try
		{
			Connection conn=Jdbc.jconn();
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from medicine");
			while(rs.next())
			{
			 model1.addElement(rs.getString("name"));
			}
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
		cb1=new JComboBox(model1);
		l1.setBounds(600,360,150,20);
		l2.setBounds(600,400,150,20);
		l3.setBounds(320,580,150,20);
		l4.setBounds(600,100,100,20);
		l5.setBounds(600,140,100,20);
		l6.setBounds(200,200,100,20);
		t1.setBounds(700,100,100,20);
		cb1.setBounds(750,360,200,20);
		cb2.setBounds(750,400,200,20);
		cb3.setBounds(700,140,40,20);
	    cb4.setBounds(740,140,100,20);
	    cb5.setBounds(840,140,80,20);
		b.setBounds(50,450,250,20);
		b1.setBounds(50,490,250,20);
	    b2.setBounds(50,530,250,20);
	    b3.setBounds(50,570,250,20);
	    b4.setBounds(770,180,150,20);
	    b5.setBounds(800,440,150,20);
		b6.setBounds(770,180,150,20);
		b7.setBounds(800,480,150,20);
		cb1.addActionListener(this);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		p1.add(l1);
		p1.add(l2);
		p1.add(l4);
		p1.add(l5);
		p1.add(t1);
		p1.add(cb1);
		p1.add(cb2);
		p1.add(cb3);
		p1.add(cb4);
		p1.add(cb5);
		p1.add(b);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		p1.add(b7);
		b5.setEnabled(false);
		b7.setEnabled(false);
		t3.setEditable(false);
		p.add(p1,"first");
		p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b)
		{
			p.add(new BuySell(idd).p,"fifth");
			cd.show(p,"fifth");
		}
		if(e.getSource()==b1)
		{
			p.add(new Pharmaceuticals(idd).p,"second");
			cd.show(p,"second");
		}
		if(e.getSource()==b2)
		{
			p.add(new User("pharmaceuticals",0).pl,"third");
			cd.show(p,"third");
		}
		if(e.getSource()==b3)
		{
			p.add(new Login(1).p,"fourth");
			cd.show(p,"fourth");
		}
		if(e.getSource()==cb1)
		{
			String name=(String)cb1.getSelectedItem();
			try
			{
				Connection conn=Jdbc.jconn();
				Statement stmt=conn.createStatement();
				model2=new DefaultComboBoxModel();
				ResultSet rs=stmt.executeQuery("select * from medicine");
				while(rs.next())
				{
					if((rs.getString("name")).equals(name))
					{
						int i=1;
						while(i<=(rs.getInt("quantity")))
						{
							model2.addElement(i);
							i++;
						}
						break;
					}
				}
				cb2.setModel(model2);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			b5.setEnabled(true);
		}
		if(e.getSource()==b4)
		{
			int date=0,year=0,a=0;
			long monthid=0,dateid=0,yearid=0,dmappingid=0,patientid;
			String month;
			patientid=Long.parseLong(t1.getText());
			date=(Integer)cb3.getSelectedItem();
            month=(String)cb4.getSelectedItem();
            year=(Integer)cb5.getSelectedItem();
			JScrollPane pane;
			JPanel p2;
			t2=new JTextArea();
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
			  ResultSet rs4=stmt.executeQuery("select * from prescribemedicines");
			  while(rs4.next())
			  {
				  if((dmappingid==(rs4.getLong("date_mapping_id")))&&(patientid==(rs4.getLong("patient_id"))))
				  {
					  a=1;
					  t2.setText(rs4.getString("prescription"));
					  break;
				  }
			  }
			}
            catch(Exception e1)
			{
				e1.printStackTrace();
			}			
			if(a==1)
			{
				
				p2=new JPanel(null);
				t2.setVisible(true);
				l6.setVisible(false);
				pane=new JScrollPane(t2);
				p2.add(pane);
			}	
			else
			{
				p2=new JPanel(null);
				t2.setVisible(false);
				l6.setVisible(true);
				pane=new JScrollPane(l6);
				p2.add(pane);
			}
			pane.setBounds(50,50,500,250);
			p2.setBounds(0,0,550,300);
			p1.add(p2);
			b4.setVisible(false);
			p1.add(b6);
			p.add(p1,"sixth");
			cd.show(p,"sixth");
		}
		if(e.getSource()==b5)
		{
			String name;
			int quantity=0;
			r++;
			name=(String)cb1.getSelectedItem();
			quantity=(Integer)cb2.getSelectedItem();
			String s[]=new String[2];
			s[0]=name;
			s[1]=Integer.toString(quantity);
			tm1.addRow(s);
			table.setModel(tm1);
			if(r==1)
			 pane=new JScrollPane(table);
			pane.setBounds(320,360,250,200);
			p1.add(pane);
			try
			{
				Connection conn=Jdbc.jconn();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from medicine");
				while(rs.next())
				{
					if((rs.getString("name")).equals(name))
					{
						price+=quantity*(rs.getInt("price"));
						break;
					}
				}
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			m6=new DefaultComboBoxModel();
			cb2.setModel(m6);
			t3.setText(Long.toString(price));
			t3.setBounds(470,580,100,20);
			p1.add(t3);
			p1.add(l3);
			b7.setEnabled(true);
			p.add(p1,"eighth");
			cd.show(p,"eighth");
		}
		if(e.getSource()==b6)
		{
			p.add(new SellMedicines(idd).p,"seventh");
			cd.show(p,"seventh");
		}
		if(e.getSource()==b7)
		{
			try
			{
				TableModel tm = table.getModel();
                for (int i = 0; i < tm.getRowCount(); i++) 
				{
                  String name=(String)(tm.getValueAt(i,0)); 
                  String q=(String)(tm.getValueAt(i,1));
                  int quantity=Integer.parseInt(q);
                  Connection conn=Jdbc.jconn();
                  PreparedStatement ps=conn.prepareStatement("update medicine set quantity=quantity-? where name=?");
                  ps.setInt(1,quantity);
                  ps.setString(2,name);
                  ps.execute(); 				  
                }
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
			p.add(new SellMedicines(idd).p,"ninth");
			cd.show(p,"ninth");
		}
	}	
}