package employe;
abstract class Employe {
protected String matricule;
protected String nom;
protected String prenom;
protected int age;
protected String date;
protected final static double SALAIRE_BASE = 110000; 
public Employe (String matricule, String nom, String prenom, int age, String date){
    this.matricule = matricule;
    this.nom = nom;
    this.prenom = prenom;
    this.age = age;
    this.date = date;
}
public abstract double calculerSalaire();
public String getTitre()
{
    return "L'employé ";
}
public String getNom() {
    return getTitre() + prenom + " " + nom;
}
}
abstract class Commercial extends Employe {
    private double chiffreAffaire;
    public Commercial (String matricule, String prenom, String nom, int age, String date, double chiffreAffaire) {
        super (matricule, prenom, nom, age, date);
        this.chiffreAffaire = chiffreAffaire;
    }
    public double getChiffreAffaire()
    {
        return chiffreAffaire;
    }
}
class Vendeur extends Commercial {
    private final static double POURCENTAGE_VENDEUR = 0.2;
    private final static int BONUS_VENDEUR = 100;
    public Vendeur (String matricule, String prenom, String nom, int age, String date, double chiffreAffaire){
        super (matricule, prenom, nom, age, date, chiffreAffaire);
    }
    public double calculerSalaire() {
        return (POURCENTAGE_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR + SALAIRE_BASE;
    }
    public String getTitre()
    {
        return "Le vendeur ";
    }
}
class Representant extends Commercial {
    private final static double POURCENTAGE_REPRESENTANT = 0.2;
    private final static int BONUS_REPRESENTANT = 200;
    public Representant (String matricule, String prenom, String nom, int age, String date, double chiffreAffaire) {
        super (matricule, prenom, nom, age, date, chiffreAffaire);
    }
    public double calculerSalaire() {
        return (POURCENTAGE_REPRESENTANT * getChiffreAffaire()) + BONUS_REPRESENTANT + SALAIRE_BASE;
    }
    public String getTitre()
    {
        return "Le représentant ";
    }
}
class Technicien extends Employe {
    private final static double FACTEUR_UNITE = 5.0;
    private int unites;
    public Technicien (String matricule, String prenom, String nom, int age, String date,int unites)
    {
        super ( matricule, prenom, nom, age, date);
        this.unites = unites;
    }
    public double calculerSalaire() {
        return FACTEUR_UNITE * unites;
    }
    public String getTitre()
    {
        return "Le technicien ";
    }
}
class Manutentionnaire extends Employe {
    private final static double SALAIRE_HORAIRE = 65.0;
    private int heures;
    public Manutentionnaire (String matricule, String prenom, String nom, int age,String date, int heures) {
        super(matricule, prenom, nom, age, date);
        this.heures = heures;
    }
    public double calculerSalaire() {
        return SALAIRE_HORAIRE * heures + SALAIRE_BASE;
    }
    public String getTitre()
    {
        return "Le manut. ";
    }
}
interface ARisque {
    int PRIME = 25000;
   }
class TechnARisque extends Technicien implements ARisque {
    public TechnARisque(String matricule, String prenom, String nom, int age, String date, int unites) {
        super(matricule, prenom, nom, age, date, unites);
    }
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}
class ManutARisque extends Manutentionnaire implements ARisque{
    public ManutARisque(String matricule, String prenom, String nom, int age, String date, int heures) {
        super(matricule, prenom, nom, age, date, heures);
    }
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}
class Personnel {
    private Employe[] staff;
    private int nbreEmploye;
    private final static int MAXEMPLOYE = 300;
    public Personnel () {
        staff = new Employe [MAXEMPLOYE];
        nbreEmploye = 0;
    }
    public void ajouterEmploye (Employe e) {
        ++nbreEmploye;
        if ( nbreEmploye <= MAXEMPLOYE) {
            staff[nbreEmploye - 1] = e;
        } else {
            System.out.println("Pas plus de " + MAXEMPLOYE + "employés");
        }
    }
public double salaireMoyen() {
double somme = 0.0;
for ( int i = 0; i < nbreEmploye; i++) {
somme += staff[i].calculerSalaire();
}
return somme / nbreEmploye;
}
public void afficherSalaires() {
for (int i = 0; i < nbreEmploye; i++){
System.out.println(staff[i].getNom() + " gagne " + staff[i].calculerSalaire() + " francs.");
}
}
}
class Salaires {
    public static void main (String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vendeur("12A802", "Resler", "Bernard", 23, "2009", 20000));
        p.ajouterEmploye(new Representant("70Y341", "Zeus", "LED", 26, "2001", 30000));
        p.ajouterEmploye(new Technicien("09Z301", "Bastos", "Ducon", 27, "1998", 1000));
        p.ajouterEmploye(new Manutentionnaire("10N543", "Edith", "Fout", 43, "1994", 47));
        p.ajouterEmploye(new TechnARisque("02JK40", "Jerry", "Power", 29, "2000", 1000));
        p.ajouterEmploye(new ManutARisque("90X202", "Bouf", "Crick", 21, "2012", 45000));
        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de " + p.salaireMoyen() + " francs.");
    }
}
