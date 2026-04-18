import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class classTujuan extends JFrame {

    // ===== KOMPONEN =====
    private JLabel     judulLabel;
    private JLabel     nimLabel;
    private JLabel     namaLabel;
    private JTextField nimTextField;
    private JTextField namaTextField;
    private JPanel     mainPanel;

    // ===== CONSTRUCTOR =====
    public classTujuan() {
        initComponents();
    }

    // ===== INIT COMPONENTS =====
    private void initComponents() {

        // === SETTING FRAME ===
        setTitle("Frame Yang Dipanggil");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // === MAIN PANEL ===
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(220, 220, 220));
        mainPanel.setBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 3)
        );

        // === JUDUL LABEL ===
        judulLabel = new JLabel(
            "FRAME YANG DIPANGGIL",
            SwingConstants.CENTER
        );
        judulLabel.setFont(new Font("Arial", Font.BOLD, 16));
        judulLabel.setForeground(Color.BLACK);
        judulLabel.setBorder(
            BorderFactory.createEmptyBorder(30, 10, 20, 10)
        );

        // === FORM PANEL ===
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(220, 220, 220));
        formPanel.setBorder(
            BorderFactory.createEmptyBorder(10, 40, 10, 40)
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(10, 10, 10, 10);
        gbc.anchor  = GridBagConstraints.WEST;
        gbc.fill    = GridBagConstraints.HORIZONTAL;

        // === LABEL NIM ===
        nimLabel = new JLabel("NIM");
        nimLabel.setFont(new Font("Arial", Font.BOLD, 13));

        // === TEXT FIELD NIM ===
        nimTextField = new JTextField(15);
        nimTextField.setFont(new Font("Arial", Font.PLAIN, 13));
        nimTextField.setPreferredSize(new Dimension(150, 28));

        // === LABEL NAMA ===
        namaLabel = new JLabel("Nama Mahasiswa");
        namaLabel.setFont(new Font("Arial", Font.BOLD, 13));

        // === TEXT FIELD NAMA ===
        namaTextField = new JTextField(15);
        namaTextField.setFont(new Font("Arial", Font.PLAIN, 13));
        namaTextField.setPreferredSize(new Dimension(150, 28));

        // === TAMBAHKAN KE FORM PANEL ===
        // Baris NIM
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        formPanel.add(nimLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 0.5;
        formPanel.add(nimTextField, gbc);

        // Baris Nama Mahasiswa
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.5;
        formPanel.add(namaLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 0.5;
        formPanel.add(namaTextField, gbc);

        // === TAMBAHKAN KE MAIN PANEL ===
        mainPanel.add(judulLabel,  BorderLayout.NORTH);
        mainPanel.add(formPanel,   BorderLayout.CENTER);

        // === TAMBAHKAN KE FRAME ===
        getContentPane().add(mainPanel);
    }

    // ===== GETTER NIM =====
    public String getNim() {
        return nimTextField.getText();
    }

    // ===== GETTER NAMA =====
    public String getNama() {
        return namaTextField.getText();
    }

    // ===== SETTER NIM =====
    public void setNim(String nim) {
        nimTextField.setText(nim);
    }

    // ===== SETTER NAMA =====
    public void setNama(String nama) {
        namaTextField.setText(nama);
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new classTujuan().setVisible(true);
            }
        });
    }
}