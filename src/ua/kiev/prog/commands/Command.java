package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;

import java.io.IOException;

abstract public class Command {

    public String[] parse(String command) { return null; };

    abstract public void run(String... args) throws IOException, ServerErrorException, CommandErrorException;

    abstract public String getRegex();
}
