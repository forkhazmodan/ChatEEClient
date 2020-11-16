package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.ServerErrorException;
import ua.kiev.prog.utils.Api;
import ua.kiev.prog.utils.SingletonScanner;

import java.io.IOException;

public class RegisterCommand extends Command {

    private String regex = "^\\/register$";

    @Override
    public void run(String ...args)  throws IOException, ServerErrorException {
        System.out.println("Enter your login: ");
        String login = SingletonScanner.scanner().nextLine();
        System.out.println("Enter your password: ");
        String password = SingletonScanner.scanner().nextLine();

        Api.register(login, password);
        System.out.println("You was successfully registered");
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
