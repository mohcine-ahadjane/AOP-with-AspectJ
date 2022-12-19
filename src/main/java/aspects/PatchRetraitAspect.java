package aspects;

import metier.Compte;
import metier.MetierbanqueImpl;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PatchRetraitAspect {
    @Pointcut("execution(* metier.MetierBanqueImpl.retirer(..))")
    public void pc1(){}

    @Around("pc1() args(code,montant")
    public Object autourRetirer(Long code, double mpntant, ProceedingJoinPoint proceedingJoinPoint){
        MetierbanqueImpl metierbanque = (MetierbanqueImpl) jointPoint.getTarget;
        Compte compte= metierbanque.consulter(code);
        if (compte.getSolde()< montant) throw new RuntimeException("Solde insuffisant");
        return proceedingJoinPoint.proceed();
    }

}
