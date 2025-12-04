package com.blogsystem.ui;

import com.blogsystem.dao.PostDAO;
import com.blogsystem.dao.ComentarioDAO;
import com.blogsystem.model.Post;
import com.blogsystem.model.Comentario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VerPostsPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JPanel postsContainer;
    private PostDAO postDAO;
    private ComentarioDAO comentarioDAO;
    
    public VerPostsPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.postDAO = new PostDAO();
        this.comentarioDAO = new ComentarioDAO();
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(15, 23, 42));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(25, 35, 55));
        headerPanel.setPreferredSize(new Dimension(1280, 100));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(100, 181, 246)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(25, 35, 55));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JLabel titleLabel = new JLabel("Ver Publicaciones");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(100, 181, 246));
        
        titlePanel.add(titleLabel);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        
        postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(new Color(15, 23, 42));
        postsContainer.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JScrollPane scrollPane = new JScrollPane(postsContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(15, 23, 42));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setPreferredSize(new Dimension(170, 45));
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBackground(new Color(100, 181, 246));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(33, 150, 243), 2, true),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> mainFrame.mostrarPanel("MENU"));
        
        bottomPanel.add(btnVolver);
        
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        cargarPosts();
    }
    
    // Carga todos los posts y comentarios desde la base de datos
    public void cargarPosts() {
        postsContainer.removeAll();
        
        try {
            List<Post> posts = postDAO.listarTodos();
            
            if (posts.isEmpty()) {
                JLabel lblVacio = new JLabel("No hay posts publicados");
                lblVacio.setForeground(Color.WHITE);
                lblVacio.setFont(new Font("Arial", Font.PLAIN, 16));
                lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
                postsContainer.add(Box.createVerticalStrut(50));
                postsContainer.add(lblVacio);
            } else {
                for (Post post : posts) {
                    JPanel postPanel = crearPostPanel(post);
                    postsContainer.add(postPanel);
                    postsContainer.add(Box.createVerticalStrut(15));
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar posts: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        postsContainer.revalidate();
        postsContainer.repaint();
    }
    
    // Crea el panel visual de un post con sus comentarios
    private JPanel crearPostPanel(Post post) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(new Color(30, 41, 59));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        panel.setMaximumSize(new Dimension(520, Integer.MAX_VALUE));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(30, 41, 59));
        
        JLabel lblTitulo = new JLabel(post.getTitulo() + " | @" + post.getAutor().getNombre());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);
        topPanel.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(30, 41, 59));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JTextArea txtContenido = new JTextArea(post.getContenido());
        txtContenido.setFont(new Font("Arial", Font.PLAIN, 12));
        txtContenido.setForeground(Color.LIGHT_GRAY);
        txtContenido.setBackground(new Color(30, 41, 59));
        txtContenido.setLineWrap(true);
        txtContenido.setWrapStyleWord(true);
        txtContenido.setEditable(false);
        txtContenido.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        contentPanel.add(txtContenido);
        
        JPanel comentariosPanel = new JPanel();
        comentariosPanel.setLayout(new BoxLayout(comentariosPanel, BoxLayout.Y_AXIS));
        comentariosPanel.setBackground(new Color(30, 41, 59));
        comentariosPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY),
            BorderFactory.createEmptyBorder(10, 0, 0, 0)
        ));
        
        JLabel lblComentarios = new JLabel("Comentarios:");
        lblComentarios.setFont(new Font("Arial", Font.BOLD, 12));
        lblComentarios.setForeground(Color.YELLOW);
        lblComentarios.setAlignmentX(Component.LEFT_ALIGNMENT);
        comentariosPanel.add(lblComentarios);
        comentariosPanel.add(Box.createVerticalStrut(5));
        
        try {
            List<Comentario> comentarios = comentarioDAO.buscarPorPost(post.getId());
            
            if (comentarios.isEmpty()) {
                JLabel lblSinComentarios = new JLabel("No hay comentarios aún");
                lblSinComentarios.setFont(new Font("Arial", Font.ITALIC, 11));
                lblSinComentarios.setForeground(Color.GRAY);
                lblSinComentarios.setAlignmentX(Component.LEFT_ALIGNMENT);
                comentariosPanel.add(lblSinComentarios);
            } else {
                for (Comentario comentario : comentarios) {
                    JPanel comentarioPanel = crearComentarioPanel(comentario);
                    comentariosPanel.add(comentarioPanel);
                    comentariosPanel.add(Box.createVerticalStrut(5));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JButton btnAgregarComentario = new JButton("+ Agregar Comentario");
        btnAgregarComentario.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAgregarComentario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAgregarComentario.setBackground(new Color(100, 181, 246));
        btnAgregarComentario.setForeground(Color.BLACK);
        btnAgregarComentario.setFocusPainted(false);
        btnAgregarComentario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregarComentario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(33, 150, 243), 2, true),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        btnAgregarComentario.addActionListener(e -> mostrarDialogoComentario(post));
        
        comentariosPanel.add(Box.createVerticalStrut(8));
        comentariosPanel.add(btnAgregarComentario);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(comentariosPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearComentarioPanel(Comentario comentario) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(45, 55, 72));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblAutor = new JLabel("Por: " + comentario.getAutorNombre());
        lblAutor.setFont(new Font("Arial", Font.BOLD, 11));
        lblAutor.setForeground(new Color(147, 197, 253));
        lblAutor.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea txtComentario = new JTextArea(comentario.getTexto());
        txtComentario.setFont(new Font("Arial", Font.PLAIN, 11));
        txtComentario.setForeground(Color.WHITE);
        txtComentario.setBackground(new Color(45, 55, 72));
        txtComentario.setLineWrap(true);
        txtComentario.setWrapStyleWord(true);
        txtComentario.setEditable(false);
        txtComentario.setBorder(null);
        txtComentario.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(lblAutor);
        panel.add(Box.createVerticalStrut(3));
        panel.add(txtComentario);
        
        return panel;
    }
    
    // Muestra diálogo para agregar un nuevo comentario
    private void mostrarDialogoComentario(Post post) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Agregar Comentario", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(new Color(30, 41, 59));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(30, 41, 59));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblNombre = new JLabel("Tu nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextField txtNombre = new JTextField();
        txtNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtNombre.setBackground(new Color(51, 65, 85));
        txtNombre.setForeground(Color.WHITE);
        txtNombre.setCaretColor(Color.WHITE);
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblComentario = new JLabel("Comentario:");
        lblComentario.setForeground(Color.WHITE);
        lblComentario.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea txtComentario = new JTextArea(5, 20);
        txtComentario.setBackground(new Color(51, 65, 85));
        txtComentario.setForeground(Color.WHITE);
        txtComentario.setCaretColor(Color.WHITE);
        txtComentario.setLineWrap(true);
        txtComentario.setWrapStyleWord(true);
        txtComentario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JScrollPane scrollComentario = new JScrollPane(txtComentario);
        scrollComentario.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        formPanel.add(lblNombre);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtNombre);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(lblComentario);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(scrollComentario);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(new Color(30, 41, 59));
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setBackground(new Color(129, 199, 132));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(76, 175, 80), 2, true),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String texto = txtComentario.getText().trim();
            
            if (nombre.isEmpty() || texto.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                Comentario nuevoComentario = new Comentario(texto, nombre, post);
                comentarioDAO.salvar(nuevoComentario);
                JOptionPane.showMessageDialog(dialog, "Comentario agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                cargarPosts();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error al guardar comentario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCancelar.setBackground(new Color(239, 154, 154));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(244, 67, 54), 2, true),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        btnPanel.add(btnGuardar);
        btnPanel.add(btnCancelar);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(btnPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
