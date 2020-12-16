package mapleGame;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LoginScr extends JFrame {

   private JPanel contentPane;
   private LoginScr loginScr=this;
   private JTextField tfUsername;
   private JButton btnLogin,button;
   private ImageIcon icon;
   JScrollPane scrollPane;
   
   
   public static void main(String[] args) {
      
   }


   public LoginScr(Loading loading) {
      icon=new ImageIcon("image/loginBG.png");
      
      JPanel background=new JPanel() {
         public void paintComponent(Graphics g) {
            g.drawImage(icon.getImage(), 0, 0, null);
            setOpaque(false);
            super.paintComponent(g);
         }
      };
      
      button=new JButton("로그인");
      button.setBounds(706, 438, 85, 36);
      tfUsername=new JTextField(20);
      tfUsername.setBounds(468, 446, 226, 21);
      background.setLayout(null);
      JLabel la1=new JLabel("ID : ");
      la1.setBounds(433, 438, 45, 36);
      background.add(la1);
      background.add(tfUsername);
      background.add(button);
      
      scrollPane=new JScrollPane(background);
      setContentPane(scrollPane);

      setTitle("LOGIN");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1280,720);

      initListener();
      setLocationRelativeTo(null);
      setVisible(true);
   }

   private void initListener() {
      
      button.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            String username=tfUsername.getText();
            if(username.equals("maple")) {
               int result = JOptionPane.showConfirmDialog(null, "로그인 완료", "안내메세지", JOptionPane.OK_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                       new MapleApp(loginScr);
                   loginScr.setVisible(false);
                    }
            }else {
               System.out.println("로그인 실패 : 아이디를 다시 입력하세요!");
            }
         }
      });
   }
}