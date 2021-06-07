package FiveChaseUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import FiveChase.Board;
import FiveChase.GameEngine;
import FiveChase.InterfaceBoard;
import FiveChase.Stone;

public class GamePanel extends JPanel implements MouseListener{
	
	public static final int UNIT_SIZE = 30;
	public static final int PANEL_WIDTH = 630;
	public static final int PANEL_HEIGHT = 630;
	public static final int STAR_RADIUS = 5;
	public int moveNum = 1;

	int x, y;
	InterfaceBoard board = new Board(19, 19);
	GameEngine engine = new GameEngine(board);
	
	GamePanel() {
		this.setBounds(0, 0, 630, 630);
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setBackground(new Color(241, 206, 95));
		this.setFocusable(true);
		this.setLayout(null);
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawEmptyBoard(g);
		board = engine.getBoard();
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j< 19; j++) {
				Stone stone = board.getStone(i, j);
				if (stone == stone.BLACK) {
					drawStone(g, "black", i, j);
				} else if (stone == stone.WHITE) {
					drawStone(g, "white", i, j);
				}
			}
		}
		if (engine.checkResult(board) != null) {
			String result = engine.checkResult(board);
			drawResult(g, result);
		}
		//drawStone(g, "black", this.x, this.y);
		//drawStone(g, "white", 4, 5);
	}
	
	private void drawEmptyBoard(Graphics g) {
		//Draw grid
		g.setColor(new Color(11, 10, 8));
		for(int i = 0; i < 19; i++) {
			g.drawLine((int)((i + 1.5) * UNIT_SIZE), (int)(1.5 * UNIT_SIZE), 
					(int)((i + 1.5) * UNIT_SIZE), (int)(19.5 * UNIT_SIZE));
			g.drawLine((int)(1.5 * UNIT_SIZE), (int)((i + 1.5) * UNIT_SIZE), 
					(int)(19.5 * UNIT_SIZE), (int)((i + 1.5) * UNIT_SIZE));
		}
		g.setFont(new Font("Ink Free", Font.PLAIN, 20));
		FontMetrics metrics = getFontMetrics(g.getFont());
		
		//Draw letters
		for (int i = 1; i <= 20; i++) {
			String temp = Character.toString((char) (i + 64));
			//Skip Letter I
			if (temp.equals("I")) {
				continue;
			}
			else if (i < 9) {
				g.drawString(temp, (int)((i + 0.5) * UNIT_SIZE) - metrics.stringWidth(temp) / 2, (int)(UNIT_SIZE));
			} else {
				g.drawString(temp, (int)((i - 0.5) * UNIT_SIZE) - metrics.stringWidth(temp) / 2, (int)(UNIT_SIZE));
			}
		}
		
		// Draw Number 
		for(int i = 1; i <= 19; i++) {
			if (i <= 9) {
				g.drawString(String.valueOf(i), (int)(UNIT_SIZE / 2), (int)((i + 0.75) * UNIT_SIZE));
			} else {
				g.drawString(String.valueOf(i), (int)(UNIT_SIZE / 2 - metrics.stringWidth(String.valueOf(i)) / 4), (int)((i + 0.75) * UNIT_SIZE));
			}
		}
		
		//Draw Star Points
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g.fillOval((int)(UNIT_SIZE * (6 * i + 4.5)) - STAR_RADIUS, (int)(UNIT_SIZE * (6 * j + 4.5)) - STAR_RADIUS, STAR_RADIUS * 2, STAR_RADIUS * 2);
			}
		}
	}
	
	private void drawResult(Graphics g, String result) {
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.PLAIN, 70));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString(result + " wins", 145, 100);
	}
	
	private void drawStone(Graphics g, String color, int x, int y) {
		if (color.equals("black")) {
			g.setColor(Color.black);
		}
		else if (color.equals("white")){
			g.setColor(new Color(233, 246, 240));
		}
		g.fillOval((x + 1) * UNIT_SIZE, (y + 1) * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE); 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
			int currentX = e.getX();
			int currentY = e.getY();
			int currentMoveNum = engine.moveHistory.size() + 1;
			System.out.println("Current Move: " + currentMoveNum);
			for (int i = 0; i < 19; i++) {
				if (UNIT_SIZE * (i + 1) < currentX && UNIT_SIZE * (i + 2) > currentX) {
					x = i;
				} 
				if (UNIT_SIZE * (i + 1) < currentY && UNIT_SIZE * (i + 2) > currentY) {
					y = i;
				}
			}
			if (currentMoveNum % 2 == 1) {
				engine.placeStone(Stone.BLACK, x, y);
			} else {
				engine.placeStone(Stone.WHITE, x, y);
			}
			repaint();
			engine.checkResult(board);
			//drawStone(g, "black", x, y);
			//System.out.println(currentX);
			//System.out.println(currentY);
			
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
