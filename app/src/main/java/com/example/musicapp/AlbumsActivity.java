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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        loadAlbums();
        albums = findViewById(R.id.rv_albums);
        layoutManager = new GridLayoutManager(this, 2);
        albums.setLayoutManager(layoutManager);
        AlbumsAdapter albumsAdapter = new AlbumsAdapter(this, albumList);
        albums.setAdapter(albumsAdapter);
        setNavBar();
    }

    @Override
    public void OnClickItem(int position) {
        MainActivity.showToast(this, getString(R.string.album,albumList.get(position).getAlbumName()), Toast.LENGTH_SHORT);
    }

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

    private void loadAlbums() {
        albumList = new ArrayList<Album>();
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
        albumList.add(new Album(MainActivity.playList, getString(R.string.album,
                "me before you"), getString(R.string.Artist_name, "khaled"), R.drawable.ic_launcher_background));
    }
}
