<?php
include('../database/connection.php');
    $category = $_POST['category'];
    $fees = $_POST['fees'];
    $description=$_POST['description'];
    $date=$_POST['date'];
    if (mysqli_num_rows(mysqli_query($con, "SELECT * from cardy where category='$category'")) > 0) {
        $response["success"] = 0;
        $response["message"] = " Failed to overwrite an existing card category";
    } else {
        if (mysqli_query($con,"INSERT INTO cardy(category,fees,description,date)
        VALUES('$category','$fees','$description','$date')")) {
            $response["success"] = 1;
            $response["message"] = " Card registration was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to register card";
        }
    }
    echo json_encode($response);
    mysqli_close($con);