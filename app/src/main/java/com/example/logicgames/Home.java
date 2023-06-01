package com.example.logicgames;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Home extends Fragment {
    Button colours, mathematics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        colours = root.findViewById(R.id.colours);
        mathematics = root.findViewById(R.id.mathematics);

        colours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateColours();
            }
        });

        mathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMathematics();
            }
        });

        return root;
    }



    public void updateMathematics() {
        Intent intent1 = new Intent(getActivity(), Mathematics.class);
        startActivity(intent1);
    }

    public void updateColours() {
        Intent intent1 = new Intent(getActivity(), Colours.class);
        startActivity(intent1);
    }

}