package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommandFacade {

    static String regex = "^\\/.*";

    public static void run(String command) {

        if(!Pattern.matches(CommandFacade.regex, command)) {
            return;
        }

        List<Command> registeredCommands = new ArrayList<>();

        registeredCommands.add(new LoginCommand());
        registeredCommands.add(new MessageCommand());
        registeredCommands.add(new MessageToCommand());
        registeredCommands.add(new MessageToRoomCommand());
        registeredCommands.add(new RegisterCommand());
        registeredCommands.add(new RoomCommand());

        try {

            for (Command commandObject:registeredCommands) {
                if(Pattern.matches(commandObject.getRegex(), command)) {
                    commandObject.run(commandObject.parse(command));
                    return;
                }
            }

            System.err.println("Command not found");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServerErrorException | CommandErrorException e) {
            System.err.println(e.getMessage());
        }
    };
}
