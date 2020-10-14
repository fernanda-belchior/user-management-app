package br.com.fernanda.usermanagement.ejb.bean;

import br.com.fernanda.usermanagement.ejb.entity.User;
import br.com.fernanda.usermanagement.ejb.exception.UserManagementException;

import java.util.List;

public interface UserBean {
    void save(User user);
    void remove(Long id);
    User findById (Long id);
    User findByEmail(String email) throws UserManagementException;
    List<User> findAll() throws UserManagementException;

}
