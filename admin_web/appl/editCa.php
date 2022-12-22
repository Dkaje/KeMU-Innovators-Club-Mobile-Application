<?php
include('../database/connection.php');
    $id = $_POST['id'];
    $fees = $_POST['fees'];
    $description=$_POST['description'];
    if (mysqli_query($con, "UPDATE cardy set fees='$fees',description='$description' where id='$id'")) {
        $response["success"] = 1;
        $response["message"] = " SamrtCard updated successfully";
    } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to update";
        }
    echo json_encode($response);
    mysqli_close($con);