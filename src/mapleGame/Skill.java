package mapleGame;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Skill extends JLabel {
   public int x;
   public int y;
   int width = 120;
   int height = 110;
   ImageIcon skillIcon;
   ImageIcon skillIconLeft, skillEffect;
   Player player;
   MapleApp mapleApp;
   GamePoint gamePoint;
   ArrayList<Enemy> enemy;
   
   boolean isSkill = true;

   public Skill(Player player, ArrayList<Enemy> enemy) {
      this.enemy = enemy;
      this.x = player.x;
      this.y = player.y;
      this.player = player;
      skillIcon = new ImageIcon("image/��ų��.png");
      skillIconLeft = new ImageIcon("image/��ų������.png");
      skillEffect = new ImageIcon("image/��ų���⶧2.gif");
      gamePoint = new GamePoint();
      
      setSize(400, 110);
      setLocation(x, y);

      if (player.seewhere == true) {
         skill();
      } else if (player.seewhere == false) {
         skillLeft();
      }

   }

   // Skill ��ü ������ �ڵ� ����
   public void skill() {
      new Thread(new Runnable() {
         @Override
         public void run() {
            while (isSkill) {
               setIcon(skillIcon);
               Col(enemy);
               x++;
               setLocation(x, y); // ���ο� repaint() ����
               try {
                  Thread.sleep(1);

               } catch (InterruptedException e) {
                  e.printStackTrace();

               }
            }
         }
      }).start();

   }

   public void skillLeft() {
      new Thread(new Runnable() {
         @Override
         public void run() {
            while (isSkill) {
               setIcon(skillIconLeft);
               Col(enemy);
               x--;
               setLocation(x, y); // ���ο� repaint() ����
               try {
                  Thread.sleep(1);

               } catch (InterruptedException e) {
                  e.printStackTrace();

               }
            }
         }
      }).start();
   }

   public void Col(ArrayList<Enemy> enemy) {
      for (int i = 0; i < enemy.size(); i++) {
         if (crash(this.x, this.y, enemy.get(i).x, enemy.get(i).y, this.width, this.height, enemy.get(i).width,
               enemy.get(i).height)) {
        	 try {
				Thread.sleep(1);
				enemy.get(i).moveState = 0;
				setIcon(skillEffect);
				setSize(500, 300);
				Thread.sleep(1000);
			} catch (Exception e) {
			}
            System.out.println("��ų ����!");
            this.isSkill = false;
            setIcon(null);
            enemy.get(i).hp -= 20;
            System.out.println(enemy.get(i).name + " hp :" + enemy.get(i).hp);
            if (enemy.get(i).hp == 0) {
               System.out.println(enemy.get(i).name + " ����...");
               enemy.get(i).x = 99999;

                  MapleApp.score(enemy.get(i).name);   //���� ���
                  gamePoint.setText("Point : "+ MapleApp.deadEnemy[2]);   //���� ǥ��

               if (enemy.get(i) instanceof Mushroom) {
                  try {
                     Thread.sleep(3000);
                     enemy.get(i).x = 550;
                     enemy.get(i).hp = 20;
                  } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }

               }

               if (enemy.get(i) instanceof Stone) {
                  try {
                     Thread.sleep(3000);
                     enemy.get(i).x = 120;
                     enemy.get(i).hp = 20;
                  } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
               
               if (enemy.get(i) instanceof Barlog) {
                  try {
                     Thread.sleep(3000);
                     enemy.get(i).x = 120;
                     enemy.get(i).hp = 20;
                  } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
               
               if (enemy.get(i) instanceof Block) {
                   try {
                      Thread.sleep(3000);
                      enemy.get(i).x = 120;
                      enemy.get(i).hp = 20;
                   } catch (InterruptedException e) {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                   }
                }
               
               

            }

         }
      }
   }

   // �浹 �Լ�
   public boolean crash(int playerX, int playerY, int enemyX, int enemyY, int playerW, int playerH, int enemyW,
         int enemyH) {
      boolean check = false;
      if (Math.abs((playerX + (playerW / 2) + 80) - (enemyX + enemyW / 2 + 20)) < (enemyW / 2 + playerW / 2 - 60)
            && Math.abs((playerY + playerH / 2) - (enemyY + enemyH / 2)) < (enemyH / 2 + playerH / 2)) {
         check = true;
      } else {
         check = false;
      }
      return check;
   }
   // end �浹

}