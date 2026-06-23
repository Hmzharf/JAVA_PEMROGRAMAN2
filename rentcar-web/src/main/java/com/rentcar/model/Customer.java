package com.rentcar.model;

public class Customer {
    private int id;
    private String nama;
    private String alamat;
    private String noTelp;
    private String ktp;
    private String email;

    public Customer() {}

    // Getter Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp; }
    public String getKtp() { return ktp; }
    public void setKtp(String ktp) { this.ktp = ktp; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}