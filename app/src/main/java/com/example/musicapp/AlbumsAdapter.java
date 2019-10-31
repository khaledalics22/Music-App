package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumHolder> {
    private ArrayList<Album> albumList;
    OnClickItemListener onClickItem;

    public interface OnClickItemListener {
        void OnClickItem(int position);
    }

    public AlbumsAdapter(OnClickItemListener listener, ArrayList<Album> albumList) {
        onClickItem = listener;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumsAdapter.AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_view_item, parent, false);

        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        holder.bind(position);
    }

    public class AlbumHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView albumName;
        TextView artistName;
        ImageView albumImage;

        @Override
        public void onClick(View view) {
            onClickItem.OnClickItem(getAdapterPosition());
        }

        public AlbumHolder(@NonNull View itemView) {
            super(itemView);
            albumName = itemView.findViewById(R.id.album_name);
            artistName = itemView.findViewById(R.id.artist_name);
            albumImage = itemView.findViewById(R.id.album_image);
            itemView.setOnClickListener(this);

        }

        public void bind(int index) {
            Album album = albumList.get(index);
            albumName.setText(album.getAlbumName());
            albumImage.setImageResource(album.getAlbumImage());
            artistName.setText(album.getArtist());
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
