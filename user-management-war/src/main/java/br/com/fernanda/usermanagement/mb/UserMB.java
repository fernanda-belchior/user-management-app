package br.com.fernanda.usermanagement.mb;


import br.com.fernanda.usermanagement.ejb.bean.UserBean;
import br.com.fernanda.usermanagement.ejb.entity.User;
import br.com.fernanda.usermanagement.ejb.exception.UserManagementException;
import br.com.fernanda.usermanagement.mb.constants.Link;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.util.List;

@Getter
@Setter
@ManagedBean(name="UserMB")
@SessionScoped
public class UserMB {

    @EJB
    private UserBean userBean;
    private User user = new User();


    public UserMB() {
        // Default Constructor
    }

    public String save(){

        this.userBean.save(user);
        this.user = new User();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage
                        (FacesMessage.SEVERITY_INFO,
                                "User Added!",
                                "Success!"));

        this.refresh();
        return Link.ADD_LIST;
    }


    public String remove(User user )  {
        this.userBean.remove(user.getId());
        this.refresh();
        return Link.ADD_LIST;
    }

    public String update( ){
        this.userBean.save(this.user);
        this.refresh();
        this.user = new User();
        return Link.ADD_LIST;
    }

    public String findById(){
        this.user = this.userBean.findById(this.user.getId());
        this.refresh();
        return Link.FIND_EDIT;
    }


    public List<User> getAllUsers() throws UserManagementException {
        return this.userBean.findAll();
    }

    public void refresh(){
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot =viewHandler.createView(context,context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }

    public String linkAdd(){
        return Link.ADD_LIST;
    }
    public String linkEdit(){
        return Link.FIND_EDIT;
    }



}
