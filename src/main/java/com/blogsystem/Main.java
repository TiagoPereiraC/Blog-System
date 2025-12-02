package com.blogsystem;

import com.blogsystem.ui.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    
    public static void main(String[] args) {
        // Configurar el Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Iniciar la interfaz gráfica en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}