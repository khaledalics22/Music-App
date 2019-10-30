package com.example.musicapp;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
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
        ActionBar ab = getSupportActionBar();
        if (ab != null) ab.setDisplayHomeAsUpEnabled(true);

        loadAlbums();
        albums = findViewById(R.id.rv_albums);
        layoutManager = new GridLayoutManager(this, 2);
        albums.setLayoutManager(layoutManager);
        AlbumsAdapter albumsAdapter = new AlbumsAdapter(this, albumList);
        albums.setAdapter(albumsAdapter);
    }

    @Override
    public void OnClickItem() {

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
