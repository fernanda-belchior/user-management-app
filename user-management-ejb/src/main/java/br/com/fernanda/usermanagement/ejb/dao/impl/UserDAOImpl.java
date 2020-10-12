package br.com.fernanda.usermanagement.ejb.dao.impl;

import br.com.fernanda.usermanagement.ejb.dao.UserDAO;
import br.com.fernanda.usermanagement.ejb.util.JpaUtil;
import br.com.fernanda.usermanagement.exception.UserManagementException;
import br.com.fernanda.usermanagement.domain.User;
import javax.persistence.EntityManager;


import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDAOImpl implements UserDAO {

    private EntityManager em;


    @Override
    public void save(User user) throws UserManagementException {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        if(user.getId()==null){
            this.em.persist(user);
            System.out.println("feito");
        }

        else{
            if(findById(user.getId())!=null)
                em.merge(user);
        }
        em.getTransaction().commit();
        em.close();

    }


    @Override
    public void remove(Long id) throws UserManagementException {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        User user = findById(id);
        em.remove(user);
        em.close();
    }


    @Override
    public User findByFiscalCode(String fiscalCode) throws UserManagementException {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<User> list;
        User user;

        try {

            em.getTransaction().begin();
            String sql = "SELECT user from User user where user.fiscalCode = :fiscalCode";
            Query query = em.createQuery(sql);
            query.setParameter("fiscalCode", fiscalCode);
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new UserManagementException("");
        } finally {
            em.close();
        }
        if (!list.isEmpty()) {
            user = list.get(0);
        }else {
            throw new UserManagementException("");
        }
        if (user ==null){
            throw new RuntimeException("");
        }

        return user;
    }


    @Override
    public User findById(Long id) throws UserManagementException {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if(user==null){
            throw new UserManagementException("");
        }
        return user;
    }


    @Override
    public List<User> findAll() throws UserManagementException {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        List<User> userList;

        try {
            String sql = "SELECT user from User user";
            Query query = em.createQuery(sql);
            userList = query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new UserManagementException("");
        } finally {
            em.close();
        }

        if(userList.isEmpty()){
            throw new UserManagementException("");

        }
        return userList;

    }





}
