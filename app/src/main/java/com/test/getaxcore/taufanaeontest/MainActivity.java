package com.test.getaxcore.taufanaeontest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.getaxcore.taufanaeontest.adapter.ImageAdapterDB;
import com.test.getaxcore.taufanaeontest.database.PhotosDB;
import com.test.getaxcore.taufanaeontest.model.Photos;
import com.test.getaxcore.taufanaeontest.service.BackgroundService;

import java.util.List;

public class MainActivity extends Activity {

    RecyclerView photosList;
    PhotosDB photosDB;
    private BackgroundService backgroundService;
    private Context context = this;
    Intent serviceIntent;
    private static final String TAG = MainActivity.class.getSimpleName();
    EditText textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        photosList = (RecyclerView)findViewById(R.id.imageList);
        photosDB = new PhotosDB(context);
        textSearch = (EditText)findViewById(R.id.txtSearch);

        backgroundService = new BackgroundService(context);
        serviceIntent = new Intent(context,backgroundService.getClass());
        if (!isRunningService(backgroundService.getClass())){
            startService(serviceIntent);
            Toast.makeText(getApplicationContext(),"Background service is running...",Toast.LENGTH_LONG).show();
        }

        //getPhotosList
        ByActionSearch();
        getPhotoFromDB();

    }

    private boolean isRunningService(Class<?> serviceClass){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if (serviceClass.getName().equals(service.service.getClassName())){
                Log.i("isMyServiceRunning?", true+"");
                Toast.makeText(getApplicationContext(),"Background service is running...",Toast.LENGTH_LONG).show();
                return true;
            }
        }

        return false;
    }


    private void ByActionSearch(){
        textSearch.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    String keyTitle = String.valueOf(textSearch.getText());

                    //get by title
                    final List<Photos> list = photosDB.getPhotosByTitle(keyTitle);

                    ImageAdapterDB adapterDB = new ImageAdapterDB(MainActivity.this);
                    adapterDB.setList(list); //add photos to list

                    //add to recyclerView
                    photosList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    photosList.setAdapter(adapterDB);

                    return true;
                }
                return false;
            }
        });
    }

    private void getPhotoFromDB(){

        //get all
        final List<Photos> list = photosDB.getAllPhotos();

        ImageAdapterDB adapterDB = new ImageAdapterDB(MainActivity.this);
        adapterDB.setList(list); //add photos to list

        //add to recyclerView
        photosList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        photosList.setAdapter(adapterDB);

    }

}
