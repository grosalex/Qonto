package com.grosalex.qonto;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by grosalex on 23/04/17.
 */

class GalleryItem {
    private String title;
    private String url;
    private String thumbnailUrl;

    public GalleryItem(JSONObject jsonObject) {
        try {
            title= jsonObject.getString("title");
            url=jsonObject.getString("url");
            thumbnailUrl=jsonObject.getString("thumbnailUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
