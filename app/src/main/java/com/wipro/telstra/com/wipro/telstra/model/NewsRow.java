package com.wipro.telstra.com.wipro.telstra.model;

/**
 * Created by Anto Stephen on 24/09/2016.
 *
 * This class is a data model class with Getter and Setter methods to hold the property values
 */

public class NewsRow {
    private String title;

    private String description;

    private String imageHref;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getImageHref ()
    {
        return imageHref;
    }

    public void setImageHref (String imageHref)
    {
        this.imageHref = imageHref;
    }

}
