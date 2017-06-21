package com.winuall.turnling;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatFragment extends Fragment {

    FirebaseDatabase database ;
    DatabaseReference databaseReference ;

    private String languagePref ;

    @BindView(R.id.fragment_chat_rv)
    RecyclerView chatRecyclerView;

    private RvChatAdapter rvChatAdapter;
    private ArrayList<String> chatContent;
    private ArrayList<String> timestamp;
    private ArrayList<Integer> imageIds;
    private  ArrayList<Boolean> isUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false) ;
        ButterKnife.bind(this, view) ;

        database = FirebaseDatabase.getInstance() ;
        database.setPersistenceEnabled(true);

        initChat();

        databaseReference = database.getReference("messages/en") ;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("TAG", "onDataChange: " + dataSnapshot.toString());
                Log.i("TAG", "onDataChange: " + dataSnapshot.getKey());
                languagePref = dataSnapshot.getKey() ;
                Log.i("TAG", "onDataChange: " + dataSnapshot.getValue());
                Log.i("TAG", "onDataChange: " + dataSnapshot.getChildren());
                Log.i("TAG", "onDataChange: " + dataSnapshot.getChildrenCount());


                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.child("target_id").getValue();
                    String message = (String) messageSnapshot.child("text").getValue();
                    Log.i("TAG", "onDataChange: " + name + " " + message);

                    chatContent.add(message);
                    timestamp.add("");
                    imageIds.add(1);
                    isUser.add(false);


                }

                rvChatAdapter.notifyDataSetChanged();



                /*try {


                    JSONObject jsonObject = new JSONObject((String) dataSnapshot.getValue()) ;
                    Log.i("TAG", "onDataChange: " + jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });


        return view ;
    }

    public void initChat() {
        chatContent = new ArrayList<>();
        timestamp = new ArrayList<>();
        imageIds = new ArrayList<>();
        isUser = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            chatContent.add("J'habite au Paris et je suis développeur, et toi ?");
            timestamp.add("02:28");
            imageIds.add(R.drawable.profile);
            isUser.add(true);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false) ;
        chatRecyclerView.setLayoutManager(layoutManager);
        rvChatAdapter = new RvChatAdapter(chatContent, timestamp, imageIds, isUser, getContext());
        chatRecyclerView.setAdapter(rvChatAdapter);
    }

    @OnClick(R.id.fragment_chat_button)
    public void onMessageSent(View v) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

