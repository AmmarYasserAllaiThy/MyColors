package com.example.ammaryasser.mycolors;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class HomeFrag extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //My Code
    SeekBar rBar, gBar, bBar;
    TextView rtv, gtv, btv;
    EditText hexET;
    LinearLayout preview;
    ToggleButton toggle;
    ImageButton addToFAV;
    MainActivity mainActivity;
    ColorDBHelper colorDBHelper;
    Object TAG;
    private static int red = 240, green = 240, blue = 240;
    private static boolean rgbMode = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frg_home, container, false);
        mainActivity = new MainActivity();
        colorDBHelper = new ColorDBHelper(getContext());

        preview = rootView.findViewById(R.id.color_preview);
        toggle = rootView.findViewById(R.id.toggleButton);

        rtv = rootView.findViewById(R.id.rTV);
        gtv = rootView.findViewById(R.id.gTV);
        btv = rootView.findViewById(R.id.bTV);
        hexET = rootView.findViewById(R.id.hexET);

        rBar = rootView.findViewById(R.id.rBar);
        gBar = rootView.findViewById(R.id.gBar);
        bBar = rootView.findViewById(R.id.bBar);

        toggle.setOnCheckedChangeListener(toggleListener);
        rBar.setOnSeekBarChangeListener(rListener);
        gBar.setOnSeekBarChangeListener(gListener);
        bBar.setOnSeekBarChangeListener(bListener);
        hexETListener();

        addToFAV = rootView.findViewById(R.id.add_to_fav);
        addToFAV.setOnClickListener(favListener);
        TAG = R.drawable.ic_favorite_border_black_24dp;

        toggle.setChecked(rgbMode);
        rBar.setProgress(red);
        rtv.setText(String.valueOf(red));
        gBar.setProgress(green);
        gtv.setText(String.valueOf(green));
        bBar.setProgress(blue);
        btv.setText(String.valueOf(blue));
        hexET.setEnabled(!rgbMode);

        return rootView;
    }

    ToggleButton.OnCheckedChangeListener toggleListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            hexET.setEnabled(!(rgbMode = b));
        }
    };

    private SeekBar.OnSeekBarChangeListener rListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            red = rBar.getProgress();
            colorFromRGB(rBar, rtv);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private SeekBar.OnSeekBarChangeListener gListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            green = gBar.getProgress();
            colorFromRGB(gBar, gtv);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private SeekBar.OnSeekBarChangeListener bListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            blue = bBar.getProgress();
            colorFromRGB(bBar, btv);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private ImageButton.OnClickListener favListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (TAG.equals(R.drawable.ic_favorite_border_black_24dp)) {
                addToFAV.setImageResource((Integer) (TAG = R.drawable.ic_favorite_primary_24dp));
                colorDBHelper.insert(
                        rBar.getProgress(), gBar.getProgress(), bBar.getProgress(),
                        hexET.getText().toString());
            }
        }
    };


    private void hexETListener() {
        hexET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String hex = hexET.getText().toString();
                if (hex.matches(getString(R.string.hex_validate))) //[\\dA-Fa-f]{6}
                    colorFromHex(hex);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void colorFromRGB(SeekBar bar, TextView tv) {
        if (toggle.isChecked()) {
            previewColor(rBar.getProgress(), gBar.getProgress(), bBar.getProgress());
            convertRGBtoHEX(rBar.getProgress(), gBar.getProgress(), bBar.getProgress());
        }
        tv.setText(String.valueOf(bar.getProgress()));
    }

    private void convertRGBtoHEX(int r, int g, int b) {
        String seq1 = Integer.toHexString(r),
                seq2 = Integer.toHexString(g),
                seq3 = Integer.toHexString(b);

        if (seq1.length() == 1)
            seq1 = "0" + seq1;
        if (seq2.length() == 1)
            seq2 = "0" + seq2;
        if (seq3.length() == 1)
            seq3 = "0" + seq3;

        hexET.setText((seq1 + seq2 + seq3).toUpperCase());
    }

    private void colorFromHex(String hex) {
        if (!toggle.isChecked()) {
            int r = Integer.parseInt(hex.substring(0, 2), 16),
                    g = Integer.parseInt(hex.substring(2, 4), 16),
                    b = Integer.parseInt(hex.substring(4, 6), 16);

            previewColor(r, g, b);

            rtv.setText(String.valueOf(r));
            gtv.setText(String.valueOf(g));
            btv.setText(String.valueOf(b));

            rBar.setProgress(r);
            gBar.setProgress(g);
            bBar.setProgress(b);
        }
    }

    private void previewColor(int r, int g, int b) {
        preview.setBackgroundColor(Color.rgb(r, g, b));
        //Change favorite vector
        addToFAV.setImageResource((Integer) (TAG = R.drawable.ic_favorite_border_black_24dp));
    }

//    private void AddToSharedPref(ArrayList<FavoriteColor> list) {
//        SharedPreferences.Editor myEdit = pref.edit();
//        for (int i = 0; i < list.size(); i++) {
//            myEdit.putString("favCol_" + (i + 1),
//                    list.get(i).getR() + "," + list.get(i).getG() + "," + list.get(i).getB()
//                            + "," + list.get(i).getHex());
//        }
//        myEdit.apply();
//    }
}

