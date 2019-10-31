package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private ArrayList<Song> foundSongs;
    private SongItemAdapter songItemAdapter;
    private SearchView searchView;
    private TextView tvNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tvNotFound = findViewById(R.id.not_found);
        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchForSong(searchView.getQuery().toString());
                return false;
            }
        });
        /*
            I had to use ListView because it was required otherwise i would use recycler view
         */
        final ListView listView = findViewById(R.id.search_lv);
        foundSongs = new ArrayList<Song>();
        songItemAdapter = new SongItemAdapter(this, foundSongs);
        listView.setAdapter(songItemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.mySong.currSongIndex = foundSongs.get(i).getId();
                MainActivity.mySong.playAudio();
                startActivity(new Intent(Search.this, NowPlaying.class));
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(true);
            }
        });
        setNavBar();
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
        btnSearch.setBackgroundResource(R.color.colorAccent);
        btnBuyOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Search.this, Online.class));
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Search.this, MainActivity.class));
            }
        });
        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Search.this, AlbumsActivity.class));
            }
        });
    }
    /*
               Name: searchForSong
               parameters: string songName (name of wanted song )
               function : searches for a song in the offline list(on device)
               return : void
            */
    private void searchForSong(String songName) {
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
        songItemAdapter.notifyDataSetChanged();
    }
}
