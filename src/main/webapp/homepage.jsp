<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Jukebox Draft</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <style>
        h2{
            text-align: center;
        }
        body {
            font-family: Arial, sans-serif;
        }
        .card {
            width: 250px;
            background-color: #fff;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            margin: 10px;
            padding: 10px;
        }
        .card-header {
            text-align: center;
            background-color: #3181A7;
            color: white;
            padding: 10px;
            border-radius: 5px 5px 0 0;
        }
        .card-body { padding: 10px; }
        .card-footer {
            background-color: #3181A7;
            color: white;
            padding: 2px;
            border-radius: 0 0 5px 5px;
        }
        #detailsbtn {
            background-color: #3181A7;
            width: 100%;
            color: white;
            border: 0px;
        }
        .headerChild{
                display: inline-block;
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

        .toStockBtn:hover {
            background-color: #2a617b;
        }

        .addProduct{
            width: 30px;
        }

        .footBtns{
            display: inline-block;
        }

        header{
            padding-left: 20px;
            background-color: #377fa1;
            color: white;
        }
        #toStockItem{
            padding-left: 20px;
        }
        footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: white;
            border-top: 1px solid #ccc;
            position: fixed;
            bottom: 0;
            left: 0;
            right: 0;
        }

        .footBtns {
            margin: 0 5px;
        }

        .left-buttons {
            display: flex;
            gap: 5px;
        }

        .right-buttons {
            display: flex;
            gap: 5px;
        }

    </style>
</head>
<body>

    <!-- Testing
    <c:forEach var="i" begin="1" end="10" step="1">
    <c:out value="${i}"/>
    </c:forEach>
    -->

        <script>
            function openRightMenu() {
              document.getElementById("rightMenu").style.display = "block";
            }

            function closeRightMenu() {
              document.getElementById("rightMenu").style.display = "none";
            }

        </script>



        <div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display:none;right:0;width:400px;" id="rightMenu">
          <button onclick="closeRightMenu()" class="w3-bar-item w3-button w3-large">OK 👌</button>

          <c:forEach var="task" items="${stockList}">
              <p id="toStockItem">☑️ ${task[0]} went out of stock on ${task[1]}</p>
          </c:forEach>

          <form action="ResolveToStock" method="post">
            <button type="submit" class="w3-bar-item w3-button w3-large">Complete Task ✅</button>
          </form>
        </div>

        <button class="w3-button w3-xlarge w3-right" id="toStockBtn" onclick="openRightMenu()"><h3>❕<h3></button>


        <header>
              <img class="child headerChild"src="resources/images/stucoLogo.png" alt="Logo" style="width:40px; padding-bottom: 15px;" class="rounded-pill">
              <h1 class="child headerChild">🩵 Jukebox 💛</h1>
        </header>


    <c:set var="currentProductType" value="${products.get(0)[0]}" />

    <h2>${currentProductType}</h2><hr>
    <!--<h2>Hello</h2><hr>-->

    <div class="table-responsive">
        <table class="table table-borderless">
            <thead></thead>
            <tbody>
                <tr>
                    <c:forEach var="line" items="${products}">
                        <c:if test="${line[0] != currentProductType}">
                            </tr></tbody></table></div>
                            <c:set var="currentProductType" value="${line[0]}" />
                            <h2>${currentProductType}</h2><hr>
                            <div class="table-responsive" style="overflow-x: auto;">
                                <table class="table table-borderless">
                                    <thead></thead>
                                    <tbody>
                                        <tr>
                        </c:if>
                        <td>
                            <div class="card">
                                <div class="card-header">${line[2]}</div>
                                <div class="card-body">
                                    <p><strong>Price:</strong> $${line[3]}</p>
                                    <p><strong>Flavor:</strong> ${line[4]}</p>
                                    <p><strong>Remaining Stock:</strong> ${line[5]}</p>
                                </div>
                                <div class="card-footer">
                                    <form action="ProductDetails" method="post">
                                        <input type="hidden" name="productCode" value="${line[1]}">
                                        <button id="detailsbtn" type="submit">View Details</button>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
    </div>
    <br><br><br><br>

    <footer>
        <div class="left-buttons">
            <button class="child footBtns" id="addProduct" onclick="openModal()">🙌 Add Product</button>

            <form class="child footBtns" action="ToViewDetailedStats" method="post">
                <button id="toViewDetailedStats" type="submit">👀 View Detailed Stats</button>
            </form>

            <form class="child footBtns" action="BarcodeStockMode" method="post">
                <button id="toBarcodeScan" type="submit">🛒 Stocking Mode</button>
            </form>

            <form class="child footBtns" action="BarcodeSellMode" method="post">
                <button id="toBarcodeSell" type="submit">👋 Selling Mode</button>
            </form>

            <form class="child footBtns" action="findRevenueByDate.jsp" method="post">
                <button id="checkRevenue" type="submit">🗓️ Check Revenue</button>
            </form>
        </div>

        <div class="right-buttons">
            <form class="child footBtns" action="LogOut" method="post">
                <button id="logout" type="submit">💨 Log Out</button>
            </form>

            <form class="child footBtns" action="DemoShowInstructions" method="post">
                <button id="instructions" type="submit" style="font-weight: bold;">HOW DO I USE THIS SYSTEM?</button>
            </form>
        </div>
    </footer>

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
        }
        .modal-content {
            margin: 15% auto;
            padding: 10px;
            border: 1px solid #888;
            width: 50%;
            background-color: #fff;
            padding: 10px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
        }
        form {
            display: grid;
            grid-template-columns: 2fr;
            grid-gap: 3px;
        }
        form input,
        form select,
        form textarea {
            width: 100%;
            font-size: 16px;
        }
        form input[type="submit"] {
            width: auto;
        }
        </style>

        <script>
            function openModal() {
                var modal = document.getElementById('myModal');
                modal.style.display = 'block';
            }
            function closeModal() {
                var modal = document.getElementById('myModal');
                modal.style.display = 'none';
            }
        </script>

        <div id="myModal" class="modal">
            <div class="modal-content">
                <div class="header">
                    <span onclick="closeModal()" style="float: right; cursor: pointer;">&times;</span>
                    <h2>🙌 Add Product 🙌</h2>
                </div>
                <form action="WriteToProductsList" method="post" enctype="multipart/form-data">
                    Product Code (found under barcode): <input type="text" name="pCode" required><br>
                    Product Name: <input type="text" name="pName" required><br>
                    Product Price: <input type="number" name="pPrice" required min="0.01" max="1000000000" step="0.01"><br>
                    Product Flavor : <input type="text" name="pFlavor"><br>
                    Product Stock Quantity: <input type="number" name="pStockQuant" required min="0" max="1000000000" step="1"><br>
                    Additional Comments: <textarea name="pComments"></textarea><br>
                    Product Picture: <input type="file" name="picAddress" accept="image/*"><br>
                    Product Type: <select id="productType" name="productType" required>
                        <option value="Cup Noodles">Cup Noodles</option>
                        <option value="Drinks">Drinks</option>
                        <option value="Ice Cream">Ice Cream</option>
                        <option value="Snacks">Snacks</option>
                    </select><br>
                    <input type="submit" value="Add Product">
                </form>
            </div>
        </div>

</body>
</html>