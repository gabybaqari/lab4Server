package com.example.lab4;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.API.APIRequests;
import com.example.lab4.API.Chat;
import com.example.lab4.API.ChatList;
import com.example.lab4.API.URLS;
import com.example.lab4.Order.OrderList;
import com.google.gson.Gson;
import com.here.oksse.ServerSentEvent;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class ConversationsActivity extends AppCompatActivity {


    private String TAG = "DEBUG11";
    private ImageView send_icon, mic_icon;
    private EditText editText;
    static RecyclerView conversation_recyclerView;
    static List<MessageAttribute> conversationsList;
    static Context conversationActivityApplicationContext;
    private List<String> sendersList;
    private List<String> serviceList;
    private ConversationAdapter conversationAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MessageAttribute messageAttribute;
    private ConversationPrintOut conversationPrintOut;
    private TextView service;
    private Gson gson;

    DateTimeFormatter dateFormatter;
    private Date currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        send_icon=findViewById(R.id.send);
        mic_icon=findViewById(R.id.mic);
        service=findViewById(R.id.service);
        editText=findViewById(R.id.editText);
       // messageAttribute=new MessageAttribute();
        conversationsList=new ArrayList<>();
        sendersList=new ArrayList<>();
        serviceList=new ArrayList<>();
        conversationPrintOut=new ConversationPrintOut();
        conversation_recyclerView=findViewById(R.id.conversationsRecyclerView);
        gson = new Gson();

        //dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        //service.setText("Service");


        //hardcodeService("hi");
        /*for(int i=0;i<4;i++){
            Log.d("list "+i, orderList.get(i).getFrom()+"");

        }*/
        buildRecyclerView();

        send_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mainActivity();
                Log.d("edit Text", editText.getText().toString());
                if(!editText.getText().toString().equals("")) {
                    //sendersName.setTextColor(Color.red(4));
                    Log.d("edit Text", editText.getText().toString());
                    //sendersList.add(editText.getText().toString());
                    hardcodeSender(editText.getText().toString(), URLS.username);
                    //conversationAdapter.notifyDataSetChanged();
                    //editText.clearAnimation();
                    editText.setText("");
                }
            }
        });
        mic_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeechInput();
            }
        });

    }

    private void hardcodeService(String hi) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        //buildRecyclerView();

        APIRequests.getMessages(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String s = response.body().string();
                ChatList chatList = gson.fromJson(s, ChatList.class);
                ConversationsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //hardcodeSender(messageAttribute.getSendersText(), messageAttribute.getSenders_name());

                        conversationPrintOut.setAdapter(chatList.getChatMessages());
                    }
                });
            }
        });


        new Chat(new ServerSentEvent.Listener() {
            @Override
            public void onOpen(ServerSentEvent sse, Response response) {
                Log.d(TAG, "onOpen: sadadasdasa");
            }

            @Override
            public void onMessage(ServerSentEvent sse, String id, String event, String message) {
                MessageAttribute messageAttribute = gson.fromJson(message, MessageAttribute.class);
                Log.d(TAG, "run: " + messageAttribute);

                ConversationsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //hardcodeSender(messageAttribute.getSendersText(), messageAttribute.getSenders_name());
                        conversationPrintOut.addToConversationList(conversationsList, messageAttribute);
                        conversationPrintOut.setAdapter(conversationsList);
                    }
                });
                Log.d(TAG, "onMessage: " + message);
                Log.d(TAG, "onMessage: ");
            }

            @Override
            public void onComment(ServerSentEvent sse, String comment) {
                Log.d(TAG, "onComment: ");
            }

            @Override
            public boolean onRetryTime(ServerSentEvent sse, long milliseconds) {
                Log.d(TAG, "onRetryTime: ");
                return false;
            }

            @Override
            public boolean onRetryError(ServerSentEvent sse, Throwable throwable, Response response) {
                Log.d(TAG, "onRetryError: ");
                return false;
            }

            @Override
            public void onClosed(ServerSentEvent sse) {
                sse.close();
                Log.d(TAG, "onClosed: ");
            }

            @Override
            public Request onPreRetry(ServerSentEvent sse, Request originalRequest) {
                Log.d(TAG, "onPreRetry: ");
                return null;
            }
        });
    }




    public void hardcodeSender(String text, String name){
        currentTime = Calendar.getInstance().getTime();

            messageAttribute=new MessageAttribute(name, "managers",  text, new Date().toString());

        APIRequests.sendMessage(messageAttribute, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.code());
            }
        });

           conversationPrintOut.addToConversationList(conversationsList, messageAttribute);
        conversationPrintOut.setAdapter(conversationsList);
    }
    public void buildRecyclerView() {
        conversation_recyclerView.setLayoutManager(new LinearLayoutManager(conversationActivityApplicationContext));
        mLayoutManager = new LinearLayoutManager(this);
        conversationAdapter = new ConversationAdapter(conversationsList);
        conversation_recyclerView.setAdapter(conversationAdapter);
    }


    //MIC
    public void getSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    //MIC
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editText.setText(result.get(0));
                }
                break;
        }
    }
}