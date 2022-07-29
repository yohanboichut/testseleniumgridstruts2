package modele;

import java.util.Collection;

public class Formation {

    private String libelleFormation;
    private Collection<Etudiant> etudiants;
    private Collection<Enseignement> modulesFormation;

    public Formation(String libelleFormation, Collection<Etudiant> etudiants) {
        this.libelleFormation = libelleFormation;
        this.etudiants = etudiants;
    }

    public void ajouterModule(Enseignement module) {
        module.ajouterEtudiants(this.etudiants);
        this.modulesFormation.add(module);
    }

    public String getLibelleFormation() {
        return libelleFormation;
    }

    public Collection<Etudiant> getEtudiants() {
        return etudiants;
    }

    public Collection<Enseignement> getModulesFormation() {
        return modulesFormation;
    }
}
