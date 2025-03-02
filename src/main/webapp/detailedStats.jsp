<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Detailed Statistics</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        /* Flexbox container for the charts */
        .chart-container {
            display: flex;
            justify-content: space-between; /* Space out the charts */
            gap: 10px; /* Add some space between the charts */
            margin-top: 20px;
        }

        .chart1 {
            flex: 2; /* Each chart takes equal space */
            max-width: 40%; /* Limit the width of each chart */
            height: 400px; /* Set a fixed height for the charts */
        }

        .chart2 {
            flex: 3; /* Each chart takes equal space */
            max-width: 60%; /* Limit the width of each chart */
            height: 400px; /* Set a fixed height for the charts */
            padding-right: 30px;
        }

        /* Other styles (unchanged) */
        button {
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
        .choseTimeChild {
            display: inline-block;
        }
        .headerChild {
            display: inline-block;
        }
        #statisticsParent {
            display: flex;
            justify-content: space-between;
        }
        #statisticsChild {
            flex: 1;
            margin-right: 10px;
        }
        body {
            padding: 25px;
            color: #3181A7;
        }
        .title {
            color: #5C6AC4;
        }
        h3 {
            color: #3181A7;
        }
        select {
            background-color: white;
            color: #3181A7;
        }
        body {
            font-family: Arial, sans-serif;
        }
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        header {
            background-color: #377fa1;
            color: white;
            width: 100%;
            padding: 10px; /* Add some padding to the header */
        }
        .headingLabel {
            color: #3181A7;
        }
        h1 {
            color: white;
        }

        button {
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
    </style>
</head>
<body>


    <header>
        <a class="navbar-brand navbar-dark" href="ToHomepage"> <!-- Link to the servlet -->
            <img src="resources/images/stucoLogo.png" alt="Logo" style="width:40px;" class="rounded-pill">
            STUCO
        </a>
    </header>

    <br>

    <div class="container-fluid">
        <h3 id='statisticsHeading'>📊 Detailed Statistics</h3>
        <div class="dropdown">
            <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown">Statistics by:</button>
            <br>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#" onclick='weeklyStats()'>Past week</a></li>
                <li><a class="dropdown-item" href="#" onclick='monthlyStats()'>Past month</a></li>
                <li><a class="dropdown-item" href="#" onclick='quintStats()'>Past quintile</a></li>
            </ul>
        </div>
    </div>

    <!-- Flexbox container for the charts -->
    <div class="chart-container">
        <div class="chart1">
            <canvas id="pieChart"></canvas>
        </div>
        <div class="chart2">
            <canvas id="barChart"></canvas>
        </div>
    </div>

    <script>
        var pieChartCtx = document.getElementById('pieChart').getContext('2d');
        var pc = null; // Global variable to store the pie chart instance

        var barChartCtx = document.getElementById('barChart').getContext('2d');
        var bc = null; // Global variable to store the bar chart instance

        function weeklyStats() {
            weeklyPieStats();
            weeklyBarStats();
        }

        function monthlyStats() {
            monthlyPieStats();
            monthlyBarStats();
        }

        function quintStats() {
            quintPieStats();
            quintBarStats();
        }

        //---week---
        function weeklyPieStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Weekly Statistics';

            if (pc) {
                pc.destroy(); // Destroy existing chart if it exists
            }

            var labels = [
                <c:forEach items="${weekCatResults}" var="entry" varStatus="loop">
                    '${entry.key}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            var values = [
                <c:forEach items="${weekCatResults}" var="entry" varStatus="loop">
                    ${entry.value}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            pc = new Chart(pieChartCtx, {
                type: "doughnut",
                data: {
                    labels: labels,
                    datasets: [{
                        backgroundColor: ["#24A2EB", "#3688AB", "#73B6D3", "#3B8EA5"],
                        data: values
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        title: {
                            display: true,
                            text: "Sales by Category"
                        }
                    }
                }
            });
        }

        function weeklyBarStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Weekly Statistics';

            if (bc) {
                bc.destroy(); // Destroy existing chart if it exists
            }

            var labels = [
                <c:forEach items="${weekResults}" var="entry" varStatus="loop">
                    '${entry.key}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            var values = [
                <c:forEach items="${weekResults}" var="entry" varStatus="loop">
                    ${entry.value}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            bc = new Chart(barChartCtx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Sales by products',
                        data: values,
                        backgroundColor: Array(7).fill('rgba(54, 162, 235, 0.2)'),
                        borderColor: Array(7).fill('rgba(54, 162, 235, 1)'),
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
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

        //---month---
        function monthlyPieStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Monthly Statistics';

            if (pc) {
                pc.destroy(); // Destroy existing chart if it exists
            }

            var labels = [
                <c:forEach items="${monthCatResults}" var="entry" varStatus="loop">
                    '${entry.key}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            var values = [
                <c:forEach items="${monthCatResults}" var="entry" varStatus="loop">
                    ${entry.value}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            pc = new Chart(pieChartCtx, {
                type: "doughnut",
                data: {
                    labels: labels,
                    datasets: [{
                        backgroundColor: ["#24A2EB", "#3688AB", "#73B6D3", "#3B8EA5"],
                        data: values
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        title: {
                            display: true,
                            text: "Sales by Category"
                        }
                    }
                }
            });
        }

        function monthlyBarStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Monthly Statistics';

            if (bc) {
                bc.destroy(); // Destroy existing chart if it exists
            }

            var labels = [
                <c:forEach items="${monthResults}" var="entry" varStatus="loop">
                    '${entry.key}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            var values = [
                <c:forEach items="${monthResults}" var="entry" varStatus="loop">
                    ${entry.value}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            bc = new Chart(barChartCtx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Sales by products',
                        data: values,
                        backgroundColor: Array(7).fill('rgba(54, 162, 235, 0.2)'),
                        borderColor: Array(7).fill('rgba(54, 162, 235, 1)'),
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
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

        //---quintile---
        function quintPieStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Quintile Statistics';

            if (pc) {
                pc.destroy(); // Destroy existing chart if it exists
            }

            var labels = [
                <c:forEach items="${quintCatResults}" var="entry" varStatus="loop">
                    '${entry.key}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            var values = [
                <c:forEach items="${quintCatResults}" var="entry" varStatus="loop">
                    ${entry.value}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            pc = new Chart(pieChartCtx, {
                type: "doughnut",
                data: {
                    labels: labels,
                    datasets: [{
                        backgroundColor: ["#24A2EB", "#3688AB", "#73B6D3", "#3B8EA5"],
                        data: values
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        title: {
                            display: true,
                            text: "Sales by Category"
                        }
                    }
                }
            });
        }

        function quintBarStats() {
            document.getElementById('statisticsHeading').innerHTML = 'Quintile Statistics';

            if (bc) {
                bc.destroy(); // Destroy existing chart if it exists
            }

            var labels = [
                <c:forEach items="${quintResults}" var="entry" varStatus="loop">
                    '${entry.key}'<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            var values = [
                <c:forEach items="${quintResults}" var="entry" varStatus="loop">
                    ${entry.value}<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];

            bc = new Chart(barChartCtx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Sales by products',
                        data: values,
                        backgroundColor: Array(7).fill('rgba(54, 162, 235, 0.2)'),
                        borderColor: Array(7).fill('rgba(54, 162, 235, 1)'),
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
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
            if (pc) {
                pc.destroy(); // Destroy the pie chart if it exists
                pc = null; // Reset the global variable
            }
            if (bc) {
                bc.destroy(); // Destroy the bar chart if it exists
                bc = null; // Reset the global variable
            }
        }
    </script>
</body>
</html>