package mapleGame;

import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;

public class Stone extends Enemy {
   

   public Stone() {
   }

   public Stone(String string, int x, int y, int hp, String name) {
      enemyMove = new ImageIcon(string);
      
      this.x = x;
      this.y = y;
      this.width = enemyMove.getIconWidth();
      this.height = enemyMove.getIconHeight();
      this.hp = hp;
      this.name = name;
      
      setIcon(enemyMove);
      setSize(200, 150);
      setLocation(x, y);
      
      moveChange();
      moveDirection();
   }

   public void moveChange() {
      new Thread(new Runnable() {
         @Override
         public void run() {
            while(true) {
               long n = System.currentTimeMillis()/1000;
               if(n % 5 == 0) {
                  speed = 100;
                  moveState = random.nextInt(3);
               }
               speed = 5;   
            }   
         }
      }).start();
   }

   public void moveDirection() {
      
      new Thread(new Runnable() {
         @Override
         public void run() {
            while (true) {
               
               if (moveState == 1) {
                  setIcon(new ImageIcon("image/Stone.gif"));
                  x++;
                  if (x >= 900) {
                     moveState = 2;
                  }
                  setLocation(x, y); // 내부에 repaint() 존재
                  try {
                     Thread.sleep(speed);

                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               } else if (moveState == 2) {
                  setIcon(new ImageIcon("image/StoneL.gif"));
                  x--;
                  if (x <= 100) {
                     moveState = 1;
                  }
                  setLocation(x, y); // 내부에 repaint() 존재
                  try {
                     Thread.sleep(speed);
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }

               } else if (moveState == 0) {
                  setLocation(x, y);
                  try {
                     Thread.sleep(speed);
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            }
         }
      }).start();
   }

}