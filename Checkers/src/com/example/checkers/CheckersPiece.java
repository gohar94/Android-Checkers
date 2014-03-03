package com.example.checkers;

public class CheckersPiece {
	public boolean crowned;
    public boolean captured;
    public boolean dark;
    public CheckersPosition position = new CheckersPosition();
    
    public static CheckersPosition[] push_back(CheckersPosition[] array, CheckersPosition push) {
    	int count = 0;
    	for (CheckersPosition x : array) {
    		count++;
    	}
    	CheckersPosition[] longer = new CheckersPosition[count + 1];
	    for (int i = 0; i < count; i++)
	        longer[i] = array[i];
	    longer[count] = push;
	    return longer;
	}
    
    public String toString() {
		String str = new String();
		if (this.crowned == false) {
            if (this.dark == false) {
                str = "w";
            } else {
            	str = "b";
            }
        } else {
            if (this.dark == false) {
            	str = "W";
            } else {
            	str = "B";
            }
        }
    	return str;
    	
    }
    
    public CheckersPiece() {
    	crowned = false;
        captured = false;
        dark = false;
        position.row = 0;
        position.col = 0;
    }
    
    public void piece_print() {
        if (this.crowned == false) {
            if (this.dark == false) {
                System.out.print("w");
            } else {
            	System.out.print("b");
            }
        } else {
            if (this.dark == false) {
            	System.out.print("W");
            } else {
            	System.out.print("B");
            }
        }
        return;
    }
    
    public CheckersPosition[]
    piece_positions_in_direction(int dir) {
        CheckersPiece cp1 = new CheckersPiece();
        CheckersPiece cp2 = new CheckersPiece();
        // cp.dark = false;
        CheckersPiece[] v_out = new CheckersPiece[0];
        CheckersPosition[] v_out_pos = new CheckersPosition[0];
        boolean dark = this.dark;
        boolean crowned = this.crowned;
        boolean pass = false;
        
        if (crowned == true) {
            pass = true;
        } else {
            if (dir == 0 && dark == true) {
                pass = true;
            } else if (dir == 1 && dark == true) {
                pass = true;
            } else if (dir == 2 && dark == false) {
                pass = true;
            } else if (dir == 3 && dark == false) {
                pass = true;
            }
        }
        
        if (pass == true) {
            if (dir == 0) {
                // north east
                cp1.position.row = this.position.row - 1;
                cp1.position.col = this.position.col + 1;
                cp2.position.row = this.position.row - 1 - 1;
                cp2.position.col = this.position.col + 1 + 1;
            } else if (dir == 1) {
                // north west
                cp1.position.row = this.position.row - 1;
                cp1.position.col = this.position.col - 1;
                cp2.position.row = this.position.row - 1 - 1;
                cp2.position.col = this.position.col - 1 - 1;
            } else if (dir == 2) {
                // south west
                cp1.position.row = this.position.row + 1;
                cp1.position.col = this.position.col - 1;
                cp2.position.row = this.position.row + 1 + 1;
                cp2.position.col = this.position.col - 1 - 1;
            } else if (dir == 3) {
                // south east
                cp1.position.row = this.position.row + 1;
                cp1.position.col = this.position.col + 1;
                cp2.position.row = this.position.row + 1 + 1;
                cp2.position.col = this.position.col + 1 + 1;
            }
        } else {
            v_out = v_out;
        }
            
        // are they within board?
        if (cp1.position.row <= 7 && cp1.position.col <= 7) {
            if (cp1.position.row >= 0 && cp1.position.col >= 0) {
                v_out_pos = push_back(v_out_pos, cp1.position);
            }
        }
        
        if (cp2.position.row <= 7 && cp2.position.col <= 7) {
            if (cp2.position.row >= 0 && cp2.position.col >= 0) {
            	v_out_pos = push_back(v_out_pos, cp2.position);
            }
        }
        return v_out_pos;
    }
}

