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

class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final Context mContext;
    private ArrayList<User> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView userNameTextView;
        public TextView phoneTextview;
        public TextView emailTextview;
        public TextView websiteTextview;
        public ViewHolder(View v) {
            super(v);
           // mTextView = v;
            cardView =(CardView)v.findViewById(R.id.user_card);
            userNameTextView=(TextView)v.findViewById(R.id.user_name);
            phoneTextview=(TextView)v.findViewById(R.id.user_phone);
            websiteTextview=(TextView)v.findViewById(R.id.user_website);
            emailTextview=(TextView)v.findViewById(R.id.user_email);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserAdapter(Context context,ArrayList<User> myDataset) {
        mDataset = myDataset;
        mContext=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);;
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // holder.mTextView.setText((CharSequence) mDataset.get(position));
        holder.userNameTextView.setText(mDataset.get(position).getName());
        holder.emailTextview.setText(mDataset.get(position).getEmail());
        holder.websiteTextview.setText(mDataset.get(position).getWebsite());
        holder.phoneTextview.setText(mDataset.get(position).getPhone());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,UserAlbumsActivity.class);
                i.putExtra("user",mDataset.get(position));
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