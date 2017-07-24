package com.example.patelneh.testproject;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter <PhotoListAdapter.photoListViewHolder>{

    private List<String> titleList;

    public PhotoListAdapter (List arrayList) {
        this.titleList = new ArrayList<>(arrayList);

    }

    //OnCreateViewHolder

    @Override
    public photoListViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int photoListItemId = R.layout.photo_list_item;
        LayoutInflater li = LayoutInflater.from(context);

        View view = li.inflate(photoListItemId, viewGroup, false);
        photoListViewHolder viewHolder =  new photoListViewHolder(view);

        return viewHolder;
    }

    //Populates each item with data

    @Override
    public void onBindViewHolder(photoListViewHolder holder, int position) {
        holder.bind(titleList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }


    class photoListViewHolder extends RecyclerView.ViewHolder {

        TextView photoListItem;

        //Constructor
        public photoListViewHolder(View itemView) {
            super(itemView);
            photoListItem = (TextView) itemView.findViewById(R.id.photoListItem);
        }

        //Conv. method
        void bind(String title) {
            photoListItem.setText(title);
        }
    }

}
