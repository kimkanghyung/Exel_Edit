

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

//복사해서 쓸것
//Java5.0버전 이후 프레임 앞에 컨테이너가 기본으로 하나가 있다.
//따라서 컨테이너를 바꿔준다.
public class TestJframe extends JFrame implements ActionListener, ItemListener
{
//////////기본 클래스의 전역변수 선언하는 곳///////////

 
 
 Container cp;
 JRadioButton rb1, rb2, rb3;
 JCheckBox cb1, cb2, cb3;
 JTextArea ta;
 JButton btn;
 
////////////////////////////////////////////////////// 
 
 
 public TestJframe(String title)
 {
  super(title);
  cp=this.getContentPane();
  this.setDesign();
  
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setBounds(300,100,300,300);
  cp.setBackground(new Color(255, 255, 200));
  this.setVisible(true);
 }
 
 public void setDesign()
 {
  JPanel pTop=new JPanel();
  pTop.setBorder(new TitledBorder("좋아하는 색상을 선택하세요"));
  //pTop.setBackground(Color.orange);
  
  JPanel pBottom=new JPanel();
  pBottom.setBorder(new TitledBorder("취미를 선택하세요"));
  //pBottom.setBackground(Color.orange);
  
  
  cp.setLayout(new BorderLayout());
  
  ////////////////////////////////////상단의 체크박스
  ButtonGroup bg=new ButtonGroup();
  rb1= new JRadioButton("Red");
  rb2= new JRadioButton("Blue");
  rb3= new JRadioButton("Green");
  
  bg.add(rb1);
  bg.add(rb2);
  bg.add(rb3);
  
  rb1.addItemListener(this);
  rb2.addItemListener(this);
  rb3.addItemListener(this);
  
  pTop.add(rb1);
  pTop.add(rb2);
  pTop.add(rb3);
  
  cp.add("North", pTop);
  /////////////////////////////////////
  /////////////////////////////////////하단의 체크박스
  
  cb1=new JCheckBox("Study"); ////////체크박스 선택하게 할때 소스를 paste로 수정할때 변수명에 유의
  cb2=new JCheckBox("Game");
  cb3=new JCheckBox("Sleeping");
  btn=new JButton("입력");
  
  pBottom.add(cb1);
  pBottom.add(cb2);
  pBottom.add(cb3);
  pBottom.add(btn);
  
  btn.addActionListener(this);
  //cb2.addActionListener(this);
  //cb3.addActionListener(this);
  
  
  
  
  cp.add("South",pBottom);
  ///////////////////////////////////////////*/
  
  ta=new JTextArea();
  ta.append("AWT의 Checkbox 클래스를 \n 이용한 라디오형 체크박스와 \n 라디오버튼 구현 ");
  cp.add("Center",ta);
 
 }
 
 
 @Override
 public void itemStateChanged(ItemEvent e)
 {
  
  
  
  ///////////////////////버튼액션 구현(pTop 패널)
  Object ob1=e.getSource();
  if(ob1==rb1)
  {
   ta.setForeground(Color.red);
  }else if(ob1==rb2)
  {
   ta.setForeground(Color.blue);
  }else if(ob1==rb3)
  {
   ta.setForeground(Color.green);
  }
  
 }

 @Override
 public void actionPerformed(ActionEvent e)
 {
  /////////////버튼액션 구현(pBottom 패널)
  String msg="제 취미는";
  
  boolean sw=false; //하나도 선택되지 않은 경우
  if(cb1.isSelected())
  {
   sw=true;
   msg+=cb1.getText()+"   ";
  }
  if(cb2.isSelected())
  {
   sw=true;
   msg+=cb2.getText()+"   ";
  } 
  if(cb3.isSelected())
  {
   sw=true;
   msg+=cb3.getText()+"   ";
  }
  msg+="입니다";
  if(!sw)
  {
   msg="취미가 없습니다.";
  }
  ta.append(msg+"\n");
  

  
 }
 
 
//////////////////////메인 시작 ///////////////////////// 
 public static void main(String[] args)
 {
  new TestJframe("Radio SwingRadio");
 }

///////////////////메인 끝 //////////////////////////
 
 
}