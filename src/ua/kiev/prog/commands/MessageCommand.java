package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;
import ua.kiev.prog.models.User;
import ua.kiev.prog.utils.Api;
import ua.kiev.prog.utils.SingletonScanner;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageCommand extends Command {

    static String regex = "^\\/msg (.*)$";

    static String text;

    public MessageCommand(String command) {
        super(command);
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(command);

        if (m.find()) {
            text = m.group(1);
        }
    }

    public void run()  throws IOException, ServerErrorException {
        if(text == null) {
            throw new CommandErrorException("Message required");
        }

        User user = User.getInstance();

        if(user == null) {
            throw new CommandErrorException("You should login first");
        }

        Api.message(text);
    }
}
