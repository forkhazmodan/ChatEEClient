package ua.kiev.prog.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Message {
    private Integer id;
    private Date date = new Date();
    private String from;
    private String to;
    private String text;
    private String room;

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb = sb.append("[");

        if(date != null) sb = sb.append(date);
        if(room != null) sb = sb.append(", Room: ").append(room);
        if(from != null) sb = sb.append(", From: ").append(from);
        if(to != null) sb = sb.append(", To: ").append(to);
        sb = sb.append("] ").append(text);

        return sb.toString();
    }

    public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        try {
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode();
        } finally {
            os.close();
        }
    }

    public String getRoom() { return room; }

    public void setRoom(String room) { this.room = room; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
