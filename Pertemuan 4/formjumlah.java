import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormJumlah extends JFrame {

    // ===== KOMPONEN =====
    private JLabel          judulLabel;
    private JLabel          nimLabel;
    private JLabel          namaLabel;
    private JLabel          jurusanLabel;
    private JTextField      nimTextField;
    private JTextField      namaTextField;
    private JTextField      jurusanTextField;
    private JButton         simpanButton;
    private JButton         hapusButton;
    private JButton         bersihButton;
    private JTable          dataTable;
    private JScrollPane     scrollPane;
    private DefaultTableModel tableModel;

    // ===== KOLOM TABEL =====
    private final String[] KOLOM = {
        "No", "NIM", "Nama Mahasiswa", "Jurusan"
    };

    // ===== NOMOR URUT =====
    private int nomorUrut = 1;

    // ===== CONSTRUCTOR =====
    public FormJumlah() {
        initComponents();
        initTable();
        setupEvents();
    }

    // ===== INIT COMPONENTS =====
    private void initComponents() {

        // === SETTING FRAME ===
        setTitle("Penggunaan JTable - Data Mahasiswa");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // === JUDUL LABEL ===
        judulLabel = new JLabel(
            "DATA MAHASISWA - PENGGUNAAN JTABLE",
            SwingConstants.CENTER
        );
        judulLabel.setFont(new Font("Arial", Font.BOLD, 16));
        judulLabel.setForeground(Color.WHITE);
        judulLabel.setOpaque(true);
        judulLabel.setBackground(new Color(70, 130, 180));
        judulLabel.setBorder(
            BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );

        // === LABEL ===
        nimLabel     = buatLabel("NIM :");
        namaLabel    = buatLabel("Nama Mahasiswa :");
        jurusanLabel = buatLabel("Jurusan :");

        // === TEXT FIELD ===
        nimTextField     = new JTextField(20);
        namaTextField    = new JTextField(20);
        jurusanTextField = new JTextField(20);

        // Set Font TextField
        Font fontField = new Font("Arial", Font.PLAIN, 13);
        nimTextField.setFont(fontField);
        namaTextField.setFont(fontField);
        jurusanTextField.setFont(fontField);

        // === BUTTON ===
        simpanButton = buatButton("Simpan",  new Color(34, 139, 34));
        hapusButton  = buatButton("Hapus",   new Color(200, 0, 0));
        bersihButton = buatButton("Bersih",  new Color(255, 140, 0));

        // === TABLE ===
        dataTable  = new JTable();
        scrollPane = new JScrollPane(dataTable);
        scrollPane.setPreferredSize(new Dimension(650, 250));

        // ============================================
        // === PANEL FORM ===
        // ============================================
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            " Input Data Mahasiswa ",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 13),
            new Color(70, 130, 180)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(8, 10, 8, 10);
        gbc.anchor  = GridBagConstraints.WEST;
        gbc.fill    = GridBagConstraints.HORIZONTAL;

        // Baris NIM
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        formPanel.add(nimLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        formPanel.add(nimTextField, gbc);

        // Baris Nama
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        formPanel.add(namaLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        formPanel.add(namaTextField, gbc);

        // Baris Jurusan
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        formPanel.add(jurusanLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        formPanel.add(jurusanTextField, gbc);

        // ============================================
        // === PANEL BUTTON ===
        // ============================================
        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 15, 10)
        );
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(simpanButton);
        buttonPanel.add(hapusButton);
        buttonPanel.add(bersihButton);

        // ============================================
        // === PANEL TABEL ===
        // ============================================
        JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
        tablePanel.setBackground(new Color(245, 245, 245));
        tablePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            " Daftar Data Mahasiswa ",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 13),
            new Color(70, 130, 180)
        ));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // ============================================
        // === MAIN PANEL ===
        // ============================================
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(judulLabel,   BorderLayout.NORTH);
        mainPanel.add(formPanel,    BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(new Color(245, 245, 245));
        southPanel.add(buttonPanel, BorderLayout.NORTH);
        southPanel.add(tablePanel,  BorderLayout.CENTER);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    // ===== INIT TABLE =====
    private void initTable() {
        tableModel = new DefaultTableModel(KOLOM, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        dataTable.setModel(tableModel);
        dataTable.setRowHeight(25);
        dataTable.setFont(new Font("Arial", Font.PLAIN, 12));
        dataTable.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 12)
        );
        dataTable.getTableHeader().setBackground(
            new Color(70, 130, 180)
        );
        dataTable.getTableHeader().setForeground(Color.WHITE);
        dataTable.setSelectionBackground(new Color(173, 216, 230));
        dataTable.setGridColor(new Color(200, 200, 200));
        dataTable.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );
        dataTable.getTableHeader().setReorderingAllowed(false);

        // Set Lebar Kolom
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        dataTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        dataTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        dataTable.getColumnModel().getColumn(3).setPreferredWidth(150);
    }

    // ===== SETUP EVENTS =====
    private void setupEvents() {

        // === EVENT SIMPAN BUTTON ===
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                simpanData();
            }
        });

        // === EVENT HAPUS BUTTON ===
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                hapusData();
            }
        });

        // === EVENT BERSIH BUTTON ===
        bersihButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                bersihForm();
            }
        });

        // === EVENT KLIK TABEL ===
        dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                klikTabel();
            }
        });
    }

    // ===== METHOD SIMPAN DATA =====
    private void simpanData() {
        String nim     = nimTextField.getText().trim();
        String nama    = namaTextField.getText().trim();
        String jurusan = jurusanTextField.getText().trim();

        // Validasi Input
        if (nim.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "NIM tidak boleh kosong!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            nimTextField.requestFocus();
            return;
        }
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nama tidak boleh kosong!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            namaTextField.requestFocus();
            return;
        }
        if (jurusan.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Jurusan tidak boleh kosong!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            jurusanTextField.requestFocus();
            return;
        }

        // Cek Duplikat NIM
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).toString().equals(nim)) {
                JOptionPane.showMessageDialog(this,
                    "NIM sudah terdaftar!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Tambah Data ke Tabel
        Object[] row = {nomorUrut++, nim, nama, jurusan};
        tableModel.addRow(row);

        JOptionPane.showMessageDialog(this,
            "Data berhasil disimpan!",
            "Sukses",
            JOptionPane.INFORMATION_MESSAGE);

        bersihForm();
    }

    // ===== METHOD HAPUS DATA =====
    private void hapusData() {
        int row = dataTable.getSelectedRow();
        if (row >= 0) {
            String nim = tableModel.getValueAt(row, 1).toString();
            int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Hapus data NIM: " + nim + "?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                tableModel.removeRow(row);
                // Update Nomor Urut
                updateNomor();
                bersihForm();
                JOptionPane.showMessageDialog(this,
                    "Data berhasil dihapus!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Pilih data yang ingin dihapus!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    // ===== METHOD UPDATE NOMOR URUT =====
    private void updateNomor() {
        nomorUrut = 1;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt(nomorUrut++, i, 0);
        }
    }

    // ===== METHOD KLIK TABEL =====
    private void klikTabel() {
        int row = dataTable.getSelectedRow();
        if (row >= 0) {
            nimTextField.setText(
                tableModel.getValueAt(row, 1).toString()
            );
            namaTextField.setText(
                tableModel.getValueAt(row, 2).toString()
            );
            jurusanTextField.setText(
                tableModel.getValueAt(row, 3).toString()
            );
        }
    }

    // ===== METHOD BERSIH FORM =====
    private void bersihForm() {
        nimTextField.setText("");
        namaTextField.setText("");
        jurusanTextField.setText("");
        nimTextField.requestFocus();
        dataTable.clearSelection();
    }

    // ===== HELPER - BUAT LABEL =====
    private JLabel buatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }

    // ===== HELPER - BUAT BUTTON =====
    private JButton buatButton(String text, Color warna) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(warna);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 33));
        return button;
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
                new FormJumlah().setVisible(true);
            }
        });
    }
}