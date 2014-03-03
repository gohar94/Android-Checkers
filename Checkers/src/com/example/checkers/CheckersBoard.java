package com.example.checkers;

public class CheckersBoard {
	
	public CheckersPiece[] light_pieces = new CheckersPiece[12];
	public CheckersPiece[] dark_pieces = new CheckersPiece[12];
    public CheckersPiece none_piece = new CheckersPiece();
    public int turn;
    
    public static String[] push_back(String[] array, String push) {
	    String[] longer = new String[array.length + 1];
	    for (int i = 0; i < array.length; i++)
	        longer[i] = array[i];
	    longer[array.length] = push;
	    return longer;
	}
    
    public static CheckersPiece[] push_back(CheckersPiece[] array, CheckersPiece push) {
    	int count = 0;
    	for (CheckersPiece x : array) {
    		count++;
    	}
    	CheckersPiece[] longer = new CheckersPiece[count + 1];
	    for (int i = 0; i < count; i++)
	        longer[i] = array[i];
	    longer[count] = push;
	    return longer;
	}
    
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
    public CheckersBoard() {
    	int a = 0;
        int odds = 1;
        int evens = 0;
        none_piece.position.row = -1;
        none_piece.position.col = -1;
       
        for (int i = 0; i < 12; i++) {
        	CheckersPiece cp = new CheckersPiece();
            a = a + 1;
            if (a <= 4) {
                cp.position.row = 0;
                cp.position.col = odds;
                odds = odds + 2;
            } else if (a > 4 && a <= 8) {
                odds = 1;
                cp.position.row = 1;
                cp.position.col = evens;
                evens = evens + 2;
            } else {
            	cp.position.row = 2;
                cp.position.col = odds;
                odds = odds + 2;           
            }
            light_pieces[i] = cp;
        }
        
        a = 0;
        odds = 1;
        evens = 0;
        for (int i = 0; i < 12; i++) {
        	CheckersPiece cp = new CheckersPiece();
            a++;
            if (a <= 4) {
            	cp.position.row = 5;
                cp.position.col = evens;
                cp.dark = true;
                evens = evens + 2;
            } else if (a > 4 && a <= 8) {
                evens = 0;
                cp.position.row = 6;
                cp.position.col = odds;
                cp.dark = true;
                odds = odds + 2;
            } else {
            	cp.position.row = 7;
                cp.position.col = evens;
                cp.dark = true;
                evens = evens + 2;
            }
            dark_pieces[i] = cp;
        }
        turn = 1;
    }
    
    
    public CheckersBoard(String st1, String st2) {
        none_piece.position.row = -1;
        none_piece.position.col = -1;
        turn = 1;
        String[] darks = string_split(st1, ',');
        String[] lights = string_split(st2, ','); 
        CheckersPiece[] v_cp_dark = new CheckersPiece[0];
        CheckersPiece[] v_cp_light = new CheckersPiece[0];
        int count_darks = 0;
        int count_lights = 0;
        
        for (String s : darks) {
            CheckersPiece cp = new CheckersPiece();
            CheckersPosition p = new CheckersPosition();
            p.position_parse(s);
            int newr = p.row;
            int newc = p.col;
            cp.position.row = newr;
            cp.position.col = newc;
            v_cp_dark = push_back(v_cp_dark, cp);
            count_darks++;
        }
        
        for (String s : lights) {
            CheckersPiece cp = new CheckersPiece();
            CheckersPosition p = new CheckersPosition();
            p.position_parse(s);
            int newr = p.row;
            int newc = p.col;
            cp.position.row = newr;
            cp.position.col = newc;
            v_cp_light = push_back(v_cp_light, cp);
            count_lights++;
        }
        
        for (int i = 0; i < 12; i++) {
            dark_pieces[i] = new CheckersPiece();
            dark_pieces[i].position.col = -1;
            dark_pieces[i].position.row = -1;
            light_pieces[i] = new CheckersPiece();
            light_pieces[i].position.col = -1;
            light_pieces[i].position.row = -1;
        }
        
        for (int i = 0; i < count_darks; i++) {
            int rr = v_cp_dark[i].position.row;
            int cc = v_cp_dark[i].position.col;
            dark_pieces[i].position.row = rr;
            dark_pieces[i].position.col = cc;
            dark_pieces[i].dark = true;
        }
        
        for (int i = 0; i < count_lights; i++) {
            int rr = v_cp_light[i].position.row;
            int cc = v_cp_light[i].position.col;
            light_pieces[i].position.row = rr;
            light_pieces[i].position.col = cc;
        }
    }

    public CheckersPiece board_get_piece_at(int r, int c) {
        for (int i = 0; i < 12; i++) {
            if (light_pieces[i].position.col == c && light_pieces[i].position.row == r) {
                return light_pieces[i];
            }  
        }
        for (int i = 0; i < 12; i++) {
            if (dark_pieces[i].position.col == c && dark_pieces[i].position.row == r) {
                return dark_pieces[i];
            }  
        }
        return none_piece;
    }
    
    public void board_print() {
        for (int r = 0; r < 8; r = r + 1) {
            System.out.print((8 - r) + (" "));
            for (int a = 0; a <= 7; a = a + 1) {
                if (board_get_piece_at(r, a).position.col == 
                -1 || board_get_piece_at(r, a).captured == true) {
                	System.out.print(".");
                } else {
                	(board_get_piece_at(r, a)).piece_print();
                }
            }
            System.out.println("\n");
        }
        System.out.print("  ABCDEFGH");
        return;
    }
    
    public String[] vec_string() {
    	String str[] = new String[64];
        for (int r = 0; r < 8; r = r + 1) {
            for (int a = 0; a < 8; a = a + 1) {
                if (board_get_piece_at(r, a).position.col == 
                -1 || board_get_piece_at(r, a).captured == true) {
                	str[(r*8)+a] = ".";
                } else {
                	str[(r*8)+a] = (board_get_piece_at(r, a)).toString();
                }
            }
        }
        return str;
    }
    
    
    public void simple_move(CheckersSimpleMove smove) {
        CheckersPiece cp = new CheckersPiece();
        cp.position.row = smove.start.row;
        cp.position.col = smove.start.col;
        int r_old = cp.position.row;
        int c_old = cp.position.col;
        cp.position.row = smove.end.row;
        cp.position.col = smove.end.col;
        int r_new = cp.position.row;
        int c_new = cp.position.col;
        		
        for (CheckersPiece cp1 : dark_pieces) {
            if (r_old == cp1.position.row && 
            c_old == cp1.position.col) {
                cp1.position.row = r_new;
                cp1.position.col = c_new;
            }
        }
        for (CheckersPiece cp1 : light_pieces) {
            if (r_old == cp1.position.row && 
            c_old == cp1.position.col) {
                cp1.position.row = r_new;
                cp1.position.col = c_new;
            }
        }
    return;
    }
    
    public int board_move_and_capture(CheckersMove cm) {
    	if (this.validate(cm) == 0) {
        int ro1 = cm.simple_moves[0].start.row;
        int co1 = cm.simple_moves[0].start.col;
        CheckersPiece p = new CheckersPiece();
        p = board_get_piece_at(ro1, co1);
        boolean cul;
        cul = p.dark;
        int count = 0;
        for (CheckersSimpleMove n : cm.simple_moves) {
            count = count + 1;
            n.start.row = n.start.row + 0;
        }
        int i = 0;
        for (CheckersSimpleMove n : cm.simple_moves) {
            n.start.row = n.start.row + 0;
        // start and end points of this move
        int ro = cm.simple_moves[i].start.row;
        int co = cm.simple_moves[i].start.col;
        int rn = cm.simple_moves[i].end.row;
        int cn = cm.simple_moves[i].end.col;
        // checkerspiece at the start point, the one which is being moved
        
        int dir = 0;
        // find out which direction ne nw sw se it went

        if (rn < ro && cn > co) {
            dir = 0;
        } else if (rn < ro && co > cn) {
            dir = 1;
        } else if (ro < rn && co > cn) {
            dir = 2;        
        } else if (ro < rn && cn > co) {
            dir = 3;
        }
        // for the direction it went to, check the box diagonally behind it
        // if it is of the other color, and was filled before, then make it
        // into a none piece
        if (dir == 0) {
            for (int k = 0; k < 12; k ++) {
                if (rn + 1 == dark_pieces[k].position.row && cn - 1 == dark_pieces[k].position.col
                && dark_pieces[k].dark != cul) {
                	dark_pieces[k] = none_piece;
                	dark_pieces[k].position.row = -1;
                	dark_pieces[k].position.col = -1;
                	dark_pieces[k].captured = true;
                }
            }
            for (int k = 0; k < 12; k ++) {
                if (rn + 1 == light_pieces[k].position.row && cn - 1 == light_pieces[k].position.col
                 && light_pieces[k].dark != cul) {
                	light_pieces[k] = none_piece;
                	light_pieces[k].position.row = -1;
                	light_pieces[k].position.col = -1;
                	light_pieces[k].captured = true;
                }
            }
        } else if (dir == 1) {
            for (int k = 0; k < 12; k ++) {
                if (rn + 1 == dark_pieces[k].position.row && cn + 1 == dark_pieces[k].position.col
                 && dark_pieces[k].dark != cul) {
                	dark_pieces[k] = none_piece;
                	dark_pieces[k].position.row = -1;
                	dark_pieces[k].position.col = -1;
                	dark_pieces[k].captured = true;
                }
            }
            for (int k = 0; k < 12; k ++) {
                if (rn + 1 == light_pieces[k].position.row && cn + 1 == light_pieces[k].position.col
                 && light_pieces[k].dark != cul) {
                	light_pieces[k] = none_piece;
                	light_pieces[k].position.row = -1;
                	light_pieces[k].position.col = -1;
                	light_pieces[k].captured = true;
                }
            }
        } else if (dir == 2) {
            for (int k = 0; k < 12; k ++) {
                if (rn - 1 == dark_pieces[k].position.row && cn + 1 == dark_pieces[k].position.col
                 && dark_pieces[k].dark != cul) {
                	dark_pieces[k] = none_piece;
                	dark_pieces[k].position.row = -1;
                	dark_pieces[k].position.col = -1;
                	dark_pieces[k].captured = true;
                }
            }
            for (int k = 0; k < 12; k ++) {
                if (rn - 1 == light_pieces[k].position.row && cn + 1 == light_pieces[k].position.col
                 && light_pieces[k].dark != cul) {
                	light_pieces[k] = none_piece;
                	light_pieces[k].position.row = -1;
                	light_pieces[k].position.col = -1;
                	light_pieces[k].captured = true;
                }
            }
        } else {
            for (int k = 0; k < 12; k ++) {
                if (rn - 1 == dark_pieces[k].position.row && cn - 1 == dark_pieces[k].position.col
                 && dark_pieces[k].dark != cul) {
                	dark_pieces[k] = none_piece;
                	dark_pieces[k].position.row = -1;
                	dark_pieces[k].position.col = -1;
                	dark_pieces[k].captured = true;
                }
            }
            for (int k = 0; k < 12; k ++) {
                if (rn - 1 == light_pieces[k].position.row && cn - 1 == light_pieces[k].position.col
                 && light_pieces[k].dark != cul) {
                	light_pieces[k] = none_piece;
                	light_pieces[k].position.row = -1;
                	light_pieces[k].position.col = -1;
                	light_pieces[k].captured = true;
                }
        }
        }
        board_move(cm);
        turn = turn * -1;
        i = i + 1;
        }
    	} else {
    		System.out.println("Invalid Move");
    		return -1;
    	}
        return 1;
    }
    
    public void board_move(CheckersMove cm) {
        CheckersPiece cp = new CheckersPiece();
        cp.position = cm.simple_moves[0].start;
        int r_old = cp.position.row;
        int c_old = cp.position.col;
        for (CheckersSimpleMove smm : cm.simple_moves) {
            cp.position = smm.end;
        }
        int r_new = cp.position.row;
        int c_new = cp.position.col;
        
        for (int k = 0; k < 12; k ++) {
            if (r_old == dark_pieces[k].position.row && c_old == dark_pieces[k].position.col) {
            	dark_pieces[k].position.row = r_new;
            	dark_pieces[k].position.col = c_new;
                if (r_new == 0) {
                	dark_pieces[k].crowned = true;
                }
            }
        }
        
        for (int k = 0; k < 12; k ++) {
            if (r_old == light_pieces[k].position.row && c_old == light_pieces[k].position.col) {
            	light_pieces[k].position.row = r_new;
            	light_pieces[k].position.col = c_new;
                if (r_new == 7) {
                	light_pieces[k].crowned = true;
                }
            }
        }
        return;
    }
    
    public int winner() {
        int count_dark_dead = 0;
        int count_light_dead = 0;
        int win = 0;
        for (CheckersPiece x : dark_pieces) {
            if (x.position.row == -1 || x.captured == true || 
            x.position.col == -1) {
                count_dark_dead++;
            }
        }
        for (CheckersPiece x : light_pieces) {
            if (x.position.row == -1 || x.captured == true || 
            x.position.col == -1) {
                count_light_dead++;
            }
        }
        if (count_dark_dead == 12) {
            win = 2;
        }
        if (count_light_dead == 12) {
            win = 1;
        }
        return win;
    }
    
    public int validate(CheckersMove cm) {
        int ro = cm.simple_moves[0].start.row;
        int co = cm.simple_moves[0].start.col;
        int rn = cm.simple_moves[0].end.row;
        int cn = cm.simple_moves[0].end.col;
        boolean cull = (board_get_piece_at(ro, co)).dark;
        
        
        if (winner() != 0) {
            return 1;
        }
        
        int junk = 0;
        if (cull == false && turn == 1) {
            junk++;
        } else if (cull == true && turn == -1) {
            junk++;
        } else {
            return 2;
        }
        
        int sizer;
        sizer = ro - rn;
        
        if (board_get_piece_at(ro, co).position.row == -1) {
            return 3;
        }
        
        if (board_get_piece_at(rn, cn).position.row != -1) {
            return 4;
        }
        
        int dif_r = ro - rn;
        CheckersPiece p = board_get_piece_at(ro, co);
        boolean cul = p.dark;
        int check_dark = 0;
        int check_light = 0;
        int a = 0;
        int dir = 0;
        if (rn < ro && cn > co) {
            dir = 0;
        } else if (rn < ro && co > cn) {
            dir = 1;
        } else if (ro < rn && co > cn) {
            dir = 2;        
        } else if (ro < rn && cn > co) {
            dir = 3;
        }
        
        // return 5
        if (dif_r != 1 && dif_r != -1) {
            if (dir == 0) {
            for (CheckersPiece cp : dark_pieces) {
                if (rn + 1 == cp.position.row && cn - 1 == cp.position.col
                && cp.dark == cul) {
                    a = 1;
                }
                check_dark++;
            }
            for (CheckersPiece cp : light_pieces) {
                if (rn + 1 == cp.position.row && cn - 1 == cp.position.col
                 && cp.dark == cul) {
                    a = 1;
                }
                check_light++;
            }
        } else if (dir == 1) {
            for (CheckersPiece cp : dark_pieces) {
                if (rn + 1 == cp.position.row && cn + 1 == cp.position.col
                 && cp.dark == cul) {
                    a = 1;
                }
                check_dark++;
            }
            for (CheckersPiece cp : light_pieces) {
                if (rn + 1 == cp.position.row && cn + 1 == cp.position.col
                 && cp.dark == cul) {
                    a = 1;
                }
                check_light++;
            }
        } else if (dir == 2) {
            for (CheckersPiece cp : dark_pieces) {
                if (rn - 1 == cp.position.row && cn + 1 == cp.position.col
                 && cp.dark == cul) {
                    a = 1;
                }
                check_dark++;
            }
            for (CheckersPiece cp : light_pieces) {
                if (rn - 1 == cp.position.row && cn + 1 == cp.position.col
                 && cp.dark == cul) {
                    a = 1;
                }
                check_light++;
            }
        } else {
            for (CheckersPiece cp : dark_pieces) {
                if (rn - 1 == cp.position.row && cn - 1 == cp.position.col
                 && cp.dark == cul) {
                    a = 1;
                }
                check_dark++;
            }
            for (CheckersPiece cp : light_pieces) {
                if (rn - 1 == cp.position.row && cn - 1 == cp.position.col
                 && cp.dark == cul) {
                    a = 1;
                }
                check_light++;
        }
        }
        if (check_dark == 12 || check_light == 12 ||
        check_dark == 12) {
            if (a == 1) {
                return 5;
            }
        }
        if (dir == 0) {
            if (board_get_piece_at(rn + 1, cn - 1).position.row == -1) {
                a = 1;
            }
        } else if (dir == 1) {
            if (board_get_piece_at(rn + 1, cn + 1).position.row == -1) {
                a = 1;
            }
        } else if (dir == 2) {
            if (board_get_piece_at(rn - 1, cn + 1).position.row == -1) {
                a = 1;
            }
        } else {
            if (board_get_piece_at(rn - 1, cn - 1).position.row == -1) {
                a = 1;
            }
        }
        if (a == 1) {
            return 5;
        }
        }
        // return 6
        
        CheckersPosition[] pp = p.piece_positions_in_direction(dir);
        int t = 0;
        if (p.crowned == false) {
            if (cull == false) {
                if (dir == 0 || dir == 1) {
                    return 6;
                }
            } else {
                if (dir == 2 || dir == 3) {
                    return 6;
                }
            }
        }
        for (CheckersPosition x : pp) {
            if (x.row == rn && x.col == cn) {
                t = 1;
            }
        }
        if (t == 0) {
            return 6;
        }
        return 0;    
    }
    
}
