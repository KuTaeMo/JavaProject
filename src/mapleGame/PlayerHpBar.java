package mapleGame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerHpBar extends JLabel{
   PlayerHpBar bar=this;
   int width, height;
   Player player;
   ImageIcon imgIcon;
   
   public PlayerHpBar() {
      imgIcon=new ImageIcon("image/hpp100.png");
      
      width = imgIcon.getIconWidth();
        height = imgIcon.getIconHeight();
        setIcon(imgIcon);
        setSize(width, height);
        setLocation(55,630);
   }
}