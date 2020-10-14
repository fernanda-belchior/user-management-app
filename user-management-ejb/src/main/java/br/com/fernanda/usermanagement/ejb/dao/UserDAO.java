package br.com.fernanda.usermanagement.ejb.dao;

import br.com.fernanda.usermanagement.ejb.entity.User;
import br.com.fernanda.usermanagement.ejb.exception.UserManagementException;

import java.util.List;

public interface UserDAO {

    void save (User user);
    void remove (Long id);
    User findById (Long id);
    User findByEmail(String email) throws UserManagementException;
    List<User> findAll() throws UserManagementException;
}
