package com.rentcar.model;

import java.sql.Date;

public class Transaksi {
    private int id;
    private int mobilId;
    private int customerId;
    private Date tanggalSewa;
    private Date tanggalKembali;
    private int jumlahHari;
    private double totalHarga;
    private String status;
    private String keterangan;

    public Transaksi() {}

    // Getter Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMobilId() { return mobilId; }
    public void setMobilId(int mobilId) { this.mobilId = mobilId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public Date getTanggalSewa() { return tanggalSewa; }
    public void setTanggalSewa(Date tanggalSewa) { this.tanggalSewa = tanggalSewa; }
    public Date getTanggalKembali() { return tanggalKembali; }
    public void setTanggalKembali(Date tanggalKembali) { this.tanggalKembali = tanggalKembali; }
    public int getJumlahHari() { return jumlahHari; }
    public void setJumlahHari(int jumlahHari) { this.jumlahHari = jumlahHari; }
    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
}