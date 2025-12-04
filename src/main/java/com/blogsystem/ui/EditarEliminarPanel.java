package com.blogsystem.ui;

import com.blogsystem.dao.PostDAO;
import com.blogsystem.model.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditarEliminarPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JTextField txtBuscar;
    private JPanel postsContainer;
    private PostDAO postDAO;
    
    public EditarEliminarPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.postDAO = new PostDAO();
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
        
        JLabel titleLabel = new JLabel("Editar & Eliminar Posts");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(100, 181, 246));
        
        titlePanel.add(titleLabel);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBackground(new Color(15, 23, 42));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        
        JLabel lblBuscar = new JLabel("Campo búsqueda por autor");
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Arial", Font.PLAIN, 12));
        
        txtBuscar = new JTextField(30);
        txtBuscar.setPreferredSize(new Dimension(400, 40));
        txtBuscar.setBackground(new Color(51, 65, 85));
        txtBuscar.setForeground(Color.WHITE);
        txtBuscar.setCaretColor(Color.WHITE);
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(110, 40));
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnBuscar.setBackground(new Color(100, 181, 246));
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(33, 150, 243), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(e -> buscarPorAutor());
        
        JPanel searchInnerPanel = new JPanel();
        searchInnerPanel.setLayout(new BoxLayout(searchInnerPanel, BoxLayout.Y_AXIS));
        searchInnerPanel.setBackground(new Color(15, 23, 42));
        
        lblBuscar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        inputPanel.setBackground(new Color(15, 23, 42));
        inputPanel.add(txtBuscar);
        inputPanel.add(btnBuscar);
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        searchInnerPanel.add(lblBuscar);
        searchInnerPanel.add(Box.createVerticalStrut(5));
        searchInnerPanel.add(inputPanel);
        
        searchPanel.add(searchInnerPanel);
        
        postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(new Color(15, 23, 42));
        postsContainer.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
        
        JScrollPane scrollPane = new JScrollPane(postsContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Panel superior combinado
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(15, 23, 42));
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);
        
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
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        cargarPosts();
    }
    
    // Carga todos los posts disponibles para editar o eliminar
    public void cargarPosts() {
        postsContainer.removeAll();
        
        try {
            List<Post> posts = postDAO.listarTodos();
            
            if (posts.isEmpty()) {
                JLabel lblVacio = new JLabel("No hay posts disponibles");
                lblVacio.setForeground(Color.WHITE);
                lblVacio.setFont(new Font("Arial", Font.PLAIN, 16));
                lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
                postsContainer.add(Box.createVerticalStrut(50));
                postsContainer.add(lblVacio);
            } else {
                for (Post post : posts) {
                    JPanel postPanel = crearPostEditablePanel(post);
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
    
    // Filtra los posts por nombre de autor
    private void buscarPorAutor() {
        String nombreAutor = txtBuscar.getText().trim();
        
        if (nombreAutor.isEmpty()) {
            cargarPosts();
            return;
        }
        
        postsContainer.removeAll();
        
        try {
            List<Post> posts = postDAO.buscarPorAutor(nombreAutor);
            
            if (posts.isEmpty()) {
                JLabel lblVacio = new JLabel("No se encontraron posts del autor: " + nombreAutor);
                lblVacio.setForeground(Color.WHITE);
                lblVacio.setFont(new Font("Arial", Font.PLAIN, 14));
                lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
                postsContainer.add(Box.createVerticalStrut(50));
                postsContainer.add(lblVacio);
            } else {
                for (Post post : posts) {
                    JPanel postPanel = crearPostEditablePanel(post);
                    postsContainer.add(postPanel);
                    postsContainer.add(Box.createVerticalStrut(15));
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar posts: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        postsContainer.revalidate();
        postsContainer.repaint();
    }
    
    // Crea el panel de un post con botones de editar y eliminar
    private JPanel crearPostEditablePanel(Post post) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(new Color(30, 41, 59));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        panel.setMaximumSize(new Dimension(520, Integer.MAX_VALUE));
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(30, 41, 59));
        
        JLabel lblAutor = new JLabel("@Autor: " + post.getAutor().getNombre());
        lblAutor.setFont(new Font("Arial", Font.BOLD, 13));
        lblAutor.setForeground(new Color(147, 197, 253));
        lblAutor.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblTitulo = new JLabel(post.getTitulo());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        infoPanel.add(lblAutor);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblTitulo);
        
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botonesPanel.setBackground(new Color(30, 41, 59));
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setPreferredSize(new Dimension(110, 38));
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEditar.setBackground(new Color(129, 199, 132));
        btnEditar.setForeground(Color.BLACK);
        btnEditar.setFocusPainted(false);
        btnEditar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(76, 175, 80), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(e -> editarPost(post));
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setPreferredSize(new Dimension(110, 38));
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(239, 154, 154));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(244, 67, 54), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(e -> eliminarPost(post));
        
        botonesPanel.add(btnEditar);
        botonesPanel.add(btnEliminar);
        
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(botonesPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // Muestra diálogo para editar un post existente
    private void editarPost(Post post) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Editar Post", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(new Color(30, 41, 59));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(30, 41, 59));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextField txtTitulo = new JTextField(post.getTitulo());
        txtTitulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtTitulo.setBackground(new Color(51, 65, 85));
        txtTitulo.setForeground(Color.WHITE);
        txtTitulo.setCaretColor(Color.WHITE);
        txtTitulo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblContenido = new JLabel("Contenido:");
        lblContenido.setForeground(Color.WHITE);
        lblContenido.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea txtContenido = new JTextArea(post.getContenido(), 8, 30);
        txtContenido.setBackground(new Color(51, 65, 85));
        txtContenido.setForeground(Color.WHITE);
        txtContenido.setCaretColor(Color.WHITE);
        txtContenido.setLineWrap(true);
        txtContenido.setWrapStyleWord(true);
        txtContenido.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JScrollPane scrollContenido = new JScrollPane(txtContenido);
        scrollContenido.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        formPanel.add(lblTitulo);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtTitulo);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(lblContenido);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(scrollContenido);
        
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
            String nuevoTitulo = txtTitulo.getText().trim();
            String nuevoContenido = txtContenido.getText().trim();
            
            if (nuevoTitulo.isEmpty() || nuevoContenido.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                post.setTitulo(nuevoTitulo);
                post.setContenido(nuevoContenido);
                postDAO.actualizar(post);
                JOptionPane.showMessageDialog(dialog, "Post actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                cargarPosts();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error al actualizar post: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    
    // Elimina un post con confirmación del usuario
    private void eliminarPost(Post post) {
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar el post '" + post.getTitulo() + "'?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                postDAO.eliminar(post.getId());
                JOptionPane.showMessageDialog(this, 
                    "Post eliminado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                cargarPosts();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar post: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
