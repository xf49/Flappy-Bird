package FlappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JFrame;

public class FlappyBird implements ActionListener,MouseListener,KeyListener {

	public static FlappyBird flappybird;
	public final int WIDTH=1500,HEIGHT=1000;
	public Render render;
	public Rectangle bird;
	public ArrayList<Rectangle> column;
	public Random rand;
	public int ticks,yMotion,score;
	public boolean gameOver,started;
	
	public FlappyBird(){
		 
		JFrame jframe= new JFrame();
		Timer timer = new Timer(20,this);
		
		render = new Render();
		rand=new Random();
		
		jframe.add(render);
		jframe.setTitle("Flappy Bird by Xuming Feng");
		jframe.setSize(WIDTH,HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(true);
		
		bird=new Rectangle(WIDTH/2-10,HEIGHT/2-10,30,30);
		column = new ArrayList<Rectangle>();
		
		
		timer.start();
        for(int i=0;i<20;i++){
				addColumn(true);
		       }
		
	}
	
	public void addColumn(boolean start){
		int space=300;
		int width=100;
		int height=50+rand.nextInt(300);
		
		if(start){
		    column.add(new Rectangle(WIDTH+width+column.size()*300,HEIGHT-height-120,width,height));
		    column.add(new Rectangle(WIDTH+width+(column.size()-1)*300,0,width,HEIGHT-height-space));
		}else{
			column.add(new Rectangle(column.get(column.size()-1).x+600,HEIGHT-height-120,width,height));
			column.add(new Rectangle(column.get(column.size()-1).x,0,width,HEIGHT-height-space));
		}
	}
	
	public void paintColumn(Graphics g,Rectangle column){
	 
		g.setColor(Color.green.darker());
	    g.fillRect(column.x, column.y, column.width, column.height);
  
	}
	
	public void jump(){
		if(gameOver){
			bird=new Rectangle(WIDTH/2-10,HEIGHT/2-10,20,20);
			column.clear();
			yMotion=0;
			score=0;
			for(int i=0;i<10;i++){
				addColumn(true);
		       }
			  gameOver=false;
		}
		if(!started){
			started=true;
		}else if(!gameOver){
			if(yMotion>0){
				yMotion=0;
			}
			yMotion-=10;
		}
	}

	public void actionPerformed(ActionEvent e){
		ticks++;
		int speed=10;
		if(started){
		for(int i=0;i<column.size();i++){
			Rectangle col = column.get(i);
			col.x-=speed;
		}
		if(ticks%2==0&&yMotion<=15){
			yMotion+=2;
		}
		
		for(int i=0;i<column.size();i++){
			Rectangle col=column.get(i);
			if(col.x+col.width<0){
				column.remove(col);
				if(col.y==0){
					addColumn(false);
				}
			}
		}
		bird.y+=yMotion;
		
		for(Rectangle col:column){
			if(col.y==0&&bird.x+bird.width/2>col.x+col.width/2-10&&bird.x+bird.width/2<col.x+col.width/2+10){
				score++;
			}
			if(col.intersects(bird)){
				gameOver=true;
				if(bird.x<=col.x){
				bird.x=col.x-bird.width;
				}else{
					if(col.y!=0){
						bird.y=col.y-bird.height;
					}else if(bird.y<col.height){
						bird.y=col.height;
					}
				}
			}
		}
		if(bird.y>HEIGHT-120||bird.y<0){
			
			gameOver=true;
		}
		if(bird.y+yMotion>=HEIGHT-120){
			bird.y=HEIGHT-120-bird.height;
		}
		}
		render.repaint();
	}


	public void repaint(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.orange);
		g.fillRect(0, HEIGHT-150, WIDTH, 150);
		
		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		
		g.setColor(Color.green);
		g.fillRect(0, HEIGHT-150, WIDTH, 20);
		
		for(Rectangle col : column){
			paintColumn(g,col);
		}
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial",1,100));
		
		if(!started){
			g.drawString("Hello ! Xu Ming ",WIDTH/2-300, HEIGHT/2-200);
			g.drawString("Click to start game ! ",WIDTH/2-300, HEIGHT/2-50);
		}
		
		if(gameOver){
			g.drawString("Game Over !!! ",WIDTH/2-300, HEIGHT/2-100);
			g.drawString("Go home and Sleep now !!! ", WIDTH/2-600, HEIGHT/2);
			g.drawString(String.valueOf(score), WIDTH/2-25,100);
		}
		
		if(!gameOver&&started){
			g.drawString(String.valueOf(score), WIDTH/2-25,100);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		jump();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()==KeyEvent.VK_SPACE){ 
			jump();
		}
		
	}
	

	
	public static void main(String args[]){
	flappybird = new FlappyBird();
    }
}

