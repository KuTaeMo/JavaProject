package mapleGame;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MapleApp extends JFrame implements Initable {
	MapleApp mapleApp = this;
	static final String TAG = "MapleApp : ";
	JLabel laBackground;
	Player player;
	PlayerHpBar bar;
	PlayerMpBar bar2;

	// Á¡¼ö º¯¼ö
	static int scoreMushNum;
	static int scoreStoneNum;
	static int[] deadEnemy = { 0, 0, 0 }; // 0¹øÁö ¹ö¼¸ Á×ÀÎ¼ö / 1¹øÁö °ñ·½ Á×ÀÎ ¼ö / 2¹øÁö ÃÑÁ¡

	static GamePoint gamePoint;

	ArrayList<Enemy> enemy;

	// ÁÖÈ²¹ö¼¸ °´Ã¼
	Mushroom mushroom;
	// ½ºÅæ°ñ·½ °´Ã¼
	Stone stone;
	// ¹ß·Ï °´Ã¼
	Barlog barlog;
	// ¾ÆÀÌ½º°ñ·½ °´Ã¼
	Block block;
	// º¸½º °´Ã¼
	Boss boss;

	boolean isBoss = false;
	boolean isBossStart = true;
	
	// ·Î±×ÀÎ 
	LoginScr loginScr;
	
	Container c;
	Skill skillShot;

	// HP ¶óº§
	ImageIcon icHp0, icHp10, icHp20, icHp30, icHp40, icHp50, icHp60, icHp70, icHp80, icHp90, icHp100;
	// MP ¶óº§
	ImageIcon icMp0, icMp10, icMp20, icMp30, icMp40, icMp50;

	public MapleApp(LoginScr loginScr) {
		init(); // new
		setting();
		batch();
		listener();

		setVisible(true); // ¸¶Áö¸· °íÁ¤
	}

	public static void main(String[] args) {

	}

	@Override
	public void init() {
		c = getContentPane();
		laBackground = new JLabel(new ImageIcon("image/background2.png"));

		enemy = new ArrayList<>();

		icHp0 = new ImageIcon("image/hpp0.png");
		icHp10 = new ImageIcon("image/hpp10.png");
		icHp20 = new ImageIcon("image/hpp20.png");
		icHp30 = new ImageIcon("image/hpp30.png");
		icHp40 = new ImageIcon("image/hpp40.png");
		icHp50 = new ImageIcon("image/hpp50.png");
		icHp60 = new ImageIcon("image/hpp60.png");
		icHp70 = new ImageIcon("image/hpp70.png");
		icHp80 = new ImageIcon("image/hpp80.png");
		icHp90 = new ImageIcon("image/hpp90.png");
		icHp100 = new ImageIcon("image/hpp100.png");

		icMp0 = new ImageIcon("image/mpp0.png");
		icMp10 = new ImageIcon("image/mpp10.png");
		icMp20 = new ImageIcon("image/mpp20.png");
		icMp30 = new ImageIcon("image/mpp30.png");
		icMp40 = new ImageIcon("image/mpp40.png");
		icMp50 = new ImageIcon("image/mpp50.png");

		player = new Player();

		mushroom = new Mushroom("image/ÁÖÈ²¹ö¼¸¿À¸¥ÂÊ.gif", 555, 380, 60, "ÁÖÈ²¹ö¼¸");
		stone = new Stone("image/Stone.gif", 100, 150, 20, "½ºÅæ°ñ·½");
		barlog = new Barlog("image/¹ß·Ï¿À¸¥ÂÊ.gif", 200, -20, 20, "ÁÖ´Ï¾î¹ß·Ï");
		block = new Block("image/ºí·Ï°ñ·½¿À¸¥ÂÊ.gif", 200, 300, 60, "ºí·Ï°ñ·½");
		
		boss = new Boss("image/ÀÚÄñ.gif", 300, 20, 400, "ÀÚÄñ");

		
		bar = new PlayerHpBar();
		bar2 = new PlayerMpBar();
		gamePoint = new GamePoint();

		enemy.add(mushroom);
		enemy.add(stone);
		enemy.add(barlog);
		enemy.add(block);
		enemy.add(boss);

		player.healing();
	
		Thread mushCol = new Thread(new col(mushroom));
		mushCol.start();
		Thread stoneCol = new Thread(new col(stone));
		stoneCol.start();
		Thread balCol = new Thread(new col(barlog));
		balCol.start();
		Thread iceCol = new Thread(new col(block));
		iceCol.start();
		Thread bossCol = new Thread(new col(boss));
		bossCol.start();
		boss.x = 9999;
	}

	@Override
	public void setting() {
		setTitle("¸ÞÀÌÇÃ Å×½ºÆ®");
		setSize(1290, 759);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setContentPane(laBackground);
	}

	@Override
	public void batch() {
		add(player);
		add(mushroom);
		add(stone);
		add(barlog);
		add(block);
		add(bar);
		add(bar2);
		add(gamePoint);

	}

	@Override
	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.moveRight1();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.moveLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					player.attack();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.moveJump();
				} else if (player.mp >= 10) {
					if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
						skillShot = new Skill(player, enemy);
						add(skillShot);
						player.skilshot();
						System.out.println("MP : " + player.mp + " ³²¾Ò½À´Ï´Ù.");

					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						player.isJump = false;
					}

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.isRight = false;
					player.isMove = false;

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false;
					player.isMove = false;
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					player.isAttack = false;
				}
			}
		});

	}

	// Ãæµ¹ Å¬·¡½º
	class col extends Thread {
		Enemy enemy;

		public col(Enemy enemy) {
			this.enemy = enemy;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
					if (enemy == boss && enemy.hp < 380) {
						enemy.setIcon(new ImageIcon("image/ÀÚÄñ°³ÇÇ.gif"));
						enemy.y = enemy.y + 30;
						enemy.x = enemy.x + 50;
					}
					// º¸½º
					if (isBossStart == true) {
		                  if (deadEnemy[2] > 50) {   // ¼³Á¤ÇÑ Á¡¼ö ÀÌ»óÀÌ¸é º¸½º¸Ê ÀÔÀå
		                     isBoss = true;
		                  }

		                  if (isBoss == true) {

		                      //int result = JOptionPane.showConfirmDialog(null, "º¸½º·ëÀÔÀå!", "¾È³»¸Þ¼¼Áö", JOptionPane.OK_OPTION);
		                     Thread.sleep(3100);
		                     laBackground.setIcon(new ImageIcon("image/ÀÚÄñÀÇÁ¦´Ü.png"));
		                     
		                     mushroom.x = 99999;
		                     stone.x = 99999;
		                     block.x = 99999;
		                     barlog.x = 99999;
		                     c.remove(block);
		                     c.remove(stone);
		                     c.remove(mushroom);
		                     c.remove(barlog);

		                     add(boss);

		                     boss.x = 400;

		                     isBossStart = false;
		                     isBoss = false;
		                     player.y = 500;
		                     System.out.println("½ÇÇàµÊ??");
		                  }
		               } //

					if (player.hp == 100) {
						bar.setIcon(icHp100);
					} else if (player.hp < 100 && player.hp >= 90) {
						bar.setIcon(icHp90);
					} else if (player.hp < 90 && player.hp >= 80) {
						bar.setIcon(icHp80);
					} else if (player.hp < 80 && player.hp >= 70) {
						bar.setIcon(icHp70);
					} else if (player.hp < 70 && player.hp >= 60) {
						bar.setIcon(icHp60);
					} else if (player.hp < 60 && player.hp >= 50) {
						bar.setIcon(icHp50);
					} else if (player.hp < 50 && player.hp >= 40) {
						bar.setIcon(icHp40);
					} else if (player.hp < 40 && player.hp >= 30) {
						bar.setIcon(icHp30);
					} else if (player.hp < 3 && player.hp >= 20) {
						bar.setIcon(icHp20);
					} else if (player.hp < 20 && player.hp >= 10) {
						bar.setIcon(icHp10);
					}

					if (player.mp == 50) {
						bar2.setIcon(icMp50);
					} else if (player.mp < 50 && player.mp >= 40) {
						bar2.setIcon(icMp40);
					} else if (player.mp < 40 && player.mp >= 30) {
						bar2.setIcon(icMp30);
					} else if (player.mp < 30 && player.mp >= 20) {
						bar2.setIcon(icMp20);
					} else if (player.mp < 20 && player.mp >= 10) {
						bar2.setIcon(icMp10);
					} else if (player.mp < 10 && player.mp >= 0) {
						bar2.setIcon(icMp0);
					}

					gamePoint.setText("Point : " + MapleApp.deadEnemy[2]); // Á¡¼ö Ç¥½Ã

					// ÇÃ·¹ÀÌ¾î
					if (crash(player.x, player.y, enemy.x, enemy.y, player.width, player.height, enemy.width,
							enemy.height)) {
						System.out.println("Ãæµ¹ ¹ß»ý!");
						Thread.sleep(1500);
						player.hp = player.hp - 10;
						System.out.println("ÇÃ·¹ÀÌ¾î hp : " + player.hp + " ³²¾Ò½À´Ï´Ù.");

						if (player.hp <= 0) {
							player.dieDown();
							int result = JOptionPane.showConfirmDialog(null, "Á×¾ú³×... Á¡¼ö´Â" + MapleApp.deadEnemy[2] + "Á¡ ÀÔ´Ï´Ù.", "¾È³»¸Þ¼¼Áö", JOptionPane.OK_OPTION);
							if (result == JOptionPane.OK_OPTION) {
								System.exit(0);
							}
						}

					}
					// ±âº»°ø°Ý
					if (attackCrash(player.x, player.y, enemy.x, enemy.y, player.width, player.height, enemy.width,
							enemy.height)) {
						if (player.isAttack == true) {
							System.out.println("±âº»°ø°Ý ÀûÁß!");
							enemy.moveState = 0;
							Thread.sleep(1000);
							enemy.hp = enemy.hp - 10;
							System.out.println(" hp : " + enemy.hp);

							if (enemy.hp <= 0) {
								System.out.println(enemy.name + " Á×À½...");
								score(enemy.name); // Á¡¼ö °è»ê
								gamePoint.setText("Point : " + deadEnemy[2]); // Á¡¼ö Ç¥½Ã
								enemy.x = 999999;
								Thread.sleep(3000);
								if (enemy == mushroom) {
									enemy.x = 550;
									enemy.hp = 20;

								}

								if (enemy == stone) {
									enemy.x = 100;
									enemy.hp = 20;
								}
								if (enemy == barlog) {
									enemy.x = 200;
									enemy.hp = 20;
								}
								if (enemy == block) {
									enemy.x = 200;
									enemy.hp = 20;
								}
							}

						}

					}

//					

				} catch (Exception e) {
					e.getMessage();
				}
			}

		}
	}

	// Ãæµ¹ ÇÔ¼ö
	public boolean crash(int playerX, int playerY, int enemyX, int enemyY, int playerW, int playerH, int enemyW,
			int enemyH) {
		boolean check = false;
		if (Math.abs((playerX + (playerW / 2)) - (enemyX + enemyW / 2 + 20)) < (enemyW / 2 + playerW / 2 - 60)
				&& Math.abs((playerY + playerH / 2) - (enemyY + enemyH / 2)) < (enemyH / 2 + playerH / 2)) {
			check = true;
		} else {
			check = false;
		}
		return check;
	}
	// end Ãæµ¹

	// ¸ó½ºÅÍ °ø°Ý ÇÔ¼ö
	public boolean attackCrash(int playerX, int playerY, int enemyX, int enemyY, int playerW, int playerH, int enemyW,
			int enemyH) {
		boolean check = false;
		if (Math.abs((playerX + (playerW / 2)) - (enemyX + enemyW / 2)) < (enemyW / 2 + playerW / 2)
				&& Math.abs((playerY + playerH / 2) - (enemyY + enemyH / 2)) < (enemyH / 2 + playerH / 2)) {
			check = true;
		} else {
			check = false;
		}
		return check;
	} // ¸ó½ºÅÍ °ø°Ý ÇÔ¼ö END

	public static int[] score(String enemy) {

		if (enemy == "ÁÖÈ²¹ö¼¸") { // ¹ö¼¸ 50Á¡
			scoreMushNum += 50; // ¹ö¼¸ Á×À» ¶§¸¶´Ù 50Á¡ Áõ°¡
			deadEnemy[0]++; // ¹ö¼¸ ÀâÀº ¼ö °è»ê
			deadEnemy[2] += 50;
			System.out.println("¹ö¼¸ Á×À½");
			System.out.println(scoreMushNum);
		} else if (enemy == "½ºÅæ°ñ·½") { // °ñ·½ 30Á¡
			scoreStoneNum += 30; // °ñ·½ Á×À» ¶§¸¶´Ù 30Á¡ Áõ°¡
			deadEnemy[1]++; // °ñ·½ Á×Àº ¼ö °è»ê
			deadEnemy[2] += 30;
			System.out.println("°ñ·½ Á×À½");
			System.out.println(scoreStoneNum);
		} else if (enemy == "ÁÖ´Ï¾î¹ß·Ï") { // °ñ·½ 30Á¡
			scoreStoneNum += 40; // °ñ·½ Á×À» ¶§¸¶´Ù 30Á¡ Áõ°¡
			deadEnemy[1]++; // °ñ·½ Á×Àº ¼ö °è»ê
			deadEnemy[2] += 40;
			System.out.println("°ñ·½ Á×À½");
			System.out.println(scoreStoneNum);
		} else if (enemy == "ºí·Ï°ñ·½") { // °ñ·½ 30Á¡
			scoreStoneNum += 30; // °ñ·½ Á×À» ¶§¸¶´Ù 30Á¡ Áõ°¡
			deadEnemy[1]++; // °ñ·½ Á×Àº ¼ö °è»ê
			deadEnemy[2] += 30;
			System.out.println("ºí·Ï°ñ·½ Á×À½");
			System.out.println(scoreStoneNum);
		}
		return deadEnemy; // 0¹øÁö - ¹ö¼¸ Á×Àº ¼ö
	} // 1¹øÁö - °ñ·½ Á×Àº ¼ö ÃÑ ¸ó½ºÅÍ ÀâÀº ¼ö ¹ÝÈ¯

}
