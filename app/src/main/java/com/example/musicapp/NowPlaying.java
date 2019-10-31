package com.example.musicapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NowPlaying extends AppCompatActivity {
    private ImageButton iBtnPlay;
    private ImageButton iBtnPrev;
    private ImageButton iBtnNext;
    private ImageView artistImage;
    private TextView tvAlbumTitle;
    private TextView tvSongTitle;
    private TextView tvArtistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        initViews();
        setListeners();
        MainActivity.mySong.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                MainActivity.mySong.playNext();
                updateScreen();
            }
        });

        artistImage.setImageResource(MainActivity.playList.get(MainActivity.mySong.currSongIndex).getIcon());
        tvSongTitle.setText(MainActivity.playList.get(MainActivity.mySong.currSongIndex).getSongTitle());
        iBtnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        tvAlbumTitle.setText(MainActivity.playList.get(MainActivity.mySong.currSongIndex).getAlbumName());
        tvArtistName.setText(MainActivity.playList.get(MainActivity.mySong.currSongIndex).getArtistName());

    }
    /*
             Name: initViews
             parameters: None
             function : initialize all Views of activity
             return : void
          */
    private void initViews() {
        iBtnNext = findViewById(R.id.iBtn_next);
        iBtnPlay = findViewById(R.id.iBtn_play);
        iBtnPrev = findViewById(R.id.iBtn_previous);
        artistImage = findViewById(R.id.artist_image);
        tvAlbumTitle = findViewById(R.id.album_name);
        tvSongTitle = findViewById(R.id.tv_song_name);
        tvArtistName = findViewById(R.id.artist_name);
    }

    /*
             Name: updateScreen
             parameters: None
             function : updates the views of screen with current song's data
             return : void
          */
    private void updateScreen() {
        Song currSong = MainActivity.playList.get(MainActivity.mySong.currSongIndex);
        tvAlbumTitle.setText(currSong.getArtistName());
        tvSongTitle.setText(currSong.getSongTitle());
        artistImage.setImageResource(currSong.getIcon());
        tvArtistName.setText(MainActivity.playList.get(MainActivity.mySong.currSongIndex).getArtistName());
        if (!MainActivity.mySong.isPlaying())
            iBtnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        else
            iBtnPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (MainActivity.mySong.isPlaying()) {
            iBtnPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
        } else {
            iBtnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        }
    }

    /*
             Name: setListeners
             parameters: None
             function : sets Listeners of Views
             return : void
          */
    private void setListeners() {
        iBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.mySong.isPlaying()) {
                    if (!MainActivity.mySong.isNull()) {
                        MainActivity.mySong.start();

                        updateScreen();
                    }
                } else {
                    if (!MainActivity.mySong.isNull()) {
                        MainActivity.mySong.pause();
                        updateScreen();
                    }
                }
            }
        });
        iBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mySong.playNext();
                updateScreen();
            }
        });
        iBtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mySong.playPrev();
                updateScreen();
            }
        });

    }
}
