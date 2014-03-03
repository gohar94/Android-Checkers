package com.example.checkers;

public class CheckersSimpleMove {
	    public CheckersPosition start = new CheckersPosition();
	    public CheckersPosition end = new CheckersPosition();
	    
	    public CheckersSimpleMove() {
	        start.row = 0;
	        start.col = 0;
	        end.row = 0;
	        end.col = 0;
	    }
	    
	    public CheckersSimpleMove(String st1, String st2) {
	        start.position_parse(st1);
	        end.position_parse(st2);
	    }
}

