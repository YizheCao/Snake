package 贪吃蛇;

import javax.swing.JFrame;

public class tanchishe {
	public static void main(String[] args){
		//初始化窗口
		JFrame frame = new JFrame("贪吃蛇小游戏");
		frame.setBounds(200, 20, 900, 700);//设置窗口的位置和长宽
		frame.setResizable(false);//设置不可改变大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
		
		//添加画布
		Panel panel = new Panel();
		frame.add(panel);
		
		//设置窗口可见性
		frame.setVisible(true);
	}
}
