package 贪吃蛇;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//实现接口
public class Panel extends JPanel implements KeyListener, ActionListener {
	//初始化图片
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon body = new ImageIcon("body.png");
	
	//定义数据结构
	int[] snakex = new int[850];
	int[] snakey = new int[850];
	int len = 3;//蛇的长度，初始为3
	int score = 0;//定义得分
	String direction = "R";//定义蛇头的方向，L左、R右、U上、D下，初始向右	
	boolean ifbegin = false;//定义一个布尔值的变量ifbegin判断是否按下空格开始游戏
	boolean iflose = false;//定义一个布尔值的变量iflose判断是否游戏结束
	boolean ifwin = false;//定义一个布尔值的变量ifwin判断是否游戏胜利
	Timer timer = new Timer(200, this);//创建一个timer控制事件,并初始化，this表明事件到了找哪个事件
	Random random = new Random();
	int foodx = random.nextInt(34) * 25 + 25;//随机产生食物的x坐标
	int foody = random.nextInt(24) * 25 + 75;//随机产生食物的y坐标
	
	//初始化析构函数
	public Panel(){
		this.setFocusable(true);//设置可以获得焦点
		this.addKeyListener(this);//添加监听键盘事件
		ini();
		timer.start();//timer启动
	}
	
	//添加画笔
	public void paint(Graphics g){
		super.paint(g);//调用paint函数
		//画出框架
		this.setBackground(Color.white);//设置背景色为黑色
		title.paintIcon(this, g, 25, 11);//画标题的图片，最后两个是坐标，g是画笔，this是当前对象
		g.fillRect(25, 75, 850, 575);//在画布上画一个矩形方框
		
		//画蛇头
		if(direction.equals("R")){
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}
		else if(direction.equals("L")){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}
		else if(direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}
		else if(direction.equals("D")){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		
		//画蛇身
		for(int i = 1; i < len; i++){
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		//判断是否开始游戏
		if(!ifbegin){
			g.setColor(Color.green);
			g.setFont(new Font("宋体", Font.BOLD, 30));//Font属性依次为字体、字形（是否斜体或粗体）、字大小
			g.drawString("按下空格开始或者暂停游戏", 250, 350);
		}
		
		//判断是否结束游戏
		if(iflose){
			g.setColor(Color.red);
			g.setFont(new Font("宋体", Font.BOLD, 30));//Font属性依次为字体、字形（是否斜体或粗体）、字大小
			g.drawString("You lose the game!", 250, 350);
			g.drawString("按空格键重新启动", 250, 400);
		}

		//判断是否结束游戏
		if(ifwin){
			g.setColor(Color.red);
			g.setFont(new Font("宋体", Font.BOLD, 30));//Font属性依次为字体、字形（是否斜体或粗体）、字大小
			g.drawString("You win the game!", 250, 350);
			g.drawString("按空格键重新启动", 250, 400);
		}
		
		//显示得分和长度
		g.setColor(Color.black);
		g.setFont(new Font("宋体", Font.PLAIN, 20));
		g.drawString("得分：" + score, 750, 35);
		g.drawString("长度：" + len, 750, 55);
		
		//画出食物
		food.paintIcon(this, g, foodx, foody);
	}
	
	//初始化坐标和长度
	public void ini(){
		iflose = false;
		ifbegin = false;
		ifwin = false;
		len = 3;
		score = 0;
		direction = "R";
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
	}

	//实现键盘响应
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//判断按下按下的是什么键
		if(key == KeyEvent.VK_SPACE){
			if(iflose)
				ini();//重新初始化
			else if(ifwin)
				ini();
			else
				ifbegin = !ifbegin;//按下一次空格就改变一次状态
		}
		else if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && direction != "D"){
			direction = "U";
		}
		else if((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && direction != "U"){
			direction = "D";
		}
		else if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && direction != "R"){
			direction = "L";
		}
		else if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && direction != "L"){
			direction = "R";
		}
	}
	
	//实现对timer事件的监听
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		//事件到了之后，再次启动timer事件
		timer.start();		
		//判断是否开始游戏且未输掉游戏，开始则移动数据
		if(ifbegin && iflose == false && ifwin == false){
			//移动身体
			for(int i = len; i > 0; i--){
				snakex[i] = snakex[i - 1];
				snakey[i] = snakey[i - 1];
			}
			//移动头
			if(direction.equals("R")){
				snakex[0] = snakex[0] + 25;
			}
			else if(direction.equals("L")){
				snakex[0] = snakex[0] - 25;
			}
			else if(direction.equals("U")){
				snakey[0] = snakey[0] - 25;
			}
			else if(direction.equals("D")){
				snakey[0] = snakey[0] + 25;
			}
			
			//判断是否吃到食物
			if(snakex[0] == foodx && snakey[0] == foody){
				len++;
				score = score + 10;
				foodx = random.nextInt(33) * 25 + 25;//随机产生食物的x坐标
				foody = random.nextInt(24) * 25 + 75;//随机产生食物的y坐标
			}

			if(score >= 50)
				ifwin = true;
			
			//判断是否吃到自己身体或者触碰墙壁，如果有则输掉比赛
			for(int i = 1; i < len; i++){
				if((snakex[i] == snakex[0] && snakey[i] == snakey[0]) || snakex[0] == 0 || snakex[0] == 875 || snakey[0] == 50 || snakey[0] == 650){
					iflose = true;
				}
			}
		}		
		//重绘
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {	
	}
	@Override
	public void keyTyped(KeyEvent e) {		
	}
}
