package ua.kiev.prog.commands;

import ua.kiev.prog.exceptions.CommandErrorException;
import ua.kiev.prog.exceptions.ServerErrorException;

import java.io.IOException;

abstract public class Command {

    Command(){}
    Command(String command){}

    abstract void run() throws IOException, ServerErrorException, CommandErrorException;
}
