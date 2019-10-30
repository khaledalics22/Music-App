package com.example.musicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NowPlaying extends AppCompatActivity {
    private ImageButton iBtnPlay;
    private ImageButton iBtnPrev;
    private ImageButton iBtnNext;
    private ImageView artistImage;
    private TextView tvAlbumTitle;
    private TextView tvSongTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        cacheViews();
        setListeners();

        if (!MainActivity.mysong.isNull()) {

            artistImage.setImageResource(MainActivity.playList.get(MainActivity.mysong.currSongIndex).getIcon());
            tvSongTitle.setText(MainActivity.playList.get(MainActivity.mysong.currSongIndex).getSongTitle());
            //TODO change the function below to get Album
            iBtnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
            tvAlbumTitle.setText(MainActivity.playList.get(MainActivity.mysong.currSongIndex).getArtistName());
        }
    }

    private void cacheViews() {
        iBtnNext = findViewById(R.id.iBtn_next);
        iBtnPlay = findViewById(R.id.iBtn_play);
        iBtnPrev = findViewById(R.id.iBtn_previous);
        artistImage = findViewById(R.id.artist_image);
        tvAlbumTitle = findViewById(R.id.album_name);
        tvSongTitle = findViewById(R.id.tv_song_name);
    }

    private void updateScreen() {
        Song currSong = MainActivity.playList.get(MainActivity.mysong.currSongIndex);
        tvAlbumTitle.setText(currSong.getArtistName());
        tvSongTitle.setText(currSong.getSongTitle());
        artistImage.setImageResource(currSong.getIcon());
        if (!MainActivity.mysong.isPlaying())
            iBtnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        else
            iBtnPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(MainActivity.mysong.isPlaying())
        {
            iBtnPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
        }
        else
        {
            iBtnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        }
    }

    private void setListeners() {
        iBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.isPlaying) {
                    if (!MainActivity.mysong.isNull()) {
                        MainActivity.isPlaying = true;
                        MainActivity.mysong.start();
                        updateScreen();
                    }
                } else {
                    if (!MainActivity.mysong.isNull()) {
                        MainActivity.mysong.pause();
                        MainActivity.isPlaying = false;
                        updateScreen();
                    }
                }
            }
        });
        iBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mysong.playNext();
                updateScreen();
            }
        });
        iBtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mysong.playPrev();
                updateScreen();
            }
        });

    }
}
