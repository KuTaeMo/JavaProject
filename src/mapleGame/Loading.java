package mapleGame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Loading extends JFrame   {
   ImageIcon icon;
   JLabel la;
   Loading loading=this;
   LoginScr loginScr;
   
   public Loading() {
      la=new JLabel();
     
      icon=new ImageIcon("image/gamestart.gif");
      la.setIcon(icon);
      
      add(la);
      setSize(icon.getIconWidth(),icon.getIconHeight());
      setLocationRelativeTo(null);
      
      new Thread(new Runnable() {
         
         @Override
         public void run() {
            int n=0;
            while(true) {
               try {
                  Thread.sleep(1000);
                  n++;
                  if(n==8) {
                     new LoginScr(loading);
                     loading.setVisible(false);
                  }
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }
         }
      }).start();
      
      setVisible(true);
   }
   
   public static void main(String[] args) {
      new Loading();
   }
}