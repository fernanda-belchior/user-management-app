package br.com.fernanda.usermanagement.ejb.dao;

import br.com.fernanda.usermanagement.domain.User;
import br.com.fernanda.usermanagement.exception.UserManagementException;

import java.util.List;

public interface UserDAO {

    void save (User user) throws UserManagementException;
    void remove (Long id) throws UserManagementException;
    User findByFiscalCode(String fiscalCode) throws UserManagementException;
    User findById (Long id) throws UserManagementException;
    List<User> findAll() throws UserManagementException;
}
