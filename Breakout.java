import java.awt.BasicStroke;
import java.awt.Canvas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Breakout extends Canvas implements ActionListener	
{	
		
	    // these instance variables keep track of the mouse position
	    // you can refer to them to determine the mouse position.
		private int mousexpos;  
		private int mouseypos;
		// add other instance variables as needed here
		boolean won =false;
		boolean lost = false;
		boolean notstarted = true;
		int xpos = this.mousexpos+360;
		int ypos = 550;
		int xspeed = 0;
		int yspeed = 0;
		int lives = 3;
		int score = 0;
		private static ArrayList<int[]> bricks = new ArrayList<int[]>();
		public Breakout() 
		{
			// this is the constructor for the DrawingSwing class
			DrawingAdapter adapter = new DrawingAdapter();
			
			// Below we configure the mouse and start the timer to create animations
			addMouseListener(adapter);
			addMouseMotionListener(adapter);
			Timer mytime = new Timer(40,(ActionListener) this);
			mytime.start();
		}
		
		public static void main(String[] args) 
		{
			
			// below we create the screen.
			JFrame frame = new JFrame("Breakout");
	        Breakout canvas = new Breakout();
	        canvas.setSize(800, 800);

	        JButton restartButton = new JButton("Restart");
	        restartButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.resetGame();
					canvas.repaint();
					canvas.requestFocus();
				}
	        });

	        JPanel panel = new JPanel();
	        panel.setLayout(new java.awt.BorderLayout());
	        panel.add(canvas, java.awt.BorderLayout.CENTER);
	        panel.add(restartButton, java.awt.BorderLayout.SOUTH);

	        frame.add(panel);
	        frame.getContentPane().setBackground(Color.lightGray);
	        frame.pack();
	        frame.setVisible(true);
	        frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        canvas.requestFocus();
	        
	        
	        for(int i=0;i<800;i+=100) 
	        {
		    	for(int j=40;j<200;j+=35) 
		    	{
		    		int[] brick = {i,j};
		    		bricks.add(brick);	
		    	}
		 	}	
		}

			// add any other initialization code here

		public void resetGame()
		{
			won = false;
			lost = false;
			notstarted = true;

			xpos = 360;
			ypos = 550;
			xspeed = 0;
			yspeed = 0;

			lives = 3;
			score = 0;

			bricks.clear();
			for(int i=0;i<800;i+=100) 
	        {
		    	for(int j=40;j<200;j+=35) 
		    	{
		    		int[] brick = {i,j};
		    		bricks.add(brick);	
		    	}
		 	}
		}
		
		private void drawCenteredText(Graphics2D g2, int width, int y, String text) 
		{
			int tw = g2.getFontMetrics().stringWidth(text);
			g2.drawString(text, (width - tw) / 2, y);
		}

		private void drawOverlayScreen(Graphics2D g2, int W, int H, String title, String subtitle) 
		{
			g2.setColor(new Color(0, 0, 0, 90));
			g2.fillRect(0, 0, W, H);

			int cardW = 420;
			int cardH = 200;
			int cx = (W - cardW) / 2;
			int cy = (H - cardH) / 2;

			g2.setColor(new Color(245, 245, 245));
			g2.fillRoundRect(cx, cy, cardW, cardH, 22, 22);

			g2.setColor(new Color(120, 120, 120));
			g2.setStroke(new BasicStroke(2));
			g2.drawRoundRect(cx, cy, cardW, cardH, 22, 22);

			g2.setColor(Color.DARK_GRAY);
			g2.setFont(new Font("SansSerif", Font.BOLD, 40));
			drawCenteredText(g2, W, cy + 78, title);

			g2.setFont(new Font("SansSerif", Font.PLAIN, 22));
			drawCenteredText(g2, W, cy + 125, subtitle);
		}
		
		public void paint(Graphics g) 
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			final int W = 800;
			final int H = 800;

			g2.setColor(new Color(235, 235, 235));
			g2.fillRect(0, 0, W, H);

			int hudH = 50;
			g2.setColor(new Color(210, 210, 210));
			g2.fillRect(0, 0, W, hudH);

			g2.setColor(Color.DARK_GRAY);
			g2.setFont(new Font("SansSerif", Font.BOLD, 18));
			g2.drawString("Score: " + score, 15, 32);

			String livesText = "Lives: " + lives;
			int livesWidth = g2.getFontMetrics().stringWidth(livesText);
			g2.drawString(livesText, W - livesWidth - 15, 32);

			g2.setColor(new Color(190, 190, 190));
			g2.drawLine(0, hudH, W, hudH);
			
			if(!won && !lost)
			{
	    		if(ypos-10 > 690+10-1)
	    		{
	    			xpos = 360;
	    			xspeed = 0;
	    			yspeed = 0;
	    			ypos = 560;
	    			lives--;
	    			notstarted=true;
	    		
	    			    
	    		}
			    xpos+=xspeed;
			    ypos+=yspeed;	
			    ArrayList<int[]> toRemove = new ArrayList<int[]>();
			    for(int i = 0; i< bricks.size(); i++) 
			    {
			    	int leftEdge = bricks.get(i)[0];
			    	int topEdge = bricks.get(i)[1];
			    	int rightEdge = bricks.get(i)[0]+95-1;
			    	int bottomEdge = bricks.get(i)[1]+30-1;

			    	if(xpos+10>=leftEdge&&xpos-10<=rightEdge&&ypos-10<=bottomEdge&&ypos+10>=topEdge)
			    	{
			    		toRemove.add(bricks.get(i));
			    	}
			    }


				for(int i = 0; i < toRemove.size(); i++)
				{
					bricks.remove(toRemove.get(i));
					score++;
					yspeed = -yspeed;
				}

				for(int i=0;i<bricks.size();i++) 
		    	{
		    		int x = bricks.get(i)[0];
		    		int y = bricks.get(i)[1];
		    		
		    		g2.setColor(new Color(120, 120, 120));
		    		g2.fillRect(x,y,95,30);
		    		
		    		g2.setColor(new Color(70, 70, 70));
		    		g2.setStroke(new BasicStroke(2));
		    		g2.drawRect(x,y,95,30);
		    	}

		    		
				g2.setColor(new Color(60, 60, 60));
		    	g2.fillOval(xpos, ypos,20,20);
		    	if(xpos>=790||xpos<=0) 
		    	{
		    		xspeed = -xspeed;
		    	}

		    	if(ypos>=760||ypos<=0) 
		    	{
		    		yspeed = -yspeed;
		    	}

		    	if(intersects()) 
		    	{
			    	xspeed = xspeed + 2*yspeed*(xpos+10-this.mousexpos-130/2)/130;
			    	yspeed = -yspeed;
			    	if(xpos<0) 
			    	{
			    		xpos=0;
			    	}
			    	if(xpos>800) 
			    	{
			    		xpos=800;
			    	}
		    	}
		    	
		    	
				g2.setColor(new Color(80, 80, 80));
		    	g2.fillRect(mousexpos,690,130,10);

		    	if (notstarted) 
		    	{
					g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
					g2.setColor(new Color(90, 90, 90));
					drawCenteredText(g2, W, 520, "Click to start");
		    	}
		    	
		    }
			if(bricks.size()==0)
		    {
				won = true;
				drawOverlayScreen(g2, W, H, "You Win!", "Score: " + score);
		    }
			else if(lives==0)
			{
				lost = true;
				drawOverlayScreen(g2, W, H, "Game Over", "Score: " + score);
			}
		}
		    

		public boolean intersects() 
	    {
	    	if(xpos+10>=this.mousexpos&&xpos-10<=this.mousexpos+130-1&&ypos+10>=690)
	    	{
	    		return true;
	    	}
			return false;	 		    	
	    }
	    	
	    	
	    	// In this section you should
	    	// update the positions of your objects
	    	// and draw the screen.
	    
	    
	   
	   private class DrawingAdapter extends MouseAdapter 
	   {
	    	
	    	@Override
	    	public void mouseMoved(MouseEvent e) 
	    	{
	    		mousexpos = e.getX();	
	    	}
	    	
	    	@Override
	    	public void mouseClicked(MouseEvent e) 
	    	{
	    			if (notstarted && !lost && !won) 
	    			{
	    			xspeed = 10;
	    			yspeed = 10;
	    			notstarted=false;
	    			}
	    	}
	    	
	    	

	    }

	    
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			repaint();
			
		}
}   
