package com.grosalex.qonto;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class UserAlbumsActivity extends AppCompatActivity {

    private static final String TAG = "UserAlbumsActivity";
    private User selectedUser;
    private ArrayList<Album> albumList;
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_albums);

        selectedUser= (User)getIntent().getParcelableExtra("user");
        Log.d(TAG, "onCreate: "+selectedUser.getId());
        albumList = new ArrayList<Album>();
        mRecycler=(RecyclerView)findViewById(R.id.album_recycler);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        albumAdapter = new AlbumAdapter(this,albumList);
        mRecycler.setAdapter(albumAdapter);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, getString(R.string.album_url,selectedUser.getId()), null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //mTxtDisplay.setText("Response: " + response.toString());
                        Log.d(TAG, "onResponse: "+response);
                        for(int i=0;i<response.length();i++){
                            try {
                                albumList.add(new Album(response.getJSONObject(i)));
                                albumAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        albumAdapter.notifyDataSetChanged();
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
