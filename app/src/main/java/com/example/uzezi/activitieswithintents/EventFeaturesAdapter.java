package com.example.uzezi.activitieswithintents;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Uzezi on 5/26/2017.
 */

public class EventFeaturesAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<SingleRow> singleRows;


    public EventFeaturesAdapter(Context context){
        singleRows = new ArrayList<>();
        mContext = context;
        Resources res = context.getResources();
        String[] features = res.getStringArray(R.array.event_features);
        int[] images = {R.drawable.eye_image, R.drawable.another_calender_icon, R.drawable.circle_icons_cash,
        R.drawable.add_friends_icon, R.drawable.add_friends_icon, R.drawable.location_and_map, R.drawable.ic_event_type};


        for(int i = 0; i<features.length; i++){
            singleRows.add(new SingleRow(features[i], images[i]));
        }
    }


    @Override
    public int getCount() {
        return singleRows.size();
    }

    @Override
    public Object getItem(int position) {
        return singleRows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_event_edit_row, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.feature_description);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_for_singleView);

        SingleRow temp = singleRows.get(position);
        textView.setText(temp.description);
        imageView.setImageResource(temp.image);

        return view;
    }



    class SingleRow{
        private String description;
        private int image;

        SingleRow(String description, int image){
            this.description = description;
            this.image = image;
        }
    }

}




