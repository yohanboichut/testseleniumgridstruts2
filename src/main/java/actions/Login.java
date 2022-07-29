package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.*;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class Login extends ActionSupport implements SessionAware,ApplicationAware {

    private FacadeGestionNotes facadeGestionNotes;
    private String email;
    private String motDePasse;
    private Map<String,Object> session;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    private String cle;



    public Collection<Enseignement> getEnseignements() throws CleInexistanteException {
        return this.facadeGestionNotes.getEnseignements(cle);
    }

    @Override
    public String execute() throws Exception {
        try {
            cle = this.facadeGestionNotes.connexion(email,motDePasse);
            this.session.put("cle",cle);

        } catch (IdentifiantsIncorrectsException e) {
            addFieldError("email","Identifiants incorrects");
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
