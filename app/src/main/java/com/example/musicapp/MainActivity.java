package com.example.musicapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton iBtnPlay;
    private ListView lvPlayList;
    private RelativeLayout playListController;
    public static ArrayList<Song> playList;
    public static Toast mToast;
    public static Media mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNavBar();
        initViews();
        /*
           check if playlist was not loaded
           else just show current song at controller
        */
        if (playList == null) {
            playList = new ArrayList<Song>();
            loadSongs();
            mySong = new Media();
        } else {
            showController();
        }
        SongItemAdapter playListAdapter = new SongItemAdapter(this, playList);
        lvPlayList.setAdapter(playListAdapter);
        lvPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mySong.currSongIndex = i;
                showController();
                mySong.playAudio();
            }
        });

    }

    /*
      Name: showController
      parameters: None
      function : initialize view of controller and update
      their data with current song's data.
      return : void
   */
    private void showController() {
        Song currSong = playList.get(mySong.currSongIndex);
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
        if (mySong.isPlaying()) {
            iBtnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
            showController();
        } else {
            iBtnPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
    }

    /*
          Name: show toast
          parameters: context (of the current activity)
                        , msg (message wanted to be displayed
                        , period (of toast)
          function : displays toasts properly
          return : void
       */
    public static void showToast(Context context, String msg, int period) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(context, msg, period);
        mToast.show();
    }

    /*
          Name: setNavBar
          parameters: None
          function : initialize all Buttons of the navigation bar and its listeners
          return : void
       */
    private void setNavBar() {
        Button btnSearch = findViewById(R.id.btn_search);
        Button btnLibrary = findViewById(R.id.btn_library);
        Button btnBuyOnline = findViewById(R.id.btn_buy_online);
        Button btnHome = findViewById(R.id.btn_home);
        btnHome.setBackgroundResource(R.color.colorAccent);
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
    }

    /*
          Name: initViews
          parameters: None
          function : Initializing views and listeners
          return : void
       */
    private void initViews() {
        iBtnPlay = findViewById(R.id.iBtn_play);
        lvPlayList = findViewById(R.id.main_activity_lv_songs);
        playListController = findViewById(R.id.main_activity_controller);
        iBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mySong.isPlaying()) {
                    iBtnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
                    mySong.start();
                    showToast(MainActivity.this, getString(R.string.playing), Toast.LENGTH_SHORT);
                } else {
                    iBtnPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    mySong.pause();
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
      class: Media
      function : creates media player and handle all media functions
   */
    public class Media {
        private AudioManager audioManager;
        private MediaPlayer mediaPlayer = null;
        public int currSongIndex = 0;
        private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int i) {
                switch (i) {
                    case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                        mediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        mediaPlayer.stop();
                        releaseAudio();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        mediaPlayer.pause();
                        break;
                    case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                        mediaPlayer.pause();
                        break;
                }
            }
        };

        public void releaseAudio() {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }

        public Media() {
        }

        public boolean isNull() {
            if (mediaPlayer == null)
                return true;
            return false;
        }

        public boolean isPlaying() {
            if (mediaPlayer != null)
                return mediaPlayer.isPlaying();
            return false;
        }

        public void pause() {
            if (mediaPlayer != null)
                mediaPlayer.pause();
        }

        public void start() {
            if (mediaPlayer != null)
                mediaPlayer.start();
        }

        public void playPrev() {
            if (mediaPlayer != null) {
                if (currSongIndex > 0)
                    currSongIndex--;
                else Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.first), Toast.LENGTH_SHORT).show();
                playAudio();
            }
        }

        /*
                create media player and start it
         */
        public void playAudio() {
            releaseAudio();
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_REQUEST_GRANTED);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), MainActivity.playList.get(currSongIndex).getSongResource());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        playNext();
                    }
                });
            }
        }

        public MediaPlayer getMediaPlayer() {
            return mediaPlayer;
        }

        // play next audio
        public void playNext() {
            if (mediaPlayer != null) {
                if (currSongIndex < MainActivity.playList.size() - 1)
                    currSongIndex++;
                else {
                    currSongIndex = 0;
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.first), Toast.LENGTH_SHORT).show();
                }
                playAudio();

            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*
        i don't want to stop the music here.
        i want it to still plays when the app is invisible
         */
    }

    /*
    add a song to the list.
    can be accessed from other activities
     */
    static public void addSong(Song song) {
        if (playList != null)
            playList.add(song);
    }

    private void loadSongs() {
        /*
         this method is supposed to load the audio files from device
         and add them to playlist ArrayList.
         hard code is just for check case, and there won't be any for real loading
         */
        int id = 0;
        playList.add(new Song(R.raw.believer, R.mipmap.ic_launcher,
                "Dragons", "this is a song published at 2017", "believer", id,
                "believer"));
        playList.add(new Song(R.raw.alekhlas, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Ekhlas", ++id));
        playList.add(new Song(R.raw.youcametome, R.mipmap.ic_launcher,
                "Samy Youssef", "this is a song published at 2017", "you came to me", ++id,
                "you came to me"));
        playList.add(new Song(R.raw.alhumaza, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Hummaza", ++id));
        playList.add(new Song(R.raw.alkaferoon, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Kaferoon", ++id));
        playList.add(new Song(R.raw.lovethewayyouare, R.mipmap.ic_launcher,
                "Unknown", "this is a song published at 2017", "love the way you are", ++id));
        playList.add(new Song(R.raw.almaaon, R.mipmap.ic_launcher,
                "Mushary Al Afasy", "this is a song published at 2017", "Al-Maaon", ++id));

    }

}
