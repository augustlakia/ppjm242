package web.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserServImp implements UserServ, UserDetailsService {

    private final EntityManager em;

    public UserServImp(EntityManagerFactory em) {
        this.em = em.createEntityManager();
    }


    public void add(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsersList() {
        return em.createQuery("select us from User us").getResultList();
    }

    public User findById(int id) {
        return (User) em.createQuery("select u from User u where u.id =:id")
                .setParameter("id", Long.parseLong(String.valueOf(id)))
                .getSingleResult();
    }

    public void Update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void Delete(int id ) {
        em.getTransaction().begin();
        em.createQuery("DELETE User us where us.id =:id").setParameter("id", Long.parseLong(String.valueOf(id))).executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return em.createQuery("select us from User us where us.name=:name", User.class).setParameter("name", s).getSingleResult();
    }
}
