package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Enseignement {

    private String nomModule;
    private Map<Etudiant,Double> noteEtudiant;
    private Collection<Etudiant> etudiantsSuivantModule;
    private Collection<Professeur> responsables;

    public Enseignement(String nomModule, Collection<Professeur> responsables) {
        this.nomModule = nomModule;
        this.responsables = responsables;
        this.noteEtudiant = new HashMap<>();
        this.etudiantsSuivantModule = new ArrayList<>();
        responsables.stream().forEach(professeur -> professeur.ajouterEnseignement(this));
    }

    public void ajouterEtudiants(Collection<Etudiant> etudiants) {
        etudiantsSuivantModule.addAll(etudiants);
    }

    public void saisirNoteEtudiant(Etudiant etudiant,double note)  {
       noteEtudiant.put(etudiant,note);

    }



    public String getLibelle() {
        return nomModule;
    }

    public Collection<Note> getNotes() {
        return noteEtudiant.entrySet().stream().map(x -> new Note(x.getKey(),x.getValue())).collect(Collectors.toList());
    }


    public Collection<Etudiant> getEtudiantsSuivantModule(){
        return this.etudiantsSuivantModule;
    }
}
