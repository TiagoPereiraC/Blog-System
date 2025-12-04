package com.blogsystem.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPrincipalPanel menuPrincipalPanel;
    private PublicarPostPanel publicarPostPanel;
    private VerPostsPanel verPostsPanel;
    private EditarEliminarPanel editarEliminarPanel;
    private RegistrarAutorPanel registrarAutorPanel;
    
    public MainFrame() {
        setTitle("App Blog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(true);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        menuPrincipalPanel = new MenuPrincipalPanel(this);
        publicarPostPanel = new PublicarPostPanel(this);
        verPostsPanel = new VerPostsPanel(this);
        editarEliminarPanel = new EditarEliminarPanel(this);
        registrarAutorPanel = new RegistrarAutorPanel(this);
        
        mainPanel.add(menuPrincipalPanel, "MENU");
        mainPanel.add(publicarPostPanel, "PUBLICAR");
        mainPanel.add(verPostsPanel, "VER_POSTS");
        mainPanel.add(editarEliminarPanel, "EDITAR_ELIMINAR");
        mainPanel.add(registrarAutorPanel, "REGISTRAR_AUTOR");
        
        add(mainPanel);
        mostrarPanel("MENU");
    }
    
    // Cambia entre paneles y actualiza datos cuando es necesario
    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(mainPanel, nombrePanel);
        
        if (nombrePanel.equals("VER_POSTS")) {
            verPostsPanel.cargarPosts();
        } else if (nombrePanel.equals("EDITAR_ELIMINAR")) {
            editarEliminarPanel.cargarPosts();
        } else if (nombrePanel.equals("PUBLICAR")) {
            publicarPostPanel.cargarAutores();
        }
    }
}
