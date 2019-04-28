package numberplace;

import java.util.HashMap;

public class Square {

	byte number;// 0 is unknown
	/*
	 * possibleNum shows numbers which could be written(1~9) if 1:true, 1 could be
	 * written this Square
	 */
	HashMap<Byte, Boolean> possibleNum = new HashMap<Byte, Boolean>();

	Square() {
		number = 0;
		for (byte i = 1; i <= 9; i++) {
			possibleNum.put(i, true);
		}
	}

	@Override
	public Square clone() {
		Square temp = new Square();
		try{
//			temp = (Square)super.clone();
			temp.number=this.number;
			for(byte i=1;i<=9;i++) {
				temp.possibleNum.put(i,this.possibleNum.get(i));
			}
		}catch(Exception e) {
		e.printStackTrace();
		}
		return temp;
	}

	public byte solveNumber() {
		byte num = 0;
		for (byte i = 1; i <= 9; i++) {
			if (possibleNum.get(i)) {// if i could be written
				if (num != 0)// if other number (not i) could be written
					return 0;
				num = i;
			}
		}
		return num;// single number could be written
	}
}
