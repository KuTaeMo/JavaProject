package mapleStroy;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MaplePlayer extends JLabel {
   public MaplePlayer player = this;
   public final static String TAG = "Player : ";

   public ImageIcon icPlayerR, icPlayerL, icPlayerJ, icPlayerW, icPlayerW2, icPlayerS;
   public int x = 55;
   public int y = 505;

   public boolean isRight = false;
   public boolean isLeft = false;
   public boolean isUp = false;
   public boolean isDown = false;
   public boolean isJump = false;

   public int floor = 1; // 535, 415, 295, 177

   public MaplePlayer() {
      icPlayerR = new ImageIcon("image/imgStayR.png");
      icPlayerL = new ImageIcon("image/imgStayL.png");
      icPlayerJ = new ImageIcon("image/imgJumpR.png");
      icPlayerW = new ImageIcon("image/imgWalkR1.png");
      icPlayerS = new ImageIcon("image/imgStayL.png");
      icPlayerW2 = new ImageIcon("image/imgWalkR2.png");
      setIcon(icPlayerS);
      setSize(100, 100);
      setLocation(x, y);
   }

   public void moveRight() {
      System.out.println(TAG + "moveRight()");

      if (isRight == false) {
         new Thread(new Runnable() {
            @Override
            public void run() {
               
               isRight = true;
               while (isRight) {
                  x++;
                  setLocation(x, y); // 내부에 repaint() 존재
                  try {
                     Thread.sleep(10);
                     setIcon(icPlayerW);
                     Thread.sleep(10);
                     setIcon(icPlayerS);
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               setIcon(icPlayerS);
            }
         }).start();
      }
   }

   public void moveLeft() {
      System.out.println(TAG + "moveLeft()");

      if (isLeft == false) {
         new Thread(new Runnable() {
            @Override
            public void run() {
               setIcon(icPlayerL);
               isLeft = true;
               while (isLeft) {
                  x--;
                  setLocation(x, y); // 내부에 repaint() 존재
                  try {
                     Thread.sleep(5);
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            }
         }).start();
      }
   }

   public void moveUp() {
      System.out.println(TAG + "moveUp()");
      if (isUp == false) {
         new Thread(new Runnable() {

            @Override
            public void run() {
               setIcon(icPlayerJ);
               isUp = true;
               while (isUp) {
                  for (int i = 0; i < 130; i++) {
                     y--;
                     setLocation(x, y); // 내부에 repaint() 존재
                     try {
                        Thread.sleep(5);
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                     }
                  }
               }
               setIcon(icPlayerS);
               moveDown();
            }
         }).start();
      }
   }

   public void moveDown() {
      System.out.println(TAG + "moveDown()");
      if (isDown == false) {
         new Thread(new Runnable() {

            @Override
            public void run() {
               isDown = true;
               while (isDown) {
                  y++;
                  if (floor == 1 && y > 535) {
                     break;
                  }
                  setLocation(x, y); // 내부에 repaint() 존재
                  try {
                     Thread.sleep(5);
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            }
         }).start();
      }
   }

   public void moveJump() {
      System.out.println(TAG + "moveJump()");
      if (isJump == false) {
         new Thread(new Runnable() {

            @Override
            public void run() {
               isJump = true;
               while (isJump) {
                  moveUp();
                  moveDown();
                  setLocation(x, y); // 내부에 repaint() 존재
                  try {
                     Thread.sleep(5);
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            }
         }).start();
      }
   }

   // 구현안함
   public void attack() {

   }

}
