package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.regex.Pattern;

public class CommandFacade {

    static String regex = "^\\/.*";

    public static void run(String command) {
        //TODO find commands by reflection api

        if(!Pattern.matches(CommandFacade.regex, command)) {
            return;
        }

        try {

            if (Pattern.matches(RegisterCommand.regex, command)) {

                new RegisterCommand().run();

            } else if(Pattern.matches(LoginCommand.regex, command)) {

                new LoginCommand().run();

            } else if(Pattern.matches(RoomCommand.regex, command)) {

                new RoomCommand(command).run();

            } else if(Pattern.matches(MessageCommand.regex, command)) {

                new MessageCommand(command).run();

            } else if(Pattern.matches(MsgToCommand.regex, command)) {

                new MsgToCommand(command).run();

            } else {
                System.err.println("Command not found");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServerErrorException | CommandErrorException e) {
            System.err.println(e.getMessage());
        }
    };
}
