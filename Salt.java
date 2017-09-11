import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
class Salt implements ActionListener
{
	JPanel p,p1;
	JScrollPane pane;
	JTable table;
	DefaultTableModel model;
	JLabel l1,l2;
	JTextField t1;
	CardLayout cd;
	JButton b1,b2,b3,b4,b5;
	long idd;
	Salt(long id)
	{
		idd=id;
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		l1=new JLabel("Salt :");
		t1=new JTextField();
		b1=new JButton("Back To Previous Page");
		b2=new JButton("Back To Login Page");
		b4=new JButton("Search Medicine");
		b5=new JButton("Refresh");
		b3=new JButton("Back To Home Page");
		l1.setBounds(300,150,100,20);
		t1.setBounds(360,150,200,20);
		b4.setBounds(580,150,150,20);
		b1.setBounds(50,490,250,20);
	    b2.setBounds(50,530,250,20);
	    b3.setBounds(50,570,250,20);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		p1.add(l1);
		p1.add(t1);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p.add(p1,"first");
		p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
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
		if(e.getSource()==b4)
		{
		  int a=0;
		  String name=t1.getText();
		  model=new DefaultTableModel();
		  model.addColumn("Medicine Name");
		  model.addColumn("Price");
		  model.addColumn("Quantity");
		  try
		  {
			  Connection conn=Jdbc.jconn();
			  Statement stmt=conn.createStatement();
			  ResultSet rs=stmt.executeQuery("select * from medicine");
			  while(rs.next())
			  {
				  if(rs.getString("main_salt").equals(name))
				  {
					a=1;
                    String s[]=new String[3];
                    s[0]=rs.getString("name");					
                    s[1]=Integer.toString(rs.getInt("price"));					
                    s[2]=Integer.toString(rs.getInt("quantity"));
                    model.addRow(s);					
				  }
			  }
		  }
		  catch(Exception e1)
		  {
			  e1.printStackTrace();
		  }
		  if(a==1)
		  {
			  table=new JTable(model);
			  pane=new JScrollPane(table);
			  pane.setBounds(300,200,430,250);
			  p1.add(pane);
			  b4.setVisible(false);
			  t1.setEditable(false);
			  b5.setBounds(580,150,150,20);
			  p1.add(b5);
		  }
		  else
		  {
			  l2=new JLabel("No Medicines With The Asked Salt Available");
			  l2.setForeground(Color.red);
		      l2.setFont(new Font("Old English Text MT", Font.ITALIC,20));
			  l2.setBounds(320,250,430,100);
			  p1.add(l2);
		  }
		  p.add(p1,"fifth");
		  cd.show(p,"fifth");
		}
		if(e.getSource()==b5)
		{
			p.add(new Salt(idd).p,"sixth");
			cd.show(p,"sixth");
		}
	}	
}