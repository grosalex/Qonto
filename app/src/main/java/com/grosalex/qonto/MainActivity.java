package com.grosalex.qonto;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG="MainActivity";
    private ArrayList<User> userList;
    private RecyclerView mRecycler;
    private UserAdapter userAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList= new ArrayList<User>();
        mRecycler=(RecyclerView)findViewById(R.id.user_recycler);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        userAdapter = new UserAdapter(userList);
        mRecycler.setAdapter(userAdapter);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, getString(R.string.users_url), null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //mTxtDisplay.setText("Response: " + response.toString());
                        Log.d(TAG, "onResponse: "+response);
                        for(int i=0;i<response.length();i++){
                            try {
                                userList.add(new User(response.getJSONObject(i)));
                                userAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        userAdapter.notifyDataSetChanged();
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
