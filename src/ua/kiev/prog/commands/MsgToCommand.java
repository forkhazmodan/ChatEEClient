package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.ServerErrorException;
import ua.kiev.prog.utils.Api;
import ua.kiev.prog.utils.SingletonScanner;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgToCommand extends Command {

    static String regex = "^\\/msg:to ([A-Za-z0-9]+) (.*)$";

    static String text;

    static String to;

    public MsgToCommand(String command) {
        super(command);
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(command);

        if (m.find()) {
            to = m.group(1);
            text = m.group(2);
        }
    }

    public void run()  throws IOException, ServerErrorException {
        Api.message(text, to);
    }
}
