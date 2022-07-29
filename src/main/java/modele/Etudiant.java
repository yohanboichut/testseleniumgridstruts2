package modele;

public class Etudiant {

    private String numeroEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;

    public Etudiant(String numeroEtudiant, String nomEtudiant, String prenomEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }
}
