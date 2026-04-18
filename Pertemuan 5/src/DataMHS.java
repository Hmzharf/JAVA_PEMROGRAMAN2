public class DataMHS {

    private String nim;
    private String nama;
    private int semester;
    private String kelas;

    public DataMHS() {}

    public DataMHS(String nim, String nama, int semester, String kelas) {
        this.nim      = nim;
        this.nama     = nama;
        this.semester = semester;
        this.kelas    = kelas;
    }

    public String getNim()      { return nim; }
    public String getNama()     { return nama; }
    public int    getSemester() { return semester; }
    public String getKelas()    { return kelas; }

    public void setNim(String nim)         { this.nim      = nim; }
    public void setNama(String nama)       { this.nama     = nama; }
    public void setSemester(int semester)  { this.semester = semester; }
    public void setKelas(String kelas)     { this.kelas    = kelas; }

    @Override
    public String toString() {
        return String.format("| %-15s | %-25s | %-8d | %-5s |",
                nim, nama, semester, kelas);
    }
}