package com.ademeridien.gdk2019.moviecatalogue;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ademeridien.gdk2019.moviecatalogue.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tablayout_id);
        ViewPager viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new MovieFragment());
        adapter.AddFragment(new TvShowFragment());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_movie).setText(R.string.tab_text_1);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_live_tv).setText(R.string.tab_text_2);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.language_settings){
            showLanguageSettingsDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLanguageSettingsDialog() {
        final String[] langItems = {"English", "Indonesia"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.select_language)
                .setSingleChoiceItems(langItems, -1, (dialogInterface, i) -> {
                    if(i == 0){
                        setLocale("en");
                        recreate();
                    }
                    else if(i == 1){
                        setLocale("id");
                        recreate();
                    }
                    dialogInterface.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("App_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences pref = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = pref.getString("App_Lang","");
        setLocale(language);
    }
}