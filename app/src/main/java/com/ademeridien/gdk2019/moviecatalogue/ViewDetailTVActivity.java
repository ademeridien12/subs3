package com.ademeridien.gdk2019.moviecatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class ViewDetailTVActivity extends AppCompatActivity {
    TextView title, overview, release, language, voteAverage;
    ImageView poster;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detail_tv);

        poster = findViewById(R.id.image_tv);
        title = findViewById(R.id.tv_title);
        overview = findViewById(R.id.tv_overview);
        release = findViewById(R.id.tv_firstRelease);
        language = findViewById(R.id.tv_language);
        voteAverage = findViewById(R.id.tv_userscore);

        Intent detailTvShowIntent = getIntent();
        if (detailTvShowIntent.hasExtra("name")) {
            String mPoster = Objects.requireNonNull(getIntent().getExtras()).getString("poster_path");
            String mTitle = getIntent().getExtras().getString("name");
            String mOverview = getIntent().getExtras().getString("overview");
            String mRelease = getIntent().getExtras().getString("first_air_date");
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