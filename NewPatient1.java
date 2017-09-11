import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
class NewPatient1 implements ActionListener
{
	JPanel p,p1;
	JLabel l,l1,l2,l3,l4,l5,l6,l7;
	CardLayout cd=new CardLayout();
	JTextArea t1,t2,t3;
	JRadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8;
	ButtonGroup bg1,bg2,bg3,bg4;
	JButton b;
    int pa,padmit,pi,od;
	long pdi,pid,userid;
	NewPatient1()
	{}
	NewPatient1(long id,long usid)
	{
		 pid=id;
		 userid=usid;
		 p=new JPanel();
		 p1=new JPanel();
		 p.setLayout(cd);
		 p1.setLayout(null);
		 rb1=new JRadioButton("Y");
		 rb2=new JRadioButton("N");
         rb3=new JRadioButton("Y");
	     rb4=new JRadioButton("N");
		 rb5=new JRadioButton("Y");
		 rb6=new JRadioButton("N");
		 rb7=new JRadioButton("Y");
		 rb8=new JRadioButton("N");
		 l=new JLabel("Patient Medical History");
		 l1=new JLabel("Previous Injuries");
		 l2=new JLabel("Previous Ailment");
		 l3=new JLabel("Previous Aliment Details");
		 l4=new JLabel("Previously Admitted");
		 l5=new JLabel("Previous Admit Details");
		 l6=new JLabel("Any Other Details");
		 l7=new JLabel("Mention Details");
		 b=new JButton("Get UserID and Password");
		 bg1 = new ButtonGroup();
		 bg1.add(rb1);	
		 bg1.add(rb2);
		 bg2 = new ButtonGroup();
	     bg2.add(rb3);	
	     bg2.add(rb4);
		 bg3 = new ButtonGroup();
		 bg3.add(rb5);	
		 bg3.add(rb6);
		 bg4 = new ButtonGroup();
		 bg4.add(rb7);	
		 bg4.add(rb8);
		 t1=new JTextArea();
		 t2=new JTextArea();
		 t3=new JTextArea();
		 l.setBounds(430,30,200,30);
		 l1.setBounds(330,70,150,20);
		 rb1.setBounds(500,70,40,20);
		 rb2.setBounds(540,70,40,20);
		 l2.setBounds(330,110,150,20);
		 rb3.setBounds(500,110,40,20);
		 rb4.setBounds(540,110,40,20);
		 l4.setBounds(330,150,150,20);
		 rb5.setBounds(500,150,40,20);
		 rb6.setBounds(540,150,40,20);
		 l6.setBounds(330,190,150,20);
		 rb7.setBounds(500,190,40,20);
		 rb8.setBounds(540,190,40,20);
		 b.setBounds(700,450,200,20);
		 p1.add(l1);
		 p1.add(l2);
		 p1.add(l4);
		 p1.add(l6);
		 p1.add(rb1);
		 p1.add(rb2);
         p1.add(rb3);
         p1.add(rb4);
		 p1.add(rb5);
		 p1.add(rb6);
		 p1.add(rb7);
		 p1.add(rb8);
		 p1.add(l);
		 p1.add(b);
		 p1.setPreferredSize(new Dimension(1000,700));
		 rb3.setEnabled(false);
		 rb4.setEnabled(false);
		 rb5.setEnabled(false);
		 rb6.setEnabled(false);
		 rb7.setEnabled(false);
		 rb8.setEnabled(false);
		 b.setEnabled(false);
		 rb1.addActionListener(this);
		 rb2.addActionListener(this);
		 rb3.addActionListener(this);
		 rb4.addActionListener(this);
		 rb5.addActionListener(this);
		 rb6.addActionListener(this);
		 rb7.addActionListener(this);
		 rb8.addActionListener(this);
		 b.addActionListener(this);
		 p.add(p1,"first");
		 p.setVisible(true);
		 cd.show(p,"first");		 
	}
	public void actionPerformed(ActionEvent e)
	{
          if(e.getSource()==rb1 || e.getSource()==rb2)
          {
            if(e.getSource()==rb1)
             pi=1;
            if(e.getSource()==rb2)
             pi=2;	 
		    rb3.setEnabled(true);
		    rb4.setEnabled(true);
          }
		  if(e.getSource()==rb3 || e.getSource()==rb4)
          {
            if(e.getSource()==rb3)
			{	
             pa=1;
			 l3.setBounds(330,150,150,20);
		     t1.setBounds(500,150,200,50);
			 l4.setBounds(330,220,150,20);
		     rb5.setBounds(500,220,40,20);
		     rb6.setBounds(540,220,40,20);
			 p1.add(l3);
			 p1.add(t1);
			 p1.add(l4);
			 p1.add(rb5);
			 p1.add(rb6);
			 l6.setBounds(330,260,150,20);
		     rb7.setBounds(500,260,40,20);
		     rb8.setBounds(540,260,40,20);
			 l3.setVisible(true);
			 t1.setVisible(true);
			 p1.add(l6);
			 p1.add(t3);
			 p.add(p1,"second");
			 p.setVisible(true);
			 cd.show(p,"second");
			}
            if(e.getSource()==rb4)
			{	
             pa=2;
			 l4.setBounds(330,150,150,20);
		     rb5.setBounds(500,150,40,20);
		     rb6.setBounds(540,150,40,20);
             l6.setBounds(330,190,150,20);
		     rb7.setBounds(500,190,40,20);
		     rb8.setBounds(540,190,40,20);
			 p1.add(l4);
			 p1.add(rb5);
			 p1.add(rb6);
			 p1.add(l6);
			 p1.add(t3);
			 l3.setVisible(false);
			 t1.setVisible(false);
			 p.add(p1,"third");
			 p.setVisible(true);
			 cd.show(p,"third");
            }			 
			rb5.setEnabled(true);
		    rb6.setEnabled(true);
			rb1.setEnabled(false);
		    rb2.setEnabled(false);
          }
		  if(e.getSource()==rb5 || e.getSource()==rb6)
          {
            if(e.getSource()==rb5)
			{	
             padmit=1;
			 if(pa!=1)
			 {
			 l5.setBounds(330,190,150,20);
		     t2.setBounds(500,190,200,50);
		     l6.setBounds(330,260,150,20);
		     rb7.setBounds(500,260,40,20);
		     rb8.setBounds(540,260,40,20); 
			 }	 
			 else
			 {
			 l5.setBounds(330,270,150,20);
		     t2.setBounds(500,270,200,50);
		     l6.setBounds(330,340,150,20);
		     rb7.setBounds(500,340,40,20);
		     rb8.setBounds(540,340,40,20);
			 }
			 l5.setVisible(true);
			 t2.setVisible(true);
			 p1.add(l5);
			 p1.add(t2);
			 p1.add(l6);
			 p1.add(t3);
			 p.add(p1,"fourth");
			 p.setVisible(true);
			 cd.show(p,"fourth");
			}
            if(e.getSource()==rb6)
			{	
             padmit=2;
			 l5.setVisible(false);
			 t2.setVisible(false);
			 if(pa!=1)
			 {
		     l6.setBounds(330,190,150,20);
		     rb7.setBounds(500,190,40,20);
		     rb8.setBounds(540,190,40,20); 
			 }	 
			 else
			 {
		     l6.setBounds(330,270,150,20);
		     rb7.setBounds(500,270,40,20);
		     rb8.setBounds(540,270,40,20);
			 }
			 p1.add(l5);
			 p1.add(t2);
			 p1.add(l6);
			 p1.add(t3);
			 p.add(p1,"fifth");
			 p.setVisible(true);
			 cd.show(p,"fifth");
            }
			rb3.setEnabled(false);
		    rb4.setEnabled(false);
			rb7.setEnabled(true);
		    rb8.setEnabled(true);
          }
		  if(e.getSource()==rb7 || e.getSource()==rb8)
          {
            if(e.getSource()==rb7)
			{	
             od=1;
			 if(pa==1 && padmit==1)
			 {
			 l7.setBounds(330,380,150,20);
		     t3.setBounds(500,380,200,50); 
			 }	 
			 else if(pa!=1 && padmit!=1)
			 {
			 l7.setBounds(330,230,150,20);
		     t3.setBounds(500,230,200,50);  
			 }
			 else
			 {
			  l7.setBounds(330,300,150,20);
		      t3.setBounds(500,300,200,50);  
			 }
			 l7.setVisible(true);
			 t3.setVisible(true);
			 p1.add(l7);
			 p1.add(t3);
			 p.add(p1,"sixth");
			 p.setVisible(true);
			 cd.show(p,"sixth");
			}
            if(e.getSource()==rb8)
			{	
             od=2;
			 l7.setVisible(false);
			 t3.setVisible(false);
			 p.add(p1,"seventh");
			 p.setVisible(true);
			 cd.show(p,"seventh");
            }
			rb5.setEnabled(false);
		    rb6.setEnabled(false);
		    b.setEnabled(true);
          }
		  if(e.getSource()==b)
		  {
			try
			{
			String s1,s2,s3;
            s1=t1.getText();
            s2=t2.getText();
			s3=t3.getText();			
			Connection conn=Jdbc.jconn(); 
            Statement stmt=conn.createStatement();
            PreparedStatement ps=conn.prepareStatement("insert into patient_description values(?,?,?,?,?,?)");
         	if(pi==1)
              ps.setString(1,"Y");
            else 
              ps.setString(1,"N");
            if(pa==1)
              ps.setString(2,"Y");
            else 
              ps.setString(2,"N");
   		    if(padmit==1)
              ps.setString(4,"Y");
            else 
              ps.setString(4,"N");
            if(pi==1)
              ps.setString(3,s1);
            else
              ps.setString(3,"null");
		    if(pi==1)
              ps.setString(5,s2);
            else
              ps.setString(5,"null");
		    if(pi==1)
              ps.setString(6,s3);
            else
              ps.setString(6,"null");
            ps.execute();		  
		    ResultSet rs=stmt.executeQuery("select * from patient_description");
            while(rs.next())
            {
				pdi=rs.getLong("patient_description_id");
			}				
            PreparedStatement ps1=conn.prepareStatement("update patient_record set patient_description_id=(?) where patient_id=(?)");
            ps1.setLong(1,pdi);
            ps1.setLong(2,pid);
			ps1.execute();
			p.add(new NewPatient2(pid,userid).p,"eighth");
			cd.show(p,"eighth");
			
            }			
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		  }
	}
}	