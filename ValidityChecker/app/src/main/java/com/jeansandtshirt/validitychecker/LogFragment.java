package com.jeansandtshirt.validitychecker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class LogFragment extends Fragment {

    ListView listView;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public LogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log, container, false);

         Firebase mRef = new Firebase("https://validitychecker-c5ec7.firebaseio.com/");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listItems.add(dataSnapshot.getValue(CandidateData.class).name);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listItems);

        listView = (ListView)view.findViewById(R.id.listView);


        listView.setAdapter(adapter);

        return view;
    }
}
