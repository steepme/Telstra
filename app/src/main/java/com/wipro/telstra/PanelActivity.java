package com.wipro.telstra;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.wipro.telstra.com.wipro.telstra.connection.RequestManager;
import com.wipro.telstra.com.wipro.telstra.model.NewsAdapter;
import com.wipro.telstra.com.wipro.telstra.model.News;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class PanelActivity extends ActionBarActivity {

    private static final String TAG = "PanelActivity";
    private static final String FEED_URL = "https://dl.dropboxusercontent.com/u/746330/facts.json";

    ProgressDialog mProgressDialog;
    private ActionBar actionBar;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mContext = this;
        initView();
        fetchFeedList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            makeNetworkCall(FEED_URL);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Initialize UI components
    private void initView() {

        actionBar = getSupportActionBar();
        updateActionBarTitle(getString(R.string.app_name));
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

   //Get response from either cache or initiate call
    private void fetchFeedList() {

        Cache cache = RequestManager.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(FEED_URL);
        if(entry != null){
            try {
                String cachedData = new String(entry.data, "UTF-8");
                processResponse(cachedData);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else{
            // Cached response doesn't exists. Make network call here
            makeNetworkCall(FEED_URL);
        }

    }

    //Update List View with the json response
    private void updateListView(News newsList) {

        updateActionBarTitle(newsList.getTitle());
        mNewsAdapter = new NewsAdapter(mContext, newsList.getRows());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mNewsAdapter);

    }

    //Update actionbar title
    private void updateActionBarTitle(String title) {
        actionBar.setTitle(title);
    }

    // Making json object request
    private void makeNetworkCall(String url) {

        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        mProgressDialog.hide();

                        String jsonString = response.toString();
                        processResponse(jsonString);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                mProgressDialog.hide();
            }
        });

        // Adding request to request queue
        RequestManager.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    //Serialize and Deserialize object
    private void processResponse(String jsonString) {
        Gson gson = new Gson();
        News newsList = gson.fromJson(jsonString, News.class);

        updateListView(newsList);
    }

}
