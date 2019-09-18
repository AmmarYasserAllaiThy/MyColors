package com.example.ammaryasser.mycolors;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecommendFrag extends Fragment {

    final String colorsValues[][] = {
            {"#2E112D", "#940032", "#820333", "#C9283E", "#F0433A"},
            {"#FCD09B", "#F59A23", "#F27326", "#F1392A", "#C02727"},
            {"#E97778", "#89C7B6", "#FFD57E", "#AD84C7", "#7998C9"},
            {"#730046", "#B4BF35", "#F0CD30", "#E88E34", "#C93F0F"},
            {"#121F27", "#416275", "#6191A8", "#669999", "#7788FF"}
    };
    ListView listView;
    int themeId = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_recommend, container, false);

        listView = rootView.findViewById(R.id.listview);
        listView.setOnItemClickListener(listListener);
        MainActivity.themes = new ArrayList<>();

        for (int i = 0; i < colorsValues.length; i++)
            MainActivity.themes.add(new Theme(i + 1,
                    colorsValues[i][0],
                    colorsValues[i][1],
                    colorsValues[i][2],
                    colorsValues[i][3],
                    colorsValues[i][4])
            );
        MyAdapter adapter = new MyAdapter(MainActivity.themes);
        listView.setAdapter(adapter);

        return rootView;
    }

    ListView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            themeId = i;
        }
    };

    class MyAdapter extends BaseAdapter {
        ArrayList<Theme> theme;

        MyAdapter(ArrayList<Theme> theme) {
            this.theme = theme;
        }

        @Override
        public int getCount() {
            return theme.size();
        }

        @Override
        public Object getItem(int i) {
            return theme.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View row = layoutInflater.inflate(R.layout.row_rec, null);

            TextView themeNo = row.findViewById(R.id.themeNo);
            View col_1 = row.findViewById(R.id.col_1),
                    col_2 = row.findViewById(R.id.col_2),
                    col_3 = row.findViewById(R.id.col_3),
                    col_4 = row.findViewById(R.id.col_4),
                    col_5 = row.findViewById(R.id.col_5);
            String themeTitle = getString(R.string.theme) + "." + String.valueOf(theme.get(i).getThemeNo());

            themeNo.setText(themeTitle);
            col_1.setBackgroundColor(Color.parseColor(theme.get(i).getCol_1()));
            col_2.setBackgroundColor(Color.parseColor(theme.get(i).getCol_2()));
            col_3.setBackgroundColor(Color.parseColor(theme.get(i).getCol_3()));
            col_4.setBackgroundColor(Color.parseColor(theme.get(i).getCol_4()));
            col_5.setBackgroundColor(Color.parseColor(theme.get(i).getCol_5()));

            return row;
        }
    }
}
