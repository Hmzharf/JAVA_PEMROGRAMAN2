<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rentcar.model.Transaksi" %>
<%@ page import="com.rentcar.model.Mobil" %>
<%@ page import="com.rentcar.model.Customer" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaksi - RentCar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp">🚗 RentCar</a>
            <a class="btn btn-outline-light" href="dashboard.jsp">Dashboard</a>
        </div>
    </nav>

    <div class="container mt-4">
        <h3>Transaksi Sewa Mobil</h3>
        <a href="#" class="btn btn-success mb-3">+ Buat Transaksi Baru</a>
        
        <table class="table table-hover table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Mobil</th>
                    <th>Customer</th>
                    <th>Tanggal Sewa</th>
                    <th>Tanggal Kembali</th>
                    <th>Total Harga</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Transaksi> listTransaksi = (List<Transaksi>) request.getAttribute("listTransaksi");
                    if (listTransaksi != null) {
                        for (Transaksi t : listTransaksi) {
                %>
                    <tr>
                        <td><%= t.getId() %></td>
                        <td><%= t.getMobilId() %></td>
                        <td><%= t.getCustomerId() %></td>
                        <td><%= t.getTanggalSewa() %></td>
                        <td><%= t.getTanggalKembali() != null ? t.getTanggalKembali() : "-" %></td>
                        <td>Rp <%= t.getTotalHarga() %></td>
                        <td>
                            <span class="badge bg-<%= t.getStatus().equals("active") ? "success" : "warning" %>">
                                <%= t.getStatus() %>
                            </span>
                        </td>
                    </tr>
                <% 
                        }
                    } 
                %>
            </tbody>
        </table>
    </div>
</body>
</html>