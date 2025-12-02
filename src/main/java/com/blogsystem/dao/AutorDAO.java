package com.blogsystem.dao;

import com.blogsystem.model.Autor;
import com.blogsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AutorDAO {
    
    public void salvar(Autor autor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(autor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public List<Autor> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Autor", Autor.class).list();
        }
    }
    
    public Autor buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Autor.class, id);
        }
    }
    
    public void atualizar(Autor autor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(autor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void deletar(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Autor autor = session.get(Autor.class, id);
            if (autor != null) {
                session.remove(autor);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}