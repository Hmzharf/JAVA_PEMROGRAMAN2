<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rentcar.model.Customer" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Customer - RentCar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <style>
        .customer-card {
            transition: all 0.3s ease;
            border-radius: 15px;
        }
        .customer-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand fw-bold" href="dashboard.jsp">🚗 RentCar</a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="dashboard.jsp">Dashboard</a>
                <a class="nav-link" href="mobil">Mobil</a>
                <a class="nav-link active" href="customer">Customer</a>
                <a class="nav-link" href="transaksi">Transaksi</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold">👥 Daftar Customer</h2>
            <a href="#" class="btn btn-success btn-lg">
                <i class="fas fa-plus"></i> Tambah Customer
            </a>
        </div>

        <div class="row">
            <%
                List<Customer> listCustomer = (List<Customer>) request.getAttribute("listCustomer");
                if (listCustomer != null && !listCustomer.isEmpty()) {
                    for (Customer c : listCustomer) {
            %>
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card customer-card h-100">
                        <div class="card-body">
                            <div class="d-flex align-items-center mb-3">
                                <i class="fas fa-user-circle fa-3x text-primary me-3"></i>
                                <div>
                                    <h5 class="card-title mb-0"><%= c.getNama() %></h5>
                                    <small class="text-muted">ID: <%= c.getId() %></small>
                                </div>
                            </div>
                            <p><i class="fas fa-id-card"></i> <%= c.getKtp() %></p>
                            <p><i class="fas fa-phone"></i> <%= c.getNoTelp() %></p>
                            <p><i class="fas fa-envelope"></i> <%= c.getEmail() %></p>
                            <p class="text-muted"><i class="fas fa-map-marker-alt"></i> <%= c.getAlamat() %></p>
                        </div>
                    </div>
                </div>
            <% 
                    }
                } 
            %>
        </div>
    </div>
</body>
</html>