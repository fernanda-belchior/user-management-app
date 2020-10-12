package br.com.fernanda.usermanagement.ejb.bean;

import br.com.fernanda.usermanagement.exception.UserManagementException;
import br.com.fernanda.usermanagement.domain.User;

public interface UserBean {
    void save(User user) throws UserManagementException;
}
