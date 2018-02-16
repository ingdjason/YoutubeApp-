package co.ingdjason.youtubeapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.ingdjason.youtubeapp.Adapter.VideoArrayAdapter;
import co.ingdjason.youtubeapp.Model.Video;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    ListView lvItem;
    TextView tvPullRefresh;
    ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;

    private ArrayList<Video> List_item;
    private VideoArrayAdapter videoArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItem = (ListView) findViewById(R.id.lvBank);
        tvPullRefresh = findViewById(R.id.tvPullRefresh);
        progressBar = findViewById(R.id.progressBar);
        swipeRefresh = findViewById(R.id.swipeBank);


// Lookup the swipe container view
        tvPullRefresh.setVisibility(View.GONE);
        findListItem();

//Listen for Swipe Refresh to fetch data again
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tvPullRefresh.setVisibility(View.GONE);
                findListItem();
                swipeRefresh.setRefreshing(false);
            }
        });

// Configure the refreshing colors
        swipeRefresh.setColorSchemeResources(android.R.color.holo_orange_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light);

    }

    private void findListItem() {

        List_item = new ArrayList<>();
        videoArrayAdapter = new VideoArrayAdapter(this,List_item );
        lvItem.setAdapter(videoArrayAdapter);
        progressBar.setVisibility(View.VISIBLE);

        String apiLink= "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet+&maxResults=20&playlistId=UUW7THngeYo9HnextHMpR0-w&key=AIzaSyDhYn8S7K4hEUv3Mc4VhgOQ87501jk8OMw";

        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(70000);
        client.get(apiLink, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                //Object-->(response) --> JSonArray --> Object
                JSONObject json = response;
                try {
                    //JSONArray array = json.getJSONArray("response");
                    videoArrayAdapter.addAll(Video.fromJSONArray(json.getJSONArray("items")));
                    Log.d("DEBUG APP: ", List_item.toString());
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, " Error connexion internet", Toast.LENGTH_SHORT).show();
                    tvPullRefresh.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(MainActivity.this, " Error connexion internet", Toast.LENGTH_SHORT).show();
                tvPullRefresh.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}

/*
Cle API : AIzaSyDhYn8S7K4hEUv3Mc4VhgOQ87501jk8OMw
https://www.googleapis.com/youtube/v3/playlists?channelId=UC_x5XG1OV2P6uZZ5FSM9Ttw&key=AIzaSyDhYn8S7K4hEUv3Mc4VhgOQ87501jk8OMw

GET https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UC_x5XG1OV2P6uZZ5FSM9Ttw&key=AIzaSyDhYn8S7K4hEUv3Mc4VhgOQ87501jk8OMw

GET https://www.googleapis.com/youtube/v3/playlistItems?part=snippet+&maxResults=20&playlistId=UUW7THngeYo9HnextHMpR0-w&key=AIzaSyDhYn8S7K4hEUv3Mc4VhgOQ87501jk8OMw
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
MD5:  69:B4:71:E4:8A:32:38:C6:2F:73:19:4B:AF:50:07:ED
	 SHA1: AD:4F:77:F9:91:63:47:F7:EE:C8:42:F8:D3:75:A2:36:21:AB:03:1B
	 SHA256: B4:BF:87:9A:86:F1:D9:37:C0:79:21:15:5C:00:F8:F4:92:BB:D5:5A:68:BD:13:1A:51:30:44:E0:A7:9C:97:3C
	 Signature algorithm name: SHA1withRSA
	 Version: 1

 */