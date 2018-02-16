package co.ingdjason.youtubeapp.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ingdjason on 2/15/18.
 */

public class Video implements Serializable {
    private String title;
    private String description;
    private String publishedAt;
    private String videoId;

    private String thumbnailsDefault;
    private String thumbnailsMedium;
    private String thumbnailsHigh;
    private String channelTitle;

    //recommended empty constructor
    public Video(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getThumbnailsDefault() {
        return thumbnailsDefault;
    }

    public void setThumbnailsDefault(String thumbnailsDefault) {
        this.thumbnailsDefault = thumbnailsDefault;
    }

    public String getThumbnailsMedium() {
        return thumbnailsMedium;
    }

    public void setThumbnailsMedium(String thumbnailsMedium) {
        this.thumbnailsMedium = thumbnailsMedium;
    }

    public String getThumbnailsHigh() {
        return thumbnailsHigh;
    }

    public void setThumbnailsHigh(String thumbnailsHigh) {
        this.thumbnailsHigh = thumbnailsHigh;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Video(JSONObject jsonObject) throws JSONException {

        //this.title = Integer.parseInt(jsonObject.getString("title"));
        this.title = jsonObject.getJSONObject("snippet").getString("title");
        this.description = jsonObject.getJSONObject("snippet").getString("description");
        this.publishedAt = jsonObject.getJSONObject("snippet").getString("publishedAt");
        this.videoId = jsonObject.getJSONObject("snippet").getJSONObject("resourceId").getString("videoId");

        this.thumbnailsDefault = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("default").getString("url");
        this.thumbnailsMedium = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");
        this.thumbnailsHigh = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url");
        this.channelTitle = jsonObject.getJSONObject("snippet").getString("channelTitle");

    }

    public static ArrayList<Video> fromJSONArray(JSONArray array) {
        ArrayList<Video> videos = new ArrayList<>();
        for (int x = 0; x < array.length(); x++){
            try {
                videos.add (new Video(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return videos;
    }
}
