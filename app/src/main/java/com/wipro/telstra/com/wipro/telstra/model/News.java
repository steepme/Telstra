package com.wipro.telstra.com.wipro.telstra.model;

import java.util.ArrayList;

/**
 * Created by Anto Stephen on 24/09/2016
 */


public class News
{
    private String title;

    private ArrayList<NewsRow> rows;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public ArrayList<NewsRow> getRows ()
    {
        return rows;
    }

    public void setRows (ArrayList<NewsRow> rows)
    {
        this.rows = rows;
    }
}
