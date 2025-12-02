package com.blogsystem.dao;

import com.blogsystem.model.Comentario;
import com.blogsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ComentarioDAO {
    
    public void salvar(Comentario comentario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(comentario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public List<Comentario> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Comentario", Comentario.class).list();
        }
    }
    
    public List<Comentario> buscarPorPost(Long postId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Comentario> query = session.createQuery(
                "SELECT c FROM Comentario c WHERE c.post.id = :postId", Comentario.class);
            query.setParameter("postId", postId);
            return query.list();
        }
    }
    
    public void deletar(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Comentario comentario = session.get(Comentario.class, id);
            if (comentario != null) {
                session.remove(comentario);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}