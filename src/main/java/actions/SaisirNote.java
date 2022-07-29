package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.*;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class SaisirNote extends ActionSupport implements ApplicationAware,SessionAware {

    private String enseignement;
    private FacadeGestionNotes facadeGestionNotes;
    private Map<String, Object> session;
    private String cle;
    private Collection<Etudiant> etudiantsModule;
    private Collection<Note> etudiantsNotes;
    private String numeroEtudiant;
    private double note;

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }




    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Collection<Note> getEtudiantsNotes() {
        return etudiantsNotes;
    }

    public Collection<Etudiant> getEtudiantsModule(){
        return etudiantsModule;
    }


    @Override
    public String execute() throws Exception{
        this.enseignement = (String) this.session.get("enseignement");
        this.cle = (String) this.session.get("cle");

        try {
            this.etudiantsModule = this.facadeGestionNotes.getEtudiantsEnseignement(cle,enseignement);
            this.etudiantsNotes = this.facadeGestionNotes.getNotesEnseignement(cle,enseignement);
            this.facadeGestionNotes.saisirNote(cle,enseignement,numeroEtudiant,note);
            this.etudiantsModule = this.facadeGestionNotes.getEtudiantsEnseignement(cle,enseignement);
            this.etudiantsNotes = this.facadeGestionNotes.getNotesEnseignement(cle,enseignement);
        } catch (CleInexistanteException e) {
            this.session.clear();
            return "home";
        } catch (EtudiantInexistantException e) {
            addFieldError("numeroEtudiant","Le numéro saisi est incorrect !");
            return INPUT;
        }
        return SUCCESS;
    }




    @Override
    public void setApplication(Map<String, Object> map) {

        this.facadeGestionNotes = (FacadeGestionNotes) map.get("facade");
        if (Objects.isNull(this.facadeGestionNotes)) {
            this.facadeGestionNotes = new FacadeGestionNotesImpl();
            map.put("facade",this.facadeGestionNotes);
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
