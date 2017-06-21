package com.winuall.turnling;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RvChatAdapter extends RecyclerView.Adapter<RvChatAdapter.ChatViewHolder> {
    private ArrayList<String> chatContent;
    private ArrayList<String> timestamp;
    private ArrayList<Integer> imageIds;
    private  ArrayList<Boolean> isUser;
    private Context context ;

    public RvChatAdapter(ArrayList<String> chatContent, ArrayList<String> timestamp, ArrayList<Integer> imageIds, ArrayList<Boolean> isUser, Context context) {
        this.chatContent = chatContent;
        this.timestamp = timestamp;
        this.imageIds = imageIds;
        this.context = context;
        this.isUser = isUser;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_view_chat, parent, false) ;
        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.message.setText(chatContent.get(position));
        holder.timestamp.setText(timestamp.get(position));
//        holder.profileImage.setImageResource(imageIds.get(position));

        if(isUser.get(position)) {

        }
    }

    @Override
    public int getItemCount() {
        return chatContent.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView timestamp, message;
        CardView cardView;
        de.hdodenhof.circleimageview.CircleImageView profileImage;

        public ChatViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.item_home_view_text) ;
            timestamp = (TextView) itemView.findViewById(R.id.item_home_view_timestamp) ;
            cardView = (CardView) itemView.findViewById(R.id.item_home_view_chat_card);
            profileImage = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.item_home_view_chat_profile_image);
        }
    }
}
