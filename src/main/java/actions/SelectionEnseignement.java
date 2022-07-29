package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.*;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class SelectionEnseignement extends ActionSupport implements ApplicationAware,SessionAware {

    private String enseignement;
    private FacadeGestionNotes facadeGestionNotes;
    private Map<String, Object> session;
    private String cle;
    private Collection<Etudiant> etudiantsModule;
    private Collection<Note> etudiantsNotes;

    public void setEnseignement(String enseignement) {
        this.enseignement = enseignement;
    }

    public Collection<Note> getEtudiantsNotes() {
        return etudiantsNotes;
    }

    public Collection<Etudiant> getEtudiantsModule(){
        return etudiantsModule;
    }

    @Override
    public String execute()  {
        this.session.put("enseignement",enseignement);
        this.cle = (String) this.session.get("cle");
        try {
            this.etudiantsModule = this.facadeGestionNotes.getEtudiantsEnseignement(cle,enseignement);
        } catch (CleInexistanteException e) {
            session.clear();
            return "home";
        }
        try {
            this.etudiantsNotes = this.facadeGestionNotes.getNotesEnseignement(cle,enseignement);
        } catch (CleInexistanteException e) {
            session.clear();
            return "home";
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
