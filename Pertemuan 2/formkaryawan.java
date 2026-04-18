import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormKaryawan {
    private JFrame frame;
    private JTextField ktpTextField;
    private JTextField nameTextField;
    private JComboBox<String> roomComboBox;
    private JPasswordField passwordField;
    private JTable table;
    private DefaultTableModel tableModel;

    public FormKaryawan() {
        // Membuat frame utama dengan ukuran dinamis
        frame = new JFrame("Master Data Karyawan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Menutup aplikasi saat klik close
        frame.setLayout(new BorderLayout());  // Menggunakan BorderLayout untuk tata letak yang fleksibel

        // Panel untuk memasukkan data karyawan
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));  // Layout 5 baris, 2 kolom
        frame.add(panel, BorderLayout.CENTER);

        // Membuat komponen input
        JLabel labelKTP = new JLabel("KTP:");
        ktpTextField = new JTextField(20);

        JLabel labelName = new JLabel("Nama:");
        nameTextField = new JTextField(20);

        JLabel labelRoom = new JLabel("Ruang:");
        String[] rooms = {"Ruang 1", "Ruang 2", "Ruang 3"};
        roomComboBox = new JComboBox<>(rooms);

        JLabel labelPassword = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // Menambahkan komponen ke panel
        panel.add(labelKTP);
        panel.add(ktpTextField);
        panel.add(labelName);
        panel.add(nameTextField);
        panel.add(labelRoom);
        panel.add(roomComboBox);
        panel.add(labelPassword);
        panel.add(passwordField);

        // Button untuk menambahkan data
        JButton submitButton = new JButton("Simpan");
        panel.add(submitButton);

        // Menambahkan aksi pada button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ambil data yang dimasukkan
                String ktp = ktpTextField.getText();
                String name = nameTextField.getText();
                String room = (String) roomComboBox.getSelectedItem();
                String password = new String(passwordField.getPassword());

                // Menambahkan data ke dalam tabel
                tableModel.addRow(new Object[]{ktp, name, room, password});  // Menambahkan baris baru ke tabel
            }
        });

        // Definisikan kolom untuk tabel
        String[] columns = {"KTP", "Nama", "Ruang", "Password"};
        Object[][] data = {{"", "", "", ""}}; // Data kosong awal

        // Membuat DefaultTableModel
        tableModel = new DefaultTableModel(data, columns);

        // Membuat JTable
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);  // Menambahkan scroll bar ke tabel
        frame.add(scrollPane, BorderLayout.SOUTH);  // Menambahkan tabel ke bagian bawah frame

        // Mengatur ukuran JFrame secara dinamis dan dapat di-resize
        frame.setSize(600, 400);  // Ukuran awal
        frame.setResizable(true);  // Agar form bisa di-resize
        frame.setLocationRelativeTo(null);  // Agar form muncul di tengah layar

        // Menampilkan frame
        frame.setVisible(true);
    }

    // Main untuk menjalankan aplikasi
    public static void main(String[] args) {
        // Menjalankan form karyawan
        new FormKaryawan();
    }
}