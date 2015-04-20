package ch.hearc.ig.ta.prjergonomie.log;


import ch.hearc.ig.ta.prjergonomie.exceptions.LoggerException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeremy.wermeille
 */
public class ApplicationLogger {
    
    private static ApplicationLogger instance = null;
    
    private Logger logger;
    
    private ApplicationLogger() throws LoggerException{
        logger = Logger.getLogger(ApplicationLogger.class.getName());
        Handler[] handlers = logger.getHandlers();
        
        for(int i = 0; i<handlers.length; i++){
            logger.removeHandler(handlers[i]);
        }
        
        try{
            // On enregistre le fichier de log à la racine du dossier du projet Netbeans
            FileHandler fileHandler = new FileHandler("TechnoActuellesApplication.log");
            fileHandler.setFormatter(new java.util.logging.SimpleFormatter());
            logger.addHandler(fileHandler);
        }catch(Exception e){
            System.out.println("Erreur pendant la création du logger : " + e);
            throw new LoggerException("Erreur pendant la création du logger", e);
        }
    }
    
    public static ApplicationLogger getInstance(){
        if(instance == null){
            instance = new ApplicationLogger();
        }
        
        return instance;
    }
    
    /**
     * Cette méthode ne sert que de raccourci pour éviter de devoir faire MyLogger.getInstance().getLogger().log(...).
     * @param level
     * @param message 
     */
    public void log(Level level, String message){
        logger.log(level, message);
    }
    
    public void log(Level level, String message, Throwable throwable){
        logger.log(level, message, throwable);
    }
    
}
