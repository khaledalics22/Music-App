package com.example.musicapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton iBtnPlay;
    private ListView lvPlayList;

    public static ArrayList<Song> playList;
    private RelativeLayout playListController;
    public static Toast mToast;
    public static Media mysong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cacheViews();

        playList = new ArrayList<Song>();
        loadSongs();
        mysong = new Media(MainActivity.this, playList);
        SongItemAdapter playListAdapter = new SongItemAdapter(this, playList);
        lvPlayList.setAdapter(playListAdapter);
        lvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Media.currSongIndex = i;
                isPlaying = true;
                showController();
                mysong.playAudio();
            }
        });
    }

    private void showController() {
        Song currSong = playList.get(Media.currSongIndex);
        playListController.setVisibility(View.VISIBLE);
        ImageView iv = playListController.findViewById(R.id.frame_artist_image);
        iv.setImageResource(currSong.getIcon());
        iBtnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
        TextView tvSongName;
        TextView tvArtistName;
        tvSongName = findViewById(R.id.tv_song_name_plController);
        tvArtistName = findViewById(R.id.tv_artist_plController);
        tvArtistName.setText(getString(R.string.Artist_name, currSong.getArtistName()));
        tvSongName.setText(getString(R.string.song_name, currSong.getSongTitle()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mysong.isPlaying()) {
            iBtnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
        } else {
            iBtnPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
    }

    public static void showToast(Context context, String msg, int period) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(context, msg, period);
        mToast.show();
    }

    private void cacheViews() {
        iBtnPlay = findViewById(R.id.iBtn_play);
        lvPlayList = findViewById(R.id.main_activity_lv_songs);
        playListController = findViewById(R.id.main_activity_controller);
        Button btnSearch = findViewById(R.id.btn_search);
        Button btnLibrary = findViewById(R.id.btn_library);
        Button btnBuyOnline = findViewById(R.id.btn_buy_online);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });
        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AlbumsActivity.class));
            }
        });
        btnBuyOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Online.class));
            }
        });
        iBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlaying) {
                    iBtnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
                    isPlaying = true;
                    mysong.start();
                    showToast(MainActivity.this, getString(R.string.playing), Toast.LENGTH_SHORT);
                } else {
                    iBtnPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    isPlaying = false;
                    mysong.pause();
                    showToast(MainActivity.this, getString(R.string.paused), Toast.LENGTH_SHORT);
                }
            }
        });
        playListController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NowPlaying.class));
            }
        });
    }

    /*
    this variable is used temporarily to indicate if the song is played or paused
     */
    public static boolean isPlaying = false;

    private void loadSongs() {
        /*
         here we are supposed to load the media files from device
         and add data to playlist ArrayList
         */
        int id = 0;
        playList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "believer", id));
        playList.add(new Song(R.raw.alekhlas, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Ekhlas", ++id));
        playList.add(new Song(R.raw.youcametome, R.mipmap.ic_launcher,
                "Samy Youssef", "this is a song published at 2017", "youcametome", ++id));
        playList.add(new Song(R.raw.alhumaza, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Hummaza", ++id));
        playList.add(new Song(R.raw.alkaferoon, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Kaferoon", ++id));
        playList.add(new Song(R.raw.lovethewayyouare, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "lovethewayyouare", ++id));
        playList.add(new Song(R.raw.almaaon, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Maaon", ++id));
        playList.add(new Song(R.raw.almasad, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-masad", ++id));
        playList.add(new Song(R.raw.alnasr, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Nasr", ++id));
        playList.add(new Song(R.raw.altakathur, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Takathur", ++id));
        playList.add(new Song(R.raw.kareaa, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-kareaa", ++id));
    }


}
