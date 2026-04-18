import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class latpanggil extends JFrame {

    // ===== KOMPONEN =====
    private JLabel  judulLabel;
    private JButton panggilButton;
    private JPanel  mainPanel;

    // ===== CONSTRUCTOR =====
    public latpanggil() {
        initComponents();
        setupEvents();
    }

    // ===== INIT COMPONENTS =====
    private void initComponents() {

        // === SETTING FRAME ===
        setTitle("Memanggil Frame");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // === MAIN PANEL ===
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // === BORDER PANEL ===
        JPanel borderPanel = new JPanel(new BorderLayout(10, 10));
        borderPanel.setBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 3)
        );
        borderPanel.setBackground(Color.WHITE);

        // === JUDUL LABEL ===
        judulLabel = new JLabel(
            "MEMANGGIL - MENAMPILKAN FRAME LAIN",
            SwingConstants.CENTER
        );
        judulLabel.setFont(new Font("Arial", Font.BOLD, 16));
        judulLabel.setForeground(Color.BLACK);
        judulLabel.setBorder(
            BorderFactory.createEmptyBorder(30, 10, 10, 10)
        );

        // === PANGGIL BUTTON ===
        panggilButton = new JButton("PANGGIL FRAME");
        panggilButton.setFont(new Font("Arial", Font.BOLD, 13));
        panggilButton.setPreferredSize(new Dimension(200, 35));
        panggilButton.setBackground(new Color(230, 230, 230));
        panggilButton.setFocusPainted(false);
        panggilButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // === PANEL BUTTON ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(panggilButton);

        // === TAMBAHKAN KE BORDER PANEL ===
        borderPanel.add(judulLabel,   BorderLayout.NORTH);
        borderPanel.add(buttonPanel,  BorderLayout.CENTER);

        // === TAMBAHKAN KE MAIN PANEL ===
        mainPanel.add(borderPanel, BorderLayout.CENTER);

        // === TAMBAHKAN KE FRAME ===
        getContentPane().add(mainPanel);
    }

    // ===== SETUP EVENTS =====
    private void setupEvents() {

        // === EVENT PANGGIL BUTTON ===
        panggilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
    }

    // ===== EVENT BUTTON ACTION =====
    private void jButton1ActionPerformed(ActionEvent evt) {
        // Membuat objek classTujuan dan menampilkannya
        classTujuan tampil = new classTujuan();
        tampil.setVisible(true);
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new latpanggil().setVisible(true);
            }
        });
    }
}