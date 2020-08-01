package com.test.getaxcore.taufanaeontest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.getaxcore.taufanaeontest.model.Photos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 001910947 on 7/31/2020.
 */

public class PhotosDB extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 14;
    private static final String DATABASE_NAME = "PhotoManager";
    private static final String TABLE_NAME = "Photos";

    private static final String IDs = "Ids";
    private static final String ALBUM_ID = "albumId";
    private static final String PHOTO_ID = "id";
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String THUMBNAIL_URL = "thumbnailUrl";

    public PhotosDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PHOTOS_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "
                +IDs+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +ALBUM_ID+" TEXT,"
                +PHOTO_ID+" TEXT,"
                +TITLE+" TEXT,"
                +URL+" TEXT,"
                +THUMBNAIL_URL+" TEXT "
                +")";

        //create table Photos
        db.execSQL(CREATE_PHOTOS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int i,int il){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(db);
    }

    //insert
    public void addPhoto(Photos photos){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ALBUM_ID,photos.getAlbumId());
        values.put(PHOTO_ID,photos.getId());
        values.put(TITLE,photos.getTitle());
        values.put(URL,photos.getUrl());
        values.put(THUMBNAIL_URL,photos.getThumbnailUrl());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //get all photos
    public List<Photos> getAllPhotos(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Photos> photosList = new ArrayList<Photos>();

        //select query all photos
        String selectQuery = "SELECT "+TITLE+","+THUMBNAIL_URL+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery,null);

        //add to list
        if (cursor.moveToFirst()){
            do {
                Photos photos = new Photos();
                photos.setTitle(cursor.getString(0));
                photos.setThumbnailUrl(cursor.getString(1));

                photosList.add(photos);
            }while (cursor.moveToNext());
        }
        return photosList;
    }

    //for search photos by title
    public List<Photos> getPhotosByTitle(String title){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Photos> photosList = new ArrayList<Photos>();

        //select by title like
        String selectQuery = "SELECT "+TITLE+","+THUMBNAIL_URL+" FROM "+TABLE_NAME+" WHERE "+TITLE+" LIKE '%"+title+"%'";
        Cursor cursor = db.rawQuery(selectQuery,null);

        //add to list
        if (cursor.moveToFirst()){
            do {
                Photos photos = new Photos();
                photos.setTitle(cursor.getString(0));
                photos.setThumbnailUrl(cursor.getString(1));

                photosList.add(photos);
            }while (cursor.moveToNext());
        }
        return photosList;
    }

    //deleteAll
    public void deletePhotos(){
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "DELETE FROM "+TABLE_NAME;

        db.execSQL(selectQuery);
    }

    public int getPhotosCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectCount = "SELECT COUNT(*) FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(selectCount,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        return count;
    }
}
