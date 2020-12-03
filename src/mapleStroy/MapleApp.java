package mapleStroy;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapleApp extends JFrame implements Initable{
	
	//컨텍스트 저장(안쓰더라도 할것!)
	private MapleApp bubbleApp=this;
	// 태그 - 실행되는 파일 구분하기
	private static final String TAG="BubbleApp : ";
	
	private JLabel laBackground;
	private MaplePlayer player;
	private ImageIcon imgBg;
	
	public MapleApp() {
		init();
		setting();
		batch();
		listener();
		
		setVisible(true);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		imgBg=new ImageIcon("images/imgBackGround.png");
		laBackground=new JLabel(imgBg);
		player=new MaplePlayer();
	}
	@Override
	public void setting() {
		// TODO Auto-generated method stub
		setTitle("버블버블");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(imgBg.getIconWidth(),imgBg.getIconHeight());
		setLayout(null);
		setContentPane(laBackground);
	}

	@Override
	public void batch() {
		// TODO Auto-generated method stub
		add(player);
	}

	@Override
	public void listener() {
		// TODO Auto-generated method stub
		addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				player.moveRight();
			}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				player.moveLeft();
			}else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				//player.moveUp();
				player.moveJump();
			}
		}
		@Override
			public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				player.isRight=false;
			}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				player.isLeft=false;
			}else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				player.isJump=false;
			}
			}
		});
		
	}
	public static void main(String[] args) {
		new MapleApp();
	}

}
