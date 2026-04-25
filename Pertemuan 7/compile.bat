@echo off
echo ============================================
echo  COMPILE PROJECT PERTEMUAN 7
echo ============================================

cd /d D:\Hamzah\java-semseter6\Pertemuan 7

if not exist bin mkdir bin
if not exist laporan mkdir laporan
if not exist database mkdir database

echo [INFO] Compile semua file Java...

javac -cp "lib/sqlite-jdbc-3.51.3.0.jar;lib/jasperreports-6.20.6.jar;lib/itext-2.1.7.jar;lib/commons-beanutils-1.9.4.jar;lib/commons-collections-3.2.2.jar;lib/commons-digester-2.1.jar;lib/commons-logging-1.2.jar;lib/slf4j-api-1.7.36.jar;lib/slf4j-simple-1.7.36.jar" -d bin src/*.java

if %errorlevel% == 0 (
    echo [OK] Compile Berhasil!
    echo [INFO] Menjalankan Aplikasi...
    java -cp "bin;lib/sqlite-jdbc-3.51.3.0.jar;lib/jasperreports-6.20.6.jar;lib/itext-2.1.7.jar;lib/commons-beanutils-1.9.4.jar;lib/commons-collections-3.2.2.jar;lib/commons-digester-2.1.jar;lib/commons-logging-1.2.jar;lib/slf4j-api-1.7.36.jar;lib/slf4j-simple-1.7.36.jar" Main
) else (
    echo [ERROR] Compile Gagal! Cek error di atas.
)

pause