package com.grosalex.qonto;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by grosalex on 23/04/17.
 */

class Album {
    private  String title;
    private  int id;

    public Album(JSONObject jsonObject) {
        try {
            id=jsonObject.getInt("id");
            title=jsonObject.getString("title");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }
}
