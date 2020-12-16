package mapleGame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerMpBar extends JLabel{
   PlayerMpBar bar=this;
   int width, height;
   Player player;
   ImageIcon imgIcon;
   
   public PlayerMpBar() {
      imgIcon=new ImageIcon("image/mpp50.png");
      
      width = imgIcon.getIconWidth();
        height = imgIcon.getIconHeight();
        setIcon(imgIcon);
        setSize(width, height);
        setLocation(400,630);
   }
}