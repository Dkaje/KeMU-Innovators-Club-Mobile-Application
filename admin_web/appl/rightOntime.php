<?php
include('../database/connection.php');
    $ann=$_POST['ann'];
    if (mysqli_query($con,"DELETE from announce where ann='$ann'")) {
            $response["success"] = 1;
            $response["message"] = "announcement delete was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to delete announcement";
        }
    echo json_encode($response);
    mysqli_close($con);