package com.example.ammaryasser.mycolors;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoriteFrag extends Fragment {

    ListView listView;
    ColorDBHelper colorDBHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_favorites, container, false);
        colorDBHelper = new ColorDBHelper(getContext());

        listView = rootView.findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter(colorDBHelper.getAllColors()));
        listView.setOnItemClickListener(listListener);

        return rootView;
    }

    ListView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, final int i, final long l) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true)
                    .setTitle("Select Action...")
                    .setMessage("Are you want to Copy color values or Delete color ?")
                    .setPositiveButton("Copy", new DialogInterface.OnClickListener() {
                        @SuppressLint("DefaultLocale")
                        @Override
                        public void onClick(DialogInterface dialogInterface, int ii) {
                            final FavoriteColor color = colorDBHelper.getAllColors().get(i);
                            String message = String.format("RGB(%d, %d, %d); %nHEX: %s;",
                                    color.getR(),
                                    color.getG(),
                                    color.getB(),
                                    color.getHex());
                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                            MainActivity.copyToClipboard(message);
                        }
                    })
                    .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int ii) {
                            colorDBHelper.delete((int) l);
                            listView.setAdapter(new MyAdapter(colorDBHelper.getAllColors()));
                            listView.setSelection(i);
                        }
                    })
                    .show();
        }
    };

    class MyAdapter extends BaseAdapter {
        ArrayList<FavoriteColor> colors;

        MyAdapter() {
        }

        MyAdapter(ArrayList<FavoriteColor> colors) {
            this.colors = colors;
        }

        @Override
        public int getCount() {
            return colors == null ? 0 : colors.size();
        }

        @Override
        public Object getItem(int i) {
            return colors.get(i);
        }

        @Override
        public long getItemId(int i) {
            return colors.get(i).getId();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row = getLayoutInflater().inflate(R.layout.row_fav, null);

            View col_img = row.findViewById(R.id.col_img);
            TextView rgbTV = row.findViewById(R.id.rgb_value);
            TextView hexTv = row.findViewById(R.id.hex_value);

            int r = colors.get(i).getR(),
                    g = colors.get(i).getG(),
                    b = colors.get(i).getB();
            String rgb = "RGB(" + r + ", " + g + ", " + b + ");",
                    hex = "#" + colors.get(i).getHex() + ";";

            col_img.setBackgroundColor(Color.rgb(r, g, b));
            rgbTV.setText(rgb);
            hexTv.setText(hex);

            return row;
        }


    }
}