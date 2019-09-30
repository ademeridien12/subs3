package com.ademeridien.gdk2019.moviecatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class ViewDetailMovieActivity extends AppCompatActivity {
    TextView title, overview, release, language, voteAverage;
    ImageView poster;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detail_movie);

        poster = findViewById(R.id.image_film);
        title = findViewById(R.id.tv_title);
        overview = findViewById(R.id.tv_description);
        release = findViewById(R.id.tv_release);
        language = findViewById(R.id.tv_language);
        voteAverage = findViewById(R.id.tv_voteAverage);

        Intent detailMovieIntent = getIntent();
        if (detailMovieIntent.hasExtra("title")) {
            String mPoster = Objects.requireNonNull(getIntent().getExtras()).getString("poster_path");
            String mTitle = getIntent().getExtras().getString("title");
            String mOverview = getIntent().getExtras().getString("overview");
            String mRelease = getIntent().getExtras().getString("release_date");
            String mLanguage = getIntent().getExtras().getString("original_language");
            String mVoteAverage = getIntent().getExtras().getString("vote_average");

            Glide.with(this)
                    .load(mPoster)
                    .placeholder(R.color.colorPrimary)
                    .into(poster);
            title.setText(mTitle);
            overview.setText(mOverview);
            release.setText(mRelease);
            language.setText(mLanguage);
            voteAverage.setText(mVoteAverage);
        } else {
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }
    }
}
