<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Barcode Stock Page</title>

<style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .bc-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 500px;
                text-align: center;
            }
            .bc-container p{
                font-style: italic;
                color: #757575;
            }

            .bc-container h1 {
                color: #377fa1;
                margin-bottom: 20px;
                font-size: 24px;
            }
            .bc-container input {
                width: 90%;
                padding: 15px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .bc-container button {
                width: 90%;
                padding: 15px;
                background-color: #377fa1;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .bc-container button:hover {
                background-color: #2a617b;
            }

          	.bc-container label {
          	  font-size: 16px;
              color: #757575;
              display: block; /* Display labels as block elements */
              text-align: left; /* Align text to the left */
              padding-left: 25px;
              margin-bottom: 5px; /* Add space between label and input field */
        }
        </style>
</head>
<body>
<!--
    <form action="BarcodeScanInput" method="post" id="barcodeForm">
      <label for="barcodeCode">Barcode:</label>
      <input type="text" id="barcodeCode" name="barcodeCode" autofocus><br><br>

      <input type="submit" value="Confirm">
    </form>

    <form action="ToHomepage" method="post">
        <input type="submit" value="Done">
    </form>
-->

<div class="bc-container">
    <form action="BarcodeStockMode" method="post">

            <p>Scan the product barcode to stock</p>

            <h1>🩵 Stocking Mode 🩵</h1>
            <label>🏷️ Barcode:</label>
            <input type="text" id="barcodeCode" name="barcodeCode" autofocus required><br><br>

            <input type="submit" value="👌 Confirm">

    </form>
    <form action="ToHomepage" method="post">
            <input type="submit" value="✔ Finish Stocking">
    </form>
    <p>❗️Ensure that the input box is in focus!</p>
    </div>

</body>
</html>