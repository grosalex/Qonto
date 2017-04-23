package com.grosalex.qonto;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by grosalex on 23/04/17.
 */

class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private final Context mContext;
    private final Picasso picassoInstance;
    private ArrayList<GalleryItem> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView galleryTitleTextView;
        private ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            // mTextView = v;
            cardView =(CardView)v.findViewById(R.id.album_card);
            galleryTitleTextView=(TextView)v.findViewById(R.id.gallery_title);
            imageView=(ImageView)v.findViewById(R.id.gallery_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GalleryAdapter(Context context,ArrayList<GalleryItem> myDataset) {
        mDataset = myDataset;
        mContext=context;
        picassoInstance=Picasso.with(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_item, parent, false);;
        // set the view's size, margins, paddings and layout parameters

        GalleryAdapter.ViewHolder vh = new GalleryAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // holder.mTextView.setText((CharSequence) mDataset.get(position));
        holder.galleryTitleTextView.setText(mDataset.get(position).getTitle());
        Log.d("PICTURE",mDataset.get(position).getThumbnailUrl());
        Picasso.with(mContext).load(mDataset.get(position).getThumbnailUrl().replace("http","https")).into(holder.imageView);

        //picassoInstance.load(mDataset.get(position).getUrl()).into(holder.imageView);
        /*
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,GalleryActivity.class);
                i.putExtra("album",mDataset.get(position));
                mContext.startActivity(i);

            }
        });*/

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
