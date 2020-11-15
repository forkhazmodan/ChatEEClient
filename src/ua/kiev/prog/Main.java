package ua.kiev.prog;

import ua.kiev.prog.commands.CommandFacade;
import ua.kiev.prog.utils.SingletonScanner;

public class Main {
    public static void main(String[] args) {

        try {

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            while (true) {
                String command = SingletonScanner.scanner().nextLine();
                CommandFacade.run(command);
            }

        } finally {
            SingletonScanner.close();
        }
    }
}
