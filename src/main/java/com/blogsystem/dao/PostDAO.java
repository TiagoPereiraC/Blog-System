package com.blogsystem.dao;

import com.blogsystem.model.Post;
import com.blogsystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class PostDAO {
    
    public void salvar(Post post) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(post);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public List<Post> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Post> query = session.createQuery(
                "SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.autor LEFT JOIN FETCH p.comentarios", 
                Post.class);
            return query.list();
        }
    }
    
    public Post buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Post> query = session.createQuery(
                "SELECT p FROM Post p LEFT JOIN FETCH p.autor LEFT JOIN FETCH p.comentarios WHERE p.id = :id", 
                Post.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }
    
    public void atualizar(Post post) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(post);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void actualizar(Post post) {
        atualizar(post);
    }
    
    public void deletar(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Post post = session.get(Post.class, id);
            if (post != null) {
                session.remove(post);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void eliminar(Long id) {
        deletar(id);
    }
    
    public List<Post> buscarPorAutor(String nomeAutor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Post> query = session.createQuery(
                "SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.autor LEFT JOIN FETCH p.comentarios WHERE p.autor.nombre LIKE :nombre", 
                Post.class);
            query.setParameter("nombre", "%" + nomeAutor + "%");
            return query.list();
        }
    }
}