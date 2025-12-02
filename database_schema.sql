CREATE DATABASE IF NOT EXISTS blog_db;
USE blog_db;

-- Insertar autores de ejemplo
INSERT INTO autor (nombre, email) VALUES
('Juan Pérez', 'juan.perez@example.com'),
('María González', 'maria.gonzalez@example.com'),
('Carlos Rodríguez', 'carlos.rodriguez@example.com');

-- Insertar posts de ejemplo
INSERT INTO post (titulo, contenido, fecha_publicacion, id_autor) VALUES
('Bienvenido al Blog', 'Este es el primer post de nuestro blog. Aquí compartiremos contenido interesante sobre tecnología y programación.', NOW(), 1),
('Introducción a Java', 'Java es un lenguaje de programación orientado a objetos muy popular en el desarrollo empresarial...', NOW(), 2),
('Hibernate y JPA', 'Hibernate es un framework ORM que facilita el mapeo objeto-relacional en aplicaciones Java...', NOW(), 1);

-- Insertar comentarios de ejemplo
INSERT INTO comentario (texto, autor_nombre, fecha, id_post) VALUES
('¡Excelente post! Me ha ayudado mucho.', 'Ana López', NOW(), 1),
('Muy buena explicación, gracias por compartir.', 'Pedro Martínez', NOW(), 1),
('¿Podrías profundizar más en este tema?', 'Laura Sánchez', NOW(), 2),
('Interesante artículo sobre Hibernate.', 'Roberto Torres', NOW(), 3);