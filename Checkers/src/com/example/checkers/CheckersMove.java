package com.example.checkers;


public class CheckersMove {
	    public CheckersSimpleMove[] simple_moves = new CheckersSimpleMove[0];
	    
	    // need to show this function to sir
	    
	    public static String[] push_back(String[] array, String push) {
		    String[] longer = new String[array.length + 1];
		    for (int i = 0; i < array.length; i++)
		        longer[i] = array[i];
		    longer[array.length] = push;
		    return longer;
		}
	    
	    public static CheckersSimpleMove[] push_back(CheckersSimpleMove[] array, CheckersSimpleMove push) {
	    	int count = 0;
	    	for (CheckersSimpleMove x : array) {
	    		count++;
	    	}
	    	CheckersSimpleMove[] longer = new CheckersSimpleMove[count + 1];
		    for (int i = 0; i < count; i++)
		        longer[i] = array[i];
		    longer[count] = push;
		    return longer;
		}
	    
	    // --
	    public String[] string_split(String st1, char c) {
	        String st_temp = "";
	        String st_blank = "";
	        String[] v = new String[0];
	        for (int i = 0; i < st1.length(); i++) {
	            if (st1.charAt(i) != c) {
	                st_temp = st_temp + st1.charAt(i);
	            } else {
	                v = push_back(v, st_temp);
	                st_temp = st_blank;
	            }
	        }
	        v = push_back(v, st_temp);
	        return v;
	    }
	    
	    void move_parse(String st2) {
	        // splits the input string
	        char splitter = '-';
	        String[] vmove = new String[0];
	        
	        // stores the splitted strings in vmove vector
	        vmove = string_split(st2, splitter);
	        
	        int count = 0;
	        for (String s : vmove) {
	            count = count + 1;
	        }
	        
	        CheckersPosition[] cp = new CheckersPosition[count];
	        for (int i = 0; i < count; i++) {
	        	cp[i] = new CheckersPosition();
	        }
	        int a = 0;
	        for (CheckersPosition c : cp) {
	            c.position_parse(vmove[a]);
	            a = a + 1;
	        }
	        a = 0;
	        
	        int count_sm = count - 1;
	        
	        CheckersSimpleMove[] sm = new CheckersSimpleMove[count_sm];
	        for (int i = 0; i < count_sm; i++) {
	        	sm[i] = new CheckersSimpleMove();
	        }
	        for (CheckersSimpleMove d : sm) {
	        	d.start.row = cp[a].row;
	            d.start.col = cp[a].col;
	            d.end.row = cp[a+1].row;
	            d.end.col = cp[a+1].col;
	            a = a + 1;
	        }
	        
	        for (CheckersSimpleMove d : sm) {
	            simple_moves = push_back(simple_moves, d);
	        }

	        return;
	    }
	    
	    public CheckersMove(String st2) {
	    	this.move_parse(st2);
	    }
	    
	    public CheckersMove() {
	    	
	    }
	    
}
