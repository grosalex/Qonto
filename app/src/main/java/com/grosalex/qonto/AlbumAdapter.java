package com.grosalex.qonto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by grosalex on 23/04/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private final Context mContext;
    private ArrayList<Album> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView albumTitleTextView;
        public ViewHolder(View v) {
            super(v);
            // mTextView = v;
            cardView =(CardView)v.findViewById(R.id.album_card);
            albumTitleTextView=(TextView)v.findViewById(R.id.album_title);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlbumAdapter(Context context,ArrayList<Album> myDataset) {
        mDataset = myDataset;
        mContext=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);;
        // set the view's size, margins, paddings and layout parameters

        AlbumAdapter.ViewHolder vh = new AlbumAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AlbumAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // holder.mTextView.setText((CharSequence) mDataset.get(position));
        holder.albumTitleTextView.setText(mDataset.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,UserAlbumsActivity.class);
               // i.putExtra("user",mDataset.get(position));
                mContext.startActivity(i);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
