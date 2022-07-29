package modele;

public class Note {

    private Etudiant etudiant;
    private double note;

    public Note() {
    }

    public Note(Etudiant etudiant, double note) {
        this.etudiant = etudiant;
        this.note = note;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public double getNote() {
        return note;
    }
}
