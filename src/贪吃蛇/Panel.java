package ̰����;

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

//ʵ�ֽӿ�
public class Panel extends JPanel implements KeyListener, ActionListener {
	//��ʼ��ͼƬ
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon body = new ImageIcon("body.png");
	
	//�������ݽṹ
	int[] snakex = new int[850];
	int[] snakey = new int[850];
	int len = 3;//�ߵĳ��ȣ���ʼΪ3
	int score = 0;//����÷�
	String direction = "R";//������ͷ�ķ���L��R�ҡ�U�ϡ�D�£���ʼ����	
	boolean ifbegin = false;//����һ������ֵ�ı���ifbegin�ж��Ƿ��¿ո�ʼ��Ϸ
	boolean iflose = false;//����һ������ֵ�ı���iflose�ж��Ƿ���Ϸ����
	boolean ifwin = false;//����һ������ֵ�ı���ifwin�ж��Ƿ���Ϸʤ��
	Timer timer = new Timer(200, this);//����һ��timer�����¼�,����ʼ����this�����¼��������ĸ��¼�
	Random random = new Random();
	int foodx = random.nextInt(34) * 25 + 25;//�������ʳ���x����
	int foody = random.nextInt(24) * 25 + 75;//�������ʳ���y����
	
	//��ʼ����������
	public Panel(){
		this.setFocusable(true);//���ÿ��Ի�ý���
		this.addKeyListener(this);//��Ӽ��������¼�
		ini();
		timer.start();//timer����
	}
	
	//��ӻ���
	public void paint(Graphics g){
		super.paint(g);//����paint����
		//�������
		this.setBackground(Color.white);//���ñ���ɫΪ��ɫ
		title.paintIcon(this, g, 25, 11);//�������ͼƬ��������������꣬g�ǻ��ʣ�this�ǵ�ǰ����
		g.fillRect(25, 75, 850, 575);//�ڻ����ϻ�һ�����η���
		
		//����ͷ
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
		
		//������
		for(int i = 1; i < len; i++){
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		//�ж��Ƿ�ʼ��Ϸ
		if(!ifbegin){
			g.setColor(Color.green);
			g.setFont(new Font("����", Font.BOLD, 30));//Font��������Ϊ���塢���Σ��Ƿ�б�����壩���ִ�С
			g.drawString("���¿ո�ʼ������ͣ��Ϸ", 250, 350);
		}
		
		//�ж��Ƿ������Ϸ
		if(iflose){
			g.setColor(Color.red);
			g.setFont(new Font("����", Font.BOLD, 30));//Font��������Ϊ���塢���Σ��Ƿ�б�����壩���ִ�С
			g.drawString("You lose the game!", 250, 350);
			g.drawString("���ո����������", 250, 400);
		}

		//�ж��Ƿ������Ϸ
		if(ifwin){
			g.setColor(Color.red);
			g.setFont(new Font("����", Font.BOLD, 30));//Font��������Ϊ���塢���Σ��Ƿ�б�����壩���ִ�С
			g.drawString("You win the game!", 250, 350);
			g.drawString("���ո����������", 250, 400);
		}
		
		//��ʾ�÷ֺͳ���
		g.setColor(Color.black);
		g.setFont(new Font("����", Font.PLAIN, 20));
		g.drawString("�÷֣�" + score, 750, 35);
		g.drawString("���ȣ�" + len, 750, 55);
		
		//����ʳ��
		food.paintIcon(this, g, foodx, foody);
	}
	
	//��ʼ������ͳ���
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

	//ʵ�ּ�����Ӧ
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//�жϰ��°��µ���ʲô��
		if(key == KeyEvent.VK_SPACE){
			if(iflose)
				ini();//���³�ʼ��
			else if(ifwin)
				ini();
			else
				ifbegin = !ifbegin;//����һ�οո�͸ı�һ��״̬
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
	
	//ʵ�ֶ�timer�¼��ļ���
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		//�¼�����֮���ٴ�����timer�¼�
		timer.start();		
		//�ж��Ƿ�ʼ��Ϸ��δ�����Ϸ����ʼ���ƶ�����
		if(ifbegin && iflose == false && ifwin == false){
			//�ƶ�����
			for(int i = len; i > 0; i--){
				snakex[i] = snakex[i - 1];
				snakey[i] = snakey[i - 1];
			}
			//�ƶ�ͷ
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
			
			//�ж��Ƿ�Ե�ʳ��
			if(snakex[0] == foodx && snakey[0] == foody){
				len++;
				score = score + 10;
				foodx = random.nextInt(33) * 25 + 25;//�������ʳ���x����
				foody = random.nextInt(24) * 25 + 75;//�������ʳ���y����
			}

			if(score >= 50)
				ifwin = true;
			
			//�ж��Ƿ�Ե��Լ�������ߴ���ǽ�ڣ���������������
			for(int i = 1; i < len; i++){
				if((snakex[i] == snakex[0] && snakey[i] == snakey[0]) || snakex[0] == 0 || snakex[0] == 875 || snakey[0] == 50 || snakey[0] == 650){
					iflose = true;
				}
			}
		}		
		//�ػ�
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {	
	}
	@Override
	public void keyTyped(KeyEvent e) {		
	}
}
