import javax.swing.*;
import java.awt.event.*;

public class PenjumlahanGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Penjumlahan Dua Angka");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Label
        JLabel label1 = new JLabel("Angka Pertama");
        label1.setBounds(30, 30, 100, 25);
        frame.add(label1);

        JLabel label2 = new JLabel("Angka Kedua");
        label2.setBounds(30, 70, 100, 25);
        frame.add(label2);

        JLabel label3 = new JLabel("Hasil");
        label3.setBounds(30, 110, 100, 25);
        frame.add(label3);

        // TextField
        JTextField tf1 = new JTextField();
        tf1.setBounds(150, 30, 150, 25);
        frame.add(tf1);

        JTextField tf2 = new JTextField();
        tf2.setBounds(150, 70, 150, 25);
        frame.add(tf2);

        JTextField tf3 = new JTextField();
        tf3.setBounds(150, 110, 150, 25);
        tf3.setEditable(false); // hasil tidak bisa diubah manual
        frame.add(tf3);

        // Tombol
        JButton tambah = new JButton("Tambah");
        tambah.setBounds(30, 150, 80, 25);
        frame.add(tambah);

        JButton hapus = new JButton("Hapus");
        hapus.setBounds(150, 150, 80, 25);
        frame.add(hapus);

        JButton exit = new JButton("Exit");
        exit.setBounds(270, 150, 80, 25);
        frame.add(exit);

        // Event tombol
        tambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int angka1 = Integer.parseInt(tf1.getText());
                int angka2 = Integer.parseInt(tf2.getText());
                int hasil = angka1 + angka2;
                tf3.setText(String.valueOf(hasil));
            }
        });

        hapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}