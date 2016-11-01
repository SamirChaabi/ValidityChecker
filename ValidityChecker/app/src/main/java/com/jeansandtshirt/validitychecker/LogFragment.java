package com.jeansandtshirt.validitychecker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogFragment extends Fragment {

    ListView listView;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    Firebase mRef;

    public LogFragment() {
        // Required empty public constructor
    }

    public void setmRef(Firebase mRef){
        this.mRef = mRef;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listItems);

        listView = (ListView)view.findViewById(R.id.listView);

        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null){
                    Iterator it = dataSnapshot.getValue(HashMap.class).entrySet().iterator();

                    while (it.hasNext()){
                        LinkedHashMap linkedHashMap =(LinkedHashMap)((Map.Entry)it.next()).getValue();
                        listItems.add((String)linkedHashMap.get(getContext().getString(R.string.name)) + "\n" +
                                (String)linkedHashMap.get(getContext().getString(R.string.p_number)) + "\n" +
                                (String)linkedHashMap.get(getContext().getString(R.string.date_added)));
                        it.remove();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
