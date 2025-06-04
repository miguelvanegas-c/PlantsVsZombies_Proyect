import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GameLogger implements Serializable {
    private static final Logger logger = Logger.getLogger(GameLogger.class.getName());


    static {
        try {
            FileHandler fileHandler = new FileHandler("pvz.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);
        } catch (Exception e){
            System.out.println("Error configurando el logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
