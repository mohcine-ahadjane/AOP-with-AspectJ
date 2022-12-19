package aspects;

import metier.Compte;
import metier.MetierbanqueImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PatchRetraitAspect {
    @Pointcut("execution(* metier.MetierBanqueImpl.retirer(..))")
    public void pc1(){}

    @Around("pc1() && args(code,montant)")
    public Object autourRetirer(Long code, double montant, JoinPoint joinPoint, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MetierbanqueImpl metierbanque = (MetierbanqueImpl) joinPoint.getTarget();
        Compte compte= metierbanque.consulter(code);
        if (compte.getSolde()< montant) throw new RuntimeException("Solde insuffisant");
        return proceedingJoinPoint.proceed();
    }

}
