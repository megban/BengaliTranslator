package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by meghnabanerjee on 5/3/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    int backGroundColorId;

    public WordAdapter(Activity context, ArrayList<Word> words, int backGroundColorId) {
        super(context, 0, words);
        this.backGroundColorId = backGroundColorId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);
        RelativeLayout relativeLayout = (RelativeLayout) listItemView.findViewById(R.id.text_container);
        relativeLayout.setBackgroundResource(backGroundColorId);

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTranslation = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation name from the current Word object and
        // set this text on the name TextView
        defaultTranslation.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView miwokTranslation = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the miwok translation from the current Word object and
        // set this text on the miwokTranslation TextView
        miwokTranslation.setText(currentWord.getMiwokTranslation());

        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            image.setImageResource(currentWord.getImageResourceId());
            // Make sure the view is visible
            image.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            image.setVisibility(View.GONE);
        }



        return listItemView;
    }

}
