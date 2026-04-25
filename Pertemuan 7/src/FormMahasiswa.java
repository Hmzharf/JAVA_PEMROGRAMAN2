import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormMahasiswa extends JFrame {

    // =============================================
    // Komponen GUI
    // =============================================
    private JTextField  txtNim, txtNama, txtJurusan, txtIpk, txtCari;
    private JButton     btnTambah, btnUpdate, btnHapus, btnBersih;
    private JButton     btnCari, btnCetakLaporan;
    private JTable      tabelMahasiswa;
    private DefaultTableModel tableModel;
    private JLabel      lblStatus;

    // =============================================
    // DAO & Laporan
    // =============================================
    private MahasiswaDAO  dao     = new MahasiswaDAO();
    private LaporanCetak  laporan = new LaporanCetak();
    private int           selectedId = -1;

    // =============================================
    // Constructor - Setup Form
    // =============================================
    public FormMahasiswa() {
        initComponents();
        loadData();
    }

    // =============================================
    // Inisialisasi semua komponen GUI
    // =============================================
    private void initComponents() {
        // Setting JFrame
        setTitle("Sistem Informasi Mahasiswa - Pemrograman 2");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ==========================================
        // PANEL ATAS - Judul
        // ==========================================
        JPanel panelJudul = new JPanel();
        panelJudul.setBackground(new Color(0, 51, 102));
        panelJudul.setPreferredSize(new Dimension(900, 60));

        JLabel lblJudul = new JLabel("SISTEM INFORMASI MAHASISWA");
        lblJudul.setFont(new Font("Arial", Font.BOLD, 20));
        lblJudul.setForeground(Color.WHITE);
        panelJudul.add(lblJudul);

        add(panelJudul, BorderLayout.NORTH);

        // ==========================================
        // PANEL KIRI - Form Input
        // ==========================================
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 51, 102), 2),
            "Form Input Data",
            0, 0,
            new Font("Arial", Font.BOLD, 12),
            new Color(0, 51, 102)
        ));
        panelForm.setPreferredSize(new Dimension(280, 400));
        panelForm.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(8, 10, 8, 10);
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.anchor  = GridBagConstraints.WEST;

        // Label & Field NIM
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("NIM :"), gbc);
        gbc.gridx = 1;
        txtNim = new JTextField(15);
        panelForm.add(txtNim, gbc);

        // Label & Field Nama
        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Nama :"), gbc);
        gbc.gridx = 1;
        txtNama = new JTextField(15);
        panelForm.add(txtNama, gbc);

        // Label & Field Jurusan
        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(new JLabel("Jurusan :"), gbc);
        gbc.gridx = 1;
        txtJurusan = new JTextField(15);
        panelForm.add(txtJurusan, gbc);

        // Label & Field IPK
        gbc.gridx = 0; gbc.gridy = 3;
        panelForm.add(new JLabel("IPK :"), gbc);
        gbc.gridx = 1;
        txtIpk = new JTextField(15);
        panelForm.add(txtIpk, gbc);

        // ==========================================
        // Panel Tombol CRUD
        // ==========================================
        JPanel panelTombol = new JPanel(new GridLayout(2, 2, 8, 8));
        panelTombol.setBackground(Color.WHITE);
        panelTombol.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnTambah = buatTombol("TAMBAH",  new Color(0, 153, 76));
        btnUpdate = buatTombol("UPDATE",  new Color(255, 153, 0));
        btnHapus  = buatTombol("HAPUS",   new Color(204, 0, 0));
        btnBersih = buatTombol("BERSIH",  new Color(102, 102, 102));

        panelTombol.add(btnTambah);
        panelTombol.add(btnUpdate);
        panelTombol.add(btnHapus);
        panelTombol.add(btnBersih);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelForm.add(panelTombol, gbc);

        // ==========================================
        // Tombol Cetak Laporan
        // ==========================================
        btnCetakLaporan = new JButton("CETAK LAPORAN PDF");
        btnCetakLaporan.setFont(new Font("Arial", Font.BOLD, 13));
        btnCetakLaporan.setBackground(new Color(0, 51, 102));
        btnCetakLaporan.setForeground(Color.WHITE);
        btnCetakLaporan.setPreferredSize(new Dimension(240, 45));
        btnCetakLaporan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCetakLaporan.setFocusPainted(false);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 5, 10);
        panelForm.add(btnCetakLaporan, gbc);

        add(panelForm, BorderLayout.WEST);

        // ==========================================
        // PANEL KANAN - Tabel Data
        // ==========================================
        JPanel panelTabel = new JPanel(new BorderLayout(5, 5));
        panelTabel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 51, 102), 2),
            "Data Mahasiswa",
            0, 0,
            new Font("Arial", Font.BOLD, 12),
            new Color(0, 51, 102)
        ));
        panelTabel.setBackground(Color.WHITE);

        // Panel Pencarian
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.setBackground(Color.WHITE);
        panelCari.add(new JLabel("Cari Nama/NIM :"));
        txtCari  = new JTextField(20);
        btnCari  = buatTombol("CARI", new Color(0, 102, 204));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelTabel.add(panelCari, BorderLayout.NORTH);

        // Tabel
        String[] kolom = {"ID", "NIM", "Nama", "Jurusan", "IPK"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // Tabel tidak bisa diedit langsung
            }
        };

        tabelMahasiswa = new JTable(tableModel);
        tabelMahasiswa.setFont(new Font("Arial", Font.PLAIN, 11));
        tabelMahasiswa.setRowHeight(25);
        tabelMahasiswa.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        tabelMahasiswa.getTableHeader().setBackground(new Color(0, 51, 102));
        tabelMahasiswa.getTableHeader().setForeground(Color.WHITE);
        tabelMahasiswa.setSelectionBackground(new Color(173, 216, 230));

        // Lebar kolom
        tabelMahasiswa.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabelMahasiswa.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabelMahasiswa.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabelMahasiswa.getColumnModel().getColumn(3).setPreferredWidth(180);
        tabelMahasiswa.getColumnModel().getColumn(4).setPreferredWidth(60);

        JScrollPane scrollPane = new JScrollPane(tabelMahasiswa);
        panelTabel.add(scrollPane, BorderLayout.CENTER);

        add(panelTabel, BorderLayout.CENTER);

        // ==========================================
        // PANEL BAWAH - Status Bar
        // ==========================================
        JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelStatus.setBackground(new Color(240, 240, 240));
        panelStatus.setBorder(BorderFactory.createEtchedBorder());

        lblStatus = new JLabel("Siap | Total Data: 0");
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
        panelStatus.add(lblStatus);

        add(panelStatus, BorderLayout.SOUTH);

        // ==========================================
        // Event Listener
        // ==========================================
        btnTambah.addActionListener(e -> tambahData());
        btnUpdate.addActionListener(e -> updateData());
        btnHapus.addActionListener(e  -> hapusData());
        btnBersih.addActionListener(e -> bersihForm());
        btnCari.addActionListener(e   -> cariData());
        btnCetakLaporan.addActionListener(e -> cetakLaporan());

        // Klik baris tabel → isi form
        tabelMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pilihBaris();
            }
        });

               // Enter di field cari
        txtCari.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    cariData();
                }
            }
        });
    }

    // =============================================
    // Helper: Buat tombol dengan warna custom
    // =============================================
    private JButton buatTombol(String teks, Color warna) {
        JButton btn = new JButton(teks);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setBackground(warna);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(100, 35));
        return btn;
    }

    // =============================================
    // Load semua data ke tabel
    // =============================================
    private void loadData() {
        tableModel.setRowCount(0); // Kosongkan tabel
        List<Mahasiswa> list = dao.getAll();
        for (Mahasiswa mhs : list) {
            tableModel.addRow(new Object[]{
                mhs.getId(),
                mhs.getNim(),
                mhs.getNama(),
                mhs.getJurusan(),
                String.format("%.2f", mhs.getIpk())
            });
        }
        lblStatus.setText("Siap | Total Data: " + list.size());
    }

    // =============================================
    // Tambah data mahasiswa
    // =============================================
    private void tambahData() {
        // Validasi input
        if (!validasiInput()) return;

        try {
            Mahasiswa mhs = new Mahasiswa(
                0,
                txtNim.getText().trim(),
                txtNama.getText().trim(),
                txtJurusan.getText().trim(),
                Double.parseDouble(txtIpk.getText().trim())
            );

            if (dao.tambah(mhs)) {
                JOptionPane.showMessageDialog(this,
                    "Data berhasil ditambahkan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE
                );
                bersihForm();
                loadData();
                lblStatus.setText("Data berhasil ditambahkan!");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Gagal menambahkan data!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Format IPK tidak valid! Gunakan angka desimal (contoh: 3.75)",
                "Error Input",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =============================================
    // Update data mahasiswa
    // =============================================
    private void updateData() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this,
                "Pilih data yang akan diupdate terlebih dahulu!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!validasiInput()) return;

        try {
            int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin ingin mengupdate data ini?",
                "Konfirmasi Update",
                JOptionPane.YES_NO_OPTION
            );

            if (konfirmasi == JOptionPane.YES_OPTION) {
                Mahasiswa mhs = new Mahasiswa(
                    selectedId,
                    txtNim.getText().trim(),
                    txtNama.getText().trim(),
                    txtJurusan.getText().trim(),
                    Double.parseDouble(txtIpk.getText().trim())
                );

                if (dao.update(mhs)) {
                    JOptionPane.showMessageDialog(this,
                        "Data berhasil diupdate!",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    bersihForm();
                    loadData();
                    lblStatus.setText("Data berhasil diupdate!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Format IPK tidak valid!",
                "Error Input",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =============================================
    // Hapus data mahasiswa
    // =============================================
    private void hapusData() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this,
                "Pilih data yang akan dihapus terlebih dahulu!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this,
            "Yakin ingin menghapus data ini?\nData yang dihapus tidak dapat dikembalikan!",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            if (dao.delete(selectedId)) {
                JOptionPane.showMessageDialog(this,
                    "Data berhasil dihapus!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE
                );
                bersihForm();
                loadData();
                lblStatus.setText("Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Gagal menghapus data!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // =============================================
    // Cari data berdasarkan nama atau NIM
    // =============================================
    private void cariData() {
        String keyword = txtCari.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadData();
            return;
        }

        tableModel.setRowCount(0);
        List<Mahasiswa> list = dao.getAll();
        int count = 0;

        for (Mahasiswa mhs : list) {
            if (mhs.getNama().toLowerCase().contains(keyword) ||
                mhs.getNim().toLowerCase().contains(keyword)  ||
                mhs.getJurusan().toLowerCase().contains(keyword)) {

                tableModel.addRow(new Object[]{
                    mhs.getId(),
                    mhs.getNim(),
                    mhs.getNama(),
                    mhs.getJurusan(),
                    String.format("%.2f", mhs.getIpk())
                });
                count++;
            }
        }
        lblStatus.setText("Hasil pencarian '" + keyword + "': " + count + " data ditemukan");
    }

    // =============================================
    // Pilih baris tabel → isi form input
    // =============================================
    private void pilihBaris() {
        int baris = tabelMahasiswa.getSelectedRow();
        if (baris >= 0) {
            selectedId = Integer.parseInt(
                tableModel.getValueAt(baris, 0).toString()
            );
            txtNim.setText(tableModel.getValueAt(baris, 1).toString());
            txtNama.setText(tableModel.getValueAt(baris, 2).toString());
            txtJurusan.setText(tableModel.getValueAt(baris, 3).toString());
            txtIpk.setText(tableModel.getValueAt(baris, 4).toString());

            lblStatus.setText("Data dipilih: " + txtNama.getText());
        }
    }

    // =============================================
    // Bersihkan form input
    // =============================================
    private void bersihForm() {
        txtNim.setText("");
        txtNama.setText("");
        txtJurusan.setText("");
        txtIpk.setText("");
        txtCari.setText("");
        selectedId = -1;
        tabelMahasiswa.clearSelection();
        lblStatus.setText("Form dibersihkan | Total Data: " + tableModel.getRowCount());
    }

    // =============================================
    // Validasi input form
    // =============================================
    private boolean validasiInput() {
        if (txtNim.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "NIM tidak boleh kosong!",
                "Validasi",
                JOptionPane.WARNING_MESSAGE
            );
            txtNim.requestFocus();
            return false;
        }
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nama tidak boleh kosong!",
                "Validasi",
                JOptionPane.WARNING_MESSAGE
            );
            txtNama.requestFocus();
            return false;
        }
        if (txtJurusan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Jurusan tidak boleh kosong!",
                "Validasi",
                JOptionPane.WARNING_MESSAGE
            );
            txtJurusan.requestFocus();
            return false;
        }
        if (txtIpk.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "IPK tidak boleh kosong!",
                "Validasi",
                JOptionPane.WARNING_MESSAGE
            );
            txtIpk.requestFocus();
            return false;
        }

        // Validasi range IPK
        try {
            double ipk = Double.parseDouble(txtIpk.getText().trim());
            if (ipk < 0.0 || ipk > 4.0) {
                JOptionPane.showMessageDialog(this,
                    "IPK harus antara 0.00 - 4.00!",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
                );
                txtIpk.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Format IPK tidak valid! Contoh: 3.75",
                "Validasi",
                JOptionPane.WARNING_MESSAGE
            );
            txtIpk.requestFocus();
            return false;
        }

        return true;
    }

    // =============================================
    // Cetak Laporan PDF menggunakan JasperReports
    // Sesuai PPT: Tombol Cetak → JasperViewer
    // =============================================
    private void cetakLaporan() {
        lblStatus.setText("Membuat laporan PDF...");

        // Jalankan di thread terpisah agar GUI tidak freeze
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                laporan.cetakLaporan();
                return null;
            }

            @Override
            protected void done() {
                lblStatus.setText("Laporan berhasil dibuat!");
                JOptionPane.showMessageDialog(
                    FormMahasiswa.this,
                    "Laporan PDF berhasil dibuat!\nCek folder 'laporan/'",
                    "Laporan Selesai",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        };
        worker.execute();
    }
}