<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - RentCar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">🚗 RentCar</a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="mobil">Mobil</a>
                <a class="nav-link" href="customer">Customer</a>
                <a class="nav-link" href="transaksi">Transaksi</a>
                <a class="nav-link text-danger" href="logout">Logout</a>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h2>Selamat Datang di Dashboard RentCar</h2>
        <div class="row mt-4">
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Total Mobil</h5>
                    <h2 class="text-primary">12</h2>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Transaksi Aktif</h5>
                    <h2 class="text-success">8</h2>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Pendapatan Hari Ini</h5>
                    <h2 class="text-warning">Rp 2.450.000</h2>
                </div>
            </div>
        </div>
    </div>
</body>
</html>