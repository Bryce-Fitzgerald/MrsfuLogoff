package com.example.mrsfulogoff;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Consult_recycle_adapter extends RecyclerView.Adapter<Consult_recycle_adapter.ViewHolder> {
    private List<Consult_message> mConsult_messageList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMessage;
        TextView rightMessage;
        public ViewHolder (View view) {
            super(view);
            leftLayout=(LinearLayout) view.findViewById(R.id.left_message_layout);
            rightLayout=(LinearLayout) view.findViewById(R.id.right_message_layout);
            leftMessage=(TextView) view.findViewById(R.id.left_message);
            rightMessage=(TextView) view.findViewById(R.id.right_message);
        }
    }
    public Consult_recycle_adapter(List<Consult_message> messageList) {
        mConsult_messageList=messageList;
    }
    @NonNull
    @Override
    public Consult_recycle_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_consult_messageitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Consult_recycle_adapter.ViewHolder holder, int position) {
        Consult_message consult_message=mConsult_messageList.get(position);
        if(consult_message.getType()==Consult_message.TYPE_RECEIVED) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMessage.setText(consult_message.getContent());
        } else if(consult_message.getType()==Consult_message.TYPE_SENT) {
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMessage.setText(consult_message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mConsult_messageList.size();
    }
}
