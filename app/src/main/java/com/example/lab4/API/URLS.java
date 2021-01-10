package com.example.lab4.API;

public class URLS {
    public static String BASE_URL = "http://192.168.0.45:8080/api/v1/";
    public static String LOGIN_URL = "http://192.168.0.45:8080/login";
    public static String SET_AVAILABILITY = BASE_URL + "http://192.168.0.45:8080/api/v1/drivers";
    //public static String TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJpIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6Im9yZGVyOndyaXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOndyaXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOnJlYWQifSx7ImF1dGhvcml0eSI6Im9yZGVyOnJlYWQifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTYwOTM0NTE3OCwiZXhwIjoxNjEwNDkyNDAwfQ.yNTsvQxnwayd9UN4MstbBk-txVx9zC9bw3jnvKpWwYG_F9NkrIrVJPdNiJNghbA_qsiNEp6FYdm-14Xm5YNG5w";
    public static String TOKEN = "";
    public static String username = "";
    public static String MESSAGE_SEND_URL = "http://192.168.0.45:8080/api/v1/chat/publish";
    public static String MESSAGE_RECEIVE_URL = "http://192.168.0.45:8080/api/v1/chat?username=" + URLS.username;
}
