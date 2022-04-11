package utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLog {
    static public Logger monitor(){ return LoggerFactory.getLogger("monitor");}
    static public Logger cache(){ return LoggerFactory.getLogger("cache");}
    static public Logger error(){ return LoggerFactory.getLogger("errors");}
    static public Logger messages(){ return LoggerFactory.getLogger("messages");}
}
