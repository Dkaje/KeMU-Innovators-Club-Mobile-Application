<?php
include('connection.php');
$result = mysqli_query($con, "CREATE TABLE quick_alrt(
    id int auto_increment PRIMARY KEY,
    user varchar(50),
    alrt varchar(250),
    reg_date varchar(50)
    )");
if (!$result) {
    die("Connection failed: " . $con->connect_error);
} else {
    echo "table created";
}
mysqli_close($con);