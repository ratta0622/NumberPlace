package numberplace;

public class Field {

	Square board[][] = new Square[9][9];

	Field() {
		for (byte i = 0; i < 9; i++) {
			for (byte j = 0; j < 9; j++) {
				board[i][j] = new Square();
			}
		}
	}

	// display board
	public void dispBoard() {
		/*
		 * for (Square array[] : board) { for (Square square : array) {
		 * System.out.print(square.number); }
		 */
		for (byte i = 0; i < 9; i++) {
			for (byte j = 0; j < 9; j++) {
				System.out.print(board[i][j].number);
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

	public void writeNum(byte num, byte x, byte y) {
		board[x][y].number = num;
	}

	// search horizontal(x is const)
	public void searchHorizontal(byte x, byte y) {
		for (byte i = 0; i < 9; i++) {
			if (board[x][i].number != 0) {// number is written(not 0)
				board[x][y].possibleNum.put(board[x][i].number, false);// if number is written, true -> false
			}
		}
	}

	// search vertical(y is const)
	public void searchVertical(byte x, byte y) {
		for (byte i = 0; i < 9; i++) {
			if (board[i][y].number != 0) {// number is written(not 0)
				board[x][y].possibleNum.put(board[i][y].number, false);// if number is written, true -> false
			}
		}
	}

	// search block(3*3)
	public void searchBlock(byte x, byte y) {
		// (xtop,ytop) is left upper coordinate
		byte xtop = (byte) ((x / (byte) 3) * (byte) 3);
		byte ytop = (byte) ((y / (byte) 3) * (byte) 3);

		for (byte i = 0; i < 3; i++) {
			for (byte j = 0; j < 3; j++) {
				if (board[xtop + i][ytop + j].number != 0) {
					board[x][y].possibleNum.put(board[xtop + i][ytop + j].number, false);
				}
			}
		}
	}

	public void solve() {
		byte i, j;
		boolean flag;
		while (true) {
			flag = true;
			for (i = 0; i < 9; i++) {
				for (j = 0; j < 9; j++) {
					if (board[i][j].number == 0) {
						searchHorizontal(i, j);
						searchVertical(i, j);
						searchBlock(i, j);
						if (board[i][j].solveNumber() != 0) {// number is decided
							board[i][j].number = board[i][j].solveNumber();// fixed number is written
							flag = false;// new number is written
							System.out.println("("+i+","+j+")"+" "+board[i][j].number);
							dispBoard();
						}
					}
				}
			}
			if (flag) {// if no number is written finish loop
				System.out.println("finish");
				break;
			}
		}
	}

}