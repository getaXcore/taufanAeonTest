package com.test.getaxcore.taufanaeontest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.test.getaxcore.taufanaeontest.R;
import com.test.getaxcore.taufanaeontest.model.Photos;

import java.util.List;

/**
 * Created by 001910947 on 7/31/2020.
 */

public class ImageAdapterDB extends RecyclerView.Adapter<ImageAdapterDB.CategoryViewHolder>{

    private Context context;
    private List<Photos> list;

    public void setList(List<Photos> listPhoto){
        list = listPhoto;
    }

    public ImageAdapterDB(Context context){
        this.context = context;
    }

    @Override
    public ImageAdapterDB.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        return new ImageAdapterDB.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapterDB.CategoryViewHolder holder,int position){
        holder.textItem.setText(getList().get(position).getTitle());
        String imageUrl = getList().get(position).getThumbnailUrl();
        Picasso.with(context).load(imageUrl)
                .placeholder(R.color.cardview_light_background)
                .error(R.mipmap.ic_launcher)
                .into(holder.itemImagePost);
    }

    @Override
    public int getItemCount(){
        return getList().size();
    }

    public List<Photos> getList(){
        return list;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImagePost;
        TextView textItem;
        public CategoryViewHolder(View itemView){
            super(itemView);
            itemImagePost = itemView.findViewById(R.id.itemImagePost);
            textItem = itemView.findViewById(R.id.textItem);
        }

    }
}
