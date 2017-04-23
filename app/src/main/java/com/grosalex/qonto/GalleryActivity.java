package com.grosalex.qonto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private Album selectedAlbum;
    private String TAG="GalleryActivity";
    private ArrayList<GalleryItem> galleryList;
    private RecyclerView mRecycler;
    private GalleryAdapter galleryAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        selectedAlbum = (Album) getIntent().getParcelableExtra("album");




        Log.d(TAG, "onCreate: "+selectedAlbum.getId());
        galleryList = new ArrayList<GalleryItem>();
        mRecycler=(RecyclerView)findViewById(R.id.gallery_recycler);
        mRecycler.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        galleryAdapter = new GalleryAdapter(this,galleryList);
        mRecycler.setAdapter(galleryAdapter);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, getString(R.string.gallery_url,selectedAlbum.getId()), null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //mTxtDisplay.setText("Response: " + response.toString());
                        //Log.d(TAG, "onResponse: "+response);
                        for(int i=0;i<response.length();i++){
                            try {
                                galleryList.add(new GalleryItem(response.getJSONObject(i)));
                                galleryAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        galleryAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d(TAG, "onErrorResponse: "+ error.toString());

                    }
                });

// Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);

    }
}
