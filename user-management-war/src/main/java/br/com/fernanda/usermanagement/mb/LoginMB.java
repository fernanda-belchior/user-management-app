package br.com.fernanda.usermanagement.mb;

import br.com.fernanda.usermanagement.ejb.bean.UserBean;
import br.com.fernanda.usermanagement.ejb.entity.User;
import br.com.fernanda.usermanagement.ejb.exception.UserManagementException;
import br.com.fernanda.usermanagement.mb.constants.Link;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@Getter
@Setter
@ManagedBean(name="LoginMB")
@SessionScoped
public class LoginMB {

    @EJB
    private UserBean userBean;

    private User user = new User(null, "admin",
            "admin@admin.com.br","admin",null);

    private String email;
    private String password;

    public String login() throws UserManagementException {
        this.userBean.save(this.user);
        User user2 = userBean.findByEmail(this.email);

        if (user2 == null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage
                            (FacesMessage.SEVERITY_ERROR,
                                    "User not found!",
                                    "Error Login!"));
            return null;
        } else if (!user2.getPassword().equals(this.password)) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage
                            (FacesMessage.SEVERITY_ERROR,
                                    "Invalid password!",
                                    "Error Login!"));
            return null;

        }

        return Link.ADD_LIST;

    }
}
