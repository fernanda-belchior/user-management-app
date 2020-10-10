package br.com.fernanda.usermanagement.dao;

import br.com.fernanda.usermanagement.entity.User;

import java.util.List;

public interface UserDAO {

    void record (User user);
    void update (User user);
    void remove (User user);
    User findByFiscalCode(String fiscalCode);
    User findById (int id);
    List<User> findAll();
}
