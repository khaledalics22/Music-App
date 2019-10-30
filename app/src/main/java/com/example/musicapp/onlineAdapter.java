package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class onlineAdapter extends RecyclerView.Adapter<onlineAdapter.SongHolder> {
    ArrayList<Song> songList;
    OnClickItemListener clickHandler;

    interface OnClickItemListener {
        void OnClickItem(int position);

        void downloadSong(int position);
    }

    public onlineAdapter(Context context, ArrayList<Song> songList) {
        clickHandler = (OnClickItemListener) context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_list_item, parent, false);
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position, @NonNull List<Object> payloads) {
        holder.bind(position);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {

    }

    public class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView songName;
        private TextView artistName;
        private ImageView songIcon;
        private ImageButton download;

        private int position;

        @Override
        public void onClick(View view) {
            clickHandler.OnClickItem(position);
        }

        public SongHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.tv_song_name);
            songIcon = itemView.findViewById(R.id.song_icon);
            artistName = itemView.findViewById(R.id.artist_name);
            download = itemView.findViewById(R.id.download_song);


        }

        public void bind(final int index) {
            position = index;
            final Song song = songList.get(index);
            songName.setText(song.getSongTitle());
            artistName.setText(song.getArtistName());
            songIcon.setImageResource(song.getIcon());
            download.setVisibility(View.VISIBLE);
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickHandler.downloadSong(index);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}
