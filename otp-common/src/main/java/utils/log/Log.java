package utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    static public Logger system(){ return LoggerFactory.getLogger("System");}
    static public Logger cache(){ return LoggerFactory.getLogger("Cache");}
    static public Logger error(){ return LoggerFactory.getLogger("Error");}
    static public Logger info(){ return LoggerFactory.getLogger("Info");}
}
