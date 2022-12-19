package metier;

public interface IMetierbanque {
    void addCompte(Compte cp);
    void verser(Long code, double montant);
    void retirer(Long code, double montant);
    Compte consulter(Long code);
}
