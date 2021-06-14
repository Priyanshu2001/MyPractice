package com.prbansal.authenticationactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.prbansal.authenticationactivity.adapter.Adapter;
import com.prbansal.authenticationactivity.databinding.ActivityMain2Binding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<String> orderIDs;
    Adapter adapter;
    ActivityMain2Binding activityMain2Binding;
    MediaPlayer mediaPlayer =new MediaPlayer();
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMain2Binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(activityMain2Binding.getRoot());
        try {
            setupList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupList() throws JSONException {
        orderIDs = new ArrayList<>(Arrays.asList("hello", "and", "welcome!"));
        /*adapter =new Adapter(this,orderIDs,activityMain2Binding);
        activityMain2Binding.recyclerView.setAdapter(adapter);
        activityMain2Binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,true));
        activityMain2Binding.recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));*/
        String json= getJsonFromAssetFile(this,"songs.json");
        JSONObject object = new JSONObject(json);
        JSONArray array = object.getJSONArray("radios");
        JSONObject newObj = null;
        try {
            newObj = array.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            url = newObj.getString("url");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        activityMain2Binding.Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "played", Toast.LENGTH_SHORT).show();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
                catch (IOException e) {
                    Log.e("song", e.toString()+"prepare() failed");
                }
            }
        });

    }
    public static String getJsonFromAssetFile(Context context, String jsonFileName) {

        String json = null;
        try {

            InputStream is = context.getAssets().open(jsonFileName);

            int size = is.available ();

            byte[] buffer = new byte[size];

            is.read (buffer);

            is.close ();

            json = new String(buffer, "UTF-8");

        }
        catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
    public void getList(){

    }
}

