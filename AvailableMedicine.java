import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
class AvailableMedicine implements ActionListener
{
	JPanel p,p1;
	DefaultTableModel model;
	JTable table;
	JScrollPane pane;
	JButton b,b1,b2,b3;
	CardLayout cd;
	long idd;
	AvailableMedicine(long id)
	{
		idd=id;
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		b=new JButton("Back To Previous Page");
		b1=new JButton("Back To Login Page");
		b2=new JButton("Back To Home Page");
		b3=new JButton("Search Specific Medicine");
		model=new DefaultTableModel();
	    model.addColumn("Medicine ID");
	    model.addColumn("Medicine Name");
	    model.addColumn("Prime Salt");
		model.addColumn("Quantity");	
		table=new JTable(model);
		pane=new JScrollPane(table);
		try
		{
			Connection conn=Jdbc.jconn();
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from medicine");
			while(rs.next())
			{
				String s[]=new String[4];
				Long medicineid=rs.getLong("medicine_id");
				s[0]=medicineid.toString();
				s[1]=rs.getString("name");
				s[2]=rs.getString("main_salt");
				Integer quantity=rs.getInt("quantity");
				s[3]=quantity.toString();
				model.addRow(s);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		b.setBounds(50,450,200,20);
	    b1.setBounds(50,490,200,20);
	    b2.setBounds(50,530,200,20);
	    b3.setBounds(300,390,450,20);
		pane.setBounds(300,70,450,300);
		p1.add(pane);
		p1.add(b);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		p.add(p1,"first");
		p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b)
		{
			p.add(new Pharmaceuticals(idd).p,"second");
			cd.show(p,"second");
		}
		if(e.getSource()==b1)
		{
			p.add(new User("pharmaceuticals",0).pl,"third");
			cd.show(p,"third");
		}
		if(e.getSource()==b2)
		{
			p.add(new Login(1).p,"fourth");
			cd.show(p,"fourth");
		}
		if(e.getSource()==b3)
		{
			p.add(new SearchMedicine(idd).p,"fifth");
			cd.show(p,"fifth");
		}
	}	
}