package ua.kiev.prog.models;

public class User {

    private static User instance;

    private String room;

    private String login;

    private User(String login, String room) {
        this.login = login;
        this.room = room;
        instance = this;
    }

    public static User getInstance() {
        return instance;
    }

    public static User createInstance(String login , String room) {
        new User(login, room);

        return getInstance();
    }

    public String getRoom() {
        return getInstance().room;
    }

    public void setRoom(String room) {
        getInstance().room = room;
    }

    public String getLogin() {
        return getInstance().login;
    }

    public void setLogin(String login) {
        getInstance().login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "room='" + room + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
