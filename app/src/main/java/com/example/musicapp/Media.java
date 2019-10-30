package com.example.musicapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.util.ArrayList;

public class Media {
    private AudioManager audioManager;
    public static int currSongIndex = 0;
    public static MediaPlayer mediaPlayer = null;
    ArrayList<Song> songs;
    Context context;
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

    public Media(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    public void releaseAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
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
        mediaPlayer.pause();
    }

    public void start() {
        if (mediaPlayer != null)
            mediaPlayer.start();
    }

    public void playPrev() {
        if (currSongIndex > 0)
            currSongIndex--;
        else Toast.makeText(context.getApplicationContext(),
                context.getResources().getString(R.string.first), Toast.LENGTH_SHORT).show();
        playAudio();
    }

    public void playAudio() {
        releaseAudio();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_REQUEST_GRANTED);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mediaPlayer = MediaPlayer.create(context.getApplicationContext(), MainActivity.playList.get(currSongIndex).getSongResource());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseAudio();
                }
            });
        }
    }

    public void playNext() {
        if (currSongIndex < MainActivity.playList.size() - 1)
            currSongIndex++;
        else {
            currSongIndex = 0;
            Toast.makeText(context.getApplicationContext(),
                    context.getResources().getString(R.string.first), Toast.LENGTH_SHORT).show();
        }
        playAudio();
    }
}

