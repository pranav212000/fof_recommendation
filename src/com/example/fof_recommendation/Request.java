package com.example.fof_recommendation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Request {

    String createdAt;
    String userId;
    String req;
    String location;

    public Request(String userId, String req, String location) {
        this.userId = userId;
        this.req = req;
        this.location = location;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.createdAt = formatter.format(date);
    }
}
