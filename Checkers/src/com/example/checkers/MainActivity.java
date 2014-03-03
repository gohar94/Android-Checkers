package com.example.checkers;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	int switcher = 1;
	String click_move = "empty";
	GridView gridView;
	CheckersBoard cb = new CheckersBoard();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int h = metrics.heightPixels;
		int w = metrics.widthPixels;
		System.out.println(h);
		System.out.println(w);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CheckersPiece all_pieces[] = new CheckersPiece[24];
		for (int i = 0; i < 12; i++) {
			all_pieces[i] = cb.dark_pieces[i];
			all_pieces[i+12] = cb.light_pieces[i];
		}
		String myvec[] = new String[64];
		myvec = cb.vec_string();
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new ImageAdapter(this, myvec));
		Context context = getApplicationContext();
		CharSequence text = "White Player starts!";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
	public void pressed(View view) {
		System.out.println("pressed");
		EditText e = (EditText) findViewById(R.id.editText1);
		System.out.println(e.getText().toString());
		if (e.getText().toString().length() >= 1) {
			CheckersMove cm = new CheckersMove(e.getText().toString());
			cb.board_move_and_capture(cm);
			String myvec[] = new String[64];
			myvec = cb.vec_string();
			gridView = (GridView) findViewById(R.id.gridview);
			gridView.setAdapter(new ImageAdapter(this, myvec));
		}
	}
	
	public void image_pressed(View view) {
		String s = new String();
		s = (String) view.getTag();
		System.out.println(s);
		if (switcher == 1) {
			System.out.println(s);
			CheckersPosition cp = new CheckersPosition();
			cp.position_parse(s);
			CheckersPiece pp = new CheckersPiece();
			pp = cb.board_get_piece_at(cp.row, cp.col);
			if (cb.turn == 1) {
				if (pp.toString().equals("w")) {
					click_move = s;
					switcher = -1;
				} else if (pp.toString().equals("W")) {
					click_move = s;
					switcher = -1;
				} else {
					Context context = getApplicationContext();
					CharSequence text = "White Player's turn!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
			} else {
				if (pp.toString().equals("b")) {
					click_move = s;
					switcher = -1;
				} else if (pp.toString().equals("B")) {
					click_move = s;
					switcher = -1;
				} else {
					Context context = getApplicationContext();
					CharSequence text = "Black Player's turn!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
			}
		} else {
			click_move = click_move + "-" + s;
			System.out.println(click_move);
			int j = cb.board_move_and_capture(new CheckersMove(click_move));
			if (j == -1) {
				Context context = getApplicationContext();
				CharSequence text = "Invalid Move, try again!";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				click_move = "";
				switcher = 1;
			} else {
				String myvec[] = new String[64];
				myvec = cb.vec_string();
				gridView = (GridView) findViewById(R.id.gridview);
				gridView.setAdapter(new ImageAdapter(this, myvec));
				switcher = 1;
				click_move = "";
				cb.board_print();
			}
		}
	}
	
}
