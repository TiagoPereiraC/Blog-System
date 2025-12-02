package com.blogsystem.ui;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalPanel extends JPanel {
    
    private MainFrame mainFrame;
    
    public MenuPrincipalPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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
        
        JLabel titleLabel = new JLabel("App Blog");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(100, 181, 246));
        
        titlePanel.add(titleLabel);
        
        JLabel subtitleLabel = new JLabel("Sistema de Gestión de Publicaciones");
        subtitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        subtitleLabel.setForeground(new Color(176, 190, 197));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(15, 23, 42));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 25, 20, 25);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton btnPublicar = crearBoton("Publicar post");
        btnPublicar.addActionListener(e -> mainFrame.mostrarPanel("PUBLICAR"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(btnPublicar, gbc);
        
        JButton btnVer = crearBoton("Ver posts");
        btnVer.addActionListener(e -> mainFrame.mostrarPanel("VER_POSTS"));
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(btnVer, gbc);
        
        JButton btnEditar = crearBoton("Editar & Eliminar post");
        btnEditar.addActionListener(e -> mainFrame.mostrarPanel("EDITAR_ELIMINAR"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        centerPanel.add(btnEditar, gbc);
        
        JButton btnRegistrarAutor = crearBoton("Registrar Autor");
        btnRegistrarAutor.addActionListener(e -> mainFrame.mostrarPanel("REGISTRAR_AUTOR"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(btnRegistrarAutor, gbc);
        
        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private JButton crearBoton(String texto) {
        JButton button = new JButton(texto);
        button.setPreferredSize(new Dimension(220, 60));
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setBackground(new Color(100, 181, 246));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(33, 150, 243), 2, true),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(33, 150, 243));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(25, 118, 210), 3, true),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 181, 246));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(33, 150, 243), 2, true),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
        });
        
        return button;
    }
}
