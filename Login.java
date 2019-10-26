import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class Login implements ActionListener
{
	JFrame fr;
	JMenuBar bar;
	JMenu m;
	JMenuItem mi1;
	CardLayout c=new CardLayout();
	JPanel p,p1;
	JButton b1,b2,b3,b4;
	Login(int check)
	{
		p=new JPanel();
		p1=new JPanel();
		p1.setLayout(null);
		p.setLayout(c);
		b1=new JButton("Reception");
		b2=new JButton("Doctor");
		b3=new JButton("Pharmaceuticals");
		b4=new JButton("Head of Departments");
		b1.setBounds(400,150,200,20);
		b2.setBounds(400,210,200,20);
		b3.setBounds(400,270,200,20);
		b4.setBounds(400,330,200,20);
		p1.setPreferredSize(new Dimension(1000,7000));
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		b4.setEnabled(false);
		p.add(p1,"first");
		c.show(p,"first");
		bar=new JMenuBar();
		m=new JMenu("File");
		mi1=new JMenuItem("Exit");
		mi1.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		m.add(mi1);
		bar.add(m);
		if(check==0)
		{	
		fr=new JFrame();
		fr.setLayout(new FlowLayout());
		fr.setJMenuBar(bar);
		fr.add(p);
		fr.setSize(1000,700);
		fr.setVisible(true);
		}
		else
		    p.setVisible(true);	
	}
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource()==mi1)
			System.exit(0);
		if(e.getSource()==b1)
		{
			p.add(new User("reception",0).pl,"second");
			c.show(p,"second");
		}
        if(e.getSource()==b2)
		{
			p.add(new User("doctor",0).pl,"second");
			c.show(p,"second");
		}
        if(e.getSource()==b3)
		{
			p.add(new User("pharmaceuticals",0).pl,"second");
			c.show(p,"second");
		}	
        if(e.getSource()==b4)
		{
			p.add(new User("hod",0).pl,"second");
			c.show(p,"second");
		}			
	}
	public static void main(String s[])
	{		
		// testing
		System.out.println("Hello world");

		new Login(0);
	}
}