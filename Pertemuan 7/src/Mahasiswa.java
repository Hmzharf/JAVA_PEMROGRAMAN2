public class Mahasiswa {

    private int    id;
    private String nim;
    private String nama;
    private String jurusan;
    private double ipk;

    public Mahasiswa() {}

    public Mahasiswa(int id, String nim, String nama, String jurusan, double ipk) {
        this.id      = id;
        this.nim     = nim;
        this.nama    = nama;
        this.jurusan = jurusan;
        this.ipk     = ipk;
    }

    // Getter & Setter
    public int    getId()      { return id; }
    public String getNim()     { return nim; }
    public String getNama()    { return nama; }
    public String getJurusan() { return jurusan; }
    public double getIpk()     { return ipk; }

    public void setId(int id)           { this.id = id; }
    public void setNim(String nim)      { this.nim = nim; }
    public void setNama(String nama)    { this.nama = nama; }
    public void setJurusan(String j)    { this.jurusan = j; }
    public void setIpk(double ipk)      { this.ipk = ipk; }

    @Override
    public String toString() {
        return id + " | " + nim + " | " + nama + " | " + jurusan + " | " + ipk;
    }
}