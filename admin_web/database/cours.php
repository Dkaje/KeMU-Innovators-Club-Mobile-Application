<?php
include('connection.php');
if (!(mysqli_query($con,"CREATE TABLE course(
    cs int AUTO_INCREMENT PRIMARY KEY,
    ses int,
    term varchar(50),
    code varchar(50),
    title varchar(250),
    credits float,
    department varchar(50),
    quote varchar(250),
    closure float default 0,
    entry_date varchar(50)
  )"))) {
    die("Connection failed: " . $con->connect_error);
} else {
    echo "Created successfully";
}