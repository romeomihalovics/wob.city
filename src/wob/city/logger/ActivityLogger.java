package wob.city.logger;

import java.io.IOException;
import java.util.logging.*;

public class ActivityLogger {
    private static volatile ActivityLogger instance;
    private static final Object mutex = new Object();

    private final Logger logger;

    private ActivityLogger() {
        this.logger = Logger.getLogger("ActivityLogger");
        this.logger.setUseParentHandlers(false);
        try {
            FileHandler fileHandler = new FileHandler("ActivityLog.txt");
            this.logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ActivityLogger getLogger() {
        ActivityLogger result = instance;
        if(result == null) {
            synchronized (mutex) {
                result = instance;
                if(result == null) {
                    instance = result = new ActivityLogger();
                }
            }
        }
        return result;
    }

    public void log(String toLog) {
        logger.log(new LogRecord(Level.INFO, "\n" + toLog + "\n\n"));
    }
}
