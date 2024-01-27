package com.example.muviesmobileapp.models.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muviesmobileapp.R;
import com.example.muviesmobileapp.models.api.responses.aboutmovie.trailers.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private List<Trailer> trailers = new ArrayList<>();

    private OnTrailerClickListener onTrailerClickListener;

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    public void setOnTrailerClickListener(OnTrailerClickListener onTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.openTrailer.setText(trailer.getNameTrailer());

        holder.itemView.setOnClickListener((view) -> {
            if (onTrailerClickListener != null) onTrailerClickListener.onTrailerClick(trailer);
        });

    }

    public interface OnTrailerClickListener {
        void onTrailerClick(Trailer trailer);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailerViewHolder extends RecyclerView.ViewHolder {
        private TextView openTrailer;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            openTrailer = itemView.findViewById(R.id.buttonTrailer);
        }
    }
}
