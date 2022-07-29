package modele;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Professeur {

    private String email;
    private String nom;
    private String prenom;
    private String motDePasse;

    private Map<String,Enseignement> enseignementMap;

    public Professeur(String email, String nom, String prenom, String motDePasse) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.enseignementMap = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Collection<Enseignement> getEnseignements() {

        return enseignementMap.values();

    }

    public void ajouterEnseignement(Enseignement enseignement){
        this.enseignementMap.put(enseignement.getLibelle(),enseignement);
    }

    public void saisirNote(String nomModule, Etudiant etudiant, double note)  {
        Enseignement enseignement = this.enseignementMap.get(nomModule);
        enseignement.saisirNoteEtudiant(etudiant,note);
    }


    public Collection<Note> getNotesModule(String nomModule){
        Enseignement enseignement = this.enseignementMap.get(nomModule);
        return enseignement.getNotes();
    }


    public Collection<Etudiant> getEtudiantModule(String nomModule) {
        Enseignement enseignement = this.enseignementMap.get(nomModule);
        return enseignement.getEtudiantsSuivantModule();
    }
}
