package com.test.getaxcore.taufanaeontest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 001910947 on 7/31/2020.
 */

public class Photos {
    @SerializedName("albumId")
    private Long albumId;
    @SerializedName("id")
    private Long id;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    public Photos(){}

    public Photos(Long albumId, Long id, String title, String url, String thumbnailUrl){
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setAlbumId(Long albumId){
        this.albumId = albumId;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setThumbnailUrl(String thumbnailUrl){
        this.thumbnailUrl = thumbnailUrl;
    }
    public Long getAlbumId(){
        return this.albumId;
    }
    public Long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getUrl(){
        return this.url;
    }
    public String getThumbnailUrl(){
        return this.thumbnailUrl;
    }
}
