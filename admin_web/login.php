<?php
ob_start();
error_reporting(0);
session_start();
require_once('database/connection.php') ;
if (isset($_POST['logged'])) {

    $username = $_POST['adm_username'];
    $password = md5($_POST['adm_password']);
    $result = mysqli_query($con, "SELECT * FROM admin WHERE username='" . $username . "' and password='" . $password . "'");
    if (!$result) {
        die("Connection failed: " . $con->connect_error);
        exit;
    }
    $count = mysqli_num_rows($result);
    if ($count) {
        $_SESSION['Babel'] = $username;
        echo "<script>location.href='index' </script>";
        die;
    } else {
        echo "<script>alert('Your Login credentials are invalid')</script>";
        echo "<script>location.href='login' </script>";
    }
}
ob_end_flush();
if (isset($_POST['reg_button'])) {
    $username = $_POST['adm_username'];
    $password = md5($_POST['adm_password']);
        if (mysqli_query($con, "INSERT into admin(username,password)values('$username','$password')")) {
            echo "<script>alert('Registration was successful')</script>";
        } else {
            echo "<script>alert('Failed to register')</script>";
        }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>KeMUSSiT Admin </title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="vendor/parsley/parsley.css"/>

    <style>
    html,
    body {
      height: 100%;
    }

    body {
      display: flex;
      align-items: center;
      padding-top: 40px;
      padding-bottom: 40px;
      background-color: #950365;
    }

    .form-signin {
      width: 100%;
      max-width: 330px;
      padding: 15px;
      margin: auto;
    }
    .form-signin .checkbox {
      font-weight: 400;
    }
    .form-signin .form-control {
      position: relative;
      box-sizing: border-box;
      height: auto;
      padding: 10px;
      font-size: 16px;
    }
    .form-signin .form-control:focus {
      z-index: 2;
    }
    .form-signin input[type="email"] {
      margin-bottom: -1px;
      border-bottom-right-radius: 0;
      border-bottom-left-radius: 0;
    }
    .form-signin input[type="password"] {
      margin-bottom: 10px;
      border-top-left-radius: 0;
      border-top-right-radius: 0;
    }
    </style>
</head>
<body class="text-center">
    <main class="form-signin">
                              <form method="post" id="login_form">
                                 <h1 class="h3 mb-3 fw-normal">KeMUSSiT Admin</h1>
						  <?php if(mysqli_num_rows(mysqli_query($con,"SELECT * from admin"))>0){
							  echo '
            <span id="error"></span>
            <div class="form-group">
                <input type="text" name="adm_username" id="adm_username" class="form-control" required autofocus data-parsley-type="email" data-parsley-trigger="keyup" placeholder="Enter Username" />
            </div>
            <div class="form-group">
                <input type="password" name="adm_password" id="adm_password" class="form-control" required  data-parsley-trigger="keyup" placeholder="Password" />
            </div>
            <div class="form-group">
                <button type="submit" name="logged" id="login_button" class="btn btn-danger btn-user btn-block">Login</button><br>
                <a href="forgot.php" style="text-decoration:none">Forgot pasword</a>
            </div>
            </form>';
        }else{
            echo '
            <span id="error"></span>
            <div class="form-group">
                <input type="text" name="adm_username" id="adm_username" class="form-control" required autofocus data-parsley-type="email" data-parsley-trigger="keyup" placeholder="Enter Username" />
            </div>
            <div class="form-group">
                <input type="password" name="adm_password" id="adm_password" class="form-control" required  data-parsley-trigger="keyup" placeholder="Password" />
            </div>
            <div class="form-group">
                <button type="submit" name="reg_button" id="login_button" class="btn btn-danger btn-user btn-block">Register</button>
            </div>';
        } ?>
        </form>
    </main>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    <script type="text/javascript" src="vendor/parsley/dist/parsley.min.js"></script>
</body>
</html>
