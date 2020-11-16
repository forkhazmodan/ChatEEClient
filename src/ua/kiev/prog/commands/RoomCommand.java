package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;
import ua.kiev.prog.models.User;
import ua.kiev.prog.utils.Api;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomCommand extends Command {

    private String regex = "^\\/room:([A-Za-z0-9]+)$";

    @Override
    public String[] parse(String command) {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(command);

        String[] signature = new String[1];

        if (m.find()) {
            signature[0] = m.group(1);
            return signature;
        }

        return signature;
    }

    @Override
    public void run(String ...args) throws IOException, ServerErrorException, CommandErrorException {

        String room = args != null && args.length > 0 ? args[0] : null;

        if(room == null) {
            throw new CommandErrorException("Room required");
        }

        User user = User.getInstance();


        if(user == null) {
            throw new CommandErrorException("You should login first");
        }

        if(Api.goToRoom(room)) {
            user.setRoom(room);
            System.out.println(String.format("Now you in the room:%s", user.getRoom()));
        }
    }

    @Override
    public String getRegex() {
        return regex;
    }

}
