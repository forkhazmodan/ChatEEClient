package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;
import ua.kiev.prog.utils.Api;
import ua.kiev.prog.utils.SingletonScanner;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageToCommand extends Command {

    private String regex = "^\\/msg:to ([A-Za-z0-9]+) (.*)$";

    @Override
    public String[] parse(String command) {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(command);

        String[] signature = new String[2];

        if (m.find()) {
            signature[0] = m.group(1);
            signature[1] = m.group(2);
            return signature;
        }

        return signature;
    }

    @Override
    public void run(String ...args) throws IOException, ServerErrorException, CommandErrorException {

        String text = args != null && args.length > 0 ? args[0] : null;
        String to = args != null && args.length > 1 ? args[1] : null;

        System.out.println(text);
        System.out.println(to);

        Api.message(text, to);
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
