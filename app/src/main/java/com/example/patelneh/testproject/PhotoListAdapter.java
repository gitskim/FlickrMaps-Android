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

    private int itemCount;
    private List<String> arrayList = new ArrayList();

    //Constructor
    public PhotoListAdapter (List arrayList){
        this.arrayList = arrayList;

    }

    //OnCreateViewHolder()

    public photoListViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int photoListItemId = R.layout.photo_list_item;
        LayoutInflater li = LayoutInflater.from(context);

        View view = li.inflate(photoListItemId, viewGroup, false);
        photoListViewHolder viewHolder =  new photoListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(photoListViewHolder holder, int position) {
        holder.bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    class photoListViewHolder extends RecyclerView.ViewHolder {

        TextView photoListItem;

        public photoListViewHolder(View itemView) {
            super(itemView);

            photoListItem = (TextView) photoListItem.findViewById(R.id.photoListItem);

        }
        void bind(String title) {
            photoListItem.setText(title);
        }
    }

    //Pass in data in constructor and create ArrayList
}
