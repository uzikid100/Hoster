package com.example.uzezi.activitieswithintents;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by uzezi on 6/11/2017.
 */

public class EventOptionsAdapter extends RecyclerView.Adapter<EventOptionsAdapter.FeatureViewHolder> {

    private int mNumberOfItems;
    private OnListItemClickedListener listItemClickedListener;
    private List<DataForAdapter> eventOption = Collections.EMPTY_LIST;

    public interface OnListItemClickedListener{
        void OnListItemClicked(int position);
    }


    public EventOptionsAdapter(List data){
        eventOption = data;
    }

    @Override
    public FeatureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutFromId = R.layout.custom_event_row;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutFromId, parent, shouldAttachToParent);
        FeatureViewHolder viewHolder = new FeatureViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeatureViewHolder holder, int position) {
        DataForAdapter current = eventOption.get(position);
        holder.description.setText(current.optionTitle);
        holder.icon.setImageResource(current.icon);
    }

    @Override
    public int getItemCount() {
        return eventOption != null ? eventOption.size() : null;
    }





    public class FeatureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView description;
        ImageView icon;

        public FeatureViewHolder(View itemView) {
            super(itemView);

            description = (TextView) itemView.findViewById(R.id.tv_custom_row_description);
            icon = (ImageView) itemView.findViewById(R.id.iv_custom_row_image);
        }

        @Override
        public void onClick(View v) {
        }
    }

    class DataForAdapter{
        int icon;
        String optionTitle;
    }
}
