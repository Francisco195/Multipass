package com.example.stardust.multipass.Activities.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.stardust.multipass.R;


public class NFC extends Fragment {

    private ImageButton nfc;
    public NFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_nfc, container, false);
        nfc=(ImageButton)view.findViewById(R.id.NFCButton);

        return view;
    }

}
