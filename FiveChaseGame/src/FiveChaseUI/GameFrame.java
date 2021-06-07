package FiveChaseUI;

import javax.swing.JFrame;

import FiveChase.Board;

public class GameFrame extends JFrame{
	
	Board board;
	GamePanel gamePanel = new GamePanel();
	
	public GameFrame() {
		this.setSize(630, 630);
		this.setTitle("五子棋");
		this.setLayout(null);
		this.add(gamePanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
}
