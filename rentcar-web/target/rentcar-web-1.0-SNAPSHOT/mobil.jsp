<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rentcar.model.Mobil" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Mobil - RentCar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .car-card {
            transition: all 0.3s ease;
            border: none;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        .car-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0,0,0,0.15);
        }
        .car-image {
            height: 200px;
            object-fit: cover;
        }
        .status-available, .status-tersedia {
            background-color: #28a745 !important;
            color: white;
        }
        .status-rented, .status-disewa {
            background-color: #dc3545 !important;
            color: white;
        }
        .price {
            font-size: 1.3rem;
            font-weight: bold;
            color: #007bff;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand fw-bold" href="dashboard.jsp">🚗 RentCar</a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="dashboard.jsp">Dashboard</a>
                <a class="nav-link active" href="mobil">Mobil</a>
                <a class="nav-link" href="customer">Customer</a>
                <a class="nav-link" href="transaksi">Transaksi</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold">📋 Daftar Mobil Tersedia</h2>
            <a href="#" class="btn btn-success btn-lg">
                <i class="fas fa-plus"></i> Tambah Mobil Baru
            </a>
        </div>

        <div class="row">
            <%
                List<Mobil> listMobil = (List<Mobil>) request.getAttribute("listMobil");
                if (listMobil != null && !listMobil.isEmpty()) {
                    for (Mobil mobil : listMobil) {
                        String statusVal = mobil.getStatus() != null ? mobil.getStatus().toLowerCase() : "";
                        // Mengamankan nilai hash agar selalu positif untuk parameter lock image
                        int imageSeed = Math.abs(mobil.getPlatNomor() != null ? mobil.getPlatNomor().hashCode() : 0) % 1000;
            %>
                <div class="col-md-4 mb-4">
                    <div class="card car-card h-100">
                        <img src="https://loremflickr.com/300/200/car,automobile/all?lock=<%= imageSeed %>" 
                             class="card-img-top car-image" 
                             alt="<%= mobil.getMerk() %> <%= mobil.getModel() %>">
                        
                        <div class="card-body">
                            <h5 class="card-title fw-bold"><%= mobil.getMerk() %> <%= mobil.getModel() %></h5>
                            <p class="text-muted"><i class="fas fa-id-card"></i> <%= mobil.getPlatNomor() %></p>
                            
                            <div class="row text-center mb-3">
                                <div class="col-6">
                                    <small class="text-muted">Tahun</small><br>
                                    <strong><%= mobil.getTahun() %></strong>
                                </div>
                                <div class="col-6">
                                    <small class="text-muted">Harga Sewa</small><br>
                                    <span class="price">Rp <%= String.format("%,.0f", mobil.getHargaSewaPerHari()) %></span>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center">
                                <span class="badge fs-6 px-3 py-2 status-<%= statusVal %>">
                                    <%= statusVal.equals("available") || statusVal.equals("tersedia") ? "✅ Tersedia" : "❌ Disewa" %>
                                </span>
                                <a href="#" class="btn btn-outline-primary btn-sm">Detail</a>
                            </div>
                        </div>
                    </div>
                </div>
            <% 
                    }
                } else { 
            %>
                <div class="col-12">
                    <div class="alert alert-info text-center">
                        Belum ada data mobil. Silakan tambahkan mobil baru.
                    </div>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>