package com.blogsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentario")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "texto", nullable = false, columnDefinition = "TEXT")
    private String texto;
    
    @Column(name = "autor_nombre", nullable = false, length = 100)
    private String autorNombre;
    
    @Column(name = "fecha")
    private LocalDateTime fecha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post", nullable = false)
    private Post post;
    
    // Constructores
    public Comentario() {}
    
    public Comentario(String texto, String autorNombre, Post post) {
        this.texto = texto;
        this.autorNombre = autorNombre;
        this.post = post;
        this.fecha = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    
    public String getAutorNombre() { return autorNombre; }
    public void setAutorNombre(String autorNombre) { this.autorNombre = autorNombre; }
    
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
    
    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", autorNombre=" + autorNombre + 
               ", fecha=" + fecha + ", post=" + post.getTitulo() + '}';
    }
}