package ̰����;

import javax.swing.JFrame;

public class tanchishe {
	public static void main(String[] args){
		//��ʼ������
		JFrame frame = new JFrame("̰����С��Ϸ");
		frame.setBounds(200, 20, 900, 700);//���ô��ڵ�λ�úͳ���
		frame.setResizable(false);//���ò��ɸı��С
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ���
		
		//��ӻ���
		Panel panel = new Panel();
		frame.add(panel);
		
		//���ô��ڿɼ���
		frame.setVisible(true);
	}
}
