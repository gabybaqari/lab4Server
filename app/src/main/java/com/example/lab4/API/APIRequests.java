package com.example.lab4.API;

import android.util.Log;
import android.widget.TextView;

import com.example.lab4.MessageAttribute;
import com.example.lab4.Order.OrderList;
import com.example.lab4.User.Login;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIRequests {

    private static OkHttpClient client = new OkHttpClient();
    private String URL = URLS.SET_AVAILABILITY;
    private Request request;
    private RequestBody requestBody;
    private static String TAG = "DEBUG2";
    private TextView textView;
    public APIRequests(TextView textView) {
        this.textView = textView;
    }

    public static void setAvailability(String username, String isAvailable){

        String json = "{\"username\":\"driver1\",\"isAvailable\": " +isAvailable + "}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url("http://192.168.0.45:8080/api/v1/drivers")
                .addHeader("Authorization", URLS.TOKEN)
                .put(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body());
            }
        });
    }


    public static void getOrders(String username, Callback callback){
        Log.d(TAG, "API REQUESTS" + URLS.TOKEN);
        Request request = new Request.Builder()
                .url("http://192.168.0.45:8080/api/v1/orders/driver1")

                .addHeader("Authorization", URLS.TOKEN)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void login(String username, String password, Callback callback){
        Gson gson = new Gson();
        Login login = new Login(username, password);

        String json = gson.toJson(login);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url("http://192.168.0.45:8080/login")
                .addHeader("Authorization", URLS.TOKEN)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }


    public static void sendMessage(MessageAttribute chatMessage, Callback callback){

        Gson gson = new Gson();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), gson.toJson(chatMessage));

        Request request = new Request.Builder()
                .url(URLS.MESSAGE_SEND_URL)
                .addHeader("Authorization", URLS.TOKEN)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void getMessages(Callback callback){
        Request request = new Request.Builder()
                .url("http://192.168.0.45:8080/api/v1/chat?username=bari")
                .addHeader("Authorization", URLS.TOKEN)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
