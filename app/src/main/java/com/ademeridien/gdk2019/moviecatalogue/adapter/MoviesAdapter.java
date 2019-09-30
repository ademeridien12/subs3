package com.ademeridien.gdk2019.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ademeridien.gdk2019.moviecatalogue.R;
import com.ademeridien.gdk2019.moviecatalogue.ViewDetailMovieActivity;
import com.ademeridien.gdk2019.moviecatalogue.model.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movie> movieList;

    public MoviesAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        holder.title.setText(movieList.get(i).getTitle());
        holder.description.setText(movieList.get(i).getOverview());

        Glide.with(context)
                .load(movieList.get(i).getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imgMovie;

        MovieViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title_film);
            description = view.findViewById(R.id.desc_film);
            imgMovie = view.findViewById(R.id.img_movie);

            view.setOnClickListener(v -> {
                int q = getAdapterPosition();
                if (q != RecyclerView.NO_POSITION) {
                    Movie movieClick = movieList.get(q);
                    Intent intent = new Intent(context, ViewDetailMovieActivity.class);
                    intent.putExtra("poster_path", movieList.get(q).getPosterPath());
                    intent.putExtra("release_date", movieList.get(q).getReleaseDate());
                    intent.putExtra("vote_average", Double.toString(movieList.get(q).getVoteAverage()));
                    intent.putExtra("original_language", movieList.get(q).getOriginalLanguage());
                    intent.putExtra("title", movieList.get(q).getOriginalTitle());
                    intent.putExtra("overview", movieList.get(q).getOverview());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    Toast.makeText(v.getContext(), movieClick.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
