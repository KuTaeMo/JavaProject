package mapleGame;

import java.awt.Font;

import javax.swing.JLabel;

public class GamePoint extends JLabel{
   GamePoint gamePoint=this;

   public GamePoint() {
      setText("Point : 0");
      setFont(new Font("Point : ", Font.BOLD, 30));
          setSize(200,30);
          setLocation(1000,30);
   }
}
