package com.example.ammaryasser.mycolors;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailsFrag extends Fragment {

    RecommendFrag recommend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View details = inflater.inflate(R.layout.fragment_details, container, false);
        recommend = new RecommendFrag();

        TextView title = details.findViewById(R.id.themeName),

                rgb_value_1 = details.findViewById(R.id.rgb_value_1),
                rgb_value_2 = details.findViewById(R.id.rgb_value_2),
                rgb_value_3 = details.findViewById(R.id.rgb_value_3),
                rgb_value_4 = details.findViewById(R.id.rgb_value_4),
                rgb_value_5 = details.findViewById(R.id.rgb_value_5),

                hex_value_1 = details.findViewById(R.id.hex_value_1),
                hex_value_2 = details.findViewById(R.id.hex_value_2),
                hex_value_3 = details.findViewById(R.id.hex_value_3),
                hex_value_4 = details.findViewById(R.id.hex_value_4),
                hex_value_5 = details.findViewById(R.id.hex_value_5);

        View col_img_1 = details.findViewById(R.id.col_img_1),
                col_img_2 = details.findViewById(R.id.col_img_2),
                col_img_3 = details.findViewById(R.id.col_img_3),
                col_img_4 = details.findViewById(R.id.col_img_4),
                col_img_5 = details.findViewById(R.id.col_img_5);

        title.setText(title.getText() + " Theme." + recommend.themeId);

        rgb_value_1.setText(recommend.colorsValues[recommend.themeId][1]);
        rgb_value_2.setText(recommend.colorsValues[recommend.themeId][2]);
        rgb_value_3.setText(recommend.colorsValues[recommend.themeId][3]);
        rgb_value_4.setText(recommend.colorsValues[recommend.themeId][4]);
        rgb_value_5.setText(recommend.colorsValues[recommend.themeId][5]);

        hex_value_1.setText(recommend.colorsValues[recommend.themeId][1]);
        hex_value_2.setText(recommend.colorsValues[recommend.themeId][2]);
        hex_value_3.setText(recommend.colorsValues[recommend.themeId][3]);
        hex_value_4.setText(recommend.colorsValues[recommend.themeId][4]);
        hex_value_5.setText(recommend.colorsValues[recommend.themeId][5]);

        return details;
    }
}
