<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>${productInfo[2]} Draft</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        button{
            padding: 15px;
            background-color: #377fa1;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #2a617b;
        }

        navbar{
            background-color: #3181A7;
        }

    </style>
</head>

<body>

    <style>
            .navbar-brand:hover {
                opacity: 0.8; /* Slightly fade the element on hover */
                cursor: pointer; /* Change the cursor to a pointer on hover */
            }
    </style>

    <nav class="navbar navbar-expand-sm navbar-primary">
        <div class="container-fluid">
            <a class="navbar-brand navbar-dark" href="ToHomepage"> <!-- Link to the servlet -->
                <img src="resources/images/stucoLogo.png" alt="Logo" style="width:40px;" class="rounded-pill">
                STUCO
            </a>
        </div>
    </nav>

    <hr>

    <div class="container">
        <div class="row">
            <div class="col-sm-10">
                <h1>${productInfo[2]}</h1>
            </div>
            <div class="col-sm">
                <button type="button" class="btn btn-lg" onclick='openEditModal()'>Edit</button>
            </div>
        </div>
    </div>
    <hr>

    <!-- Product info -->
    <div class="container fluid">
        <div class="row">
            <div class="col-sm-4"><img class="card-img-top" src="${productInfo[7]}" alt="Card image"></div>
            <div class="col-sm-8">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">🏷️ Code: ${productInfo[1]}</li>
                    <li class="list-group-item">🪪 Name: ${productInfo[2]}</li>
                    <li class="list-group-item">💰 Price: ${productInfo[3]}</li>
                    <li class="list-group-item">😋 Flavor (if applicable): ${productInfo[4]}</li>
                    <li class="list-group-item">🛒 Remaining: ${productInfo[5]}</li>
                    <li class="list-group-item">💬 Comments: ${productInfo[6]}</li>
                    <br>
                    <br>
                    <div class="container fluid">
                        <h3>📈 On average ${averageSales} sale(s) per day</h3>
                        <h3>📅 Estimated restock needed on ${stockDay}</h3>
                    </div>
                </ul>
            </div>
        </div>
    </div>
    <hr>

    <!-- Product sales stats + graph -->
    <div class="container fluid">
        <h3 id='statisticsHeading'>📊 Product Statistics</h3>
        <div class="dropdown">
            <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown">Statistics by:</button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#" onclick='weeklyStats()'>Past week</a></li>
                <li><a class="dropdown-item" href="#" onclick='monthlyStats()'>Past month</a></li>
                <li><a class="dropdown-item" href="#" onclick='quintStats()'>Past quintile</a></li>
            </ul>
        </div>
    </div>

    <div class="container fluid">
        <canvas id="weeklyChart" width="250" height="100"></canvas>
    </div>

    <script>
        var ctx = document.getElementById('weeklyChart').getContext('2d');
        var weeklyChart = null; // Global variable to store the chart instance

        function weeklyStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Weekly Statistics';

            if (weeklyChart) {
                weeklyChart.destroy(); // Destroy existing chart if it exists
            }

            //var labels = [1, 2, 3, 4, 5, 6, 7];

            var labels = [
                <c:forEach items="${weekDates}" var="stat" varStatus="loop">
                    '${stat}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ].reverse();

            var values = [
                <c:forEach items="${weekStats}" var="stat" varStatus="loop">
                    ${stat}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ].reverse(); // Populate values array with weeklyStats data

            weeklyChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Number of ${productInfo[2]} sold',
                        data: values,
                        backgroundColor: Array(7).fill('rgba(54, 162, 235, 0.2)'),
                        borderColor: Array(7).fill('rgba(54, 162, 235, 1)'),
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            ticks: {
                                stepSize: 1,
                                max: 20,
                                suggestedMax: 20
                            }
                        }
                    }
                }
            });
        }

        function monthlyStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Monthly Statistics';

            if (weeklyChart) {
                weeklyChart.destroy(); // Destroy existing chart if it exists
            }

            var labels = [
                <c:forEach items="${monthDates}" var="stat" varStatus="loop">
                    '${stat}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ].reverse();

            var values = [
                <c:forEach items="${monthStats}" var="stat" varStatus="loop">
                    ${stat}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ].reverse(); // Populate values array with monthlyStats data

            weeklyChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Number of ${productInfo[2]} sold',
                        data: values,
                        backgroundColor: Array(30).fill('rgba(54, 162, 235, 0.2)'),
                        borderColor: Array(30).fill('rgba(54, 162, 235, 1)'),
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            ticks: {
                                stepSize: 1,
                                max: 20,
                                suggestedMax: 20
                            }
                        }
                    }
                }
            });
        }

        function quintStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Quintile Statistics';

            if (weeklyChart) {
                weeklyChart.destroy(); // Destroy existing chart if it exists
            }

            var labels = [
                <c:forEach items="${quintDates}" var="stat" varStatus="loop">
                    '${stat}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ].reverse();

            var values = [
                <c:forEach items="${quintStats}" var="stat" varStatus="loop">
                    ${stat}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ].reverse(); // Populate values array with quintStats data

            weeklyChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Number of ${productInfo[2]} sold',
                        data: values,
                        backgroundColor: Array(90).fill('rgba(54, 162, 235, 0.2)'),
                        borderColor: Array(90).fill('rgba(54, 162, 235, 1)'),
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            ticks: {
                                stepSize: 1,
                                max: 20,
                                suggestedMax: 20
                            }
                        }
                    }
                }
            });
        }

        function removeGraph() {
            if (weeklyChart) {
                weeklyChart.destroy(); // Destroy the chart if it exists
                weeklyChart = null; // Reset the global variable
            }
        }
    </script>

    <hr>

    <!-- Bottom stock and delete products buttons -->
    <div class="container fluid">
        <nav class="navbar navbar-expand-sm">
            <button type="button" class="btn" onclick="deleteProductModal()">Delete Product</button>
        </nav>
    </div>
    <hr>


    <script>
    // Function to open the Edit Product modal
    function openEditModal() {
        var modal = document.getElementById('editProduct');
        modal.style.display = "block";
    }

    // Function to open the Delete Product modal
    function deleteProductModal() {
        var modal = document.getElementById('deleteModal');
        modal.style.display = "block";
    }

    // Function to close any modal
    function closeModal() {
        var modals = document.querySelectorAll('.modal');
        modals.forEach(function(modal) {
            modal.style.display = "none";
        });
    }

    // Close the modal when clicking outside of it
    window.onclick = function(event) {
        var modals = document.querySelectorAll('.modal');
        modals.forEach(function(modal) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        });
    }
    </script>

    <!-- Modal for editing product -->
    <div id='editProduct' class='modal'>
        <div class='modal-content'>
            <span onclick='closeModal()' style='float: right; cursor: pointer;'>&times;</span>
            <h2>Edit Product</h2>
            <form action='EditProduct' method='post' enctype="multipart/form-data">
                Product Name: <input type='text' name='pName' value="${productInfo[2]}" required><br>
                Product Price: <input type='number' name='pPrice' value="${productInfo[3]}" min="0.01" max="1000000000" step="0.01" required><br>
                Product Flavor : <input type='text' name='pFlavor' value="${productInfo[4]}"><br>
                Product Stock Quantity: <input type='number' name='pStockQuant' value="${productInfo[5]}" min="0" max="1000000000" required><br>
                Additional Comments: <input type='text' name='pComments' value="${productInfo[6]}"></textarea><br>
                <input type="file" name="picAddress" accept="image/*">
                <input type="hidden" name="picAddress" value="${productInfo[7]}">

                <input type="hidden" name="pCode" value="${productInfo[1]}">
                <input type='hidden' name='category' value="${productInfo[0]}">

                <input type='submit' value='Save Changes'>
            </form>
        </div>
    </div>


    <!-- Modal for deleting product -->
    <div id='deleteModal' class='modal'>
        <div class='modal-content'>
            <span onclick='closeModal()' style='float: right; cursor: pointer;'>&times;</span>
            <h2>🚮 Delete Product</h2>
            <form action='DeleteProduct' method='post'>
                <p>Are you sure you want to delete ${productInfo[2]} 🥺?<p>
                <p>By deleting this product you will be deleting all the past records</p>
                <input type='hidden' name='pCode' value="${productInfo[1]}">
                <input type='submit' value='😭 Yes'>
            </form>
        </div>
    </div>

</body>

</html>