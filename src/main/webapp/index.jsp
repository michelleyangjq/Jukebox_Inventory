<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Jukebox Log In Page</title>

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
                font-size: 24px;
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

          	.login-container label {
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

<script>
    function validateForm() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        if (username === "michelleisawesome" && password === "henrysucks") {
            // Allow form submission to the Servlet
            return true;
        } else {
            // Show error message
            document.getElementById("error-message").innerText = "Incorrect username or password - please try again!";
            return false; // Prevent form submission
        }
    }
</script>

<form action="ToHomepage" method="post" onsubmit="return validateForm()">
    <div class="login-container">

        <img src="resources/images/stucoLogo.png" alt="Logo" style="width:40px;" class="rounded-pill">

        <p>🔎 Are you a member of the Jukebox committee?</p>
        <h1>🩵 Sign In to Jukebox 🩵</h1>
        <label>🏷️ Username:</label>
        <input type="text" id="username" name="username" placeholder="Enter username">

        <label>🙈 Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter password">

        <button type="submit">Sign In</button>
        <p id="error-message" style="color: red;"></p>
    </div>
</form>


</body>
</html>