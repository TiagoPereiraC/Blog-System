package com.blogsystem.ui;

import com.blogsystem.dao.AutorDAO;
import com.blogsystem.dao.PostDAO;
import com.blogsystem.model.Autor;
import com.blogsystem.model.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PublicarPostPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JComboBox<String> comboAutor;
    private JTextField txtTitulo;
    private JTextArea txtContenido;
    private AutorDAO autorDAO;
    private PostDAO postDAO;
    private List<Autor> autores;
    
    public PublicarPostPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.autorDAO = new AutorDAO();
        this.postDAO = new PostDAO();
        initComponents();
        cargarAutores();
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
        
        JLabel titleLabel = new JLabel("Publicar Nuevo Post");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(100, 181, 246));
        
        titlePanel.add(titleLabel);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(15, 23, 42));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Panel del formulario con borde redondeado
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(30, 41, 59));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2, true),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        formGbc.insets = new Insets(10, 10, 10, 10);
        formGbc.gridx = 0;
        
        comboAutor = new JComboBox<>();
        comboAutor.setPreferredSize(new Dimension(350, 40));
        comboAutor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboAutor.setBackground(Color.WHITE);
        comboAutor.setForeground(Color.BLACK);
        comboAutor.setBorder(BorderFactory.createLineBorder(new Color(100, 181, 246), 2, true));
        formGbc.gridy = 0;
        
        JLabel lblAutor = new JLabel("Campo autor");
        lblAutor.setForeground(Color.WHITE);
        lblAutor.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JPanel autorPanel = crearCampoConEtiqueta(lblAutor, comboAutor);
        formPanel.add(autorPanel, formGbc);
        
        txtTitulo = new JTextField();
        txtTitulo.setPreferredSize(new Dimension(350, 40));
        txtTitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTitulo.setBackground(new Color(51, 65, 85));
        txtTitulo.setForeground(Color.WHITE);
        txtTitulo.setCaretColor(Color.WHITE);
        txtTitulo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formGbc.gridy = 1;
        
        JLabel lblTitulo = new JLabel("Campo titulo");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JPanel tituloPanel = crearCampoConEtiqueta(lblTitulo, txtTitulo);
        formPanel.add(tituloPanel, formGbc);
        
        txtContenido = new JTextArea(6, 30);
        txtContenido.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContenido.setBackground(new Color(51, 65, 85));
        txtContenido.setForeground(Color.WHITE);
        txtContenido.setCaretColor(Color.WHITE);
        txtContenido.setLineWrap(true);
        txtContenido.setWrapStyleWord(true);
        txtContenido.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JScrollPane scrollContenido = new JScrollPane(txtContenido);
        scrollContenido.setPreferredSize(new Dimension(350, 120));
        scrollContenido.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        formGbc.gridy = 2;
        
        JLabel lblContenido = new JLabel("Campo contenido");
        lblContenido.setForeground(Color.WHITE);
        lblContenido.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JPanel contenidoPanel = crearCampoConEtiqueta(lblContenido, scrollContenido);
        formPanel.add(contenidoPanel, formGbc);
        
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        botonesPanel.setBackground(new Color(30, 41, 59));
        formGbc.gridy = 3;
        
        JButton btnPublicar = new JButton("Publicar");
        btnPublicar.setPreferredSize(new Dimension(120, 40));
        btnPublicar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnPublicar.setBackground(new Color(129, 199, 132));
        btnPublicar.setForeground(Color.BLACK);
        btnPublicar.setFocusPainted(false);
        btnPublicar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(76, 175, 80), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btnPublicar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPublicar.addActionListener(e -> publicarPost());
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(120, 40));
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setBackground(new Color(239, 154, 154));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(244, 67, 54), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> {
            limpiarCampos();
            mainFrame.mostrarPanel("MENU");
        });
        
        botonesPanel.add(btnPublicar);
        botonesPanel.add(btnCancelar);
        formPanel.add(botonesPanel, formGbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(formPanel, gbc);
        
        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private JPanel crearCampoConEtiqueta(JLabel etiqueta, JComponent campo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 41, 59));
        etiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(etiqueta);
        panel.add(Box.createVerticalStrut(5));
        panel.add(campo);
        return panel;
    }
    
    // Carga todos los autores disponibles en el ComboBox
    public void cargarAutores() {
        try {
            autores = autorDAO.listarTodos();
            comboAutor.removeAllItems();
            
            if (autores.isEmpty()) {
                Autor autorDefault = new Autor("Usuario Default", "default@email.com");
                autorDAO.salvar(autorDefault);
                autores.add(autorDefault);
            }
            
            for (Autor autor : autores) {
                comboAutor.addItem(autor.getNombre() + " - " + autor.getEmail());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar autores: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Valida y guarda un nuevo post en la base de datos
    private void publicarPost() {
        String titulo = txtTitulo.getText().trim();
        String contenido = txtContenido.getText().trim();
        
        if (titulo.isEmpty() || contenido.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete todos los campos", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (comboAutor.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un autor", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Autor autorSeleccionado = autores.get(comboAutor.getSelectedIndex());
            Post nuevoPost = new Post(titulo, contenido, autorSeleccionado);
            postDAO.salvar(nuevoPost);
            
            JOptionPane.showMessageDialog(this, 
                "Post publicado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();
            mainFrame.mostrarPanel("MENU");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al publicar el post: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtTitulo.setText("");
        txtContenido.setText("");
        if (comboAutor.getItemCount() > 0) {
            comboAutor.setSelectedIndex(0);
        }
    }
}
