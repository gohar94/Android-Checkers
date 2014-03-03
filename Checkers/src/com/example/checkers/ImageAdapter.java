package com.example.checkers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private final String[] dataValues;
 
	public ImageAdapter(Context context, String[] dataValues) {
		this.context = context;
		this.dataValues = dataValues;
	}
 
	
	// functions from internet
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
		String[] vec_tags = {"A8", "B8", "C8", "D8", "E8", "F8", "G8", "H8",
				"A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7", 
				"A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6", 
				"A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5", 
				"A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4", 
				"A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3", 
				"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", 
				"A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1"};
		
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from data.xml
			gridView = inflater.inflate(R.layout.lay2, null);
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
			
			String data = new String();
			data = dataValues[position];
 
			if (data.equals("b")) {
				imageView.setImageResource(R.drawable.blackpiece);
			} else if (data.equals("w")) {
				imageView.setImageResource(R.drawable.whitepiece);
			} else if (data.equals("B")) {
				// DISTINGUISH COLUMN NAME WITH BLACK CROWNED PIECE
				if (position != 74) {
				imageView.setImageResource(R.drawable.blackcrowned);
				} else {
					imageView.setVisibility(View.GONE);
				}
			} else if (data.equals("W")) {
				imageView.setImageResource(R.drawable.whitecrowned);
			} else if (data.equals(".")) {
				imageView.setImageResource(R.drawable.blank);
			} else if (data.equals("b\n")) {
				imageView.setImageResource(R.drawable.blackpiece);
			} else if (data.equals("w\n")) {
				imageView.setImageResource(R.drawable.whitepiece);
			} else if (data.equals(".\n")) {
				imageView.setImageResource(R.drawable.whitepiece);
//				imageView.setVisibility(View.GONE);
			} else {
				imageView.setImageResource(R.drawable.whitepiece);
//				imageView.setVisibility(View.GONE);
			}
			
			String s = new String();
			Integer z = new Integer(position);
			s = z.toString();
			imageView.setTag(vec_tags[position]);
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return dataValues.length;
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}

