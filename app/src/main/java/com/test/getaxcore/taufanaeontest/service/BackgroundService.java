package com.test.getaxcore.taufanaeontest.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.test.getaxcore.taufanaeontest.database.PhotosDB;
import com.test.getaxcore.taufanaeontest.model.Photos;
import com.test.getaxcore.taufanaeontest.restapi.ApiClient;
import com.test.getaxcore.taufanaeontest.restapi.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import retrofit2.Callback;

/**
 * Created by 001910947 on 7/31/2020.
 */

public class BackgroundService extends Service {

    private PhotosDB db;
    private static final String TAG = BackgroundService.class.getSimpleName();
    public final Context context = this;
    private Long albumId, id;
    private String title,url,thumbnailTitle;
    //private final String baseUrl = "https://jsonplaceholder.typicode.com";

    public BackgroundService(Context context){
        super();
    }
    public BackgroundService(){}

    @Override
    public int onStartCommand(Intent intent,int flags, int startId){
        getAllPhotos();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Intent broadcastIntent = new Intent("com.test.getaxcore.ActivityRecognition.RestartSensor");
        sendBroadcast(broadcastIntent);
    }

    public void getAllPhotos(){
        db = new PhotosDB(context);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<List<Photos>> resultCall = apiInterface.getResult();
        resultCall.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Photos>> call, retrofit2.Response<List<Photos>> response) {
                try {
                    Log.d(TAG, "onResponse: "+response.toString());
                    final List<Photos> list = response.body();

                    //deleteOldData
                    db.deletePhotos();

                    for (int i=0;i<list.size();i++){
                        db.addPhoto(new Photos(
                                list.get(i).getAlbumId(),
                                list.get(i).getId(),
                                list.get(i).getTitle(),
                                list.get(i).getUrl(),
                                list.get(i).getThumbnailUrl()
                        ));
                    }

                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Photos>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                Log.i("onFailure",t.getMessage());

            }
        });
    }

    public IBinder onBind(Intent intent){
        return null;
    }
}
