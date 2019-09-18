package com.example.ammaryasser.mycolors;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Theme> themes = new ArrayList<>();
    static ClipboardManager clipboard;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFrag())
                .addToBackStack(null)
                .commit();
        BottomNavigationView bottom_nav = findViewById(R.id.bottom_navigation);
        bottom_nav.setOnNavigationItemSelectedListener(navListener);

        context = this;
        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    static void copyToClipboard(String text) {
        clipboard.setPrimaryClip(ClipData.newPlainText("Color", text));
        Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment sel_frg = new Fragment();

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    sel_frg = new HomeFrag();
                    break;
                case R.id.nav_recommend:
                    sel_frg = new RecommendFrag();
                    break;
                case R.id.nav_favourites:
                    sel_frg = new FavoriteFrag();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, sel_frg).commit();

            return true;
        }
    };

}

class FavoriteColor {
    private int id, r, g, b;
    private String hex;

    FavoriteColor(int id, int r, int g, int b, String hex) {
        this.id = id;
        this.r = r;
        this.g = g;
        this.b = b;
        this.hex = hex;
    }

    int getId() {
        return id;
    }

    int getR() {
        return r;
    }

    int getG() {
        return g;
    }

    int getB() {
        return b;
    }

    String getHex() {
        return hex;
    }
}

class Theme {
    int themeNo;
    String col_1, col_2, col_3, col_4, col_5;

    public Theme(int themeNo, String col_1, String col_2, String col_3, String col_4, String col_5) {
        this.themeNo = themeNo;
        this.col_1 = col_1;
        this.col_2 = col_2;
        this.col_3 = col_3;
        this.col_4 = col_4;
        this.col_5 = col_5;
    }

    public int getThemeNo() {
        return themeNo;
    }

    public String getCol_1() {
        return col_1;
    }

    public String getCol_2() {
        return col_2;
    }

    public String getCol_3() {
        return col_3;
    }

    public String getCol_4() {
        return col_4;
    }

    public String getCol_5() {
        return col_5;
    }
}