package aspects;

import org.aspectj.lang.annotation.Aspect;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {
    Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    public LoggingAspect() throws IOException {
        logger.addHandler(new FileHandler("log.xml"));
        logger.setUseParentHandlers(false);
    }

    long t1, t2;
    @Pointcut("execution(* metier.MetierBanqueImpl.*(..)")
    public void pc1(){}
//    @Before("pc1()")
//    public void avant(JointPoint jointPoint){
//        t1=System.currentTimeMillis();
//        logger.info("-----------------------------------------");
//        logger.info("Avant l'execution de la methode" + jointPoint.getSignature());
//    }
//    @After("pc1()")
//    public void apres(JointPoint jointPoint){
//        logger.info("Apres l'execution de la methode" + jointPoint.getSignature());
//    t2=System.currentTimeMillis();
//        logger.info("Duree d'execution de la methode" + (t2-t1));
//        logger.info("------------------------------------------");
//    }

    @Around("pc1")
    public  Object autour(ProceedingJoinPoint proceedingJoinPoint, JointPoint jointPoint) throws Throwable{
        long t1=System.currentTimeMillis();
        logger.info("-------------------------------------------");
        logger.info("Avant l'execution de la methode" + jointPoint.getSignature());
        Object result = proceedingJoinPoint.proceed();
        logger.info("Apres l'execution de la methode" + jointPoint.getSignature());
        t2=System.currentTimeMillis();
        logger.info("Duree d'execution de la methode" + (t2-t1));
        logger.info("------------------------------------------");
        return result;
    }
}
