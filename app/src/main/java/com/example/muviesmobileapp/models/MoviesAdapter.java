package com.example.muviesmobileapp.models;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.muviesmobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();

    public void setOnReachEndListener(MoviesAdapter.onReachEndListener onReachEndListener) {
        this.reachEndListener = onReachEndListener;
    }

    private onReachEndListener reachEndListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getPreviewUrl())
                .into(holder.imageViewHolder);

        double rating = movie.getRating().getRating();
        int backgroundId;
        if (rating > 7) {
            backgroundId = R.drawable.circle_green;
        } else if (rating > 5) {
            backgroundId = R.drawable.circle_yellow;
        } else {
            backgroundId = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewHolder.setBackground(background);
        holder.textViewHolder.setText(String.format("%.1f", movie.getRating().getRating()));

        if (position == movies.size() - 10 && reachEndListener != null) {
            reachEndListener.onReachEnd();
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface onReachEndListener {
        void onReachEnd();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewHolder;
        private TextView textViewHolder;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewHolder = itemView.findViewById(R.id.action_image_Movie_item);
            textViewHolder = itemView.findViewById(R.id.text_view_poster_Movie_item);
        }
    }
}
