import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class BuySell implements ActionListener
{
	JPanel p,p1;
	JButton b1,b2,b3,b4,b5;
	CardLayout cd;
	long idd;
	BuySell(long id)
	{
		idd=id;
		cd=new CardLayout();
		p=new JPanel(cd);
		p1=new JPanel(null);
		b1=new JButton("Back To Previous Page");
		b2=new JButton("Back To Login Page");
		b3=new JButton("Back To Home Page");
		b4=new JButton("Buy Medicines");
		b5=new JButton("Sell Medicines");
		b1.setBounds(50,490,250,20);
	    b2.setBounds(50,530,250,20);
	    b3.setBounds(50,570,250,20);
		b4.setBounds(400,210,200,20);
		b5.setBounds(400,270,200,20);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
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
			p.add(new BuyMedicines(idd).p,"fifth");
			cd.show(p,"fifth");
		}
		if(e.getSource()==b5)
		{
			p.add(new SellMedicines(idd).p,"sixth");
			cd.show(p,"sixth");
		}
	}	
}