package br.com.fernanda.usermanagement.ejb.bean.impl;

import br.com.fernanda.usermanagement.ejb.bean.UserBean;
import br.com.fernanda.usermanagement.ejb.dao.UserDAO;
import br.com.fernanda.usermanagement.ejb.entity.User;
import br.com.fernanda.usermanagement.ejb.exception.UserManagementException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserBeanImpl implements UserBean {

    @EJB
    UserDAO userDAO;


    @Override
    public void save(User user)  {
        userDAO.save(user);
    }


    public void remove(Long id)  {
        userDAO.remove(id);
    }

    public User findById(Long id)  {
        return userDAO.findById(id);
    }

    public User findByEmail(String email) throws UserManagementException {
        return userDAO.findByEmail(email);
    }

    public List<User> findAll() throws UserManagementException {
        return userDAO.findAll();
    }

}




