package com.example.musicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Online extends AppCompatActivity implements onlineAdapter.OnClickItemListener {

    private RecyclerView onlineRecycler;
    private RecyclerView.LayoutManager linearManager;
    public static ArrayList<Song> onlineList;
    private SearchView searchView;
    private ArrayList<Song> foundSongs;
    private TextView tvNotFound;
    private onlineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        }
        attachViews();
        loadOnlineFiles();
        /*
             recycler view
         */
        linearManager = new LinearLayoutManager(this);
        onlineRecycler.setLayoutManager(linearManager);
        adapter = new onlineAdapter(this, onlineList);
        onlineRecycler.setAdapter(adapter);
        foundSongs = new ArrayList<>();
    }

    /*
        this function handles the action when the user clicks on a song
        it should take him to NowPlaying activity if it was downloaded
     */
    @Override
    public void OnClickItem(int position) {
        MainActivity.mysong = new Media(this, onlineList);
        Media.currSongIndex = onlineList.get(position).getId();
    }

    @Override
    public void downloadSong(int id) {
            /*
                this is supposed to download song online
             */
        MainActivity.showToast(this, getString(R.string.succeeded), Toast.LENGTH_SHORT);

    }

    private void attachViews() {
        onlineRecycler = findViewById(R.id.online_recycler);
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchForSongOnline(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchForSongOnline(newText);
                return true;
            }
        });
    }

    private void searchForSongOnline(String songName) {
        /*
             suppose to  search online

             this code is just for checking
         */
        foundSongs.clear();
        for (int i = 0; i < MainActivity.playList.size(); i++) {
            if (MainActivity.playList.get(i).getSongTitle().contains(songName)) {
                foundSongs.add(MainActivity.playList.get(i));
            }
        }
        if (searchView.getQuery().length() != 0 && foundSongs.size() == 0) {
            tvNotFound.setVisibility(View.VISIBLE);
        } else {
            tvNotFound.setVisibility(View.INVISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    private void loadOnlineFiles() {

        /*
            supposed to load files online

            this code is just for checking
         */
        onlineList = new ArrayList<Song>();
        int id = 0;
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));

    }
}

