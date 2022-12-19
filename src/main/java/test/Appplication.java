package test;

import metier.Compte;
import metier.IMetierbanque;
import metier.MetierbanqueImpl;

import java.util.Scanner;

public class Appplication {
    public static void main(String[] args) {
        // System.out.println("Message From Main App");
        new Appplication().start();
    }
    public void start(){

        System.out.println("Demarrage de l'application");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisir le code du compte : ");
        long code= scanner.nextLong();
        System.out.println("Saisir le solde initiale du compte :");
        double solde=scanner.nextDouble();
        IMetierbanque metierbanque=new MetierbanqueImpl();
        metierbanque.addCompte(new Compte(code, solde));
        while (true){
            try {
                System.out.println("Type Operation : ");
                String type = scanner.next();
                if (type.equals("quitter")) break;
                System.out.println("Montant : ");
                double montant = scanner.nextDouble();
                if (type.equals("v")) {
                    metierbanque.verser(code, montant);
                } else if (type.equals("r")) {
                    metierbanque.retirer(code, montant);
                }
                Compte compte = metierbanque.consulter(code);
                System.out.println("Etat du compte : " + compte.toString());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Fin de l'application");
    }
}
