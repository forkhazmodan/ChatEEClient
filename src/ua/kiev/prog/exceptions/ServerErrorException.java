package ua.kiev.prog.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ServerErrorException extends RuntimeException{

    @Expose()
    public Integer status;

    @Expose()
    public String message;

    public ServerErrorException(String message) {
        super(message);
        this.message = message;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(this);
    }

    public static ServerErrorException fromJSON(String s) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.fromJson(s, ServerErrorException.class);
    }

}
