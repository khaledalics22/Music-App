package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SongItemAdapter extends ArrayAdapter<Song> {

    private ImageView songIcon;
    private TextView songTitle;
    private TextView songArtist;
    private ImageButton download;
    public SongItemAdapter(@NonNull Context context, @NonNull List<Song> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Song currSong = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_list_item, parent, false);
        }
        songIcon = convertView.findViewById(R.id.song_icon);
        songTitle = convertView.findViewById(R.id.tv_song_name);
        songArtist = convertView.findViewById(R.id.artist_name);
        download = convertView.findViewById(R.id.download_song);
        download.setVisibility(View.INVISIBLE);
        songIcon.setImageResource(currSong.getIcon());
        songTitle.setText(currSong.getSongTitle());
        songArtist.setText(currSong.getArtistName());
        return convertView;
    }
}
