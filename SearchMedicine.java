import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class SearchMedicine implements ActionListener
{
	JPanel p,p1;
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JTextField t1,t2,t3,t4,t5,t6;
	JButton b,b1,b2,b3,b4;
	CardLayout cd;
	long idd;
	SearchMedicine(long id)
	{
		idd=id;
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		l1=new JLabel("Medicine :");
		l2=new JLabel("Medicine ID:");
		l3=new JLabel("Medicine Name:");
		l4=new JLabel("Prime Salt:");
		l5=new JLabel("Quantity:");
		l6=new JLabel("Medicine Not Available In Pharmacy");
		l6.setForeground(Color.red);
		l6.setFont(new Font("Old English Text MT", Font.ITALIC,18));
		b=new JButton("Back To Previous Page");
		b1=new JButton("Back To Pharmaceutical Main Page");
		b2=new JButton("Back To Login Page");
		b3=new JButton("Back To Home Page");
		b4=new JButton("Search");
		t1=new JTextField();
		t2=new JTextField();
		t3=new JTextField();
		t4=new JTextField();
		t5=new JTextField();
        b.setBounds(50,450,250,20);
	    b1.setBounds(50,490,250,20);
	    b2.setBounds(50,530,250,20);
	    b3.setBounds(50,570,250,20);
		l1.setBounds(400,100,100,20);
		t1.setBounds(470,100,200,20);
		b4.setBounds(690,100,100,20);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		p1.add(b);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(l1);
		p1.add(t1);
		p.add(p1,"first");
		p.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b)
		{
			p.add(new AvailableMedicine(idd).p,"fifth");
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
		if(e.getSource()==b4)
		{
			String name=t1.getText();
			int a=0;
			try
			{
				Connection conn=Jdbc.jconn();
				Statement stmt=conn.createStatement();
			    ResultSet rs=stmt.executeQuery("select * from medicine");
				while(rs.next())
				{
					if(name.equals(rs.getString("name")))
					{
						Long id,quantity;
						id=rs.getLong("medicine_id");
						quantity=rs.getLong("quantity");
						t2.setText(id.toString());
						t3.setText(name);
						t4.setText(rs.getString("main_salt"));
						t5.setText(id.toString());
						a=1;
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
				l6.setVisible(false);
				l2.setVisible(true);
				l3.setVisible(true);
				l4.setVisible(true);
				l5.setVisible(true);
				t2.setVisible(true);
				t3.setVisible(true);
				t4.setVisible(true);
				t5.setVisible(true);
				t2.setEnabled(false);
				t3.setEnabled(false);
				t4.setEnabled(false);
				t5.setEnabled(false);
				l2.setBounds(400,200,100,20);
				t2.setBounds(500,200,100,20);
				l3.setBounds(400,240,100,20);
				t3.setBounds(500,240,100,20);
				l4.setBounds(400,280,100,20);
				t4.setBounds(500,280,100,20);
				l5.setBounds(400,320,100,20);
				t5.setBounds(500,320,100,20);
				p1.add(l2);
				p1.add(l3);
				p1.add(l4);
				p1.add(l5);
				p1.add(t2);
				p1.add(t3);
				p1.add(t4);
				p1.add(t5);
				p.add(p1,"sixth");
				cd.show(p,"sixth");
			}
			else
			{
				l6.setVisible(true);
				l2.setVisible(false);
				l3.setVisible(false);
				l4.setVisible(false);
				l5.setVisible(false);
				t2.setVisible(false);
				t3.setVisible(false);
				t4.setVisible(false);
				t5.setVisible(false);
				l6.setBounds(450,200,300,100);
				p1.add(l6);
				p.add(p1,"seventh");
				cd.show(p,"seventh");
			}
		}
	}
}