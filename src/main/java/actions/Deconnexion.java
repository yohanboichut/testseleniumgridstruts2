package actions;

import com.opensymphony.xwork2.ActionSupport;
import modele.*;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;
import java.util.Objects;

public class Deconnexion extends ActionSupport implements ApplicationAware,SessionAware {

    private FacadeGestionNotes facadeGestionNotes;
    private Map<String, Object> session;
    private String cle;



    @Override
    public String execute() throws Exception{
        this.cle = (String) this.session.get("cle");
        this.facadeGestionNotes.deconnexion(cle);
        this.session.clear();
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
