package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlbumsActivity extends AppCompatActivity implements AlbumsAdapter.OnClickItemListener {

    private ArrayList<Album> albumList;
    private RecyclerView albums;
    private RecyclerView.LayoutManager layoutManager;
    private AlbumsAdapter albumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        initViews();
        loadAlbums();
        /*
               initializing vertical recycler view with 2 columns
         */
        layoutManager = new GridLayoutManager(this, 2);
        albums.setLayoutManager(layoutManager);
        albumsAdapter = new AlbumsAdapter(this, albumList);
        albums.setAdapter(albumsAdapter);
        /*
            initializing navigation bar
         */
        setNavBar();
    }

    /*
        Name: initViews
        parameters: None
        function : Initializing views
        return : void
     */
    private void initViews() {
        albums = findViewById(R.id.rv_albums);
    }

    @Override
    public void OnClickItem(int position) {
        /*
            this method is supposed to open Songs of a certain album
         */
        MainActivity.showToast(this, getString(R.string.open) +
                getString(R.string.album, albumList.get(position).getAlbumName()), Toast.LENGTH_SHORT);
    }

    /*
       Name: setNabBar
       parameters: None
       function : Initializing Navigation Bar and sets its listeners
       return : void
    */
    private void setNavBar() {
        Button btnSearch = findViewById(R.id.btn_search);
        Button btnAlbum = findViewById(R.id.btn_library);
        Button btnBuyOnline = findViewById(R.id.btn_buy_online);
        Button btnHome = findViewById(R.id.btn_home);
        btnAlbum.setBackgroundResource(R.color.colorAccent);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AlbumsActivity.this, Search.class));
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AlbumsActivity.this, MainActivity.class));
            }
        });
        btnBuyOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AlbumsActivity.this, Online.class));
            }
        });
    }
    /*
           Name: loadAlbums
           parameters: None
           function : load offline Albums
           return : void
        */
    private void loadAlbums() {
        /*
             this is just for checking
             strings are just for checking, there will not be any hard string when real loading
         */
        albumList = new ArrayList<Album>();
        albumList.add(new Album(MainActivity.playList,
                "me before you", "khaled", R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList,
                "me before you", "khaled", R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList,
                "me before you", "khaled", R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList,
                "me before you", "khaled", R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList,
                "me before you", "khaled", R.drawable.ic_launcher_background));
    }
}
