package com.wipro.telstra.com.wipro.telstra.model;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wipro.telstra.R;

import java.util.ArrayList;

/**
 * Created by Anto Stephen on 27/09/2016.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private ArrayList<NewsRow> newsList;
    private Context mContext;

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

        NewsRow item = newsList.get(position);
        String url = item.getImageHref();
        String description;

        holder.txtTitle.setText(item.getTitle());
        description = item.getDescription();
        if(description == null) {
            description = mContext.getString(R.string.description);
        }

        if (holder.imgFeed == null) {
            holder.imgFeed = new ImageView(mContext);
        }

        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.img_not_available)
                .error(R.drawable.img_not_available)
                .into(holder.imgFeed);
        holder.webview.loadData("<html><body>" + "<p align= \"justify\">" + description + "</p> " + "</body></html>", "text/html", "utf-8");
    }

    public NewsAdapter(Context context, ArrayList<NewsRow> feedList) {
        this.newsList = feedList;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFeed;
        TextView txtTitle;
        WebView webview;

        public ViewHolder(View view){
            super(view);
            imgFeed = (ImageView) view.findViewById(R.id.feed_image);
            txtTitle = (TextView) view.findViewById(R.id.feed_title);
            webview = (WebView) view.findViewById(R.id.webview);
        }
    }
}
