package br.com.fernanda.usermanagement.ejb.bean.impl;

import br.com.fernanda.usermanagement.ejb.bean.UserBean;
import br.com.fernanda.usermanagement.ejb.dao.UserDAO;
import br.com.fernanda.usermanagement.ejb.dao.impl.UserDAOImpl;
import br.com.fernanda.usermanagement.domain.User;
import br.com.fernanda.usermanagement.exception.UserManagementException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserBeanImpl implements UserBean {

    @EJB
    UserDAO userDAO;

    public UserBeanImpl() {
    }

    @Override
    public void save(User user) throws UserManagementException {
        userDAO.save(user);
    }


    public void remove(Long id) throws UserManagementException {
        userDAO.remove(id);
    }

    public User findByFiscalCode(String fiscalCode) throws UserManagementException {
        return userDAO.findByFiscalCode(fiscalCode);
    }

    public User findById(Long id) throws UserManagementException {
        return userDAO.findById(id);
    }


    public List<User> findAll() throws UserManagementException {
        return userDAO.findAll();
    }

    public static void main (String[] args) throws UserManagementException {

        User user = new User(null,"Fernanda","","1","",null);
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.save(user);
        System.out.println(userDAO.findAll().get(0).getId());
        user.setName("Leninho");
        userDAO.save(user);
        System.out.println(userDAO.findById(Long.valueOf(1)).getName());
        System.out.println(userDAO.findAll().get(0).getId());
        System.out.println(userDAO.findByFiscalCode("1").getName());


    }

}


