package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;
import ua.kiev.prog.models.User;
import ua.kiev.prog.utils.Api;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomCommand extends Command{

    static String regex = "^\\/room:([A-Za-z0-9]+)$";

    public String room;

    public RoomCommand(String command) {
        super(command);
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(command);

        if (m.find()) {
            this.room = m.group(1);
        }
    }

    public void run()  throws IOException, ServerErrorException, CommandErrorException {
        if(room == null) {
            throw new CommandErrorException("Room required");
        }

        User user = User.getInstance();

        if(user == null) {
            throw new CommandErrorException("You should login first");
        }

        if(Api.goToRoom(room)) {
            System.out.println(String.format("Now you in the room:%s", user.getRoom()));
        }
    }

}
