package ua.kiev.prog.responses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import ua.kiev.prog.models.User;

public class UserResponse {

    @Expose()
    public String room;

    @Expose()
    public String login;

    @Expose()
    public String password;

    @Expose()
    public boolean isOnline;

    public String toJSON() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(this);
    }

    public static UserResponse fromJSON(String s) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.fromJson(s, UserResponse.class);
    }
}
