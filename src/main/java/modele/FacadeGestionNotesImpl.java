package modele;

import java.util.*;

public class FacadeGestionNotesImpl implements FacadeGestionNotes {

    private Map<String,Professeur> professeurMap;
    private Map<String,String> professeursConnectes;
    private Map<String, Etudiant> etudiantMap;
    private Collection<Enseignement> enseignements;

    public FacadeGestionNotesImpl() {
        this.professeurMap = new HashMap<>();
        this.professeursConnectes = new HashMap<>();
        this.etudiantMap = new HashMap<>();
        this.initialisation();
    }


    private void initialisation(){
        Etudiant etudiant1 = new Etudiant("1234567","NomEtudiant1","PrenomEtudiant1");
        Etudiant etudiant2 = new Etudiant("2345678","NomEtudiant2","PrenomEtudiant2");
        Etudiant etudiant3 = new Etudiant("3456789","NomEtudiant3","PrenomEtudiant3");
        Etudiant etudiant4 = new Etudiant("7654321","NomEtudiant4","PrenomEtudiant4");
        Professeur yohan = new Professeur("yohan.boichut@univ-orleans.fr","Boichut","Yohan","password");
        Professeur mathieu = new Professeur("mathieu.chapelle@univ-orleans.fr","Chapelle","Mathieu","password");

        Enseignement pnt = new Enseignement("PnT",Arrays.asList(yohan,mathieu));
        Enseignement test = new Enseignement("Test",Arrays.asList(yohan));
        pnt.ajouterEtudiants(Arrays.asList(etudiant1,etudiant2));
        test.ajouterEtudiants(Arrays.asList(etudiant3,etudiant4));

        List<Etudiant> etudiants = Arrays.asList(etudiant1,etudiant2,etudiant3,etudiant4);
        List<Professeur> professeurs = Arrays.asList(yohan,mathieu);

        etudiants.stream().forEach(e -> etudiantMap.put(e.getNumeroEtudiant(),e));
        professeurs.stream().forEach(p -> professeurMap.put(p.getEmail(),p));
        enseignements = Arrays.asList(pnt,test);

    }

    @Override
    public String connexion(String email, String motDePasse) throws IdentifiantsIncorrectsException {

        Professeur professeur = this.professeurMap.get(email);
        if (Objects.isNull(professeur))
            throw new IdentifiantsIncorrectsException();
        if (professeur.getMotDePasse().equals(motDePasse)) {
            String cle = UUID.randomUUID().toString();
            this.professeursConnectes.put(cle,email);
            return cle;
        }
        else
            throw new IdentifiantsIncorrectsException();
    }


    @Override
    public Collection<Enseignement> getEnseignements(String cle) throws CleInexistanteException {
        if (this.professeursConnectes.containsKey(cle)) {
            return this.professeurMap.get(this.professeursConnectes.get(cle)).getEnseignements();
        }
        else
            throw new CleInexistanteException();

    }


    @Override
    public void saisirNote(String cle, String nomEnseignement, String numeroEtudiant, double note) throws CleInexistanteException, EtudiantInexistantException {
        if (this.professeursConnectes.containsKey(cle)) {
            Professeur professeur = this.professeurMap.get(professeursConnectes.get(cle));
            Etudiant etudiant = this.etudiantMap.get(numeroEtudiant);
            if (Objects.isNull(etudiant)) {
                throw new EtudiantInexistantException();
            }
            professeur.saisirNote(nomEnseignement,etudiant,note);
        }

        else
            throw new CleInexistanteException();
    }



    @Override
    public Collection<Note> getNotesEnseignement(String cle, String nomEnseignement) throws CleInexistanteException {
        if (this.professeursConnectes.containsKey(cle)) {
            Professeur professeur = this.professeurMap.get(professeursConnectes.get(cle));
            return professeur.getNotesModule(nomEnseignement);
        }
        else
            throw new CleInexistanteException();
    }


    @Override
    public Collection<Etudiant> getEtudiantsEnseignement(String cle, String nomEnseignement) throws CleInexistanteException {
        if (this.professeursConnectes.containsKey(cle)) {
            Professeur professeur = this.professeurMap.get(professeursConnectes.get(cle));
            return professeur.getEtudiantModule(nomEnseignement);
        }
        else
            throw new CleInexistanteException();
    }


    @Override
    public void deconnexion(String cle) {
        this.professeursConnectes.remove(cle);
    }
}
