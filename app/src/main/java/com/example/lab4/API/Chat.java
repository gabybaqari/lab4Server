package com.example.lab4.API;

import android.util.Log;

import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;

import okhttp3.Request;
import okhttp3.Response;

public class Chat {

    private final String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJpIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6Im9yZGVyOndyaXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOndyaXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOnJlYWQifSx7ImF1dGhvcml0eSI6Im9yZGVyOnJlYWQifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTYxMDA3MzIxNywiZXhwIjoxNjExMjcwMDAwfQ.tqyo1BBB8_Symrl4n1LUM5Ix5ujqVwOrAulj6fd43JZFZ_AOn4Eq-5RTThhYEvDSKiOs1Wu_M-J5R6HE_5NCSQ";

    private Request request;
    private OkSse okSse;
    private ServerSentEvent serverSentEvent;
    private static String TAG = "DEBUG2";
    private String URLtoSub = "http://192.168.0.45:8080/api/v1/chat/subscribe?username=" + URLS.username + "&role=driver";
    //http://192.168.0.45:8080/api/v1/chat/subscribe
    public Chat(ServerSentEvent.Listener listener) {
        request = new Request.Builder().url(URLtoSub)
                .addHeader("Authorization", URLS.TOKEN)
                .build();
        okSse = new OkSse();
        okSse.newServerSentEvent(request, listener);
    }





}
