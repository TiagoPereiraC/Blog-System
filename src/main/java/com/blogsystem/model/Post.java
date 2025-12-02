package com.blogsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;
    
    @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
    private String contenido;
    
    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();
    
    // Constructores
    public Post() {}
    
    public Post(String titulo, String contenido, Autor autor) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = autor;
        this.fechaPublicacion = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    
    public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) { 
        this.fechaPublicacion = fechaPublicacion; 
    }
    
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    
    public List<Comentario> getComentarios() { return comentarios; }
    public void setComentarios(List<Comentario> comentarios) { 
        this.comentarios = comentarios; 
    }
    
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", titulo=" + titulo + 
               ", fechaPublicacion=" + fechaPublicacion + ", autor=" + autor.getNombre() + '}';
    }
}