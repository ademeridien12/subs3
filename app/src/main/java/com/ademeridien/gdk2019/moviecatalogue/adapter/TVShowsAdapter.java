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
import com.ademeridien.gdk2019.moviecatalogue.ViewDetailTVActivity;
import com.ademeridien.gdk2019.moviecatalogue.model.TVShow;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TvViewHolder> {
    private Context context;
    private ArrayList<TVShow> tvShowList;

    public TVShowsAdapter(Context context, ArrayList<TVShow> tvShowList) {
        this.context = context;
        this.tvShowList = tvShowList;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new TvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int i) {
        holder.title.setText(tvShowList.get(i).getName());
        holder.description.setText(tvShowList.get(i).getOverview());

        Glide.with(context)
                .load(tvShowList.get(i).getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(holder.imgTv);
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imgTv;

        TvViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title_film);
            description = view.findViewById(R.id.desc_film);
            imgTv = view.findViewById(R.id.img_movie);

            view.setOnClickListener(v -> {
                int i = getAdapterPosition();
                if (i != RecyclerView.NO_POSITION) {
                    TVShow tvClick = tvShowList.get(i);
                    Intent intent = new Intent(context, ViewDetailTVActivity.class);
                    intent.putExtra("name", tvShowList.get(i).getName());
                    intent.putExtra("poster_path", tvShowList.get(i).getPosterPath());
                    intent.putExtra("first_air_date", tvShowList.get(i).getFirstAirDate());
                    intent.putExtra("vote_average", Double.toString(tvShowList.get(i).getVoteAverage()));
                    intent.putExtra("original_language", tvShowList.get(i).getOriginalLanguage());
                    intent.putExtra("overview", tvShowList.get(i).getOverview());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    Toast.makeText(v.getContext(), tvClick.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
