<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Product Exists Already</title>

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
            .login-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 500px;
                text-align: center;
            }
            .login-container p{
                font-style: italic;
                color: #757575;
            }

            .login-container h1 {
                color: #377fa1;
                margin-bottom: 20px;
                font-size: 20px;
            }
            .login-container input {
                width: 90%;
                padding: 15px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .login-container button {
                width: 90%;
                padding: 15px;
                background-color: #377fa1;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .login-container button:hover {
                background-color: #2a617b;
            }

        </style>

</head>
<body>


<form action="ToHomepage" method="post">
    <div class="login-container">

        <h1>‼️ Product with current barcode already exists ‼️</h1>
        <p>please double check</p>

        <button type="submit">OK</button>
    </div>
</form>


</body>
</html>