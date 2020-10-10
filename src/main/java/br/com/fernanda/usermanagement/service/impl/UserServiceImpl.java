package br.com.fernanda.usermanagement.service.impl;

import br.com.fernanda.usermanagement.entity.User;
import br.com.fernanda.usermanagement.service.UserService;
import br.com.fernanda.usermanagement.dao.UserDAO;
import br.com.fernanda.usermanagement.dao.impl.UserDAOImpl;

import java.util.List;


public class UserServiceImpl implements UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public void record(User user){
        userDAO.record(user);
    }

    @Override
    public void update(User user){
        userDAO.update(user);
    }

    @Override
    public void remove(User user){
        userDAO.remove(user);
    }

    @Override
    public User findByFiscalCode(String fiscalCode){
        return userDAO.findByFiscalCode(fiscalCode);
    }

    @Override
    public User findById(int id){
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll(){
        return userDAO.findAll();
    }

}


