package br.com.fernanda.usermanagement.ejb.dao.impl;

import br.com.fernanda.usermanagement.ejb.exception.UserManagementException;
import br.com.fernanda.usermanagement.ejb.constants.ErrorMessage;
import br.com.fernanda.usermanagement.ejb.constants.LogMessage;
import br.com.fernanda.usermanagement.ejb.dao.UserDAO;
import br.com.fernanda.usermanagement.ejb.entity.User;
import br.com.fernanda.usermanagement.ejb.util.JpaUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ejb.Stateless;
import java.util.List;



@Stateless
public class UserDAOImpl implements UserDAO {

    private EntityManager em;
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    @Override
    public void save(User user)  {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        if(user.getId()==null){
            em.persist(user);
        }

        else{
            if(findById(user.getId())!=null)
                em.merge(user);
        }

        LOGGER.info(LogMessage.SAVE_USER,user.getId());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Long id)  {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        User user = findById(id);
        em.remove(user);
        LOGGER.info(LogMessage.DELETE_USER,user.getId());
        em.close();
    }

    @Override
    public User findById(Long id)  {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        return em.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) throws UserManagementException {
        em = JpaUtil.getEntityManagerFactory().createEntityManager();
        List<User> list;
        User user;

        try {

            em.getTransaction().begin();
            String sql = "SELECT user from User user where user.email = :email";
            Query query = em.createQuery(sql);
            query.setParameter("email", email);
            list = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.error(ErrorMessage.ERROR_FIND_BY_EMAIL);
            throw new UserManagementException(ErrorMessage.ERROR_FIND_BY_EMAIL);
        } finally {
            em.close();
        }
        if (!list.isEmpty()) {
            user = list.get(0);
        }else {
            return null;
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
            LOGGER.error(ErrorMessage.ERROR_FIND_ALL);
            throw new UserManagementException(ErrorMessage.ERROR_FIND_ALL);
        } finally {
            em.close();
        }

        return userList;

    }


}
