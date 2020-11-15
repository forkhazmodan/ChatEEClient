package ua.kiev.prog.responses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ErrorResponse extends RuntimeException {
    public String status;
    public String message;

    public static ErrorResponse fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, ErrorResponse.class);
    }
}
