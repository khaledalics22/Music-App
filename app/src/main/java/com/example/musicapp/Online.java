package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        initViews();
        loadOnlineFiles();
        setNavBar();
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
             Name: setNavBar
             parameters: None
             function : initialize all Buttons of the navigation bar and its listeners
             return : void
          */
    private void setNavBar() {
        Button btnSearch = findViewById(R.id.btn_search);
        Button btnAlbum = findViewById(R.id.btn_library);
        Button btnBuyOnline = findViewById(R.id.btn_buy_online);
        Button btnHome = findViewById(R.id.btn_home);
        btnBuyOnline.setBackgroundResource(R.color.colorAccent);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Online.this, Search.class));
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Online.this, MainActivity.class));
            }
        });
        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Online.this, AlbumsActivity.class));
            }
        });
    }

    /*
        this function handles the action when the user clicks on a song
        it should take him to NowPlaying activity if it was downloaded
     */
    @Override
    public void OnClickItem(int position) {
        MainActivity.showToast(this, getString(R.string.not_available_offline), Toast.LENGTH_SHORT);
    }

    /*
         Name: downloadSong
         parameters: int position (of song in online list)
         function : 1-downloads the chosen song and add it to the offline list
                    2-remove it from online list
                    3-and updates the list displayed
         return : void
      */
    @Override
    public void downloadSong(int position) {
            /*
                this is supposed to download songs to be available offline
             */
        MainActivity.addSong(onlineList.get(position));  // add it to my offline list
        onlineList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, onlineList.size());
        MainActivity.showToast(this, getString(R.string.succeeded), Toast.LENGTH_SHORT);
    }
    /*
             Name: initViews
             parameters: None
             function : initialize Views of activity and listeners
             return : void
          */
    private void initViews() {
        onlineRecycler = findViewById(R.id.online_recycler);
        searchView = findViewById(R.id.search_view);
        tvNotFound = findViewById(R.id.tv_not_found);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (foundSongs.size() != 0) {
                    onlineList.clear();
                    onlineList.addAll(foundSongs);
                    foundSongs.clear();
                }
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchForSongOnline(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchForSongOnline(newText);
                if (newText.matches(""))
                    if (foundSongs.size() != 0) {
                        onlineList.clear();
                        onlineList.addAll(foundSongs);
                        foundSongs.clear();
                    }
                return true;
            }
        });
    }

    /*
         Name: searchForSongOnline
         parameters: songName (name of song )
         function : searches for song online and show it
         return : void
      */
    private void searchForSongOnline(String songName) {
        /*
             suppose to  search online
             this code is just for checking
         */
        if(foundSongs.size()>0)onlineList.clear();
        onlineList.addAll(foundSongs);
        foundSongs.clear();
        foundSongs.addAll(onlineList);
        for (int i = 0; i < onlineList.size(); i++) {
            if (searchView.getQuery().length()> 0 &&!onlineList.get(i).getSongTitle().contains(songName)) {  //check if query is not empty and song is available
                onlineList.remove(onlineList.get(i));
                i--;
            }
        }
        adapter.notifyDataSetChanged();
        if (searchView.getQuery().length() != 0 && onlineList.size() == 0) {
            tvNotFound.setVisibility(View.VISIBLE);
        } else {
            tvNotFound.setVisibility(View.INVISIBLE);
        }
    }
    /*
             Name: loadOnlineFiles
             parameters: None
             function : load audio files to show it
             return : void
          */
    private void loadOnlineFiles() {

        /*
            supposed to load files online
            that is updated every time the user refresh

            this code is just for checking
         */
        onlineList = new ArrayList<Song>();
        int id = 0;
          onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "catch", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "so on", ++id));
           onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "try", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "match", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "play", ++id));
        onlineList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "found", ++id));
    }
}

