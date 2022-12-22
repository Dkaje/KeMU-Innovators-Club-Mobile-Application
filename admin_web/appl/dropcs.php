<?php
include('../database/connection.php');
    $cs=$_POST['cs'];
    if (mysqli_query($con,"DELETE from course where cs='$cs'")) {
            $response["success"] = 1;
            $response["message"] = "course delete was successful";
        } else {
            $response["success"] = 0;
            $response["message"] = "  Failed to delete course";
        }
    echo json_encode($response);
    mysqli_close($con);