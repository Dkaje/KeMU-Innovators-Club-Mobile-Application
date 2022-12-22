<?php
session_start();
unset($_SESSION['Babel']);
echo "<script>location.href='login.php' </script>";