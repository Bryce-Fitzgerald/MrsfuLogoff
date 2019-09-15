package com.example.mrsfulogoff;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ConsultActivity extends AppCompatActivity {
    private List<Consult_message> consult_messageList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView consult_messageRecyclerView;
    private Consult_recycle_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        initConsult_message();
        inputText=(EditText) findViewById(R.id.consult_input);
        send=(Button)findViewById(R.id.consult_send);
        consult_messageRecyclerView=(RecyclerView) findViewById(R.id.consult_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        consult_messageRecyclerView.setLayoutManager(layoutManager);
        adapter=new Consult_recycle_adapter(consult_messageList);
        consult_messageRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=inputText.getText().toString();
                if(!"".equals(content)) {
                    Consult_message consult_message=new Consult_message(content, Consult_message.TYPE_SENT);
                    consult_messageList.add(consult_message);
                    adapter.notifyItemInserted(consult_messageList.size()-1);
                    consult_messageRecyclerView.scrollToPosition(consult_messageList.size()-1);
                    inputText.setText("");
                }
            }
        });
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        initTitleView();
    }
    private void initTitleView() {
        new TitleBuilder(this).setTitleText("客服咨询").setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).build();
    }

    private  void initConsult_message() {
        Consult_message consult_message1=new Consult_message("Hello guy.", Consult_message.TYPE_RECEIVED);
        consult_messageList.add(consult_message1);
        Consult_message consult_message2=new Consult_message("Hello. Who is that?", Consult_message.TYPE_SENT);
        consult_messageList.add(consult_message2);
        Consult_message consult_message3=new Consult_message("This is Tom.Nice talking to you.", Consult_message.TYPE_RECEIVED);
        consult_messageList.add(consult_message3);
    }
}
