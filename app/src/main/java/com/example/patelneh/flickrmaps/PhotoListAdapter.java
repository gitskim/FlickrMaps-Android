package com.example.patelneh.flickrmaps;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter <PhotoListAdapter.photoListViewHolder>{

    private List<FlickrPhotos> flickrPhotosList;
    private List<Bitmap> flickrImg;

    public PhotoListAdapter (List titleList, List imgList) {
        this.flickrPhotosList = new ArrayList<>(titleList);
        this.flickrImg = new ArrayList<>(imgList);

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

    @Override
    public void onBindViewHolder(photoListViewHolder holder, int position) {
        //
        holder.titleBind(flickrPhotosList.get(0).getTitle().get(position));
        holder.imgBind(flickrImg.get(position));
    }

    @Override
    public int getItemCount() {
        return flickrPhotosList.get(0).getTitle().size();
    }


    class photoListViewHolder extends RecyclerView.ViewHolder {

        TextView photoListTitle;
        ImageView photoListImg;

        //Constructor
        public photoListViewHolder(View itemView) {
            super(itemView);
            photoListTitle = (TextView) itemView.findViewById(R.id.photoListTitle);
            photoListImg = (ImageView) itemView.findViewById(R.id.photoListImg);
        }

        //Conv. methods
        void titleBind(String title) {
            photoListTitle.setText(title);
        }

        void imgBind (Bitmap img){
            photoListImg.setImageBitmap(img);
        }

    }

}
