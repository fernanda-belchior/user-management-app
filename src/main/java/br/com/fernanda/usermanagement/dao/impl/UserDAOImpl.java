package br.com.fernanda.usermanagement.dao.impl;

import br.com.fernanda.usermanagement.dao.UserDAO;
import br.com.fernanda.usermanagement.entity.User;
import br.com.fernanda.usermanagement.util.JpaUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class UserDAOImpl implements UserDAO {


    @Override
    public void record(User user)  {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();


        try {
            userIsValid(user);
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            //throw new DaoException("Error saving user to database:"+e.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }


    }

    @Override
    public void update(User user)  {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        User userFind = new User();

        if (user.getId() <= 0) {
            //throw new DaoException("The id must be greater than 0.", ErrorCode.BAD_REQUEST.getCode());
        }

        try {
            userIsValid(user);
            em.getTransaction().begin();
            userFind = this.findById(user.getId());
            userFind.setName(user.getName());
            userFind.setEmail(user.getEmail());
            userFind.setPassword(user.getPassword());
            userFind.setFiscalCode(user.getFiscalCode());
            em.merge(userFind);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            em.getTransaction().rollback();
            //throw new DaoException("User reported for update does not exist:"+e.getMessage(),ErrorCode.NOT_FOUND.getCode());
        } catch (RuntimeException e){
            //throw new DaoException("Error updating user in the database:"+e.getMessage(),ErrorCode.SERVER_ERROR.getCode());

        }finally {
            em.close();
        }

    }

    @Override
    public void remove(User user) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        if (user.getId() <= 0) {
//            //throw new DaoException("The id must be greater than 0", ErrorCode.BAD_REQUEST.getCode());
        }
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE  FROM User where id = :idUser ");
            query.setParameter("idUser", user.getId());
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
//            //throw new DaoException("User reported for removal does not exist:"+ e.getMessage(), ErrorCode.NOT_FOUND.getCode());
        } catch (RuntimeException e){
//            //throw new DaoException("Error to remove the user from the database"+e.getMessage(), ErrorCode.SERVER_ERROR.getCode());

        } finally {
            em.close();
        }

    }


    @Override
    public User findByFiscalCode(String fiscalCode) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<User> list = null;
        User user = null;

        try {

            em.getTransaction().begin();
            String sql = "SELECT user from User user where user.fiscal_code = :fiscalCode";
            Query query = em.createQuery(sql);
            query.setParameter("fiscalCode", fiscalCode);
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            //throw new DaoException(ERROR_FIND_BY_NAME+ e.getMessage(), ErrorCode.NOT_FOUND.getCode());
        } finally {
            em.close();
        }
        if (list != null && list.size() > 0) {
            user = list.get(0);
        }else {
            //throw new DaoException(ERROR_FIND_BY_NAME, ErrorCode.SERVER_ERROR.getCode());
        }
        if (user ==null){
            //throw new DaoException(ERROR_FIND_BY_NAME, ErrorCode.SERVER_ERROR.getCode());
        }

        return user;

    }


    @Override
    public User findById(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<User> list = null;
        User user = null;

        if (id <= 0) {
            //throw new DaoException("The id must be greater than 0.", ErrorCode.BAD_REQUEST.getCode());
        }


        try {
            em.getTransaction().begin();
            String sql = "SELECT user from User user WHERE user.id = :id";
            Query query = em.createQuery(sql);
            query.setParameter("id", id);
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            //throw new DaoException(ERROR_FIND_BY_ID + e.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }

        if (list != null && list.size() > 0) {
            user = list.get(0);
        }else {
            //throw new DaoException(ERROR_FIND_BY_ID, ErrorCode.SERVER_ERROR.getCode());
        }
        if (user ==null){
            //throw new DaoException(ERROR_FIND_BY_ID, ErrorCode.SERVER_ERROR.getCode());
        }

        return user;

    }


    @Override
    public List<User> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<User> list = null;


        try {
            em.getTransaction().begin();
            String sql = "SELECT user from User user";
            Query query = em.createQuery(sql);
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            //throw new DaoException(ERROR_FIND_ALL + e.getMessage(), ErrorCode.SERVER_ERROR.getCode());
        } finally {
            em.close();
        }

        if(list.isEmpty()){
            //throw new DaoException(ERROR_FIND_ALL, ErrorCode.NOT_FOUND.getCode());

        }

        return list;

    }

    private boolean userIsValid(User user) {
        try {
            if ((user.getName().isEmpty())) {
                return false;
            }
        } catch (NullPointerException e) {
            throw e;
            //throw new DaoException("User with incomplete data", ErrorCode.BAD_REQUEST.getCode());
        }

        return true;
    }


}
