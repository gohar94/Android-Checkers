package com.example.checkers;

public class CheckersPosition {
	public int row;
    public int col;
    
    public CheckersPosition() {
    	row = 0;
    	col = 0;
    }
    
    public CheckersPosition(int r, int c) {
    	row = r;
    	col = c;
    }
    
    public void position_parse(String st) {
      row = 8 - (st.charAt(1) - '0');
      col = st.charAt(0) - 'A';
      return;
  }
    
    
}
