package ua.kiev.prog.utils;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;
import ua.kiev.prog.models.Message;
import ua.kiev.prog.models.User;
import ua.kiev.prog.requests.UserRequest;
import ua.kiev.prog.responses.UserResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Api {

    static String domain = "http://127.0.0.1:8080";

    public static boolean register(String login, String password) throws IOException, ServerErrorException {

        UserRequest userRequest = new UserRequest();
        userRequest.setLogin(login);
        userRequest.setPassword(password);

        HttpURLConnection conn = (HttpURLConnection) new URL(Api.domain + "/register").openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();

        String json = userRequest.toJSON();
        os.write(json.getBytes(StandardCharsets.UTF_8));
        os.close();

        int code = conn.getResponseCode();

        if (200 <= code && code <= 299) {
            return true;
        } else {
            InputStream is = conn.getErrorStream();
            byte[] buf = Http.responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            ServerErrorException ex = ServerErrorException.fromJSON(strBuf);
            throw ex;
        }
    }

    public static boolean login(String login, String password) throws IOException, ServerErrorException {
        UserRequest userRequest = new UserRequest();
        userRequest.setLogin(login);
        userRequest.setPassword(password);

        HttpURLConnection conn = (HttpURLConnection) new URL(Api.domain + "/login").openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();

        String json = userRequest.toJSON();
        os.write(json.getBytes(StandardCharsets.UTF_8));
        os.close();

        int code = conn.getResponseCode();

        if (200 <= code && code <= 299) {
            InputStream is = conn.getInputStream();
            byte[] buf = Http.responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            UserResponse userResponse = UserResponse.fromJSON(strBuf);
            User.createInstance(userResponse.login, userResponse.room);
            return true;
        } else {
            InputStream is = conn.getErrorStream();
            byte[] buf = Http.responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            ServerErrorException ex = ServerErrorException.fromJSON(strBuf);
            throw ex;
        }
    }

    public static boolean goToRoom(String room) throws IOException, ServerErrorException {
        User user = User.getInstance();

        if(user == null) {
            return false;
        }

        UserRequest userRequest = new UserRequest();
        userRequest.setLogin(user.getLogin());
        userRequest.setRoom(user.getRoom());

        String json = userRequest.toJSON();

        HttpURLConnection conn = (HttpURLConnection) new URL(Api.domain + "/users/" + user.getLogin()).openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes(StandardCharsets.UTF_8));
        os.close();

        int code = conn.getResponseCode();

        if (200 <= code && code <= 299) {
            InputStream is = conn.getInputStream();
            byte[] buf = Http.responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);

            UserResponse userResponse = UserResponse.fromJSON(strBuf);
            user.setRoom(userResponse.room);

            return true;
        } else {
            InputStream is = conn.getErrorStream();
            byte[] buf = Http.responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            ServerErrorException ex = ServerErrorException.fromJSON(strBuf);
            throw ex;
        }
    }

    public static boolean message(String text) throws IOException, ServerErrorException {
        User user = User.getInstance();

        if(user == null) {
            return false;
        }

        Message message = new Message(user.getLogin(), text);
        String json = message.toJSON();

        HttpURLConnection conn = (HttpURLConnection) new URL(Api.domain + "/messages").openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes(StandardCharsets.UTF_8));
        os.close();

        int code = conn.getResponseCode();

        if (200 <= code && code <= 299) {
            return true;
        } else {
            InputStream is = conn.getErrorStream();
            byte[] buf = Http.responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            ServerErrorException ex = ServerErrorException.fromJSON(strBuf);
            throw ex;
        }
    }
}
