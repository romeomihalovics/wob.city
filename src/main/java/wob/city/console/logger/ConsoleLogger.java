package wob.city.console.logger;

import java.io.IOException;
import java.util.logging.*;

public class ConsoleLogger {
    private static volatile ConsoleLogger instance;
    private static final Object mutex = new Object();

    private final Logger logger;

    private ConsoleLogger() {
        this.logger = Logger.getLogger("ConsoleLogger");
        try {
            FileHandler fileHandler = new FileHandler("ConsoleLog.txt");
            this.logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConsoleLogger getLogger() {
        ConsoleLogger result = instance;
        if(result == null) {
            synchronized (mutex) {
                result = instance;
                if(result == null) {
                    instance = result = new ConsoleLogger();
                }
            }
        }
        return result;
    }

    public void log(String toLog) {
        logger.log(new LogRecord(Level.INFO, "\n" + toLog + "\n\n"));
    }
}
