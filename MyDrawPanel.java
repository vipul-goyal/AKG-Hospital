import javax.swing.*;
import java.awt.*;
class MyDrawPanel extends JPanel
{
 static int x=11,a;
 public void paintComponent(Graphics g)
 {
  System.out.println("chala2");
  g.drawRect (0,10,122,11);
  g.drawRect (122,10,120,11);
  g.drawRect (242,10,60,11);
  if(a>0)
  {
  g.setColor(Color.green);
  g.fillRect(1,12,x,7);
  x=x+11;
  }
  else
  {
	  a++;
  }
 }
}