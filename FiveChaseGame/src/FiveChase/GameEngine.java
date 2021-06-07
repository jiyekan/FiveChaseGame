package FiveChase;

import java.util.LinkedList;

public class GameEngine {
	private InterfaceBoard board;
	private class ReadOnlyBoard implements InterfaceBoard{
        private InterfaceBoard board;
        public ReadOnlyBoard (InterfaceBoard board) {
            this.board = board;
        }
        @Override
        public int getDimensionX() {
            return board.getDimensionX();
        }
        @Override
        public int getDimensionY() {
            return board.getDimensionY();
        }
        @Override
        public void setStone(Stone stone, int x, int y) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void resetBoard() {
            throw new UnsupportedOperationException();
        }
        @Override
        public Stone getStone(int x, int y) {
            return board.getStone(x, y);
        }
        @Override
        public InterfaceBoard deepCopy() {
            return board.deepCopy();
        }
    }
	
	public enum GameResult {BLACK, WHITE, DRAW};
    public LinkedList<Move> moveHistory;
    private LinkedList<InterfaceBoard> boardHistory;
    public GameEngine(InterfaceBoard board) {
        if (board == null) {
            throw new IllegalArgumentException();
        }
        this.board = board;
        this.moveHistory = new LinkedList<>();
        this.boardHistory = new LinkedList<>();
    }
    
    public Stone getStone(int x, int y) {
        return board.getStone(x, y);
    }
    
    public InterfaceBoard getBoard() {
        return new ReadOnlyBoard(board);
    }
    
    public boolean placeStone (Stone stone, int x, int y) {
        if (!isLegalMove(stone, x, y)) {
            return false;
        }
        board.setStone(stone, x, y);
        Move lastMove = new Move(stone, x, y);
        moveHistory.add(lastMove);
        boardHistory.add(board.deepCopy());
        return true;
    }

	private boolean isLegalMove(Stone stone, int x, int y) {
		if (board.getStone(x, y) != Stone.EMPTY) {
            return false;
        }
		return true;
	}
	
	public boolean isEndGame() {
        throw new UnsupportedOperationException();
	}
	 
	public GameResult getResult () {
        throw new UnsupportedOperationException();
	}

    public String checkResult(InterfaceBoard curBoard) {
    	for (int i = 0; i < 14; i++) {
    		for (int j = 0; j < 14; j++) {
    			if ((curBoard.getStone(i, j) == Stone.WHITE) 
    				&& (curBoard.getStone(i, j + 1) == Stone.WHITE)
    				&& (curBoard.getStone(i, j + 2) == Stone.WHITE)
    				&& (curBoard.getStone(i, j + 3) == Stone.WHITE)
					&& (curBoard.getStone(i, j + 4) == Stone.WHITE))
    				return ("White");
    			if ((curBoard.getStone(i, j) == Stone.WHITE) 
        			&& (curBoard.getStone(i + 1, j) == Stone.WHITE)
        			&& (curBoard.getStone(i + 2, j) == Stone.WHITE)
        			&& (curBoard.getStone(i + 3, j) == Stone.WHITE)
    				&& (curBoard.getStone(i + 4, j) == Stone.WHITE)) 
       				return ("White");
    			if ((curBoard.getStone(i, j) == Stone.WHITE) 
            		&& (curBoard.getStone(i + 1, j + 1) == Stone.WHITE)
            		&& (curBoard.getStone(i + 2, j + 2) == Stone.WHITE)
            		&& (curBoard.getStone(i + 3, j + 3) == Stone.WHITE)
        			&& (curBoard.getStone(i + 4, j + 4) == Stone.WHITE)) 
           			return ("White");
    			if ((curBoard.getStone(i, j) == Stone.BLACK) 
        			&& (curBoard.getStone(i, j + 1) == Stone.BLACK)
        			&& (curBoard.getStone(i, j + 2) == Stone.BLACK)
        			&& (curBoard.getStone(i, j + 3) == Stone.BLACK)
    				&& (curBoard.getStone(i, j + 4) == Stone.BLACK))
        			return ("Black");
        		if ((curBoard.getStone(i, j) == Stone.BLACK) 
            		&& (curBoard.getStone(i + 1, j) == Stone.BLACK)
            		&& (curBoard.getStone(i + 2, j) == Stone.BLACK)
            		&& (curBoard.getStone(i + 3, j) == Stone.BLACK)
        			&& (curBoard.getStone(i + 4, j) == Stone.BLACK)) 
        			return ("Black");
        		if ((curBoard.getStone(i, j) == Stone.BLACK) 
                	&& (curBoard.getStone(i + 1, j + 1) == Stone.BLACK)
                	&& (curBoard.getStone(i + 2, j + 2) == Stone.BLACK)
                	&& (curBoard.getStone(i + 3, j + 3) == Stone.BLACK)
            		&& (curBoard.getStone(i + 4, j + 4) == Stone.BLACK)) 
               		return ("Black");
    		}//End for j
    		for (int n = 4; n < 19; n++) {
        		if ((curBoard.getStone(i, n) == Stone.BLACK) 
       				&& (curBoard.getStone(i + 1, n - 1) == Stone.BLACK)
                    && (curBoard.getStone(i + 2, n - 2) == Stone.BLACK)
                    && (curBoard.getStone(i + 3, n - 3) == Stone.BLACK)
                   	&& (curBoard.getStone(i + 4, n - 4) == Stone.BLACK)) 
        			return ("Black");
        		if ((curBoard.getStone(i, n) == Stone.WHITE) 
          			&& (curBoard.getStone(i + 1, n - 1) == Stone.WHITE)
                    && (curBoard.getStone(i + 2, n - 2) == Stone.WHITE)
                    && (curBoard.getStone(i + 3, n - 3) == Stone.WHITE)
                    && (curBoard.getStone(i + 4, n - 4) == Stone.WHITE)) 
            		return ("White");
    		}//End for n
    	}//End entire for loop
    	return null;
    }
}
