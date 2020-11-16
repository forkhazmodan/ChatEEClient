package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;
import ua.kiev.prog.models.User;
import ua.kiev.prog.utils.Api;
import ua.kiev.prog.utils.SingletonScanner;

import java.io.IOException;

public class LoginCommand extends Command {

    private String regex = "^\\/login$";

    @Override
    public void run(String ...args) throws IOException, ServerErrorException, CommandErrorException {
        System.out.println("Enter your login: ");
        String login = SingletonScanner.scanner().nextLine();
        System.out.println("Enter your password: ");
        String password = SingletonScanner.scanner().nextLine();

        Api.login(login, password);

        User user = User.getInstance();

        System.out.println(String.format("You was successfully logged-in as: %s", user.getLogin()));
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
