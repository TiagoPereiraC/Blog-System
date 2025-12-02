package com.blogsystem.ui;

import com.blogsystem.dao.AutorDAO;
import com.blogsystem.model.Autor;

import javax.swing.*;
import java.awt.*;

public class RegistrarAutorPanel extends JPanel {
    
    private MainFrame mainFrame;
    private JTextField txtNombre;
    private JTextField txtEmail;
    private AutorDAO autorDAO;
    
    public RegistrarAutorPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.autorDAO = new AutorDAO();
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
        
        JLabel titleLabel = new JLabel("Registrar Nuevo Autor");
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
        
        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(350, 40));
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNombre.setBackground(new Color(51, 65, 85));
        txtNombre.setForeground(Color.WHITE);
        txtNombre.setCaretColor(Color.WHITE);
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formGbc.gridy = 0;
        
        JLabel lblNombre = new JLabel("Nombre completo");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JPanel nombrePanel = crearCampoConEtiqueta(lblNombre, txtNombre);
        formPanel.add(nombrePanel, formGbc);
        
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(350, 40));
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEmail.setBackground(new Color(51, 65, 85));
        txtEmail.setForeground(Color.WHITE);
        txtEmail.setCaretColor(Color.WHITE);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formGbc.gridy = 1;
        
        JLabel lblEmail = new JLabel("Correo electrónico");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JPanel emailPanel = crearCampoConEtiqueta(lblEmail, txtEmail);
        formPanel.add(emailPanel, formGbc);
        
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        botonesPanel.setBackground(new Color(30, 41, 59));
        formGbc.gridy = 2;
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setPreferredSize(new Dimension(120, 40));
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(129, 199, 132));
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(76, 175, 80), 2, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(e -> registrarAutor());
        
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
        
        botonesPanel.add(btnRegistrar);
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
    
    private void registrarAutor() {
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        
        if (nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete todos los campos", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese un correo electrónico válido", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Autor nuevoAutor = new Autor(nombre, email);
            autorDAO.salvar(nuevoAutor);
            
            JOptionPane.showMessageDialog(this, 
                "Autor registrado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();
            mainFrame.mostrarPanel("MENU");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al registrar el autor: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtEmail.setText("");
    }
}
