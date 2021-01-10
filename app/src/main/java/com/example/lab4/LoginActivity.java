package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab4.API.APIRequests;
import com.example.lab4.API.URLS;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView username;
    private TextView password;
    private String TAG = "DEBUG12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked");
                APIRequests.login(username.getText().toString(), password.getText().toString(), new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        Log.d(TAG, "" + response.code());
                        if(response.code() == 200){
                            Log.d(TAG, "onResponse: " + response.toString());

                            Log.d(TAG, "onResponse: " + response.body());
                            String auth2 = response.header("Authorization");
                            if(auth2 != null){
                                String token = auth2;
                                Log.d(TAG, " " + token);
                                URLS.username = username.getText().toString();
                                URLS.TOKEN = token;
                                next();
                            }
                        }
                    }
                });
            }
        });
    }

    public void next(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}