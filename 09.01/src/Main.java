import org.apache.log4j.Logger;

public class Main {
    static Logger logger = Logger.getLogger("test");

    public static void main(String[] args) {
        logger.debug("Это тестовый лог");
        logger.info("Привет, логгер работает!");
    }
}
