package com.example.musicapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
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

    private SeekBar seekBar;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        cacheViews();
        setListeners();
        handler = new Handler();


        MainActivity.mysong.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                MainActivity.mysong.playNext();
                updateScreen();
                showSeekBar();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              if(b)
                  MainActivity.mysong.getMediaPlayer().seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        showSeekBar();
        artistImage.setImageResource(MainActivity.playList.get(MainActivity.mysong.currSongIndex).getIcon());
        tvSongTitle.setText(MainActivity.playList.get(MainActivity.mysong.currSongIndex).getSongTitle());
        iBtnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        tvAlbumTitle.setText(MainActivity.playList.get(MainActivity.mysong.currSongIndex).getAlbumName());
        tvArtistName.setText(MainActivity.playList.get(MainActivity.mysong.currSongIndex).getArtistName());

    }
    private void showSeekBar()
    {
        int duration =MainActivity.mysong.getMediaPlayer().getDuration();
        //Make sure you update Seekbar on UI thread
        seekBar.setMax(duration);
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (MainActivity.mysong.isPlaying()) {
                    int mCurrentPosition = MainActivity.mysong.getMediaPlayer().getCurrentPosition();
                    seekBar.setProgress(mCurrentPosition);
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void cacheViews() {
        iBtnNext = findViewById(R.id.iBtn_next);
        iBtnPlay = findViewById(R.id.iBtn_play);
        iBtnPrev = findViewById(R.id.iBtn_previous);
        artistImage = findViewById(R.id.artist_image);
        tvAlbumTitle = findViewById(R.id.album_name);
        tvSongTitle = findViewById(R.id.tv_song_name);
        tvArtistName = findViewById(R.id.artist_name);
        seekBar = findViewById(R.id.seek_bar);

    }

    private void updateScreen() {
        Song currSong = MainActivity.playList.get(MainActivity.mysong.currSongIndex);
        tvAlbumTitle.setText(currSong.getArtistName());
        tvSongTitle.setText(currSong.getSongTitle());
        artistImage.setImageResource(currSong.getIcon());
        tvArtistName.setText(MainActivity.playList.get(MainActivity.mysong.currSongIndex).getArtistName());
        if (!MainActivity.mysong.isPlaying())
            iBtnPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        else
            iBtnPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (MainActivity.mysong.isPlaying()) {
            iBtnPlay.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
        } else {
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
                seekBar.clearAnimation();
                updateScreen();
            }
        });
        iBtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mysong.playPrev();
                seekBar.clearAnimation();
                updateScreen();
            }
        });

    }
}
